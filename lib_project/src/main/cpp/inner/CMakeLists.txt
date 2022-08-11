cmake_minimum_required(VERSION 3.22.1)

project(Inner)

#include(cmake/android.toolchain.ext.cmake)

include_directories(include)

file(GLOB INCLUDES include/*.h)
file(GLOB SOURCES src/*.cpp)

add_library(Inner SHARED ${SOURCES} ${INCLUDES})
target_include_directories(Inner PUBLIC include)