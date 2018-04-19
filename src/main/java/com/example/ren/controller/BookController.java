package com.example.ren.controller;

import com.example.ren.exception.DataFormatException;
import com.example.ren.model.BookBean;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 书的接口
 *
 * @author qiang.ren
 * @date 2018/4/3
 */
@RestController
@RequestMapping("/book")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    private BookBean bookBean;

    @Autowired
    public BookController(BookBean bookBean) {
        this.bookBean = bookBean;
        Assert.notNull(bookBean,"bookBean为空！");
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ApiOperation(value = "查看书")
    public BookBean getBook(){
        logger.info(bookBean.toString());
        return bookBean;
    }

    @RequestMapping(value = "/get2", method = RequestMethod.GET)
    @ApiOperation(value = "触发异常")
    public BookBean getBook2() throws DataFormatException{
        throw new DataFormatException("DataFormatException");
    }
}
