# Makefile

## Execute sh command

#### Linux
use `sh -c "comman1 arg1 && command2 arg2"` directly.

#### Windows
* 1 Download MSYS and install it(by default, it will install to C:\wsys64).
* 2 Add `C:\wsys64\usr\bin` to Windows environment for `shell` etc.
* 3 Add `C:\msys64\mingw64\bin` to Windows environment for `gcc`、`g++`...
* 4use `sh -c "comman1 arg1 && command2 arg2"` directly.

## Reference
* [Makefile-Templates](https://github.com/TheNetAdmin/Makefile-Templates.git)
* [Autoconf](https://www.gnu.org/savannah-checkouts/gnu/autoconf/manual/autoconf-2.71/html_node/index.html)
* [How Configuration Should Work](https://www.gnu.org/prep/standards/html_node/Configuration.html)
* [ 跟我一起写Makefile](https://seisman.github.io/how-to-write-makefile/overview.html)

```
 sh -c "D:/Develop/Git/Self/AndroidCMake/lib_makefile/src/main/cpp/configure --cc=D:/Develop/android-sdk-windows/ndk/21.4.7075529/toolchains/llvm/prebuilt/windows-x86_64/bin/clang.exe --cxx=D:/Develop/android-sdk-windows/ndk/21.4.7075529/toolchains/llvm/prebuilt/windows-x86_64/bin/clang++.exe --debug --prefix=D:/Develop/Git/Self/AndroidCMake/lib_makefile/.cxx/Debug/691yb2z4/x86"
```