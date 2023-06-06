package com.api.project.service.impl.inner;

import com.api.project.service.InterfaceInfoService;
import com.api.project.service.UserInterfaceInfoService;
import com.api.project.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zwh.common.model.entity.InterfaceInfo;
import com.zwh.common.model.entity.User;
import com.zwh.common.model.entity.UserInterfaceInfo;
import com.zwh.common.service.InnerUserInterfaceInfoService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

import static com.api.project.constant.UserConstant.ADMIN_ROLE;

/**
 * 公共接口实现类
 * @author zwh
 */
@DubboService
public class InnerUserInterfaceInfoServiceImpl implements InnerUserInterfaceInfoService {

    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;

    @Resource
    private InterfaceInfoService interfaceInfoService;

    @Resource
    private UserService userService;

    /**
     * 更新接口调用次数
     * @param interfaceInfoId 接口 id
     * @param userId 用户 id
     * @return 返回更新是否成功
     */
    @Override
    public boolean invokeCount(long interfaceInfoId, long userId) {
        return userInterfaceInfoService.invokeCount(interfaceInfoId, userId);
    }

    /**
     * 判断是否还有调用接口的次数
     * @param interfaceInfoId 接口 id
     * @param userId 用户 id
     * @return 结果
     */
    @Override
    public  boolean validInvoke(long interfaceInfoId, long userId) {
        // 如果调用接口的用户是接口的开发者或系统管理员，那么不限制调用次数
        InterfaceInfo interfaceInfo = interfaceInfoService.getById(interfaceInfoId);
        User user = userService.getById(userId);
        if(interfaceInfo.getUserId() == userId || ADMIN_ROLE.equals(user.getUserRole())){
            return true;
        }
        // 不是开发者
        LambdaQueryWrapper<UserInterfaceInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserInterfaceInfo::getInterfaceInfoId, interfaceInfoId)
                .eq(UserInterfaceInfo::getUserId, userId);
        UserInterfaceInfo userInterfaceInfo = userInterfaceInfoService.getOne(wrapper);
        // 调用接口记录不为空，并且记录中有剩余次数才返回 true
        return userInterfaceInfo != null && userInterfaceInfo.getLeftNum() > 0;
    }
}
