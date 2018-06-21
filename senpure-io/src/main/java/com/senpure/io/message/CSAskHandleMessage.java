package com.senpure.io.message;

import io.netty.buffer.ByteBuf;

/**
 * 询问服务器是否可以处理该值得请求
 * 
 * @author senpure-generator
 * @version 2018-3-21 20:05:32
 */
public class CSAskHandleMessage extends Message {
    //yuan
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
        //yuan
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
        //yuan
        this.token = readLong(buf);
        //消息ID
        this.fromMessageId = readInt(buf);
        //值
        this.value= readStr(buf);
    }

    /**
     * get yuan
     * @return
     */
    public  long getToken() {
        return token;
    }

    /**
     * set yuan
     */
    public CSAskHandleMessage setToken(long token) {
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
    public CSAskHandleMessage setFromMessageId(int fromMessageId) {
        this.fromMessageId=fromMessageId;
        return this;
    }
    public  String getValue() {
        return value;
    }

    public CSAskHandleMessage setValue(String value) {
        this.value=value;
        return this;
    }

    @Override
    public int getMessageId() {
    return 1105;
    }

    @Override
    public String toString() {
        return "CSAskHandleMessage{"
                +"token=" + token
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
        sb.append("CSAskHandleMessage").append("{");
        //yuan
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