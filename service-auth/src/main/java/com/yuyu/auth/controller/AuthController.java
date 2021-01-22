package com.yuyu.auth.controller;

import com.yuyu.common.utils.VerifyCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;


@Api(tags={"认证鉴权接口"})
@RestController
public class AuthController {

    @ApiOperation(httpMethod = "GET",value = "获取验证码")
    @GetMapping("/vercode")
    public void code(HttpServletResponse resp, HttpSession session) throws IOException {
        VerifyCodeUtil vc = new VerifyCodeUtil();
        BufferedImage image = vc.getImage();
        String text = vc.getText();
        session.setAttribute("verify_code", text);
        resp.setContentType("image/jpeg");
        try(ServletOutputStream out = resp.getOutputStream()) {
            ImageIO.write(image, "jpg", out);
        }
    }

}
