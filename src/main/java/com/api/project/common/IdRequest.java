package com.api.project.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 接口上线下线请求体
 *
 * @author zwh
 */
@Data
public class IdRequest implements Serializable {
    /**
     * id
     */
    private Long id;

    private static final long serialVersionUID = 1L;
}