#include<sys/types.h>
#include<sys/socket.h>
#include<unistd.h>
#include<stdio.h>

#pragma comment(lib, "Ws2_32.lib")

int main(int argc, char *argv[])
{
    // 三个 id 分别对应了实际用户ID，有效用户ID，保存的用户ID
    uid_t ruid, euid,suid;
    getresuid(&ruid, &euid, &suid);
    printf("beginning uid : \n ruid = %d, euid = %d, suid = %d\n",
		    ruid, euid, suid);
    int server_socket = socket(AF_INET, SOCK_STREAM, 0);
    if(server_socket < 0) 
    {
        printf("erro \n");
    }
    printf("socket_id = %d\n", server_socket);
    return 0;
}
