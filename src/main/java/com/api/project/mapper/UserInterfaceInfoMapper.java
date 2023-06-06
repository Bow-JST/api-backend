package com.api.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zwh.common.model.entity.UserInterfaceInfo;

import java.util.List;

/**
 * 用户调用接口信息接口
 */
public interface UserInterfaceInfoMapper extends BaseMapper<UserInterfaceInfo> {

    List<UserInterfaceInfo> listTopInvokeInterfaceInfo(int limit);
}




