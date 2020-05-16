package compile;

import java.io.IOException;

/**
 * 借助这个类，让java代码能够去执行一个具体的指令
 * 例如：javac Test.java
 * @author zhaomin
 * @date 2020/5/16 17:30
 */
public class CommandUtil {
    public static void run(String cmd) throws IOException {
        //1.获取Runtime对象.Runtime 是一个单例模式
        Runtime runtime=Runtime.getRuntime();
        //2.通过Runtime对象中的exec方法来执行一个指令
        runtime.exec(cmd);
    }
    public static void main(String[] args) throws IOException {
        CommandUtil.run("javac");
    }
}
