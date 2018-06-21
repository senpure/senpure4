package com.senpure.io;

import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 罗中正 on 2018/3/2 0002.
 */
public class GatewayComponentChannelServer {

    private ConcurrentMap<Integer, Channel> serverChannels = new ConcurrentHashMap<>();
    private List<Channel> channels = new ArrayList<>(16);

    private AtomicInteger atomicIndex = new AtomicInteger(-1);

    private Set<Integer> handleIds = new HashSet<>();

    private long start;
    private long end;


    private String serverKey;


    public boolean handleRange(long num) {
        return num >= start && num <= end;
    }

    public void addChannel(Channel channel) {
        channels.add(channel);
    }


    public boolean handle(long value) {
        return value >= start && value <= end;
    }

    public String getServerKey() {
        return serverKey;
    }

    public void setServerKey(String serverKey) {
        this.serverKey = serverKey;
    }

    public boolean handleMessageId(int messageId) {
        return handleIds.contains(messageId);

    }

    public void markMessageId(int messageId) {
        handleIds.add(messageId);

    }

    public Channel nextChannel() {

        return channels.get(nextIndex());
    }

    private int nextIndex() {
        int index = atomicIndex.incrementAndGet();
        if (index >= channels.size()) {
            boolean reset = atomicIndex.compareAndSet(index, 0);
            if (!reset) {
                return nextIndex();
            }
            return 0;
        }
        return index;
    }


    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public static void main(String[] args) {
        ConcurrentMap<Integer, Integer> ids = new ConcurrentHashMap<>();


        System.out.println(ids.putIfAbsent(1, 1));
        System.out.println(ids.putIfAbsent(1, 2));
        System.out.println(ids.putIfAbsent(1, 3));
    }
}
