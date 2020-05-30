package problem;

import common.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据访问层
 * @author zhaomin
 * @date 2020/5/30 21:43
 */
public class ProblemDAO {
    //获取所欲题目信息
    public List<Problem> selectAll(){
        List<Problem> result = new ArrayList<>();
        // 1. 获取数据库连接
        Connection connection = DBUtil.getConnection();
        // 2. 拼装 SQL 语句
        String sql = "select * from oj_table";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            // 3. 执行 SQL 语句
            resultSet = statement.executeQuery();
            // 4. 遍历结果集
            while (resultSet.next()) {
                Problem problem = new Problem();
                problem.setId(resultSet.getInt("id"));
                problem.setTitle(resultSet.getString("title"));
                problem.setLevel(resultSet.getString("level"));
                // 这几个字段暂时不需要.
//                problem.setDescription(resultSet.getString("description"));
//                problem.setTemplateCode(resultSet.getString("templateCode"));
//                problem.setTestCode(resultSet.getString("testCode"));
                result.add(problem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 5. 关闭释放资源
            DBUtil.close(connection, statement, resultSet);
        }
        return result;
    }

    //获取指定id的题目信息
    public Problem selectOne(int id){
        // 1. 建立数据库连接
        Connection connection = DBUtil.getConnection();
        // 2. 拼装 SQL
        String sql = "select * from oj_table where id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            // 3. 执行 SQL
            resultSet = statement.executeQuery();
            // 4. 遍历结果集
            if (resultSet.next()) {
                Problem problem = new Problem();
                problem.setId(resultSet.getInt("id"));
                problem.setTitle(resultSet.getString("title"));
                problem.setLevel(resultSet.getString("level"));
                problem.setDescription(resultSet.getString("description"));
                problem.setTemplateCode(resultSet.getString("templateCode"));
                problem.setTestCode(resultSet.getString("testCode"));
                return problem;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 5. 释放关闭资源
            DBUtil.close(connection, statement, resultSet);
        }
        return null;
    }

    //新增一个题目到数据库
    public void insert(Problem problem) {
        // 1. 获取数据库连接
        Connection connection = DBUtil.getConnection();
        if(connection==null){
            System.out.println("lianjie h");
            return ;
        }
        // 2. 拼装 SQL 语句
        String sql = "insert into oj_table values (null, ?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, problem.getTitle());
            statement.setString(2, problem.getLevel());
            statement.setString(3, problem.getDescription());
            statement.setString(4, problem.getTemplateCode());
            statement.setString(5, problem.getTestCode());
            System.out.println("insert: " + statement);
            // 3. 执行 SQL 语句
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 4. 关闭释放相关资源.
            DBUtil.close(connection, statement, null);
        }
    }

    //删除指定题目信息
    public void delete(int id){
        // 1. 获取连接
        Connection connection = DBUtil.getConnection();
        // 2. 拼装 SQL
        String sql = "delete from oj_table where id = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            // 3. 执行 SQL
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 4. 关闭并释放资源
            DBUtil.close(connection, statement, null);
        }
    }

    public static void main(String[] args) {
//        //1验证insert
//        Problem problem=new Problem();
//        problem.setTitle("各位相加");
//        problem.setLevel("简单");
//        problem.setDescription("给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。\n" +
//                "\n" +
//                "示例:\n" +
//                "\n" +
//                "输入: 38\n" +
//                "输出: 2 \n" +
//                "解释: 各位相加的过程为：3 + 8 = 11, 1 + 1 = 2。 由于 2 是一位数，所以返回 2。\n" +
//                "\n" +
//                "来源：力扣（LeetCode）\n" +
//                "链接：https://leetcode-cn.com/problems/add-digits\n" +
//                "著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。");
//        problem.setTemplateCode("class Solution {\n" +
//                "    public int addDigits(int num) {\n" +
//                "\n" +
//                "    }\n" +
//                "}");
//        problem.setTemplateCode("public class TestCode {\n" +
//                "    public static void main(String[] args) {\n" +
//                "        Solution s=new Solustion();\n" +
//                "        if(s.addDigits(38)==2){\n" +
//                "            System.out.println(\"test OK\");\n" +
//                "        }else{\n" +
//                "            System.out.println(\"test Failed\");\n" +
//                "        }\n" +
//                "        \n" +
//                "        if(s.addDigits(1)==1){\n" +
//                "            System.out.println(\"test OK\");\n" +
//                "        }else{\n" +
//                "            System.out.println(\"test Failed\");\n" +
//                "        }\n" +
//                "        \n" +
//                "    }\n" +
//                "    \n" +
//                "}");
//        ProblemDAO problemDAO=new ProblemDAO();
//        problemDAO.insert(problem);
//        System.out.println("insert ok");
        // 2. 测试 selectAll
        ProblemDAO problemDAO = new ProblemDAO();
        List<Problem> problems = problemDAO.selectAll();
        System.out.println("selectAll: " + problems);

        // 3. 测试 selectOne
//        ProblemDAO problemDAO = new ProblemDAO();
//        Problem problem = problemDAO.selectOne(1);
//        System.out.println("selectOne: " + problem);
    }
}
