package com.senpure.demo.criteria;

import com.senpure.demo.model.Proxy;
import com.senpure.base.criterion.Criteria;

import java.util.Date;
import java.io.Serializable;

/**
 * 代理关系表
 *
 * @author senpure-generator
 * @version 2018-6-6 15:27:45
 */
public class ProxyCriteria extends Criteria implements Serializable {
    private static final long serialVersionUID = 315239481L;

    //主键
    private Long id;
    //乐观锁，版本控制
    private Integer version;
    //上级id
    private Long parent;
    //上级id
    private Long child;
    //间隔级数
    private Integer level;
    //绑定时间
    private Long bindTime;
    //table [demo_proxy][column = bind_time] order
    private String bindTimeOrder;
    //绑定时间
    private Date bindDate;

    public static Proxy toProxy(ProxyCriteria criteria, Proxy proxy) {
        proxy.setId(criteria.getId());
        proxy.setParent(criteria.getParent());
        proxy.setChild(criteria.getChild());
        proxy.setLevel(criteria.getLevel());
        proxy.setBindTime(criteria.getBindTime());
        proxy.setBindDate(criteria.getBindDate());
        proxy.setVersion(criteria.getVersion());
        return proxy;
    }

    public Proxy toProxy() {
        Proxy proxy = new Proxy();
        return toProxy(this, proxy);
    }

    /**
     * 将ProxyCriteria 的有效值(不为空),赋值给 Proxy
     *
     * @return Proxy
     */
    public Proxy effective(Proxy proxy) {
        if (getId() != null) {
            proxy.setId(getId());
        }
        if (getParent() != null) {
            proxy.setParent(getParent());
        }
        if (getChild() != null) {
            proxy.setChild(getChild());
        }
        if (getLevel() != null) {
            proxy.setLevel(getLevel());
        }
        if (getBindTime() != null) {
            proxy.setBindTime(getBindTime());
        }
        if (getBindDate() != null) {
            proxy.setBindDate(getBindDate());
        }
        if (getVersion() != null) {
            proxy.setVersion(getVersion());
        }
        return proxy;
    }

    @Override
    protected void beforeStr(StringBuilder sb) {
        sb.append("ProxyCriteria{");
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
        if (bindTime != null) {
            sb.append("bindTime=").append(bindTime).append(",");
        }
        if (bindDate != null) {
            sb.append("bindDate=").append(bindDate).append(",");
        }
    }

    /**
     * get 主键
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * set 主键
     *
     * @return
     */
    public ProxyCriteria setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * get 上级id
     *
     * @return
     */
    public Long getParent() {
        return parent;
    }

    /**
     * set 上级id
     *
     * @return
     */
    public ProxyCriteria setParent(Long parent) {
        this.parent = parent;
        return this;
    }


    /**
     * get 上级id
     *
     * @return
     */
    public Long getChild() {
        return child;
    }

    /**
     * set 上级id
     *
     * @return
     */
    public ProxyCriteria setChild(Long child) {
        this.child = child;
        return this;
    }


    /**
     * get 间隔级数
     *
     * @return
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * set 间隔级数
     *
     * @return
     */
    public ProxyCriteria setLevel(Integer level) {
        this.level = level;
        return this;
    }


    /**
     * get 绑定时间
     *
     * @return
     */
    public Long getBindTime() {
        return bindTime;
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
     * set 绑定时间
     *
     * @return
     */
    public ProxyCriteria setBindTime(Long bindTime) {
        this.bindTime = bindTime;
        return this;
    }

    /**
     * set table [demo_proxy][column = bind_time] order DESC||ASC
     *
     * @return
     */
    public ProxyCriteria setBindTimeOrder(String bindTimeOrder) {
        this.bindTimeOrder = bindTimeOrder;
        putSort("bind_time", bindTimeOrder);
        return this;
    }


    /**
     * get 绑定时间
     *
     * @return
     */
    public Date getBindDate() {
        return bindDate;
    }

    /**
     * set 绑定时间
     *
     * @return
     */
    public ProxyCriteria setBindDate(Date bindDate) {
        this.bindDate = bindDate;
        return this;
    }


    /**
     * get 乐观锁，版本控制
     *
     * @return
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * set 乐观锁，版本控制
     *
     * @return
     */
    public ProxyCriteria setVersion(Integer version) {
        this.version = version;
        return this;
    }

}