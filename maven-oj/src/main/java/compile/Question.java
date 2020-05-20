package compile;

/**
 * 要编译执行的代码
 * @author zhaomin
 * @date 2020/5/17 22:44
 */
public class Question {
    //要编译和执行的代码内容
    private String code;
    //要执行时标准输入要输入的内容，实际后边没有用
    private String stdin;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStdin() {
        return stdin;
    }

    public void setStdin(String stdin) {
        this.stdin = stdin;
    }
}
