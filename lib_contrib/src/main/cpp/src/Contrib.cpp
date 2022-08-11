/**
* @Author peerless2012
* @Email peerless2012@126.com
* @DateTime 2022/8/1 17:04
* @Version V1.0
* @Description 
*/

#include "Contrib.h"

extern "C" {

    JNIEXPORT jstring JNICALL
    Java_com_peerless2012_cmake_contrib_Contrib_getName(JNIEnv *env, jclass clazz) {
        return env->NewStringUTF(FFmpeg::getName().c_str());
    }

}

