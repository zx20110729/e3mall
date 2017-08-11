package cn.e3mall.search.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理器
 * Created by ZX on 2017/8/7 14:54.
 */
public class GlobalExceptionResolver implements HandlerExceptionResolver {
    public static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionResolver.class);
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
        //打印控制台
        e.printStackTrace();
        //写日志
        LOG.error("系统发生异常",e);
        //预警 邮件,短信......

        return new ModelAndView("error/exception");
    }
}
