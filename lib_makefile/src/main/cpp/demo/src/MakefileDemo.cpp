/**
 * @Author peerless2012
 * @Email peerless2012@126.com
 * @DateTime 2022/8/11 14:10
 * @Version V1.0
 * @Description:
 */

#include "Makefile.h"

extern "C"
JNIEXPORT jstring
JNICALL
Java_com_peerless2012_cmake_makefile_Makefile_getName(JNIEnv *env, jclass clazz) {
    return env->NewStringUTF(Makefile::getName().c_str());
}