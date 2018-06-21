package com.senpure.io.message;

/**
 * Created by 罗中正 on 2018/3/21 0021.
 */
public class AskMessage {

    private Client2GatewayMessage message;
    private long token;
    private long askTime;

    public Client2GatewayMessage getMessage() {
        return message;
    }

    public void setMessage(Client2GatewayMessage message) {
        this.message = message;
    }

    public long getToken() {
        return token;
    }

    public void setToken(long token) {
        this.token = token;
    }

    public long getAskTime() {
        return askTime;
    }

    public void setAskTime(long askTime) {
        this.askTime = askTime;
    }
}
