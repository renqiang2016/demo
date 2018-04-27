package com.example.ren.service.mysql;

import com.example.ren.configuration.SwaggerPropertiesConfig;
import com.example.ren.module.filter.SwaggerFilter;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.common.base.Strings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * swagger login
 *
 * @author qiang.ren on 2018/3/26
 */
@Service
public class SwaggerLoginService {

    private static final Logger logger = LogManager.getLogger(SwaggerLoginService.class);

    private static final String IMAGE_TYPE = "jpg";

    private static final String DEV = "dev";

    private DefaultKaptcha kaptchaProducer;

    private String username;

    private String password;

    private Boolean dev;

    @Autowired
    public SwaggerLoginService(SwaggerPropertiesConfig swaggerPropertiesConfig,
                               DefaultKaptcha kaptchaProducer) {
        this.username = swaggerPropertiesConfig.getSwaggerLoginName();
        this.password = swaggerPropertiesConfig.getSwaggerLoginPasswd();
        this.dev = swaggerPropertiesConfig.getEnvironment().equals(DEV);
        this.kaptchaProducer = kaptchaProducer;
        Assert.notNull(this.username, "username is null");
        Assert.notNull(this.password, "password is null");
        Assert.notNull(this.dev, "dev is null");
        Assert.notNull(this.kaptchaProducer, "kaptchaProducer is null");
    }

    /**
     * swagger 登录
     *
     * @param username   用户名
     * @param password   账户密码
     * @param verifyCode 验证码
     * @param session    session
     * @return 状态
     */
    public Boolean swaggerLogin(String username, String password, String verifyCode, HttpSession session) {
        if (dev) {
            session.setAttribute(SwaggerFilter.SWAGGER_LOGIN_FLAG, username);
            return Boolean.TRUE;
        }
        String currentVerifyCode = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (Strings.isNullOrEmpty(currentVerifyCode)) {
            return Boolean.FALSE;
        }
        if (this.username.equals(username)
                && this.password.equals(password)
                && currentVerifyCode.equalsIgnoreCase(verifyCode)) {
            session.setAttribute(SwaggerFilter.SWAGGER_LOGIN_FLAG, username);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 获取验证码
     *
     * @param httpSession httpSession
     * @param response    response
     */
    public void verificationCode(HttpSession httpSession, HttpServletResponse response) throws IOException {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        String code = kaptchaProducer.createText();
        logger.info("verification code is : " + code);
        httpSession.setAttribute(Constants.KAPTCHA_SESSION_KEY, code);
        BufferedImage image = kaptchaProducer.createImage(code);
        OutputStream outputStream = response.getOutputStream();
        ImageIO.write(image, IMAGE_TYPE, outputStream);
    }

}
