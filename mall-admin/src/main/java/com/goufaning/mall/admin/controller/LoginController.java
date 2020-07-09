package com.goufaning.mall.admin.controller;

import com.goufaning.mall.bean.vo.UserVo;
import com.goufaning.mall.common.result.CommonResult;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 登陆
 *
 * @author goufn
 * @version V1.0
 * @date 2020-07-09 13:50
 */
@RestController
public class LoginController {

    @PostMapping("login")
    public CommonResult login(@RequestBody UserVo user, HttpServletRequest request) {
        String username = user.getUsername();
        String password = user.getPassword();
        Map<String, String> map = new HashMap<>();
        map.put("token", "12344");
        if (username.equals("admin")) {
            return CommonResult.success("登陆成功", map);
        }
        return CommonResult.error("失败啦");
    }

    @GetMapping("hello")
    public String hello() {
        return "hello";
    }





}
