package com.api.project.model.dto.user;

import lombok.Data;

/**
 * @author Bonbons
 * @version 1.0
 */
@Data
public class UserUpdateSecret {
    /**
     * id
     */
    private Long id;

    /**
     * ak
     */
    private String accessKey;

    /**
     * sk
     */
    private String secretKey;
}
