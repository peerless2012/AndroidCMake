/**
 * @Author peerless2012
 * @Email peerless2012@126.com
 * @DateTime 2022/8/2 11:54
 * @Version V1.0
 * @Description: 
 */

#include "Makefile.h"

std::string Makefile::getName() {
#ifdef DEBUG
    return "Makefile with debug!";
#else
    return "Makefile with release";
#endif
}