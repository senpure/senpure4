package com.senpure.io.bean;

import io.netty.buffer.ByteBuf;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;

/**
 * Created by 罗中正 on 2017/5/26.
 */
public abstract class Bean {
    public abstract void write(ByteBuf buf);

    public abstract void read(ByteBuf buf);

    public abstract String toString(String indent);

    protected void writeBoolean(ByteBuf buf, boolean value) {
        buf.writeByte(value ? 1 : 0);
    }

    protected void writeStr(ByteBuf buf, String str) {
        if (str == null) {
            buf.writeInt(0);
            return;
        }
        try {
            byte[] bytes = str.getBytes("utf-8");
            buf.writeInt(bytes.length);
            buf.writeBytes(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void writeInt(ByteBuf buf, int value) {


        buf.writeInt(value);
    }

    protected void writeByte(ByteBuf buf, byte value) {
        buf.writeByte(value);

    }

    protected void writeByte(ByteBuf buf, int value) {
        buf.writeByte(value);

    }

    protected void writeShort(ByteBuf buf, int value) {

        buf.writeShort(value);
    }

    protected void writeShort(ByteBuf buf, short value) {

        buf.writeShort(value);
    }

    protected void writeFloat(ByteBuf buf, float value) {
        buf.writeFloat(value);
    }

    protected void writeDouble(ByteBuf buf, double value) {
        buf.writeDouble(value);
    }

    protected void writeLong(ByteBuf buf, long value) {

        buf.writeLong(value);
    }

    protected void writeBean(ByteBuf buf, Bean bean, boolean check) {
        if (check) {
            writeBoolean(buf, bean != null);
        }
        if (bean != null) {
            bean.write(buf);
        }

    }

    protected boolean readBoolean(ByteBuf buf) {
        int i = buf.readByte();
        return i == 1;
    }

    protected String readStr(ByteBuf buf) {
        int length = buf.readInt();

        if (length <= 0) {
            return null;
        }
        byte[] bytes = new byte[length];
        buf.readBytes(bytes);
        try {
            return new String(bytes, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected int readInt(ByteBuf buf) {

        return buf.readInt();
    }

    protected short readShort(ByteBuf buf) {

        return buf.readShort();
    }

    protected byte readByte(ByteBuf buf) {

        return buf.readByte();
    }

    protected float readFloat(ByteBuf buf) {

        return buf.readFloat();
    }

    protected double readDouble(ByteBuf buf) {

        return buf.readDouble();
    }

    protected long readLong(ByteBuf buf) {

        return buf.readLong();
    }


    protected Bean readBean(ByteBuf buf, Class<? extends Bean> beanClass, boolean check) {
        check = check ? readBoolean(buf) : true;
        if (check) {
            try {
                Bean bean = beanClass.newInstance();
                bean.read(buf);
                return bean;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    protected String rightPad(String str, int pad) {
        return StringUtils.rightPad(str, pad);
    }
}
