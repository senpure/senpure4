package com.senpure.io.message;

import io.netty.buffer.ByteBuf;

/**
 * 应答是否可以请求
 * 
 * @author senpure-generator
 * @version 2018-3-21 20:49:21
 */
public class SCAskHandleMessage extends Message {
    //是否可以处理
    private boolean handle;
    //token
    private long token;
    //消息ID
    private int fromMessageId;
    //值
    private String value;

    /**
     * 写入字节缓存
     */
    @Override
    public void write(ByteBuf buf){
        //是否可以处理
        writeBoolean(buf,handle);
        //token
        writeLong(buf,token);
        //消息ID
        writeInt(buf,fromMessageId);
        //值
        writeStr(buf,value);
    }


    /**
     * 读取字节缓存
     */
    @Override
    public void read(ByteBuf buf){
        //是否可以处理
        this.handle = readBoolean(buf);
        //token
        this.token = readLong(buf);
        //消息ID
        this.fromMessageId = readInt(buf);
        //值
        this.value= readStr(buf);
    }

    /**
     *  is 是否可以处理
     * @return
     */
    public  boolean  isHandle() {
        return handle;
    }

    /**
     * set 是否可以处理
     */
    public SCAskHandleMessage setHandle(boolean handle) {
        this.handle=handle;
        return this;
    }
    /**
     * get token
     * @return
     */
    public  long getToken() {
        return token;
    }

    /**
     * set token
     */
    public SCAskHandleMessage setToken(long token) {
        this.token=token;
        return this;
    }
    /**
     * get 消息ID
     * @return
     */
    public  int getFromMessageId() {
        return fromMessageId;
    }

    /**
     * set 消息ID
     */
    public SCAskHandleMessage setFromMessageId(int fromMessageId) {
        this.fromMessageId=fromMessageId;
        return this;
    }
    public  String getValue() {
        return value;
    }

    public SCAskHandleMessage setValue(String value) {
        this.value=value;
        return this;
    }

    @Override
    public int getMessageId() {
    return 1106;
    }

    @Override
    public String toString() {
        return "SCAskHandleMessage{"
                +"handle=" + handle
                +",token=" + token
                +",fromMessageId=" + fromMessageId
                +",value=" + value
                + "}";
   }

    //最长字段长度 13
    private int filedPad = 13;

    @Override
    public String toString(String indent) {
        indent = indent == null ? "" : indent;
        StringBuilder sb = new StringBuilder();
        sb.append("SCAskHandleMessage").append("{");
        //是否可以处理
        sb.append("\n");
        sb.append(indent).append(rightPad("handle", filedPad)).append(" = ").append(handle);
        //token
        sb.append("\n");
        sb.append(indent).append(rightPad("token", filedPad)).append(" = ").append(token);
        //消息ID
        sb.append("\n");
        sb.append(indent).append(rightPad("fromMessageId", filedPad)).append(" = ").append(fromMessageId);
        //值
        sb.append("\n");
        sb.append(indent).append(rightPad("value", filedPad)).append(" = ").append(value);
        sb.append("\n");
        sb.append(indent).append("}");
        return sb.toString();
    }

}