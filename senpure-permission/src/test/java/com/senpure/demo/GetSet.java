package com.senpure.demo;

import com.senpure.base.AppEvn;
import org.junit.Test;

/**
 * Created by 罗中正 on 2018/1/12 0012.
 */
public class GetSet {

    private  boolean man;
    private Boolean woman;

    public boolean isMan() {
        return man;
    }

    public void setMan(boolean man) {
        this.man = man;
    }

    public Boolean getWoman() {
        return woman;
    }

    public void setWoman(Boolean woman) {
        this.woman = woman;
    }


    @Test
    public void  root()
    {

        System.out.println(AppEvn.getClassRootPath());

    }
}
