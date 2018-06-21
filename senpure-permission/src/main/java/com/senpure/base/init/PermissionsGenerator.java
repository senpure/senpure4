package com.senpure.base.init;


import com.senpure.base.HTTPPermission;
import com.senpure.base.PermissionConstant;
import com.senpure.base.annotation.ExtPermission;
import com.senpure.base.annotation.PermissionVerify;
import com.senpure.base.annotation.ResourceVerify;
import com.senpure.base.menu.MenuGenerator;
import com.senpure.base.model.Menu;
import com.senpure.base.model.Permission;
import com.senpure.base.model.PermissionMenu;
import com.senpure.base.model.URIPermission;
import com.senpure.base.service.AuthorizeService;
import com.senpure.base.service.ResourcesVerifyService;
import com.senpure.base.spring.SpringContextRefreshEvent;
import com.senpure.base.util.Assert;
import com.senpure.base.util.Pinyin;
import com.senpure.base.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.*;

@Component
@Order(value = 1)
public class PermissionsGenerator extends SpringContextRefreshEvent {


    @Autowired
    private AuthorizeService authorizeService;


    private static Map<Integer, Menu> menuMap = new HashMap<>();
    @Autowired
    private ResourcesVerifyService resourcesVerifyService;

    public static void checkAndPutMenu(Menu menu) {
        if (menuMap.containsKey(menu.getId())) {
            Assert.error(" 菜单ID重复:" + menu.getId() + "," + menu.getText() + "," + menuMap.get(menu.getId()).getText());
        }
        menuMap.put(menu.getId(), menu);
    }


    Map<String, Integer> classMap = new HashMap<>();
    Map<String, Integer> pMap = new HashMap<>();

    int classInt = 0;

    public void sort(String name, Permission permission) {


        Integer i = classMap.get(name);
        if (i == null) {
            i = ++classInt;
            classMap.put(name, i);
        }
        Integer p = pMap.get(name);
        if (p == null) {
            p = -1;
        }
        p++;
        pMap.put(name, p);
        int sort = 9000;
        if (permission.getName().contains("_read")) {
            sort = 1000;
        } else if (permission.getName().contains("_create")) {

            sort = 2000;
        } else if (permission.getName().contains("_update")) {
            sort = 3000;
        } else if (permission.getName().contains("_delete")) {
            sort = 4000;
        }

        if (permission.getName().contains("_owner")) {
            sort += 100;
        }
        sort += p;
        Integer s = Integer.valueOf(i + "" + sort);
        permission.setSort(s);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("检查权限");
        RequestMappingHandlerMapping rm = event.getApplicationContext().getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> map = rm.getHandlerMethods();
        Iterator<Map.Entry<RequestMappingInfo, HandlerMethod>> iterator = map.entrySet().iterator();
        List<Permission> createPermissions = new ArrayList<>(16);
        Map<String, Permission> permissionMap = new HashMap<>(128);
        Map<String, List<URIPermission>> uriPermissionMap = new TreeMap();
        List<PermissionMenu> permissionMenus = new ArrayList<>(16);
        while (iterator.hasNext()) {
            Map.Entry<RequestMappingInfo, HandlerMethod> entry = iterator.next();
            RequestMappingInfo info = entry.getKey();
            HandlerMethod handlerMethod = entry.getValue();
            PermissionVerify permissionVerify = handlerMethod.getMethod().getAnnotation(PermissionVerify.class);
            String permissionName = null;
            if (permissionVerify != null) {
                List<String> methods;
                if (info.getMethodsCondition().isEmpty()) {
                    methods = HTTPPermission.allMethod();
                } else {
                    methods = new ArrayList<>();
                    info.getMethodsCondition().getMethods().forEach(requestMethod -> methods.add(requestMethod.toString().toUpperCase()));
                }
                List<URIPermission> uriPermissions = new ArrayList<>();
                StringBuilder permissionNameBuilder = new StringBuilder();
                Iterator<String> patternsIterator = info.getPatternsCondition().getPatterns().iterator();
                while (true) {
                    String uri = patternsIterator.next();
                    for (String method : methods) {
                        URIPermission uriPermission = new URIPermission();
                        uriPermission.setUriAndMethod(uri + "[" + method + "]");
                        uriPermission.setPermissionId(1L);
                        uriPermission.setDatabaseUpdate(false);
                        uriPermissions.add(uriPermission);
                        permissionNameBuilder.append(uri);
                    }
                    if (patternsIterator.hasNext()) {
                        permissionNameBuilder.append("||");
                    } else {
                        break;
                    }
                }
                String suffix = mapping2Suffix(info, handlerMethod);
                permissionNameBuilder.append("_").append(suffix);
                Permission permission = new Permission();
                permission.setDatabaseUpdate(false);
                if (StringUtil.isExist(permissionVerify.value())) {
                    permission.setReadableName(permissionVerify.value());
                } else {
                    permission.setReadableName(permissionNameBuilder.toString());
                }
                String name = permissionVerify.name();
                if (StringUtil.isExist(name)) {
                    permission.setName(name);
                } else {
                    permission.setName(permissionNameBuilder.toString());
                }
                permissionName = permission.getName();
                permission.setType(PermissionConstant.PERMISSION_TYPE_NORMAL);
                if (permission.getName().endsWith("_owner")) {
                    permission.setDescription(permissionNameBuilder.toString() + "_owner" + "->" + handlerMethod.getMethod().getName());
                    permission.setType(PermissionConstant.PERMISSION_TYPE_OWNER);
                } else {
                    permission.setDescription(permissionNameBuilder.toString() + "->" + handlerMethod.getMethod().getName());
                }
                sort(handlerMethod.getBeanType().getName(), permission);
                checkPermission(permissionMap, permission, uriPermissionMap, uriPermissions);

                //认证资源权限
                ResourceVerify[] resourceVerifies = handlerMethod.getMethod().getAnnotationsByType(ResourceVerify.class);
                String offset = "";
                String verifyName = "";
                if (resourceVerifies != null && resourceVerifies.length > 0) {
                    for (int i = 0; i < resourceVerifies.length; i++) {
                        if (i > 0) {
                            offset += ",";
                            verifyName += ",";
                        }
                        offset += resourceVerifies[i].offset() + "";
                        verifyName += resourceVerifies[i].value();
                        if (!resourcesVerifyService.hasVerifyService(resourceVerifies[i].value())) {
                            Assert.error(info.getPatternsCondition() + ",资源验证器[" + resourceVerifies[i].value() + "]没有注册");
                        }
                    }
                    if (permission.getName().endsWith("_owner")) {
                        permission.setOffset(offset);
                        permission.setVerifyName(verifyName);
                    } else {
                        Permission resourcePermission = new Permission();
                        resourcePermission.setOffset(offset);
                        resourcePermission.setVerifyName(verifyName);
                        resourcePermission.setName(permission.getName() + "_owner");
                        resourcePermission.setDatabaseUpdate(false);
                        String ownerPermissionName = resourceVerifies[0].permissionName();
                        if (ownerPermissionName.length() > 0) {
                            resourcePermission.setReadableName(ownerPermissionName);
                        } else {
                            StringBuilder sb = new StringBuilder();
                            sb.append(permission.getReadableName())
                                    .insert(2, "我的");
                            resourcePermission.setReadableName(sb.toString());
                        }
                        resourcePermission.setType(PermissionConstant.PERMISSION_TYPE_OWNER);
                        resourcePermission.setDescription(permissionNameBuilder.toString() + "_owner" + "->" + handlerMethod.getMethod().getName());
                        List<URIPermission> resourceURIPermissions = new ArrayList<>();
                        patternsIterator = info.getPatternsCondition().getPatterns().iterator();
                        while (patternsIterator.hasNext()) {
                            String uri = patternsIterator.next();
                            for (String method : methods) {
                                URIPermission uriPermission = new URIPermission();
                                uriPermission.setUriAndMethod(uri + "[" + method + "]");
                                uriPermission.setDatabaseUpdate(false);
                                uriPermission.setPermissionId(1L);
                                resourceURIPermissions.add(uriPermission);
                            }
                        }
                        sort(handlerMethod.getBeanType().getName(), resourcePermission);
                        checkPermission(permissionMap, resourcePermission, uriPermissionMap, resourceURIPermissions);
                    }
                }

            }
            //生成菜单
            MenuGenerator menuGenerator = handlerMethod.getMethod().getAnnotation(MenuGenerator.class);
            if (menuGenerator != null) {
                Menu menu = new Menu();
                menu.setDatabaseUpdate(false);
                menu.setDirectView(false);
                setBaseProp(menuGenerator, menu);
                if (menuMap.get(menu.getId()) != null) {
                    Assert.error("菜单ID重复" + menu.getId() + "," + handlerMethod.getBeanType());
                } else {
                    menuMap.put(menu.getId(), menu);
                }
                if (permissionName != null) {
                    PermissionMenu permissionMenu = new PermissionMenu();
                    permissionMenu.setDataBaseUpdate(false);
                    permissionMenu.setMenuId(menu.getId());
                    permissionMenu.setPermissionName(permissionName);
                    permissionMenus.add(permissionMenu);
                } else {
                    menu.setDirectView(true);
                }

                if (menuGenerator.uri().length() > 0) {
                    menu.setUri(menuGenerator.uri());
                } else {
                    //取第一个
                    menu.setUri(info.getPatternsCondition().getPatterns().iterator().next());
                }
                if (menuGenerator.parentId() == 0) {
                    MenuGenerator parent = handlerMethod.getBeanType().getAnnotation(MenuGenerator.class);
                    if (parent != null) {
                        menu.setParentId(parent.id());
                        Menu parentMenu = new Menu();
                        parentMenu.setDatabaseUpdate(false);
                        setBaseProp(parent, parentMenu);
                        if (menuMap.get(parentMenu.getId()) == null) {
                            menuMap.put(parentMenu.getId(), parentMenu);
                        }
                    } else {
                        menu.setParentId(0);
                    }
                } else {
                    menu.setParentId(menuGenerator.parentId());
                }
            }
        }

        extPermission(event, permissionMap, uriPermissionMap);
        createPermissions.addAll(permissionMap.values());
        if (!createPermissions.isEmpty()) {
            authorizeService.syncPermission(permissionMap.values(), uriPermissionMap);
        }
        if (menuMap.size() > 0) {
            authorizeService.syncMenu(menuMap.values());
            authorizeService.syncMenuPermission(permissionMenus);
        }
        authorizeService.loadStatic();

    }

    //将同一个permisson name 合并
    private Permission checkPermission(Map<String, Permission> permissionMap, Permission permission,
                                       Map<String, List<URIPermission>> uriPermissionMap, List<URIPermission> uriPermissions) {
        Permission beforePermission = permissionMap.get(permission.getName());
        if (beforePermission != null) {
            List<URIPermission> beforeURIPermission = uriPermissionMap.get(permission.getName());
            beforeURIPermission.addAll(uriPermissions);
            if (beforePermission.getDescription() == null) {
                beforePermission.setDescription(permission.getDescription());
            } else {
                if (permission.getDescription() != null) {
                    beforePermission.setDescription(beforePermission.getDescription() + "||" + permission.getDescription());
                }
            }
            return beforePermission;
        } else {
            permissionMap.put(permission.getName(), permission);
            uriPermissionMap.put(permission.getName(), uriPermissions);
            return permission;
        }
    }


    private static String mapping2Suffix(RequestMappingInfo info, HandlerMethod handlerMethod) {
        String suffix = "read";
        if (info.getMethodsCondition().isEmpty()) {
            suffix = method2Suffix(handlerMethod, false);
        } else {
            List<String> methods = new ArrayList<>();
            info.getMethodsCondition().getMethods().forEach(requestMethod ->
                    methods.add(requestMethod.toString().toUpperCase()));
            if (methods.contains("DELETE")) {
                suffix = "delete";
            } else if (
                    methods.contains("PUT")) {
                suffix = "update";
            } else if (
                    methods.contains("POST")) {
                if (methods.contains("GET")) {
                    suffix = method2Suffix(handlerMethod, false);
                } else {
                    suffix = method2Suffix(handlerMethod, true);
                }

            }

        }
        return suffix;
    }

    public static String method2Suffix(HandlerMethod handlerMethod, boolean post) {
        String suffix = "create";
        if (handlerMethod.getMethod().getName().startsWith("add") || handlerMethod.getMethod().getName().startsWith("create")) {
            suffix = "create";
        } else if (handlerMethod.getMethod().getName().startsWith("del")) {
            suffix = "delete";
        } else if (handlerMethod.getMethod().getName().startsWith("update") || handlerMethod.getMethod().getName().startsWith("change")) {
            suffix = "update";
        } else {
            suffix = post ? "update" : "read";
        }
        return suffix;
    }


    private void setBaseProp(MenuGenerator menuGenerator, Menu menu) {
        menu.setText(menuGenerator.text());
        if (menuGenerator.description().length() > 0) {
            menu.setDescription(menuGenerator.description());
        }
        if (menuGenerator.config().length() > 0) {
            menu.setConfig(menuGenerator.config());
        }

        if (menuGenerator.i18nKey().length() > 0) {
            menu.setI18nKey(menuGenerator.i18nKey());
        } else {
            menu.setI18nKey(Pinyin.toAccount(menu.getText())[0]);
            menu.setI18nKey(Pinyin.toAccount(menu.getText())[0].replace(" ", ".").toLowerCase());
        }
        menu.setIcon(menuGenerator.icon());
        menu.setId(menuGenerator.id());
        menu.setSort(menuGenerator.sort());

    }

    private void extPermission(ContextRefreshedEvent event, Map<String, Permission> permissionMap,
                               Map<String, List<URIPermission>> uriPermissionMap) {
        ApplicationContext context = event.getApplicationContext();

        String beans[] = context.getBeanNamesForAnnotation(ExtPermission.class);

        for (String bean : beans) {
            Object obj = context.getBean(bean);
            ExtPermission parent = obj.getClass().getAnnotation(ExtPermission.class);
            if (parent.value().length > 1) {
                Assert.error("ExtPermission 在类上value 只能有一个");
            }
            String parentURI = "";
            if (parent.value().length>0&&parent.value()[0] != null) {
                parentURI = parent.value()[0];
            }
            Method[] methods = obj.getClass().getDeclaredMethods();
            for (Method method : methods) {
                ExtPermission extPermission = method.getAnnotation(ExtPermission.class);
                if (extPermission != null) {
                    Permission permission = new Permission();
                    if (extPermission.value().length == 0) {
                        Assert.error("ExtPermission 在参数的方法上value 不为空");
                    }
                    List<String> httpMethods;
                    if (extPermission.method().length == 0) {
                        httpMethods = HTTPPermission.allMethod();
                    } else {
                        httpMethods = new ArrayList<>();
                        for (RequestMethod requestMethod : extPermission.method()) {
                            httpMethods.add(requestMethod.toString().toUpperCase());
                        }
                    }
                    String description = "";
                    List<URIPermission> uriPermissionList = new ArrayList<>();
                    for (int i = 0; i < extPermission.value().length; i++) {

                        String thisURI = parentURI + extPermission.value()[i];
                        if (thisURI.length() == 0) {
                            Assert.error("ExtPermission 在参数的方法上 有效值 value 不为空");
                        }
                        if (i > 0) {
                            description += "||";
                        }
                        description += thisURI;
                        for (String httpMethod : httpMethods) {
                            URIPermission uriPermission = new URIPermission();
                            uriPermission.setPermissionId(0L);
                            uriPermission.setDatabaseUpdate(false);
                            uriPermission.setUriAndMethod(thisURI + "[" + httpMethod + "]");
                            uriPermissionList.add(uriPermission);
                        }
                    }
                    permission.setDatabaseUpdate(false);
                    String permissionName = extPermission.name();
                    if (permissionName.length() == 0) {
                        permissionName = extPermission.value()[0] + "_read";
                    }
                    permission.setName(permissionName);
                    String readableName = extPermission.readableName();
                    if (readableName.length() == 0) {
                        readableName = permissionName;
                    }
                    permission.setReadableName(readableName);
                    permission.setType(PermissionConstant.PERMISSION_TYPE_NORMAL);
                    if (permission.getName().endsWith("_owner")) {
                        permission.setType(PermissionConstant.PERMISSION_TYPE_OWNER);
                    }
                    permission.setDescription(httpMethods+" " + description);
                    sort(obj.getClass().getName(), permission);
                    checkPermission(permissionMap, permission, uriPermissionMap, uriPermissionList);
                }
            }
        }
    }

    public static void main(String[] args) {
        String str = "创建容器";
        StringBuilder sb = new StringBuilder(str);
        sb.insert(2, "我的");
        System.out.println(sb);

    }
}
