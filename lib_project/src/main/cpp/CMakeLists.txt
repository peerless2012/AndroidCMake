cmake_minimum_required(VERSION 3.22.1)

project(Project)

add_subdirectory(inner)

include_directories(include)

file(GLOB INCLUDES include/*.h)
file(GLOB SOURCES src/*.cpp)

add_library(Project SHARED ${SOURCES} ${INCLUDES})
target_link_libraries(Project PUBLIC Inner)
target_include_directories(Project PUBLIC include)