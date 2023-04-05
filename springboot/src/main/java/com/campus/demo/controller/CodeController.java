package com.campus.demo.controller;

/**
 * Description：生成验证码图片
 * Param：
 * return：request   HttpServletRequest对象
 *                  代表客户端的请求，
 *                  当客户端通过HTTP协议访问服务器时，
 *                  HTTP请求头中的所有信息都封装在这个对象中，
 *                  通过这个对象提供的方法，可以获得客户端请求的所有信息。
 * Author：Dengshiting
 * Date：2023/3/9
 */
import com.campus.demo.config.VerifyCodeUtils;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/user")
public class CodeController {

    @GetMapping("/getImage")
    public String getImageCode(HttpServletRequest request) throws IOException {
        //1.使用工具类生成验证码
        String code = VerifyCodeUtils.generateVerifyCode(4);
        //2.将验证码放入servletContext作用域
        request.getServletContext().setAttribute("code",code);
        //3.将图片转换成base64
        //字节数组输出流在内存中创建一个字节数组缓冲区，所有发送到输出流的数据保存在该字节数组缓冲区中。
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //将得到的验证码，使用工具类生成验证码图片，并放入到字节数组缓存区
        VerifyCodeUtils.outputImage(200,60,byteArrayOutputStream,code);
        //使用spring提供的工具类，将字节缓存数组中的验证码图片流转换成Base64的形式
        //并返回给浏览器
        return "data:image/png;base64," + Base64Utils.encodeToString(byteArrayOutputStream.toByteArray());
    }

}

