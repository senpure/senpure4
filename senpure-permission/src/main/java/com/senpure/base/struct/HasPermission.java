package com.senpure.base.struct;

import com.senpure.base.model.Permission;

/**
 * Created by 罗中正 on 2017/12/27 0027.
 */
public class HasPermission extends Permission implements Comparable<HasPermission> {

    private boolean has;

    public boolean isHas() {
        return has;
    }

    public void setHas(boolean has) {
        this.has = has;
    }

    public void copy(Permission permission) {
        setId(permission.getId());
        setName(permission.getName());
        setReadableName(permission.getReadableName());
        setDatabaseUpdate(permission.getDatabaseUpdate());
        setType(permission.getType());
        setSort(permission.getSort());
        setDescription(permission.getDescription());
        setVersion(permission.getVersion());
    }

    @Override
    public String toString() {
        return "HasPermission{" +
                "has=" + has +
                "} " + super.toString();
    }

    @Override
    public int compareTo(HasPermission o) {
        return this.getName().compareTo(o.getName());
    }
}
