#include<stdio.h>
#include<unistd.h>
int main()
{
    printf("执行了一个可执行文件\n");
    uid_t ruid, euid,suid;
    getresuid(&ruid, &euid, &suid);
    printf("------利用 exec 执行 setuid 程序后 uid :------ \n ruid = %d, euid = %d, suid = %d\n",
		    ruid, euid, suid);
}
