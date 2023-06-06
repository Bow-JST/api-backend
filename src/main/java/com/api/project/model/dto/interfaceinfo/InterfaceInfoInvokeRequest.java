package com.api.project.model.dto.interfaceinfo;

import lombok.Data;

import java.io.Serializable;

/**
 * 调用接口
 * @author zwh
 */
@Data
public class InterfaceInfoInvokeRequest implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户请求参数
     */
    private String userRequestParams;

    private static final long serialVersionUID = 1L;
}