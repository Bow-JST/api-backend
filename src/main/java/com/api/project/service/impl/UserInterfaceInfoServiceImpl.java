package com.api.project.service.impl;

import com.api.project.exception.BusinessException;
import com.api.project.model.dto.userinterfaceinfo.UserInterfaceInfoRecord;
import com.api.project.service.InterfaceInfoService;
import com.api.project.service.UserInterfaceInfoService;
import com.api.project.service.UserService;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.api.project.common.ErrorCode;
import com.api.project.mapper.UserInterfaceInfoMapper;
import com.zwh.common.model.entity.InterfaceInfo;
import com.zwh.common.model.entity.User;
import com.zwh.common.model.entity.UserInterfaceInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 *
 */
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
    implements UserInterfaceInfoService {

    @Resource
    private UserService userService;

    @Resource
    private InterfaceInfoService interfaceInfoService;

    public static final String SALT = "zwh";

    @Override
    public void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add) {
        if (userInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 创建时，所有参数必须非空
        if (add) {
            if (userInterfaceInfo.getInterfaceInfoId() <= 0 || userInterfaceInfo.getUserId() <= 0) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口或用户不存在");
            }
        }
        if (userInterfaceInfo.getLeftNum() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "剩余次数不能小于等于 0");
        }
    }

    @Override
    public  boolean invokeCount(long interfaceInfoId, long userId) {
        // 判断
        if (interfaceInfoId <= 0 || userId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        UpdateWrapper<UserInterfaceInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("interfaceInfoId", interfaceInfoId);
        updateWrapper.eq("userId", userId);
        updateWrapper.gt("leftNum", 0);
        updateWrapper.setSql("leftNum = leftNum - 1, totalNum = totalNum + 1");
        return this.update(updateWrapper);
    }

    @Override
    public boolean updateUserInterfaceInfo(UserInterfaceInfoRecord record, HttpServletRequest request) {
        // 获取登录用户
        User loginUser = userService.getLoginUser(request);
        Long interfaceInfoId = record.getInterfaceId();
        Integer number = record.getNumber();
        // 申请额度限制
        if(number > 1000){
            return false;
        }
        // 查询接口是否存在
        InterfaceInfo interfaceInfo = interfaceInfoService.getById(interfaceInfoId);
        if(interfaceInfo == null){
            return false;
        }
        // 查询接口调用记录
        LambdaUpdateWrapper<UserInterfaceInfo> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(UserInterfaceInfo::getUserId, loginUser.getId()).eq(UserInterfaceInfo::getInterfaceInfoId, interfaceInfo.getId());
        UserInterfaceInfo userInterfaceInfo = getOne(wrapper);
        // 记录存在的情况下，用户处于禁用状态或 剩余调用次数大于 1000，则拒绝申请
        if(userInterfaceInfo != null && (userInterfaceInfo.getStatus() == 1 || userInterfaceInfo.getLeftNum() > 1000)){
            return false;
        }
        if(userInterfaceInfo != null){
            // 更新额度
            userInterfaceInfo.setLeftNum(number);
            return updateById(userInterfaceInfo);
        }
        // 插入新记录
        UserInterfaceInfo newUserInterfaceInfo = new UserInterfaceInfo();
        newUserInterfaceInfo.setUserId(loginUser.getId());
        newUserInterfaceInfo.setInterfaceInfoId(interfaceInfoId);
        newUserInterfaceInfo.setLeftNum(number);
        return save(newUserInterfaceInfo);
    }

}




