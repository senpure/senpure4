package ${controllerPackage};

import com.senpure.base.result.ResultMap;
import com.senpure.base.spring.BaseController;
<#if useCriteriaStr>
import ${criteriaPackage}.${name}CriteriaStr;
</#if>
import ${criteriaPackage}.${name}Criteria;
import ${servicePackage}.${name}Service;
import ${modelPackage}.${name};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
<#if generatePermission>
import com.senpure.base.annotation.PermissionVerify;
</#if>
<#if generateMenu>
import com.senpure.base.menu.MenuGenerator;
</#if>

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author senpure-generator
 * @version ${.now?datetime}
 */
@Controller
@RequestMapping("/${module}")
<#if generateMenu>
@MenuGenerator(id = ${menuId?c}, text = "${name}")
</#if>
public class ${name}Controller extends BaseController {

    @Autowired
    private ${name}Service ${nameRule(name)}Service;
    private String view = "/${module}/${nameRule(name)}";

    @RequestMapping(value = {"/${pluralize(nameRule(name))}", "/${pluralize(nameRule(name))}/{page}"}, method = {RequestMethod.GET, RequestMethod.POST})
    <#if generatePermission>
    @PermissionVerify(name = "/${module}/${nameRule(name)}_read", value = "${pluralize(nameRule(name))}_read")
    </#if>
<#if generateMenu>
    @MenuGenerator(id = ${(menuId+1)?c}, text = "${name} Detail")
</#if>

<#if useCriteriaStr>
    <#assign criteriaClazz>${name}CriteriaStr</#assign>
    <#assign criteriaName>criteriaStr</#assign>
<#else >
    <#assign criteriaClazz>${name}Criteria</#assign>
    <#assign criteriaName>criteria</#assign>
</#if>
    public ModelAndView read${pluralize(nameRule(name))?cap_first}(HttpServletRequest request, @ModelAttribute("criteria") @Valid ${criteriaClazz} ${criteriaName}, BindingResult result) {
        if (result.hasErrors()) {
            logger.warn("客户端输入不正确{}", result);
            return incorrect(request, result, view);
        }
<#if useCriteriaStr>
        ${name}Criteria criteria = ${criteriaName}.to${name}Criteria();
</#if>
        logger.debug("查询条件:{}", criteria);
        ResultMap resultMap = ${nameRule(name)}Service.findPage(criteria);
        return result(request, view, resultMap);
    }

    @RequestMapping(value = "/${nameRule(name)}/{${id.name}}", method = RequestMethod.GET)
    <#if generatePermission>
    @PermissionVerify(name = "/${module}/${nameRule(name)}_read", value = "${nameRule(name)}_read")
    </#if>
    @ResponseBody
    public ResultMap read${name}(HttpServletRequest request, @PathVariable String ${id.name}) {
        <#if id.clazzType !="String">
        ${id.clazzType} number${id.name?cap_first};
        try {
            number${id.name?cap_first} = ${id.clazzType}.valueOf(${id.name});
        } catch (NumberFormatException e) {
            logger.error("输入转换失败", e);
            return wrapMessage(request, ResultMap.notExist(), id);
        }
    </#if>
        logger.debug("查询${name}:{}", ${id.name});
        ${name} ${nameRule(name)} = ${nameRule(name)}Service.find(<#if id.clazzType !="String">number${id.name?cap_first}<#else>${id.name}</#if>);
        if (${nameRule(name)} != null) {
            return wrapMessage(request, ResultMap.success().putItem(${nameRule(name)}));
        } else {
            return wrapMessage(request, ResultMap.notExist(), id);
        }
    }


    @RequestMapping(value = "/${nameRule(name)}", method = RequestMethod.POST)
    <#if generatePermission>
    @PermissionVerify(value = "${nameRule(name)}_create")
    </#if>
    @ResponseBody
    public ResultMap create${name}(HttpServletRequest request, @ModelAttribute("criteria") @Valid ${criteriaClazz} ${criteriaName}, BindingResult result) {
        if (result.hasErrors()) {
            logger.warn("客户端输入不正确{}", result);
            return incorrect(request, result);
        }
<#if useCriteriaStr>
        ${name}Criteria criteria = ${criteriaName}.to${name}Criteria();
</#if>
        logger.debug("创建${name}:{}", criteria);
        if (${nameRule(name)}Service.save(criteria)) {
            return wrapMessage(request, ResultMap.success().put("${id.name}", criteria.get${id.name?cap_first}()));
        } else {
            return wrapMessage(request, ResultMap.dim());
        }
    }

    @RequestMapping(value = "/${nameRule(name)}/{${id.name}}", method = RequestMethod.PUT)
    <#if generatePermission>
    @PermissionVerify(value = "${nameRule(name)}_update")
    </#if>
    @ResponseBody
    public ResultMap update${name}(HttpServletRequest request, @PathVariable String ${id.name}, @ModelAttribute("criteria") @Valid ${criteriaClazz} ${criteriaName}, BindingResult result) {
        if (result.hasErrors()) {
            logger.warn("客户端输入不正确{}", result);
            return incorrect(request, result);
        }
    <#if id.clazzType !="String">
        ${id.clazzType} number${id.name?cap_first};
        try {
            number${id.name?cap_first} = ${id.clazzType}.valueOf(${id.name});
        } catch (NumberFormatException e) {
            logger.error("输入转换失败", e);
            return wrapMessage(request, ResultMap.notExist(), id);
        }
    </#if>
        <#if useCriteriaStr>
        ${name}Criteria criteria = ${criteriaName}.to${name}Criteria();
        </#if>
        criteria.set${id.name?cap_first}(<#if id.clazzType !="String">number${id.name?cap_first}<#else>${id.name}</#if>);
        logger.debug("修改${name}:{}", criteria);
    <#if version??>
       <#if version.javaNullable>
        if (criteria.get${version.name?cap_first}() == null) {
        <#else>
        if (criteria.get${version.name?cap_first}() == 0) {
        </#if>
            ${name} ${nameRule(name)} = ${nameRule(name)}Service.find(criteria.get${id.name?cap_first}());
            if (${nameRule(name)} == null) {
                return wrapMessage(request, ResultMap.notExist(), id);
            }
            criteria.effective(${nameRule(name)});
            if (${nameRule(name)}Service.update(${nameRule(name)})) {
                return wrapMessage(request, ResultMap.success());
            } else {
                return wrapMessage(request, ResultMap.dim());
            }
        }
    <#else >
    </#if>
        if (${nameRule(name)}Service.update(criteria.to${name}())) {
            return wrapMessage(request, ResultMap.success());
        } else {
            return wrapMessage(request, ResultMap.dim());
        }
    }

    @RequestMapping(value = "/${nameRule(name)}/{${id.name}}", method = RequestMethod.DELETE)
<#if generatePermission>
    @PermissionVerify(value = "${nameRule(name)}_delete")
</#if>
    @ResponseBody
    public ResultMap delete${name}(HttpServletRequest request, @PathVariable String ${id.name}) {
        <#if id.clazzType !="String">
        ${id.clazzType} number${id.name?cap_first};
        try {
            number${id.name?cap_first} = ${id.clazzType}.valueOf(${id.name});
        } catch (NumberFormatException e) {
            logger.error("输入转换失败", e);
            return wrapMessage(request, ResultMap.notExist(), id);
        }
        </#if>
        logger.debug("删除${name}:{}", ${id.name});
        if (${nameRule(name)}Service.delete(<#if id.clazzType !="String">number${id.name?cap_first}<#else>${id.name}</#if>)) {
            return wrapMessage(request, ResultMap.success());
        } else {
            return wrapMessage(request, ResultMap.dim());
        }
    }
}
