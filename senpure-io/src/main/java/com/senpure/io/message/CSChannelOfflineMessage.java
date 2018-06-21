package com.senpure.io.message;

import com.senpure.io.message.Message;
import io.netty.buffer.ByteBuf;

/**
  * 客户端掉线
  * 
  * @author senpure-generator
  * @version 2018-3-16 17:14:32
  */
public class CSChannelOfflineMessage extends  Message {
    //channel token
    private int token;
    //玩家Id
    private int playerId;

    /**
     * 写入字节缓存
     */
    @Override
    public void write(ByteBuf buf){
        //channel token
        writeInt(buf,token);
        //玩家Id
        writeInt(buf,playerId);
    }


    /**
     * 读取字节缓存
     */
    @Override
    public void read(ByteBuf buf){
        //channel token
        this.token = readInt(buf);
        //玩家Id
        this.playerId = readInt(buf);
    }

    /**
     * get channel token
     * @return
     */
    public  int getToken() {
        return token;
    }

    /**
     * set channel token
     */
    public CSChannelOfflineMessage setToken(int token) {
        this.token=token;
        return this;
    }
    /**
     * get 玩家Id
     * @return
     */
    public  int getPlayerId() {
        return playerId;
    }

    /**
     * set 玩家Id
     */
    public CSChannelOfflineMessage setPlayerId(int playerId) {
        this.playerId=playerId;
        return this;
    }

    @Override
    public int getMessageId() {
        return 1103;
    }

    @Override
    public String toString() {
        return "CSChannelOfflineMessage{"
                +"token=" + token
                +",playerId=" + playerId
                + "}";
   }

    //最长字段长度 8
    private int filedPad = 8;

    @Override
    public String toString(String indent) {
        indent = indent == null ? "" : indent;
        StringBuilder sb = new StringBuilder();
        sb.append("CSChannelOfflineMessage").append("{");
        //channel token
        sb.append("\n");
        sb.append(indent).append(rightPad("token", filedPad)).append(" = ").append(token);
        //玩家Id
        sb.append("\n");
        sb.append(indent).append(rightPad("playerId", filedPad)).append(" = ").append(playerId);
        sb.append("\n");
        sb.append(indent).append("}");
        return sb.toString();
    }

}