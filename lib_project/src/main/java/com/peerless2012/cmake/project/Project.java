package com.peerless2012.cmake.project;

/**
 * @Author peerless2012
 * @Email peerless2012@126.com
 * @DateTime 2022/8/2 10:57
 * @Version V1.0
 * @Description:
 */
public class Project {

    static {
        System.loadLibrary("Project");
    }

    public static native String getName();

}
