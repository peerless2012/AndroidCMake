cmake_minimum_required(VERSION 3.22.1)

# https://stackoverflow.com/questions/35260552/how-do-i-explicitly-specify-an-out-of-tree-source-in-cmake
add_subdirectory(../../../../contrib/OpenSSL OpenSSL)
add_subdirectory(../../../../contrib/FFmpeg FFmpeg)

project(Contrib)

include_directories(include)

file(GLOB INCLUDES include/*.h)
file(GLOB SOURCES src/*.cpp)

add_library(Contrib SHARED ${SOURCES} ${INCLUDES})
target_link_libraries(Contrib FFmpeg)
target_include_directories(Contrib PUBLIC include)
