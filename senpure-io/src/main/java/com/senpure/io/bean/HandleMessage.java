package com.senpure.io.bean;

import io.netty.buffer.ByteBuf;

/**
* @author senpure-generator
* @version 2018-3-21 20:05:32
*/
public class HandleMessage extends  Bean {
    //可以处理的消息ID
    private int handleMessageId;
    //消息类名
    private String messageClasses;
    //是否共享messageId 不同的服务都可以处理
    private boolean serverShare;
    //消息类型 0 可以直接转发过来 1 网关读取范围  2 网关询问
    private int messageType;
    //数字类型 0int 1 long
    private int valueType;
    //范围开始
    private long numStart;
    //范围结束
    private long numEnd;

    /**
     * 写入字节缓存
     */
    @Override
    public void write(ByteBuf buf){
        //可以处理的消息ID
        writeInt(buf,handleMessageId);
        //消息类名
        writeStr(buf,messageClasses);
        //是否共享messageId 不同的服务都可以处理
        writeBoolean(buf,serverShare);
        //消息类型 0 可以直接转发过来 1 网关读取范围  2 网关询问
        writeInt(buf,messageType);
        //数字类型 0int 1 long
        writeInt(buf,valueType);
        //范围开始
        writeLong(buf,numStart);
        //范围结束
        writeLong(buf,numEnd);
    }


    /**
     * 读取字节缓存
     */
    @Override
    public void read(ByteBuf buf){
        //可以处理的消息ID
        this.handleMessageId = readInt(buf);
        //消息类名
        this.messageClasses= readStr(buf);
        //是否共享messageId 不同的服务都可以处理
        this.serverShare = readBoolean(buf);
        //消息类型 0 可以直接转发过来 1 网关读取范围  2 网关询问
        this.messageType = readInt(buf);
        //数字类型 0int 1 long
        this.valueType = readInt(buf);
        //范围开始
        this.numStart = readLong(buf);
        //范围结束
        this.numEnd = readLong(buf);
    }

    /**
     * get 可以处理的消息ID
     * @return
     */
    public  int getHandleMessageId() {
        return handleMessageId;
    }

    /**
     * set 可以处理的消息ID
     */
    public HandleMessage setHandleMessageId(int handleMessageId) {
        this.handleMessageId=handleMessageId;
        return this;
    }
    /**
     * get 消息类名
     * @return
     */
    public  String getMessageClasses() {
        return messageClasses;
    }

    /**
     * set 消息类名
     */
    public HandleMessage setMessageClasses(String messageClasses) {
        this.messageClasses=messageClasses;
        return this;
    }
    /**
     *  is 是否共享messageId 不同的服务都可以处理
     * @return
     */
    public  boolean  isServerShare() {
        return serverShare;
    }

    /**
     * set 是否共享messageId 不同的服务都可以处理
     */
    public HandleMessage setServerShare(boolean serverShare) {
        this.serverShare=serverShare;
        return this;
    }
    /**
     * get 消息类型 0 可以直接转发过来 1 网关读取范围  2 网关询问
     * @return
     */
    public  int getMessageType() {
        return messageType;
    }

    /**
     * set 消息类型 0 可以直接转发过来 1 网关读取范围  2 网关询问
     */
    public HandleMessage setMessageType(int messageType) {
        this.messageType=messageType;
        return this;
    }
    /**
     * get 数字类型 0int 1 long
     * @return
     */
    public  int getValueType() {
        return valueType;
    }

    /**
     * set 数字类型 0int 1 long
     */
    public HandleMessage setValueType(int valueType) {
        this.valueType=valueType;
        return this;
    }
    /**
     * get 范围开始
     * @return
     */
    public  long getNumStart() {
        return numStart;
    }

    /**
     * set 范围开始
     */
    public HandleMessage setNumStart(long numStart) {
        this.numStart=numStart;
        return this;
    }
    /**
     * get 范围结束
     * @return
     */
    public  long getNumEnd() {
        return numEnd;
    }

    /**
     * set 范围结束
     */
    public HandleMessage setNumEnd(long numEnd) {
        this.numEnd=numEnd;
        return this;
    }

    @Override
    public String toString() {
        return "HandleMessage{"
                +"handleMessageId=" + handleMessageId
                +",messageClasses=" + messageClasses
                +",serverShare=" + serverShare
                +",messageType=" + messageType
                +",valueType=" + valueType
                +",numStart=" + numStart
                +",numEnd=" + numEnd
                + "}";
   }

    //最长字段长度 15
    private int filedPad = 15;

    @Override
    public String toString(String indent) {
        indent = indent == null ? "" : indent;
        StringBuilder sb = new StringBuilder();
        sb.append("HandleMessage").append("{");
        //可以处理的消息ID
        sb.append("\n");
        sb.append(indent).append(rightPad("handleMessageId", filedPad)).append(" = ").append(handleMessageId);
        //消息类名
        sb.append("\n");
        sb.append(indent).append(rightPad("messageClasses", filedPad)).append(" = ").append(messageClasses);
        //是否共享messageId 不同的服务都可以处理
        sb.append("\n");
        sb.append(indent).append(rightPad("serverShare", filedPad)).append(" = ").append(serverShare);
        //消息类型 0 可以直接转发过来 1 网关读取范围  2 网关询问
        sb.append("\n");
        sb.append(indent).append(rightPad("messageType", filedPad)).append(" = ").append(messageType);
        //数字类型 0int 1 long
        sb.append("\n");
        sb.append(indent).append(rightPad("valueType", filedPad)).append(" = ").append(valueType);
        //范围开始
        sb.append("\n");
        sb.append(indent).append(rightPad("numStart", filedPad)).append(" = ").append(numStart);
        //范围结束
        sb.append("\n");
        sb.append(indent).append(rightPad("numEnd", filedPad)).append(" = ").append(numEnd);
        sb.append("\n");
        sb.append(indent).append("}");
        return sb.toString();
    }

}