package bank.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Log {
	public static FileOutputStream output;
	public Log() {
		// 打开日志
		try {
			File f = new File("log.txt");
            if (f.exists()) {  
                System.out.print("文件存在");  
            } else {  
                System.out.print("文件不存在");  
            }
            FileOutputStream output = new FileOutputStream(f, true);
            Log.output = output;
		} catch (IOException e) {
			System.out.println("打开日志文件失败");
		}
	}
}
