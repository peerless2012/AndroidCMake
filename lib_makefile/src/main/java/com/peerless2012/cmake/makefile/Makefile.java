package com.peerless2012.cmake.makefile;

/**
 * @Author peerless2012
 * @Email peerless2012@126.com
 * @DateTime 2022/8/11 14:07
 * @Version V1.0
 * @Description:
 */
public class Makefile {

    static {
        System.loadLibrary("MakefileDemo");
    }

    public static native String getName();

}
