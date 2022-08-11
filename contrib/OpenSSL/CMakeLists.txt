cmake_minimum_required(VERSION 3.22.1)

project(OpenSSL)

message("${PROJECT_NAME} SOURCE_DIR : ${PROJECT_SOURCE_DIR}")
message("${PROJECT_NAME}_BINARY_DIR : ${PROJECT_BINARY_DIR}")

include_directories(include)

file(GLOB INCLUDES include/*.h)
file(GLOB SOURCES src/*.cpp)

add_library(OpenSSL SHARED ${SOURCES} ${INCLUDES})
target_include_directories(OpenSSL PUBLIC include)