package com.example.ren.exception;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * 异常处理
 *
 * @author qiang.ren
 * @date 2018/4/3
 */
@ControllerAdvice
public class ExceptionHandlerController {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);

    private static JSONObject exception1001;
    private static JSONObject exception1;

    static {
        exception1001 = new JSONObject();
        exception1001.put("status", 1001);
        exception1001.put("description", "qiang.ren not exception.");
    }

    static {
        exception1 = new JSONObject();
        exception1.put("status", 1);
        exception1.put("description", "data format exception.");
    }

    /**
     * 未处理异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ModelAndView processException(Exception e){
        logger.info(e.getMessage(),e);
        return new ModelAndView(new MappingJackson2JsonView(),exception1001);
    }

    /**
     * 数据格式异常
     */
    @ExceptionHandler(DataFormatException.class)
    @ResponseBody
    public ModelAndView processDataFormatException(DataFormatException e){
        logger.info(e.getMessage(),e);
        return new ModelAndView(new MappingJackson2JsonView(),exception1);
    }




}
