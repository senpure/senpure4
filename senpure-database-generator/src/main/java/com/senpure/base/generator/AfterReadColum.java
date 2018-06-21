package com.senpure.base.generator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 罗中正 on 2017/9/21.
 */
public class AfterReadColum {


    private List<ModelField> modelFields = new ArrayList<>();

    public List<ModelField> getModelFields() {
        return modelFields;
    }

    public void setModelFields(List<ModelField> modelFields) {
        this.modelFields = modelFields;
    }

    private List<String> targetClass=new ArrayList<>();

    public List<String> getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(List<String> targetClass) {
        this.targetClass = targetClass;
    }
}
