#include<sys/types.h>
#include<sys/socket.h>
#include<unistd.h>
#include<stdio.h>
#include<netinet/in.h>        //sockaddr_in 库
#include<arpa/inet.h>         // inet_addr() 函数 库
#include<cstring>

void abandonRootTemporary(uid_t uid_tran);
void abandonRootPermanent(uid_t uid_tran);

int main(int argc, char *argv[])
{
    // 三个 id 分别对应了实际用户ID，有效用户ID，保存的用户ID
    uid_t ruid, euid,suid;
    getresuid(&ruid, &euid, &suid);
    printf("------开始的 uid :------ \n ruid = %d, euid = %d, suid = %d\n",
		    ruid, euid, suid);
    // 1. 提供 http 网络服务，需要设置 setuid 位, 否则会失败
    printf("------1.提供 http 网络服务------\n");
    int server_socket = socket(AF_INET, SOCK_STREAM, 0);
    if(server_socket < 0) 
    {
        printf("erro \n");
    }
    // bind 绑定
    struct sockaddr_in server_sockaddr;
    memset(&server_sockaddr, 0, sizeof(server_sockaddr));
    server_sockaddr.sin_family = AF_INET;
    server_sockaddr.sin_port = htons(80);
    server_sockaddr.sin_addr.s_addr = inet_addr("127.0.0.1");
    int is_bind = bind(server_socket, (struct sockaddr *)&server_sockaddr,
		    sizeof(server_sockaddr));
    if (is_bind < 0)
    {
        printf("bind error \n");
    }
    getresuid(&ruid, &euid, &suid);
    printf("------bind 后的 uid :------ \n ruid = %d, euid = %d, suid = %d\n",
                    ruid, euid, suid);
    
    // 2. 用户fork进程后，父进程和子进程中euid、ruid、suid的差别
    if(fork() == 0)  
    {  
        getresuid(&ruid, &euid, &suid);
        printf("------子进程 uid:------\n ruid = %d, euid = %d, suid = %d\n",
                ruid, euid, suid);
    }
    else  
    {   
        getresuid(&ruid, &euid, &suid);
        printf("------父进程 uid:------\n ruid = %d, euid = %d, suid = %d\n",
                ruid, euid, suid);
        
        // 3. 利用 execl 执行 setuid 程序后，euid、ruid、suid是否有变化
        if(fork() == 0) 
	{
	    execl("/bin/ping", "ping", "-c", "3", "178.128.63.64",(char *)0);
            getresuid(&ruid, &euid, &suid);
            printf("------ 3.利用 execl 执行 setuid 程序后 :------ \n ruid = %d, euid = %d, suid = %d\n",
                    ruid, euid, suid);
	}

        // 4.两种放弃 root 权限的方式
        abandonRootTemporary(1001);  // 临时性放弃root权限
        abandonRootPermanent(1001);  // 永久性放弃root权限

        // 5. 比较有环境变量和无环境变量的函数使用的差异。
	if(fork() == 0) 
	{
	    // 5.1 有环境变量的函数使用
            execlp("ping", "ping", "-c", "3", "178.128.63.64", (char *)0);
            printf("------ 5.1 有环境变量的函数使用 :------ \n ruid = %d, euid = %d, suid = %d\n",
                        ruid, euid, suid);
            // 5.2 无环境变量的函数使用
            execl("/bin/ping", "ping", "-c", "3", "178.128.63.64", (char *)0);
            getresuid(&ruid, &euid, &suid);
            printf("------ 5.2 无环境变量的函数使用 :------ \n ruid = %d, euid = %d, suid = %d\n",
                        ruid, euid, suid);
	}
    }
    return 0;
}

// 4.1 程序临时性放弃root权限
void abandonRootTemporary(uid_t uid_tran)
{
    uid_t ruid, euid,suid;
    getresuid(&ruid, &euid, &suid);
    if (euid == 0) 
    {
        // 临时性放弃root权限
        int is_seteuid = seteuid(uid_tran);
        getresuid(&ruid, &euid, &suid);
        if(euid > 0)
        {
            printf("------ 4.1 临时性放弃root权限成功 ------\n");
        }
        else
        {
            printf("------ 4.1 临时性放弃root权限失败 ------\n");
        }
        printf("ruid = %d, euid = %d, suid = %d\n", ruid, euid, suid);
    }
    else
    {
        printf("4.1 无 root 权限, 无法放弃root权限\n");
    }
}

// 4.2永久性放弃root权限
void abandonRootPermanent(uid_t uid_tran)
{
    uid_t ruid, euid,suid;
    getresuid(&ruid, &euid, &suid);
    if (euid != 0 && (ruid == 0 || suid == 0))
    {
        setuid(0);
	getresuid(&ruid, &euid, &suid);
    }
    if (euid == 0) 
    {
        // 永久性放弃root权限
        setuid(uid_tran);
        getresuid(&ruid, &euid, &suid);
        if(ruid > 0 && euid > 0 && suid > 0)
        {
            printf("------ 4.2 永久性放弃root权限成功 ------\n");
        }
        else
        {
            printf("------ 4.2 永久性放弃root权限失败 ------\n");
        }
        printf("ruid = %d, euid = %d, suid = %d\n", ruid, euid, suid);
    }
    else
    {
        printf("4.2 无 root 权限, 无法放弃root权限\n");
    }
}
