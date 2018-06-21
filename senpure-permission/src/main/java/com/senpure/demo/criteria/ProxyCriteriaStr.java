package com.senpure.demo.criteria;

import com.senpure.base.criterion.CriteriaStr;
import com.senpure.base.struct.PatternDate;
import com.senpure.base.validator.DynamicDate;

import java.io.Serializable;

/**
 * 代理关系表
 *
 * @author senpure-generator
 * @version 2018-6-6 15:27:45
 */
public class ProxyCriteriaStr extends CriteriaStr implements Serializable {
    private static final long serialVersionUID = 315239481L;

    //主键
    private String id;
    //乐观锁，版本控制
    private String version;
    //上级id
    private String parent;
    //上级id
    private String child;
    //间隔级数
    private String level;
    //table [demo_proxy][column = bind_time] order
    private String bindTimeOrder;
    //绑定时间
    private String bindDate;
    @DynamicDate
    private PatternDate bindDateValid = new PatternDate();

    public ProxyCriteria toProxyCriteria() {
        ProxyCriteria criteria = new ProxyCriteria();
        criteria.setUsePage(Boolean.valueOf(getUsePage()));
        criteria.setPage(Integer.valueOf(getPage()));
        criteria.setPageSize(Integer.valueOf(getPageSize()));
        criteria.setStartDate(getStartDateValid().getDate());
        criteria.setEndDate(getEndDateValid().getDate());
        //主键
        if (id != null) {
            criteria.setId(Long.valueOf(id));
        }
        //乐观锁，版本控制
        if (version != null) {
            criteria.setVersion(Integer.valueOf(version));
        }
        //上级id
        if (parent != null) {
            criteria.setParent(Long.valueOf(parent));
        }
        //上级id
        if (child != null) {
            criteria.setChild(Long.valueOf(child));
        }
        //间隔级数
        if (level != null) {
            criteria.setLevel(Integer.valueOf(level));
        }
        //table [demo_proxy][column = bind_time] order
        if (bindTimeOrder != null) {
            criteria.setBindTimeOrder(bindTimeOrder);
        }
        //绑定时间
        if (bindDate != null) {
            criteria.setBindDate(bindDateValid.getDate());
            if (bindDateValid.getDate() != null) {
                criteria.setBindTime(bindDateValid.getDate().getTime());
            }
        }
        return criteria;
    }

    @Override
    public void setDatePattern(String datePattern) {
        super.setDatePattern(datePattern);
        bindDateValid.setPattern(datePattern);
    }

    @Override
    protected void beforeStr(StringBuilder sb) {
        sb.append("ProxyCriteriaStr{");
        if (id != null) {
            sb.append("id=").append(id).append(",");
        }
        if (version != null) {
            sb.append("version=").append(version).append(",");
        }
        if (parent != null) {
            sb.append("parent=").append(parent).append(",");
        }
        if (child != null) {
            sb.append("child=").append(child).append(",");
        }
        if (level != null) {
            sb.append("level=").append(level).append(",");
        }
        if (bindDate != null) {
            sb.append("bindDate=").append(bindDate).append(",");
        }
    }

    @Override
    protected void afterStr(StringBuilder sb) {
        if (bindTimeOrder != null) {
            sb.append("bindTimeOrder=").append(bindTimeOrder).append(",");
        }
        super.afterStr(sb);
    }


    /**
     * get 主键
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * set 主键
     *
     * @return
     */
    public ProxyCriteriaStr setId(String id) {
        if (id != null && id.trim().length() == 0) {
            return this;
        }
        this.id = id;
        return this;
    }


    /**
     * get 乐观锁，版本控制
     *
     * @return
     */
    public String getVersion() {
        return version;
    }

    /**
     * set 乐观锁，版本控制
     *
     * @return
     */
    public ProxyCriteriaStr setVersion(String version) {
        if (version != null && version.trim().length() == 0) {
            return this;
        }
        this.version = version;
        return this;
    }


    /**
     * get 上级id
     *
     * @return
     */
    public String getParent() {
        return parent;
    }

    /**
     * set 上级id
     *
     * @return
     */
    public ProxyCriteriaStr setParent(String parent) {
        if (parent != null && parent.trim().length() == 0) {
            return this;
        }
        this.parent = parent;
        return this;
    }


    /**
     * get 上级id
     *
     * @return
     */
    public String getChild() {
        return child;
    }

    /**
     * set 上级id
     *
     * @return
     */
    public ProxyCriteriaStr setChild(String child) {
        if (child != null && child.trim().length() == 0) {
            return this;
        }
        this.child = child;
        return this;
    }


    /**
     * get 间隔级数
     *
     * @return
     */
    public String getLevel() {
        return level;
    }

    /**
     * set 间隔级数
     *
     * @return
     */
    public ProxyCriteriaStr setLevel(String level) {
        if (level != null && level.trim().length() == 0) {
            return this;
        }
        this.level = level;
        return this;
    }


    /**
     * get table [demo_proxy][column = bind_time] order
     *
     * @return
     */
    public String getBindTimeOrder() {
        return bindTimeOrder;
    }

    /**
     * set table [demo_proxy][column = bind_time] order DESC||ASC
     *
     * @return
     */
    public ProxyCriteriaStr setBindTimeOrder(String bindTimeOrder) {
        if (bindTimeOrder != null && bindTimeOrder.trim().length() == 0) {
            this.bindTimeOrder = null;
            return this;
        }
        this.bindTimeOrder = bindTimeOrder;
        return this;
    }


    /**
     * get 绑定时间
     *
     * @return
     */
    public String getBindDate() {
        return bindDate;
    }

    /**
     * set 绑定时间
     *
     * @return
     */
    public ProxyCriteriaStr setBindDate(String bindDate) {
        if (bindDate != null && bindDate.trim().length() == 0) {
            return this;
        }
        this.bindDate = bindDate;
        return this;
    }


}