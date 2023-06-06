package com.api.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zwh.common.model.entity.InterfaceInfo;

/**
 * 接口服务
 * @author zwh
 */
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);
}
