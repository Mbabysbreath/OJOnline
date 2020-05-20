package common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 这是一个简单的工具类，帮助我们更方便的读写文件
 * @author zhaomin
 * @date 2020/5/17 23:05
 */
public class FileUtil {
    //读文件：一下把整个文件内容都读写到String
    public static String readFile(String filePath){
        //当前涉及到的编译错误，标准输出结果等文件内容都是文本，使用字符流的方式实现
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader=new BufferedReader(fileReader)) {
                StringBuilder stringBuilder=new StringBuilder();
                //按行读取文件内容
            String line="";
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     *写文件：一下把整个String的内容都写到指定文件中
     * @param filePath 要把数据写到那个文件中
     * @param content 要写的文件内容
     */
    public static void writeFile(String filePath, String content) {
        try(FileWriter fileWriter=new FileWriter(filePath)){
                fileWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
