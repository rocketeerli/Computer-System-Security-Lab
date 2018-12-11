#include <stdio.h>
#include <cstring>
#include <pwd.h>
#include <unistd.h>
#include <sys/types.h>

// 改变文件中相应用户的内容
void changeFileContext(char *user, char *userContext);

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
            changeFileContext(user->pw_name, argv[1]);
	        break;
	    case 3 :
	        if(strcmp(user->pw_name, "root") == 0)
                changeFileContext(user->pw_name, argv[2]);
	            break;
	        break;
    }
    return 0;
}

// 改变文件中相应用户的内容
void changeFileContext(char *user, char *userContext)
{
    FILE *fp;
    char *str_line - NULL;
    size_t len = 0;
    // ssize_t是signed size_t
    ssize_t read_line;
    long offset;
    char *p = NULL;
    int is_same_str;
    char left[2048] = {0}

    // 打开可读写的文件，该文件必须存在。
    fp = fopen("aaa", "r+");
    // 得到文件位置指针当前位置相对于文件首的偏移字节数
    offset = ftell(fp);
    while((read_line = getline(&str_line, &len, fp))) 
    {
        p = strstr(str_line, ":");
        if(p == NULL)
        {
            continue;
        }
        is_same_str = strncmp(user, str_line, p-str_line);
        printf("%s %d\n", str_line, is_same_str);
        if(!is_same_str)
        {

        }
        offset = ftell(fp);
    }
}

