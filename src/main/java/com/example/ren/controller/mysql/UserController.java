package com.example.ren.controller.mysql;

import com.example.ren.model.mysql.User;
import com.example.ren.service.mysql.UserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用户接口
 *
 * @author qiang.ren
 * @date 2018/4/22
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
        Assert.notNull(this.userService);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查看用户")
    public User getUser(@PathVariable Long id){
        User user = userService.findById(id);
        logger.info(user.toString());
        return user;
    }

    @RequestMapping("/hello")
    public ModelAndView hello() {
        ModelAndView modelAndView = new ModelAndView("login/hello");
        modelAndView.getModel().put("name", "任强");
        return modelAndView;
    }

    @RequestMapping("/login/view")
    public ModelAndView login() {
        return new ModelAndView("login/login");
    }

}
