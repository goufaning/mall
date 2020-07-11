package com.goufaning.mall.admin.controller;

import com.goufaning.mall.bean.vo.UserVo;
import com.goufaning.mall.common.result.CommonResult;
import com.goufaning.mall.db.model.Manager;
import com.goufaning.mall.db.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    @Autowired
    private ManagerService managerService;

    @PostMapping("login")
    public CommonResult login(@RequestBody UserVo user, HttpServletRequest request) {
        String username = user.getUsername();
        String password = user.getPassword();
        Manager manager = managerService.findByName(username);
        if (user == null) {
            return CommonResult.error("用户名不存在");
        }
//        if (password != manager.getPassword()) {
//            return CommonResult.error("用户名或者密码错误");
//        }
        Map<String, String> map = new HashMap<>();
        map.put("token", "12344");
        return CommonResult.success("登陆成功", map);
    }

    @GetMapping("hello")
    public String hello() {
        return "hello";
    }


}
