# 实验内容

## 设想一种场景需要进行普通用户和root用户切换，设计程序实现euid的安全管理

* 设想一种场景，比如提供http网络服务，需要设置setuid位，并为该场景编制相应的代码

* 如果用户fork进程后，父进程和子进程中 euid、 ruid、 suid 的差别

* 利用execl执行setuid程序后， euid、 ruid、 suid 是否有变化

* 程序何时需要临时性放弃root权限，何时需要永久性放弃root权限，并在程序中分别实现两种放弃权限方法

* execl函数族中有多个函数，比较有环境变量和无环境变量的函数使用的差异

## 搭建安全的沙盒环境，在沙盒环境中提供必须的常见工具，并提供程序验证沙盒环境的安全性

Ubuntu 下搭建 ftp 请参考 [ftpd chroot的实现](https://blog.csdn.net/hit_fantasy/article/details/17960829?fps=1&locationnum=7)

# 执行过程

首先，将可执行文件的源代码运行一下：

	g++ -o a a.cpp
	
然后，编译 main.cpp ：

	g++ -o main main.cpp
	
然后，普通用户执行 main 主程序：

	./main
	
最后，root 用户执行 main 主程序：

	sudo ./main

# 注意事项

* 由于 execl() 函数在执行后，自动退出进程，即使在子进程中执行，也检测不出来 euid、 ruid、 suid 的变化。因此，需要在可执行文件中，检测 euid、 ruid、 suid 的变化

* 在设置放弃 root 权限的时候，一定不要使用 setuid() 函数，这个函数是有歧义的，在不同的操作系统中是不一样的，要使用 setresuid()，上课讲过。（因为这个错误，我被老师严厉训斥了一顿）