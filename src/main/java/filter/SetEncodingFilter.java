package filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by zangyaoyi on 2017/3/23.
 */
public class SetEncodingFilter implements Filter {
    protected String encoding = null;//定义缺省字符编码方式
    protected boolean ignore = true;//定义客户端指定的编码方式是否应被忽略
    protected FilterConfig filterConfig = null;//定义过滤器配置对象,若为null,则说明过滤器未配置

    public void destroy()//停止过滤器的工作
    {
        this.encoding = null;
        this.filterConfig = null;
    }

    //设置字符编码
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        if (ignore || (req.getCharacterEncoding() == null)) {
            req.setCharacterEncoding(encoding);
        }
        chain.doFilter(req, res);
    }

    //启动过滤器
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        this.encoding = filterConfig.getInitParameter("encoding");
        String value = filterConfig.getInitParameter("ignore");
        if (value == null) this.ignore = true;
        else if (value.equalsIgnoreCase("true")
                || value.equalsIgnoreCase("yes")) this.ignore = true;
        else this.ignore = false;
    }

}
