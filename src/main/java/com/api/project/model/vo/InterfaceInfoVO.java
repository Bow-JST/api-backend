package com.api.project.model.vo;

import com.zwh.common.model.entity.InterfaceInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 接口信息封装视图
 *
 * @author zwh
 * @TableName product
 */
@EqualsAndHashCode(callSuper = true) // 用当前类和父类的属性生成 HashCode
@Data
public class InterfaceInfoVO extends InterfaceInfo {

    /**
     * 调用次数
     */
    private Integer totalNum;

    private static final long serialVersionUID = 1L;
}