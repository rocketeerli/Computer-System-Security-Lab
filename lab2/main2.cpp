#include <stdio.h>
#include <cstring>
#include <pwd.h>
#include <unistd.h>
#include <sys/types.h>

int main(int argc, char **argv) 
{
    uid_t ruid, euid, suid;
    struct passwd *user;
    getresuid(&ruid, &euid, &suid);
    user = getpwuid(ruid);

    printf("当前用户为：%s\n", user->pw_name);
    switch(argc)
    {
        case 2 :
	    break;
	case 3 :
	    if(strcmp(user->pw_name, "root") == 0)
	        break;
	    break;
    }
    return 0;
}

void changeFileContext()
{
}

