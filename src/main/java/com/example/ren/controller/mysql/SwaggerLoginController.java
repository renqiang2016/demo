package com.example.ren.controller.mysql;

import com.example.ren.service.mysql.SwaggerLoginService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * swagger-ui登陆页面
 *
 * @author qiang.ren
 */
@Controller
@ApiIgnore
public class SwaggerLoginController {

    private SwaggerLoginService swaggerLoginService;

    @Autowired
    public SwaggerLoginController(SwaggerLoginService swaggerLoginService) {
        this.swaggerLoginService = swaggerLoginService;
        Assert.notNull(this.swaggerLoginService, "swaggerLoginService is null");
    }

    /**
     * 展示登陆页面
     *
     * @return 页面
     */
    @RequestMapping(value = "swagger/login", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "正常情况", response = ModelAndView.class)
    })
    @ApiOperation("获取swagger登录页面")
    public ModelAndView showLoginPage() {
        return new ModelAndView("swagger/swagger_login");
    }

    /**
     * 请求验证码，并将验证码写到session中
     */
    @RequestMapping(value = "swagger/verify/code", method = RequestMethod.GET)
    @ApiOperation(value = "获取验证码", notes = "获取验证码")
    @ResponseBody
    public void verifyCode(HttpSession httpSession, HttpServletResponse response) throws IOException {
        swaggerLoginService.verificationCode(httpSession, response);
    }

    /**
     * 登陆验证
     */
    @RequestMapping(value = "swagger/login/check", method = RequestMethod.POST)
    @ApiOperation(value = "登陆验证", notes = "登陆验证")
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "正常")
    })
    @ResponseBody
    public int loginCheck(@RequestParam("username") String username,
                                     @RequestParam("password") String password,
                                     @RequestParam("verifyCode") String verifyCode,
                                     HttpSession session) {
        boolean result = swaggerLoginService.swaggerLogin(username, password, verifyCode, session);
        return result ? 0 : 1;
    }

}
