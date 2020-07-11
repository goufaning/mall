package com.goufaning.mall.admin.service;

import com.goufaning.mall.MallApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author goufn
 * @version V1.0
 * @date 2020-07-10 15:42
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MallApplication.class)
public class UserServiceImplTest {

    @Autowired
    private UserService userService;


    @Test
    public void getUser() {
        userService.getUser();
    }
}
