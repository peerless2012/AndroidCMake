cmake_minimum_required(VERSION 3.22.1)

project(Makefile)

#message("Origin path = $ENV{PATH}")
#SET(ENV{PATH} "${ANDROID_TOOLCHAIN_ROOT}/bin:$ENV{PATH}")
#message("Current path = $ENV{PATH}")

cmake_path(SET ANDROID_TOOLCHAIN_PATH ${ANDROID_TOOLCHAIN_ROOT}/bin)
cmake_path(GET ANDROID_TOOLCHAIN_PATH ROOT_NAME ANDROID_TOOLCHAIN_PATH_ROOT)
# Win path if ROOT not empty
if(NOT ${ANDROID_TOOLCHAIN_PATH_ROOT} EQUAL "")
    cmake_path(GET ANDROID_TOOLCHAIN_PATH RELATIVE_PART ANDROID_TOOLCHAIN_PATH_RELATIVE)
    # remove : in path
    string(REPLACE ":" "" ANDROID_TOOLCHAIN_PATH_ROOT ${ANDROID_TOOLCHAIN_PATH_ROOT})
    # to lower case path
    string(TOLOWER ${ANDROID_TOOLCHAIN_PATH_ROOT} ANDROID_TOOLCHAIN_PATH_ROOT)
    # concat path
    string(APPEND MSYS_ANDROID_TOOLCHAIN_PATH "/" ${ANDROID_TOOLCHAIN_PATH_ROOT} "/" ${ANDROID_TOOLCHAIN_PATH_RELATIVE})
    # replace path
    cmake_path(SET ANDROID_TOOLCHAIN_PATH ${MSYS_ANDROID_TOOLCHAIN_PATH})
endif()
message("ANDROID_TOOLCHAIN_PATH = ${ANDROID_TOOLCHAIN_PATH}")

set(CFG_FLAGS)
set(C_FLAGS)
set(CXX_FLAGS)
set(LD_FLAGS)

# Android Arch
if(${CMAKE_ANDROID_ARCH_ABI} STREQUAL "armeabi-v7a")
    set(CROSS_PREFIX "armv7a-linux-androideabi")
    set(CFG_FLAGS " --arch=arm --cpu=cortex-a8")
elseif(${CMAKE_ANDROID_ARCH_ABI} STREQUAL "arm64-v8a")
    set(CROSS_PREFIX "aarch64-linux-android")
    set(CFG_FLAGS " --arch=aarch64")
elseif(${CMAKE_ANDROID_ARCH_ABI} STREQUAL "x86")
    set(CROSS_PREFIX "i686-linux-android")
    set(CFG_FLAGS " --arch=x86 --cpu=i686")
elseif(${CMAKE_ANDROID_ARCH_ABI} STREQUAL "x86_64")
    set(CROSS_PREFIX "x86_64-linux-android")
    set(CFG_FLAGS " --arch=x86_64")
else()
    message(FATAL_ERROR " unknown architecture ${CMAKE_ANDROID_ARCH_ABI}")
endif()
set(MAKE_PREFIX ${PROJECT_BINARY_DIR}/build)
set(TARGET_ARCH "${CROSS_PREFIX}${ANDROID_NATIVE_API_LEVEL}")

set(CFG_FLAGS "${CFG_FLAGS} --prefix=${MAKE_PREFIX}")
set(CFG_FLAGS "${CFG_FLAGS} --cross-prefix=${CROSS_PREFIX}-")
set(CFG_FLAGS "${CFG_FLAGS} --enable-cross-compile")
set(CFG_FLAGS "${CFG_FLAGS} --target-os=${CMAKE_SYSTEM_NAME}")
set(CFG_FLAGS "${CFG_FLAGS} --cc=${ANDROID_C_COMPILER}")
set(CFG_FLAGS "${CFG_FLAGS} --cxx=${ANDROID_CXX_COMPILER}")
set(C_FLAGS "$(PRE_CFLAGS) -Os -fpic -I${PROJECT_BINARY_DIR} --target=${TARGET_ARCH}")
set(CXX_FLAGS "$(PRE_CXXFLAGS) -I${PROJECT_BINARY_DIR} --target=${TARGET_ARCH}")
set(LD_FLAGS "-lc -ldl -lm -lz -lgcc -I${PROJECT_BINARY_DIR} --target=${TARGET_ARCH}")

# BuildType
if( NOT CMAKE_BUILD_TYPE)
    set( CMAKE_BUILD_TYPE Release)
endif( NOT CMAKE_BUILD_TYPE)

if(CMAKE_BUILD_TYPE STREQUAL "Debug")
    set(CFG_FLAGS "${CFG_FLAGS} --enable-debug")
else()
    set(CFG_FLAGS "${CFG_FLAGS} --disable-debug")
endif()

set(CONFIGURE_COMMAND sh -c "${CMAKE_CURRENT_SOURCE_DIR}/configure ${CFG_FLAGS} --extra-cflags='${C_FLAGS}' --extra-cxxflags='${CXX_FLAGS}' --extra-ldflags='${LD_FLAGS}'")
message("CONFIGURE_COMMAND = ${CONFIGURE_COMMAND}")

execute_process(
        COMMAND ${CONFIGURE_COMMAND}
        WORKING_DIRECTORY ${PROJECT_BINARY_DIR}
        RESULT_VARIABLE LS_DESCRIBE
        OUTPUT_VARIABLE LS_DESCRIBE
        ERROR_VARIABLE LS_DESCRIBE
)
if(NOT EXISTS "${PROJECT_BINARY_DIR}/Makefile")
    file(WRITE ${PROJECT_BINARY_DIR}/Makefile "include ${CMAKE_CURRENT_SOURCE_DIR}/Makefile")
#    TODO Check why the Makefile is created when sysc， and not create when run.
    message(WARNING "The Makefile link not generated, so wired!")
else()
    message(WARNING "The Makefile already exist!")
endif()
message("Configure result = ${LS_DESCRIBE}")

# https://cmake.org/cmake/help/latest/module/ProcessorCount.html
include(ProcessorCount)
ProcessorCount(HOST_NPROC)
if(${HOST_NPROC} EQUAL 0)
    message(FATAL_ERROR "Invalidate processor count ${HOST_NPROC}.")
endif()
message("CMake processor count is ${HOST_NPROC}.")

add_custom_command(
        OUTPUT MakeAndInstall
        COMMAND sh -c "export PATH=${ANDROID_TOOLCHAIN_PATH}:$PATH && make -j${HOST_NPROC} && make install"
        BYPRODUCTS ${MAKE_PREFIX}/include ${MAKE_PREFIX}/lib
        WORKING_DIRECTORY ${PROJECT_BINARY_DIR}
        VERBATIM
)

## https://cmake.org/cmake/help/latest/command/add_custom_target.html
add_custom_target(
        Makefile_static ALL
        DEPENDS MakeAndInstall
        WORKING_DIRECTORY ${PROJECT_BINARY_DIR}
        VERBATIM
)

include_directories(include)
include_directories(${PROJECT_BINARY_DIR})

file(GLOB INCLUDES ./*.h ${PROJECT_BINARY_DIR}/include/*.h)
file(GLOB SOURCES src/*.cpp)

add_library(Makefile SHARED ${INCLUDES})
add_dependencies(Makefile Makefile_static)
set_target_properties(Makefile PROPERTIES LINKER_LANGUAGE CXX)
set_property(DIRECTORY APPEND PROPERTY ADDITIONAL_CLEAN_FILES ${MAKE_PREFIX})
target_include_directories(Makefile PUBLIC ${MAKE_PREFIX}/include)
target_link_directories(Makefile PUBLIC ${MAKE_PREFIX}/lib)
target_link_libraries(Makefile
        "-Wl,--whole-archive" #之后的库使用--whole-archive选项
        makefile
        "-Wl,--no-whole-archive" #之后的库不使用--whole-archive选项
)
