/*******************************************************************************
 * (C) Copyright Global CyberSoft (GCS) 2019. All rights reserved. Proprietary
 * and confidential.
 ******************************************************************************/
package com.mylearn.microutil.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Gets the status.
 *
 * @return the status
 */

/**
 * Gets the code.
 *
 * @return the code
 */
@Getter
public enum ApiError {

    /**
     * The tenant not found.
     */
    TENANT_NOT_FOUND("0x00101", HttpStatus.OK),

    INTERNAL_SERVER_ERROR("0x00116", HttpStatus.OK),

    BAD_REQUEST("0x00117", HttpStatus.BAD_REQUEST),

    NOT_FOUND("0x00118", HttpStatus.NOT_FOUND),

    VALIDATE_INPUT_ERROR("400", HttpStatus.OK);


    /**
     * The status.
     */
    private HttpStatus status;


    /**
     * The code.
     */
    private String code;

    /**
     * Instantiates a new api error.
     *
     * @param code   the code
     * @param status the status
     */
    ApiError(String code, HttpStatus status) {
        this.status = status;
        this.code = code;
    }

}
