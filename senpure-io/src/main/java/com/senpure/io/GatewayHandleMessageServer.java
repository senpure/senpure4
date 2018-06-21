package com.senpure.io;

import com.senpure.io.message.Client2GatewayMessage;

/**
 * Created by 罗中正 on 2018/3/19 0019.
 */
public class GatewayHandleMessageServer {


    private int messageType;
    private long numStart;
    private long numEnd;
    private int valueType;
    private boolean serverShare;


    public void  handleMessage(Client2GatewayMessage message){}
    public GatewayHandleMessageServer(int messageType) {
        this.messageType = messageType;
    }

    public boolean handleRange(long num) {
        return num >= numStart && num <= numEnd;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public long getNumStart() {
        return numStart;
    }

    public void setNumStart(long numStart) {
        this.numStart = numStart;
    }

    public long getNumEnd() {
        return numEnd;
    }

    public void setNumEnd(long numEnd) {
        this.numEnd = numEnd;
    }

    public int getValueType() {
        return valueType;
    }

    public void setValueType(int valueType) {
        this.valueType = valueType;
    }

    public boolean isServerShare() {
        return serverShare;
    }

    public void setServerShare(boolean serverShare) {
        this.serverShare = serverShare;
    }
}
