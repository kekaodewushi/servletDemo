package init;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by zangyaoyi on 2017/3/23.
 */
public class Log4jInit extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Log4jInit() {
        super();
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("22");
        Logger logger= Logger.getRootLogger();
        logger.debug("Hello_Bug");
    }
    /**
     * Initialization of the servlet. <br>
     *
     * @throws ServletException if an error occurs
     */
    public void init(ServletConfig config) throws ServletException {
        String path = config.getServletContext().getRealPath("");
        path  = path + File.separator + config.getInitParameter("log4j");
        System.out.println(path);
        PropertyConfigurator.configure(path);
    }
}
