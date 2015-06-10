package com.zebone.core.web;

import com.zebone.core.util.JsonGrid;
import com.zebone.core.util.Pagination;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 基础管理
 * @author 杨英
 * @version 2013-8-16 上午08:45
 */
public class BaseController {
    private static final Log logger = LogFactory.getLog(BaseController.class);
    private static String characterEncoding = "UTF-8";
    private static ObjectMapper objectMapperCommon = new ObjectMapper();
    private static ObjectMapper objectMapperDateTime = new ObjectMapper();
    private static ObjectMapper objectMapperDate = new ObjectMapper();

    static {
        objectMapperDateTime.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        objectMapperDate.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
    }

    private Integer curPage = 1;

    private Integer countEveryPage;

    public int getCountEveryPage() {
        return countEveryPage;
    }

    @ModelAttribute
    public void setCountEveryPage(Integer countEveryPage) {
        this.countEveryPage = countEveryPage;
    }

    public int getCurPage() {
        return curPage;
    }

    @ModelAttribute
    public void setCurPage(Integer curPage) {

        this.curPage = curPage == null || curPage <= 0 ? 1 : curPage;
    }

    @SuppressWarnings("unchecked")
    public Pagination getPagination() {
        Pagination page = new Pagination(curPage);
        page.setCountEveryPage(countEveryPage);
        return page;
    }


    /**
     * 将字符串返回给客户端
     *
     * @param value
     * @param response
     */
    public void wirte(String value, HttpServletResponse response) {
        try {
            response.getWriter().write(value);
        } catch (IOException e) {
            logger.error("", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 将对象转成josn数据返回。
     *
     * @param value     被转换成json数据的对象
     * @param response
     * @param dataStyle 日期样式，如果被转换的对象属性有日期类型，此参数决定转换的样式。<br/>
     *                  1：yyyy-MM-dd HH:mm:ss ;<br/>
     *                  2：yyyy-MM-DD ; <br/>
     *                  其他值或者null，则转换为毫秒数值<br/>
     */
    public void writeJson(Object value, HttpServletResponse response, Integer dataStyle) {
        if (value == null) {
            return;
        }
        response.setCharacterEncoding(characterEncoding);
        try {
            if (dataStyle == null) {
                objectMapperCommon.writeValue(response.getWriter(), value);
            } else if (dataStyle == 1) {
                objectMapperDateTime.writeValue(response.getWriter(), value);
            } else if (dataStyle == 2) {
                objectMapperDate.writeValue(response.getWriter(), value);
            } else {
                objectMapperCommon.writeValue(response.getWriter(), value);
            }
        } catch (IOException e) {
            logger.error("", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 将分页查询结果数据返回
     *
     * @param page
     * @param response
     * @param dataStyle 日期样式，如果被转换的对象属性有日期类型，此参数决定转换的样式。<br/>
     *                  1：yyyy-MM-dd HH:mm:ss ;<br/>
     *                  2：yyyy-MM-DD ; <br/>
     *                  其他值或者null，则转换为毫秒数值<br/>
     */
    @SuppressWarnings("unchecked")
    public void writeJsonGrid(Pagination page, HttpServletResponse response, Integer dataStyle) {
        if (page == null) {
            return;
        }
        response.setCharacterEncoding(characterEncoding);
        int total = page.getTotalCount();
        List data = page.getData();

        JsonGrid grid = new JsonGrid("success", total, data);
        try {
            if (dataStyle == null) {
                objectMapperCommon.writeValue(response.getWriter(), grid);
            } else if (dataStyle == 1) {
                objectMapperDateTime.writeValue(response.getWriter(), grid);
            } else if (dataStyle == 2) {
                objectMapperDate.writeValue(response.getWriter(), grid);
            } else {
                objectMapperCommon.writeValue(response.getWriter(), grid);
            }
        } catch (IOException e) {
            logger.error("", e);
            throw new RuntimeException(e);
        }
    }
}
