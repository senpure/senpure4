package com.senpure.io;


import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

public class ChannelAttributeUtil {
    private static String PLAYER_ID = "playerId";
    private static String PLAYER_NAME = "playerName";

    private static String OFFLINE_HANDLER = "offlineHandler";
    private static String CHANNEL_PLAYER = "channelPlayer";

    private static String IP_AND_PORT= "ipAndPort";
    public static AttributeKey<Integer> playerIdKey = AttributeKey.valueOf(PLAYER_ID);

    public static AttributeKey<String> playerNameKey = AttributeKey.valueOf(PLAYER_NAME);
    public static AttributeKey<String> ipAndPortKey = AttributeKey.valueOf(IP_AND_PORT);
    public static AttributeKey<ChannelPlayer> channelPlayerKey = AttributeKey.valueOf(CHANNEL_PLAYER);
    public static AttributeKey<OffLineHandler> offlineHandlerKey = AttributeKey.valueOf(OFFLINE_HANDLER);

    public static void clear(ChannelHandlerContext ctx, AttributeKey<?> key) {

        ctx.channel().attr(key).set(null);

    }

    public static void clear(Channel channel, AttributeKey<?> key) {

        channel.attr(key).set(null);
    }


    public static Integer getPlayerId(ChannelHandlerContext ctx) {
        if (ctx == null) {
            return null;
        }

        return ctx.channel().attr(playerIdKey).get();

    }

    public static void setPlayerId(Channel channel, Integer playerId) {

        channel.attr(playerIdKey).set(playerId);

    }

    public static void setPlayerId(ChannelHandlerContext ctx, Integer playerId) {

        ctx.channel().attr(playerIdKey).set(playerId);

    }

    public static void setPlayerName(Channel channel, String name) {

        channel.attr(playerNameKey).set(name);

    }

    public static Integer getPlayerId(Channel channel) {

        return channel.attr(playerIdKey).get();

    }

    public static String getPlayerName(Channel channel) {

        return channel.attr(playerNameKey).get();

    }



    public static String getIpAndPort(Channel channel) {

        return channel.attr(ipAndPortKey).get();

    }

    public static void setIpAndPort(Channel channel,String ipAndPort) {

     channel.attr(ipAndPortKey).set(ipAndPort);

    }
    public static void setOfflineHandler(Channel channel, OffLineHandler handler) {

        channel.attr(offlineHandlerKey).set(handler);

    }

    public static OffLineHandler getOfflineHandler(Channel channel) {

        return channel.attr(offlineHandlerKey).get();

    }

    public static void setChannelPlayer(Channel channel, ChannelPlayer player) {

        channel.attr(channelPlayerKey).set(player);
    }

    public static ChannelPlayer getChannelPlayer(Channel channel) {
        return channel.attr(channelPlayerKey).get();
    }

    private static void appenPlayerStr(Channel channel, StringBuilder sb) {
        ChannelPlayer player = ChannelAttributeUtil.getChannelPlayer(channel);
        if (player == null) {
            Integer playerId = ChannelAttributeUtil.getPlayerId(channel);
            String name = ChannelAttributeUtil.getPlayerName(channel);
            if (playerId != null) {
                sb.append("[ID][").append(playerId).append("]");
            }
            if (name != null) {
                sb.append("[NICK][").append(name).append("]");
            }
        } else {
            sb.append("[ID][").append(player.getIdStr()).append("]");
            sb.append("[NICK][").append(player.getNick()).append("]");
        }

    }

    public static String getChannelLogStr(Channel channel) {
        StringBuilder sb = new StringBuilder();
        sb.append(channel);
        appenPlayerStr(channel, sb);
        return sb.toString();
    }

    public static String getChannelPlayerStr(Channel channel) {
        StringBuilder sb = new StringBuilder();
        appenPlayerStr(channel, sb);
        return sb.toString();
    }

    public static <T> T get(ChannelHandlerContext ctx, AttributeKey<T> key) {
        return ctx.channel().attr(key).get();
    }

    public static <T> void set(Channel channel, AttributeKey<T> key, T value) {

        channel.attr(key).set(value);
    }

    public static <T> T get(Channel channel, AttributeKey<T> key) {

        return channel.attr(key).get();

    }

    public static <T> void set(ChannelHandlerContext ctx, AttributeKey<T> key, T value) {

        ctx.channel().attr(key).set(value);
    }
}
