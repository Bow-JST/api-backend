package com.zwh.apiinterface;

import com.zwh.clientsdk.client.ApiClient;
import com.zwh.clientsdk.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ApiInterfaceApplicationTests {

    @Resource
    private ApiClient apiClient;

    @Test
    void contextLoads() {
        // 测试第三个接口
        User user = new User();
        user.setUsername("WangWu");
        System.out.println(apiClient.getUsernameByPost(user));
    }

}
