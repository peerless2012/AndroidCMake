include config.mak

# 头文件
HEADER := $(wildcard $(SRC_PATH)/*.h)
# 源码（绝对路径）
SOURCE := $(wildcard $(SRC_PATH)/*.cpp)
# 源码（相对路径）
SOURCE := $(foreach var,$(SOURCE),$(subst $(SRC_PATH),.,$(var)))
# obj文件
OBJ := $(SOURCE:%.cpp=%.o)

# 设置某种类型文件的查找路径
vpath %.h $(SRC_PATH)
vpath %.c $(SRC_PATH)
vpath %.cpp $(SRC_PATH)
vpath %.o $(DEST_PATH)

TARGET = makefile
TARGET_FILE = lib$(TARGET).a

.PHONY: all install clean help

# make（缺省）
all: $(OBJ)
	$(LD) -r $^ -o $(TARGET_FILE)

# 模式匹配
%.o: %.cpp
	$(CXX) $(CPPFLAGS) -c $^ -o $@

# install
install:
	-mkdir -p -v $(LIBDIR)
	mv $(TARGET_FILE) $(LIBDIR)/$(TARGET_FILE)
	-mkdir -p -v $(INCDIR)
	cp config.h $(INCDIR)/config.h
	cp $(HEADER) $(INCDIR)

# clean
clean:
	-$(RM) $(INCDIR) $(LIBDIR) *.a *.o

help:
	@echo $(CXX)
	@echo $(LD)
	@echo $(TARGET)
	@echo $(TARGET_FILE)
	@echo $(SRC_PATH)
	@echo $(DEST_PATH)
	@echo $(INCDIR)
	@echo $(LIBDIR)
	@echo $(HEADER)
	@echo $(SOURCE)
	@echo $(OBJ)

