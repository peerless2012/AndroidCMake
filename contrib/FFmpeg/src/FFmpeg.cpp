/**
* @Author peerless2012
* @Email peerless2012@126.com
* @DateTime 2022/8/1 15:35
* @Version V1.0
* @Description 
*/
#include "FFmpeg.h"
#include <jni.h>

std::string FFmpeg::getName() {
    return "--FFmpeg with " + OpenSSL::getName() + "--";
}