package com.api.project.service;


import com.alibaba.nacos.api.naming.pojo.healthcheck.impl.Http;
import com.api.project.model.dto.user.UserUpdateSecret;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zwh.common.model.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户服务
 *
 * @author zwh
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @return 新用户 id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * 用户登录
     *
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @param request request 请求
     * @return 脱敏后的用户信息
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 获取当前登录用户
     *
     * @param request request 请求
     * @return 登录用户
     */
    User getLoginUser(HttpServletRequest request);

    /**
     * 是否为管理员
     *
     * @param request request 请求
     * @return true-管理员、false-普通用户
     */
    boolean isAdmin(HttpServletRequest request);

    /**
     * 用户注销
     * @param request request 请求
     * @return 是否成功退出登录
     */
    boolean userLogout(HttpServletRequest request);

    /**
     * 更新用户信息 [更换签名]
     * @param secret 秘钥请求体
     * @param request request 请求
     * @return 结果
     */
    boolean updateUserInfo(UserUpdateSecret secret, HttpServletRequest request);

}
