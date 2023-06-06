package com.api.project.service;

import com.api.project.model.dto.userinterfaceinfo.UserInterfaceInfoRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zwh.common.model.entity.UserInterfaceInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户调用接口服务
 * @author zwh
 */
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {
    /**
     * 判断用户调用接口是否合法
     * @param userInterfaceInfo 用户接口信息
     * @param add 为 false 可以跳过某些检查
     */
    void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add);

    /**
     * 调用接口统计
     * @param interfaceInfoId 接口 id
     * @param userId 用户 id
     * @return 统计是否成功
     */
    boolean invokeCount(long interfaceInfoId, long userId);

    /**
     * 用户申请接口调用额度
     * @param interfaceInfoId 接口 id
     * @param number 申请调用次数
     * @param request request 请求
     * @return 结果
     */
    boolean updateUserInterfaceInfo(UserInterfaceInfoRecord record, HttpServletRequest request);

}
