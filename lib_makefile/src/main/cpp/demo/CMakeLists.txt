cmake_minimum_required(VERSION 3.22.1)

project(MakefileDemo)

include_directories(include)

file(GLOB INCLUDES include/*.h)
file(GLOB SOURCES src/*.cpp)

add_library(MakefileDemo SHARED ${SOURCES} ${INCLUDES})
target_include_directories(MakefileDemo PUBLIC include)
target_link_libraries(MakefileDemo Makefile)
