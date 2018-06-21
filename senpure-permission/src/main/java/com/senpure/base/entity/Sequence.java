package com.senpure.base.entity;



import com.senpure.base.PermissionConstant;
import com.senpure.base.annotation.Explain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = PermissionConstant.DATA_BASE_PREFIX+"_SEQUENCE")
public class Sequence extends LongAndVersionEntity{
    private static final long serialVersionUID = 849244885236870774L;
    @Explain("标识")
    private String type;
    @Column
    //前缀
    @Explain("前缀")
    private String prefix="";
    @Column
   // 后缀
    @Explain("后缀")
    private String suffix="";
    @Column
    @Explain("当前序列号")
    private Integer sequence=1;
    @Column
    @Explain("格式化的长度")
    private Integer digit=6;
    @Column
    @Explain("每次增长")
    private Integer span=1;
    @Column(unique = true)


    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public int getDigit() {
        return digit;
    }

    public void setDigit(int digit) {
        this.digit = digit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSpan() {
        return span;
    }

    public void setSpan(int span) {
        this.span = span;
    }


}
