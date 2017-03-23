package servlet;

import org.apache.log4j.Logger;
import utils.ResponseJsonUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 最简单的Servlet
 *
 * @author Winter Lau
 */
public class HelloServlet extends HttpServlet {
    Logger logger = Logger.getLogger(HelloServlet.class.getName());

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        logger.debug("HelloServlet Get data" );
        request.getSession().setAttribute("customers", "小明");
        request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Map<String, Object> data = new HashMap<>();
        data.put("email", "770882203@qq.com");
        data.put("age", 18);
        data.put("name", "csdn");
        data.put("array", new int[]{1, 2, 3, 4});
        ResponseJsonUtils.json(response, data);
    }
}