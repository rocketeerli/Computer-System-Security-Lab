#include<sys/types.h>
#include<sys/socket.h>
#include<unistd.h>
#include<stdio.h>
#include<netinet/in.h>        //sockaddr_in 库
#include<arpa/inet.h>         // inet_addr() 函数 库

#pragma comment(lib, "Ws2_32.lib")

int main(int argc, char *argv[])
{
    // 三个 id 分别对应了实际用户ID，有效用户ID，保存的用户ID
    uid_t ruid, euid,suid;
    getresuid(&ruid, &euid, &suid);
    printf("------beginning uid :------ \n ruid = %d, euid = %d, suid = %d\n",
		    ruid, euid, suid);
    int server_socket = socket(AF_INET, SOCK_STREAM, 0);
    if(server_socket < 0) 
    {
        printf("erro \n");
    }
    getresuid(&ruid, &euid, &suid);
    printf("------after socket uid :------ \n ruid = %d, euid = %d, suid = %d\n",
		    ruid, euid, suid);
    struct sockaddr_in server_sockaddr;
    server_sockaddr.sin_family = AF_INET;
    server_sockaddr.sin_port = htons(8080);
    server_sockaddr.sin_addr.s_addr = inet_addr("127.0.0.1");
    //非 root 用户进行连接
    int is_connect = connect(server_socket, (struct sockaddr *)&server_sockaddr,
		    sizeof(server_sockaddr));
    if (is_connect < 0)
    {
        printf("connect erro \n");
    }
    getresuid(&ruid, &euid, &suid);
    printf("------after rocketeerli connect uid :------ \n ruid = %d, euid = %d, suid = %d\n",
                    ruid, euid, suid);
    int is_lgj = setuid(1001);
    if(is_lgj == -1) 
    {
        printf("change lgj erro\n");
    }
    //设置成root 再连接
    int is_root = seteuid(0);
    if(is_root == -1) 
    {
        printf("root erro\n");
    }
    printf("------after set root uid :------ \n ruid = %d, euid = %d, suid = %d\n",
                    ruid, euid, suid);
    is_connect = connect(server_socket, (struct sockaddr *)&server_sockaddr,
                    sizeof(server_sockaddr));
    if (is_connect < 0)
    {
        printf("connect erro \n");
    }
    printf("------after connect uid :------ \n ruid = %d, euid = %d, suid = %d\n",
                    ruid, euid, suid);
    printf("socket_id = %d\n", server_socket);
    return 0;
}
