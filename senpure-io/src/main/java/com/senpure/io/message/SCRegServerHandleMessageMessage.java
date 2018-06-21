package com.senpure.io.message;

import com.senpure.io.bean.HandleMessage;
import com.senpure.io.message.Message;
import io.netty.buffer.ByteBuf;

import java.util.List;
import java.util.ArrayList;

/**
  * 服务器注册消息处理器到网关
  * 
  * @author senpure-generator
  * @version 2018-3-16 17:14:32
  */
public class SCRegServerHandleMessageMessage extends  Message {
    //服务器名
    private String serverName;
    //ip和端口号
    private String ipAndFirstPort;
    //服务器名
    private String readableServerName;
    //可以处理的消息
    private List<HandleMessage> messages=new ArrayList();

    /**
     * 写入字节缓存
     */
    @Override
    public void write(ByteBuf buf){
        //服务器名
        writeStr(buf,serverName);
        //ip和端口号
        writeStr(buf,ipAndFirstPort);
        //服务器名
        writeStr(buf,readableServerName);
        //可以处理的消息
        int messagesSize=messages.size();
        writeShort(buf,messagesSize);
        for(int i=0;i< messagesSize;i++){
            writeBean(buf,messages.get(i),false);
           }
    }


    /**
     * 读取字节缓存
     */
    @Override
    public void read(ByteBuf buf){
        //服务器名
        this.serverName= readStr(buf);
        //ip和端口号
        this.ipAndFirstPort= readStr(buf);
        //服务器名
        this.readableServerName= readStr(buf);
        //可以处理的消息
        int messagesSize=readShort(buf);
        for(int i=0;i<messagesSize;i++){
            this.messages.add((HandleMessage)readBean(buf,HandleMessage.class,false));
         }
    }

    /**
     * get 服务器名
     * @return
     */
    public  String getServerName() {
        return serverName;
    }

    /**
     * set 服务器名
     */
    public SCRegServerHandleMessageMessage setServerName(String serverName) {
        this.serverName=serverName;
        return this;
    }
    /**
     * get ip和端口号
     * @return
     */
    public  String getIpAndFirstPort() {
        return ipAndFirstPort;
    }

    /**
     * set ip和端口号
     */
    public SCRegServerHandleMessageMessage setIpAndFirstPort(String ipAndFirstPort) {
        this.ipAndFirstPort=ipAndFirstPort;
        return this;
    }
    /**
     * get 服务器名
     * @return
     */
    public  String getReadableServerName() {
        return readableServerName;
    }

    /**
     * set 服务器名
     */
    public SCRegServerHandleMessageMessage setReadableServerName(String readableServerName) {
        this.readableServerName=readableServerName;
        return this;
    }
     /**
      * get 可以处理的消息
      * @return
      */
    public List<HandleMessage> getMessages(){
        return messages;
    }
     /**
      * set 可以处理的消息
      */
    public SCRegServerHandleMessageMessage setMessages (List<HandleMessage> messages){
        this.messages=messages;
        return this;
    }


    @Override
    public int getMessageId() {
        return 1104;
    }

    @Override
    public String toString() {
        return "SCRegServerHandleMessageMessage{"
                +"serverName=" + serverName
                +",ipAndFirstPort=" + ipAndFirstPort
                +",readableServerName=" + readableServerName
                +",messages=" + messages
                + "}";
   }

    //18 + 3 = 21 个空格
    private String nextIndent ="                     ";
    //最长字段长度 18
    private int filedPad = 18;

    @Override
    public String toString(String indent) {
        indent = indent == null ? "" : indent;
        StringBuilder sb = new StringBuilder();
        sb.append("SCRegServerHandleMessageMessage").append("{");
        //服务器名
        sb.append("\n");
        sb.append(indent).append(rightPad("serverName", filedPad)).append(" = ").append(serverName);
        //ip和端口号
        sb.append("\n");
        sb.append(indent).append(rightPad("ipAndFirstPort", filedPad)).append(" = ").append(ipAndFirstPort);
        //服务器名
        sb.append("\n");
        sb.append(indent).append(rightPad("readableServerName", filedPad)).append(" = ").append(readableServerName);
        //可以处理的消息
        sb.append("\n");
        sb.append(indent).append(rightPad("messages", filedPad)).append(" = ");
        int messagesSize = messages.size();
        if (messagesSize > 0) {
            sb.append("[");
            for (int i = 0; i<messagesSize;i++) {
                sb.append("\n");
                sb.append(nextIndent);
                sb.append(indent).append(messages.get(i).toString(indent + nextIndent));
            }
            sb.append("\n");
        sb.append(nextIndent);
            sb.append(indent).append("]");
        }else {
            sb.append("null");
        }

        sb.append("\n");
        sb.append(indent).append("}");
        return sb.toString();
    }

}