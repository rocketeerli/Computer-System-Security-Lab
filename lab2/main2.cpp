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
    char *str_line = NULL;
    size_t len = 0;
    // ssize_t是signed size_t
    ssize_t read_line;
    long offset;
    char *p = NULL;
    int is_same_str;
    char buf[1000] = {0};

    // 打开可读写的文件，该文件必须存在。
    fp = fopen("aaa", "r+");
    // 得到文件位置指针当前位置相对于文件首的偏移字节数
    offset = ftell(fp);
    while((read_line = getline(&str_line, &len, fp)) != -1) 
    {
        // 判断字符串str2是否是str1的子串。如果是，则返回str2在str1中首次出现的地址；否则，返回NULL。
        p = strstr(str_line, ":");
        if(p == NULL)
        {
            continue;
        }
        // p-str_line 指定比较字符的个数
        is_same_str = strncmp(user, str_line, p-str_line);
        printf("%s %d\n", str_line, is_same_str);
        // 匹配成功，找到该用户的内容
        if(!is_same_str)
        {
            int index = 0;
            // 检测流上的文件结束符，如果文件结束，则返回非0值，否则返回0
            // 读取当前指针之后的所有文件内容
            while(!feof(fp)) 
            {
                buf[index++] = fgetc(fp); 
            }
            if(index > 0) 
            {
                buf[index - 1] = '\0';
            }
            // stream将指向以fromwhere为基准，偏移offset个字节的位置
            // 偏移起始位置：文件头0(SEEK_SET)
            // 将文件指针移到要修改的内容部分
            fseek(fp, offset, SEEK_SET);
            // 写入文件，覆盖掉之前的内容
            fprintf(fp, "%s:%s\n", user, userContext);
            if(index > 0) 
            {
                fprintf(fp, "%s", buf);
            }
            fclose(fp);
            return 0;
        }
        offset = ftell(fp);
    }
}

