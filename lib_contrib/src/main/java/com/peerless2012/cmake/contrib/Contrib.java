package com.peerless2012.cmake.contrib;

/**
 * @Author peerless2012
 * @Email peerless2012@126.com
 * @DateTime 2022/8/1 15:27
 * @Version V1.0
 * @Description
 */
public class Contrib {

    static {
        System.loadLibrary("Contrib");
    }

    public static native String getName();

}
