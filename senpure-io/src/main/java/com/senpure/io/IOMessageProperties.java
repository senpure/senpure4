package com.senpure.io;

/**
 * Created by 罗中正 on 2017/8/29.
 */
public class IOMessageProperties {
    private boolean inFormat=false;
    private boolean outFormat=false;

    public boolean isInFormat() {
        return inFormat;
    }

    public void setInFormat(boolean inFormat) {
        this.inFormat = inFormat;
    }

    public boolean isOutFormat() {
        return outFormat;
    }

    public void setOutFormat(boolean outFormat) {
        this.outFormat = outFormat;
    }
}
