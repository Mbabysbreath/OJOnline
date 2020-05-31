package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import problem.Problem;
import problem.ProblemDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author zhaomin
 * @date 2020/5/31 20:13
 */
public class PronlemServlet extends HttpServlet {
    private Gson gson=new GsonBuilder().create();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null || "".equals(id)) {
            //1.没有id这个参数，就执行查找全部题目      }
            selectAll(resp);
        } else {
            //2.存在id参数，执行查找指定题目信息
            selectOne(Integer.parseInt(id), resp);
        }
    }

    private void selectOne(int i, HttpServletResponse resp) {
    }

    private void selectAll(HttpServletResponse resp) throws IOException {
        ProblemDAO problemDAO = new ProblemDAO();
        List<Problem> problems = problemDAO.selectAll();
        //把结果组织成json结构
        //【注意】需要把problem中的有序字段注释掉
        String jsonString = gson.toJson(problems);
        resp.getWriter().write(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}