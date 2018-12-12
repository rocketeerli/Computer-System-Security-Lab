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
                changeFileContext(argv[1], argv[2]);
	            break;
	        break;
    }
    return 0;
}

// 改变文件中相应用户的内容
void changeFileContext(char *user, char *userContext)
{
    FILE *fp;
    char *str_line = NULL;
    size_t len = 0;
    // ssize_t是signed size_t
    ssize_t read_line;
    long offset;
    char *p = NULL;
    int is_same_str;
    char buf_after[1000] = {0};
    char buf_before[100][100];

    // 打开可读写的文件，该文件必须存在。
    fp = fopen("aaa", "r+");
    // 得到文件位置指针当前位置相对于文件首的偏移字节数
    offset = ftell(fp);
    int i = 0;
    while((read_line = getline(&str_line, &len, fp)) != -1) 
    {
        // 存储文件内容
        strcpy(buf_before[i], str_line);
        // 判断字符串str2是否是str1的子串。如果是，则返回str2在str1中首次出现的地址；否则，返回NULL。
        p = strstr(str_line, ":");
        if(p == NULL)
        {
            continue;
        }
        // p-str_line 指定比较字符的个数
        is_same_str = strncmp(user, str_line, p-str_line);
        // 匹配成功，找到该用户的内容
        if(!is_same_str)
        {
            int index = 0;
            // 检测流上的文件结束符，如果文件结束，则返回非0值，否则返回0
            // 读取当前指针之后的所有文件内容
            while(!feof(fp)) 
            {
                buf_after[index++] = fgetc(fp);
            }
            if(index > 0) 
            {
                buf_after[index - 1] = '\0';
            }       
            fclose(fp);
	        break;
        }
        offset = ftell(fp);
        i++;
    }
    // 重新写入文件
    fp = fopen("aaa", "w+");
    for (int j = 0; j < i; j++)
    {
        fprintf(fp, "%s", buf_before[j]);
    }
    fprintf(fp, "%s:%s\n", user, userContext);
    fprintf(fp, "%s", buf_after);
    printf("已经将用户 %s 的内容更改为 %s \n", user, userContext);
}

