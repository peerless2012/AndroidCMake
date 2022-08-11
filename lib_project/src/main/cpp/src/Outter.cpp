/**
* @Author peerless2012
* @Email peerless2012@126.com
* @DateTime 2022/8/1 15:35
* @Version V1.0
* @Description 
*/
#include "Outter.h"
#include <jni.h>

std::string Outter::getName() {
    return "Outter-" + Inner::getName();
}

extern "C" {

    JNIEXPORT jstring JNICALL
    Java_com_peerless2012_cmake_project_Project_getName(JNIEnv *env, jclass clazz) {
        std::string str = "--" + Outter::getName() + "--";
        return env->NewStringUTF(str.c_str());
    }

}
