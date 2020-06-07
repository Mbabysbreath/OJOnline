package compile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 借助这个类，让java代码能够去执行一个具体的指令
 * 例如：javac Test.java
 * @author zhaomin
 * @date 2020/5/16 17:30
 */
public class CommandUtil {
    //cmd:表示要执行的命令
    // stdoutFile:表示输出结果重定向到哪个文件中，如果为null表示不需要重定向
    //stderrFile:标准错误重定向到哪个文件
    public static int run(String cmd, String stdoutFile,
                           String stderrFile) throws IOException, InterruptedException {

        //1.获取Runtime对象.Runtime 是一个单例模式
        Runtime runtime = Runtime.getRuntime();
        //2.通过Runtime对象中的exec方法来执行一个指令
        //exec的作用：创建一个子进程、替换程序
        //3.exec返回一个process进程
        Process process = runtime.exec(cmd);
        if (stdoutFile != null) {
            //针对标准输出进行重定向
            //4.进程的标准输出中的结果可以通过这个InputSream获得
            InputStream stdoutFrom = process.getInputStream();
            OutputStream stdoutTo = new FileOutputStream(stdoutFile);
            int ch1 = -1;
            while ((ch1 = stdoutFrom.read()) != -1) {
                stdoutTo.write(ch1);
            }
            //流关闭
            stdoutFrom.close();
            stdoutTo.close();
        }
        //5.针对标准错误也进行重定向

        if (stderrFile != null) {
            InputStream stderrFrom = process.getErrorStream();
            OutputStream stderrTo=new FileOutputStream(stderrFile);
            int ch2=-1;
            while ((ch2 = stderrFrom.read()) != -1) {
                stderrTo.write(ch2);
            }
            stderrFrom.close();
            stderrTo.close();
        }

        //6.为了确保子进程先执行完，需要加上进程等待
        // 父进程会在waitFor阻塞等待，直到子进程执行结束在继续往下执行
        // exitCode 进程的退出码 0--执行成功，非0--失败
        int exitCode = process.waitFor();
        return exitCode;
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        CommandUtil.run("javac", "D:\\OJOnline\\maven-oj\\File\\stdoutFile.txt","D:\\OJOnline\\maven-oj\\File\\stderrorFile.txt");
    }
}
