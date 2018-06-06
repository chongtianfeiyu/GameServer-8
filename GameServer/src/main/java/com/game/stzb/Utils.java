package com.game.stzb;

import java.io.ByteArrayOutputStream;

public class Utils {
    public static int byte2int(byte b) {
        return b & 0xff;
    }

    public static byte int2byte(int i) {
        return (byte) (i & 0xff);
    }

    public static byte[] getDefaultHeroCount() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        for (int i = 0; i < 500; i++) {
            outputStream.write(Utils.int2byte(0));
        }
        return outputStream.toByteArray();
    }

    public static byte[] setHeroCount(byte[] src, int position, int num) {
        if (num > 255) {
            num = 255;
        }
        if (position > 500) {
            position = 500;
        }
        src[position] = int2byte(num);
        return src;
    }

    public static int getHeroCount(byte[] src, int position) {
        if (position > 500) {
            position = 500;
        }
        return byte2int(src[position]);
    }

    public static byte[] updateHeroCount(byte[] src, int position) {
        if (position > 500) {
            position = 500;
        }
        int num = byte2int(src[position]);
        if (num == 255) {
            return src;
        }
        num++;
        src[position] = int2byte(num);
        return src;
    }
}
