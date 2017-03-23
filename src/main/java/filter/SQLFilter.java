package filter;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by zangyaoyi on 2017/2/27.
 */
public class SQLFilter implements Filter {
    private static Logger logger = Logger.getLogger(SQLFilter.class.getName());
    private static final String badStr = "'|and|exec|execute|insert|select|delete|update|count|drop|*|%|chr|mid|master|truncate|" +
            "char|declare|sitename|net user|xp_cmdshell|;|or|-|+|,|like'|and|exec|execute|insert|create|drop|" +
            "table|from|grant|use|group_concat|column_name|" +
            "information_schema.columns|table_schema|union|where|select|delete|update|order|by|count|*|" +
            "chr|mid|master|truncate|char|declare|or|;|-|--|+|,|like|//|/|%|#";//过滤掉的sql关键字，可以手动添加

    public void destroy() {
        // TODO Auto-generated method stub
    }

    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub
    }

    public void doFilter(ServletRequest args0, ServletResponse args1,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) args0;
        //获得所有请求参数名
        Enumeration params = req.getParameterNames();
        String sql = "";
        while (params.hasMoreElements()) {
            //得到参数名
            String name = params.nextElement().toString();
            //得到参数对应值
            String[] value = req.getParameterValues(name);
            for (int i = 0; i < value.length; i++) {
                sql = sql + value[i];
            }
        }

        if (sqlValidate(sql)) {
            logger.debug("SQLFilter Found that injection of character |values=" + JSON.toJSONString(params));
        } else {
            chain.doFilter(args0, args1);
        }
    }

    //效验
    private static boolean sqlValidate(String str) {
        str = str.toLowerCase();//统一转为小写
        String[] badStrArray = badStr.split("\\|");
        for (int i = 0; i < badStrArray.length; i++) {
            if (str.indexOf(" "+badStrArray[i]+" ") >= 0) {
                return true;
            }
        }
        return false;
    }
}

