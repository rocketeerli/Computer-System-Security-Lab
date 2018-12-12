#include <stdio.h>
#include <unistd.h>
#include <sys/time.h>
#include <sys/wait.h>

int main()
{
    if(fork() == 0)
    {
        printf("当前系统时间：\n");
	execlp("date", "date", NULL);
    }
    else
    {
	wait(NULL);
        printf("修改后的系统时间：\n");
	execlp("date", "date", "-s", "2020-6-7", NULL);
    }
    wait(NULL);
    return 0;
}
