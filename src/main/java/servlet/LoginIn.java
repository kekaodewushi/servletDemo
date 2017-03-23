package servlet;

/**
 * Created by zangyaoyi on 2017/3/23.
 */

import domain.Customers;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class LoginIn extends HttpServlet {

    /**
     * Constructor of the object.
     */
    public LoginIn() {
        super();
    }

    /**
     * Destruction of the servlet. <br>
     */
    public void destroy() {
        super.destroy(); // Just puts "destroy" string in log
        // Put your code here
    }

    /**
     * The doGet method of the servlet. <br>
     * <p>
     * This method is called when a form has its tag value method equals to get.
     *
     * @param request  the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException      if an error occurred
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Add some codes
        doPost(request, response);
    }

    /**
     * The doPost method of the servlet. <br>
     * <p>
     * This method is called when a form has its tag value method equals to post.
     *
     * @param request  the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException      if an error occurred
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        String methodName = request.getParameter("method");

        try {
            // 利用反射获取方法
            Method method = getClass().getDeclaredMethod(methodName,
                    HttpServletRequest.class, HttpServletResponse.class);
            // 执行相应的方法
            method.invoke(this, request, response);
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    /**
     * Initialization of the servlet. <br>
     *
     * @throws ServletException if an error occurs
     */
    public void init() throws ServletException {
        // Put your code here
    }

    private void validate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        request.getSession().setAttribute("customers", username);      //将user放在Attribute中
        if (UserService.validate(username, password)) {
            request.getRequestDispatcher("/jsp/welcome.jsp").forward(request, response); //校验用户名密码正确，跳转到welcome.jsp
        } else {
            request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);  //校验用户名密码不正确，跳转到index.jsp

        }
    }

    private void insertDate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Customers customers = new Customers();
        customers.setUserName(request.getParameter("username"));
        customers.setPassWord(request.getParameter("password"));
        if (UserService.insert(customers) > 0) {
            request.getSession().setAttribute("customers", "新用户：" + customers.getUserName());      //将user放在Attribute中
            request.getRequestDispatcher("/jsp/welcome.jsp").forward(request, response); //校验用户名密码正确，跳转到welcome.jsp
        } else {
            request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);  //校验用户名密码不正确，跳转到index.jsp

        }
    }
}