package com.senpure.io.message;

import java.util.Arrays;

/**
 * Created by 罗中正 on 2018/2/28 0028.
 */
public class Server2GatewayMessage {

    private Integer[] playerIds;

    private int token;
    private int messageId;
    //具体服务器上有值
    private Message message;
    //网关解析出来有值
    private byte[] data;


    public Integer[] getPlayerIds() {
        return playerIds;
    }

    public void setPlayerIds(Integer[] playerIds) {
        this.playerIds = playerIds;
    }


    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Server2GatewayMessage{" +
                "playerIds=" + Arrays.toString(playerIds) +
                ", token=" + token +
                ", messageId=" + messageId +
                ", data=" + data==null?"":data.length+""+
                '}';
    }
}
