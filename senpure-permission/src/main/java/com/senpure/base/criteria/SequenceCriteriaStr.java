package com.senpure.base.criteria;

import com.senpure.base.criterion.CriteriaStr;

import java.io.Serializable;

/**
 * @author senpure-generator
 * @version 2018-1-25 18:24:19
 */
public class SequenceCriteriaStr extends CriteriaStr implements Serializable {
    private static final long serialVersionUID = 945199211L;

    //主键
    private String id;
    //乐观锁，版本控制
    private String version;
    //标识
    private String type;
    //table [senpure_sequence][column = type] order
    private String typeOrder;
    //前缀
    private String prefix;
    //后缀
    private String suffix;
    //当前序列号
    private String sequence;
    //格式化的长度
    private String digit;
    //每次增长
    private String span;

    public SequenceCriteria toSequenceCriteria() {
        SequenceCriteria criteria = new SequenceCriteria();
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
        //标识
        if (type != null) {
            criteria.setType(type);
        }
        //table [senpure_sequence][column = type] order
        if (typeOrder != null) {
            criteria.setTypeOrder(typeOrder);
        }
        //前缀
        if (prefix != null) {
            criteria.setPrefix(prefix);
        }
        //后缀
        if (suffix != null) {
            criteria.setSuffix(suffix);
        }
        //当前序列号
        if (sequence != null) {
            criteria.setSequence(Integer.valueOf(sequence));
        }
        //格式化的长度
        if (digit != null) {
            criteria.setDigit(Integer.valueOf(digit));
        }
        //每次增长
        if (span != null) {
            criteria.setSpan(Integer.valueOf(span));
        }
        return criteria;
    }


    @Override
    protected void beforeStr(StringBuilder sb) {
        sb.append("SequenceCriteriaStr{");
        if (id != null) {
            sb.append("id=").append(id).append(",");
        }
        if (version != null) {
            sb.append("version=").append(version).append(",");
        }
        if (type != null) {
            sb.append("type=").append(type).append(",");
        }
        if (prefix != null) {
            sb.append("prefix=").append(prefix).append(",");
        }
        if (suffix != null) {
            sb.append("suffix=").append(suffix).append(",");
        }
        if (sequence != null) {
            sb.append("sequence=").append(sequence).append(",");
        }
        if (digit != null) {
            sb.append("digit=").append(digit).append(",");
        }
        if (span != null) {
            sb.append("span=").append(span).append(",");
        }
    }

    @Override
    protected void afterStr(StringBuilder sb) {
        if (typeOrder != null) {
            sb.append("typeOrder=").append(typeOrder).append(",");
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
    public SequenceCriteriaStr setId(String id) {
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
    public SequenceCriteriaStr setVersion(String version) {
        if (version != null && version.trim().length() == 0) {
            return this;
        }
        this.version = version;
        return this;
    }


    /**
     * get 标识
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * get table [senpure_sequence][column = type] order
     *
     * @return
     */
    public String getTypeOrder() {
        return typeOrder;
    }

    /**
     * set 标识
     *
     * @return
     */
    public SequenceCriteriaStr setType(String type) {
        if (type != null && type.trim().length() == 0) {
            return this;
        }
        this.type = type;
        return this;
    }

    /**
     * set table [senpure_sequence][column = type] order DESC||ASC
     *
     * @return
     */
    public SequenceCriteriaStr setTypeOrder(String typeOrder) {
        if (typeOrder != null && typeOrder.trim().length() == 0) {
            return this;
        }
        this.typeOrder = typeOrder;
        return this;
    }


    /**
     * get 前缀
     *
     * @return
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * set 前缀
     *
     * @return
     */
    public SequenceCriteriaStr setPrefix(String prefix) {
        if (prefix != null && prefix.trim().length() == 0) {
            return this;
        }
        this.prefix = prefix;
        return this;
    }


    /**
     * get 后缀
     *
     * @return
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * set 后缀
     *
     * @return
     */
    public SequenceCriteriaStr setSuffix(String suffix) {
        if (suffix != null && suffix.trim().length() == 0) {
            return this;
        }
        this.suffix = suffix;
        return this;
    }


    /**
     * get 当前序列号
     *
     * @return
     */
    public String getSequence() {
        return sequence;
    }

    /**
     * set 当前序列号
     *
     * @return
     */
    public SequenceCriteriaStr setSequence(String sequence) {
        if (sequence != null && sequence.trim().length() == 0) {
            return this;
        }
        this.sequence = sequence;
        return this;
    }


    /**
     * get 格式化的长度
     *
     * @return
     */
    public String getDigit() {
        return digit;
    }

    /**
     * set 格式化的长度
     *
     * @return
     */
    public SequenceCriteriaStr setDigit(String digit) {
        if (digit != null && digit.trim().length() == 0) {
            return this;
        }
        this.digit = digit;
        return this;
    }


    /**
     * get 每次增长
     *
     * @return
     */
    public String getSpan() {
        return span;
    }

    /**
     * set 每次增长
     *
     * @return
     */
    public SequenceCriteriaStr setSpan(String span) {
        if (span != null && span.trim().length() == 0) {
            return this;
        }
        this.span = span;
        return this;
    }


}