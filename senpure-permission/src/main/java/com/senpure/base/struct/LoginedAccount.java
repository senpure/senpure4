package com.senpure.base.struct;

import com.senpure.base.menu.Menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/7.
 */
public class LoginedAccount implements Serializable {
    private static final long serialVersionUID = 1347550701424356203L;
    private Long id;
    private String account;
    private String password;
    private long loginTime;
    private long createTime;
    private String loginIP;
    private int containerId;
    private String name;
    private List<Menu> viewMenus = new ArrayList<>();
    private List<MergePermission> permissions = new ArrayList<>();

    private Map<String, NormalValue>
            normalValueMap = new HashMap<>();


    public NormalValue getNormalValue(String key) {
        return normalValueMap.get(key);
    }

    public String getConfig(String key) {
        NormalValue normalValue = normalValueMap.get(key);
        if (normalValue == null) {
            return null;
        }
        return normalValue.getValue();
    }

    public void setConfig(String key, String value) {

        NormalValue normalValue = normalValueMap.get(key);
        if (normalValue == null) {
            normalValue = new NormalValue();
            normalValue.setNormal(true);
            normalValue.setVersion(0);
            normalValue.setKey(key);
            normalValue.setValue(value);
            normalValue.setId(id);
            normalValueMap.put(key, normalValue);
        }
        normalValue.setVersion(normalValue.getVersion() + 1);
        normalValue.setValue(value);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }


    public String getLoginIP() {
        return loginIP;
    }

    public void setLoginIP(String loginIP) {
        this.loginIP = loginIP;
    }

    public long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(long loginTime) {
        this.loginTime = loginTime;
    }

    public int getContainerId() {
        return containerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContainerId(int containerId) {
        this.containerId = containerId;
    }


    public List<Menu> getViewMenus() {
        return viewMenus;
    }

    public void setViewMenus(List<Menu> viewMenus) {
        this.viewMenus = viewMenus;
    }

    public List<MergePermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<MergePermission> permissions) {
        this.permissions = permissions;
    }

    public Map<String, NormalValue> getNormalValueMap() {
        return normalValueMap;
    }

    public void setNormalValueMap(Map<String, NormalValue> normalValueMap) {
        this.normalValueMap = normalValueMap;
    }
}
