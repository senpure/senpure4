package com.senpure.io;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by 罗中正 on 2018/3/1 0001.
 */
@ConfigurationProperties("game")
public class IOServerProperties {

    private boolean ssl = false;
    private int port = 1111;
    private String host = "127.0.0.1";
    private boolean inFormat = false;
    private boolean outFormat = false;

    private boolean csInFormat = false;
    private boolean csOutFormat = false;
    private boolean scInFormat = false;
    private boolean scOutFormat = false;
    private int csPort = 2222;
    private int scPort = 3333;

    private String gatewayAddress = "127.0.0.1:3333";

    public boolean isSsl() {
        return ssl;
    }

    public void setSsl(boolean ssl) {
        this.ssl = ssl;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

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

    public boolean isCsInFormat() {
        return csInFormat;
    }

    public void setCsInFormat(boolean csInFormat) {
        this.csInFormat = csInFormat;
    }

    public boolean isCsOutFormat() {
        return csOutFormat;
    }

    public void setCsOutFormat(boolean csOutFormat) {
        this.csOutFormat = csOutFormat;
    }

    public boolean isScInFormat() {
        return scInFormat;
    }

    public void setScInFormat(boolean scInFormat) {
        this.scInFormat = scInFormat;
    }

    public boolean isScOutFormat() {
        return scOutFormat;
    }

    public void setScOutFormat(boolean scOutFormat) {
        this.scOutFormat = scOutFormat;
    }

    public int getCsPort() {
        return csPort;
    }

    public void setCsPort(int csPort) {
        this.csPort = csPort;
    }

    public int getScPort() {
        return scPort;
    }

    public void setScPort(int scPort) {
        this.scPort = scPort;
    }

    public String getGatewayAddress() {
        return gatewayAddress;
    }

    public void setGatewayAddress(String gatewayAddress) {
        this.gatewayAddress = gatewayAddress;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
