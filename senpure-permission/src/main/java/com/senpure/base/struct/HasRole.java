package com.senpure.base.struct;


import com.senpure.base.model.Role;

/**
 * Created by 罗中正 on 2017/5/23.
 */
public class HasRole extends Role {
    private boolean has;

    public boolean isHas() {
        return has;
    }

    public void setHas(boolean has) {
        this.has = has;
    }

    public void copy(Role role) {

        setId(role.getId());
        setName(role.getName());
        setCreateDate(role.getCreateDate());
        setCreateTime(role.getCreateTime());
        setDescription(role.getDescription());
        setContainerId(role.getContainerId());
        setVersion(role.getVersion());
    }
}
