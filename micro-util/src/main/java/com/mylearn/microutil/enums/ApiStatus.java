/*******************************************************************************
 * (C) Copyright Global CyberSoft (GCS) 2019. All rights reserved. Proprietary
 * and confidential.
 ******************************************************************************/
package com.mylearn.microutil.enums;

import lombok.Getter;

/**
 * Gets the code.
 *
 * @return the code
 */
@Getter
public enum ApiStatus {

  /**
   * The ok.
   */
  OK("OK"),
  /**
   * The created.
   */
  CREATED("CREATED"),
  /**
   * The deleted.
   */
  DELETED("DELETED"),
  /**
   * The updated.
   */
  UPDATED("UPDATED"),

  ENTERPRISE_CREATED("CREATED_ENTERPRISE"),
  IMPORTED_EXCEL_LIST_CUSTOMER("IMPORTED_EXCEL_LIST_CUSTOMER"),
  IMPORTED_EXCEL_LIST_CUSTOMER_ERROR("IMPORTED_EXCEL_LIST_CUSTOMER_ERROR"),

  ENTERPRISE__BRANCH_CREATED("BRANCH_CREATED_ENTERPRISE"),

  /**
   * The enterprise change status success.
   */
  ENTERPRISE_CHANGE_STATUS_SUCCESS("ENTERPRISE_CHANGE_STATUS_SUCCESS"),

  /**
   * The customer delete success.
   */
  CUSTOMER_DELETE_SUCCESS("api.msg.customer.delete_success"),

  /**
   * The group user update success.
   */
  GROUP_USER_UPDATE_SUCCESS("api.msg.group.user.change_status_success"),

  /**
   * The user update success.
   */
  USER_UPDATE_SUCCESS("api.msg.user.change_status_success"),

  /**
   * The user change status success.
   */
  USER_CHANGE_STATUS_SUCCESS("api.msg.user.change_status_success"),

  /**
   * The grant permission success.
   */
  GRANT_PERMISSION_SUCCESS("GRANT_PERMISSION_SUCCESS"),

  /**
   * The change status success.
   */
  CHANGE_STATUS_SUCCESS("CHANGE_STATUS_SUCCESS"),
  CHANGE_STATUS_ACTIVE_ACCOUNT("CHANGE_STATUS_ACTIVE_ACCOUNT"),
  CHANGE_STATUS_INACTIVE_ACCOUNT("CHANGE_STATUS_INACTIVE_ACCOUNT"),

  /**
   * The reset password success.
   */
  RESET_PASSWORD_SUCCESS("RESET_PASSWORD_SUCCESS"),

  DOCUMENT_TYPE_CHANGE_STATUS_SUCCESS("DOCUMENT_TYPE_CHANGE_STATUS_SUCCESS"),

  SET_AUTO_SIGN_SUCCESS("SET_AUTO_SIGN_SUCCESS"),

  SEND_CONTRACT_SUCCESS("SEND_CONTRACT_SUCCESS"),

  CREATE_SIGNATURE_SERVER_SUCCESS("CREATE_SIGNATURE_SERVER_SUCCESS"),

  CREATE_SIMCA_SUCCESS("CREATE_SIMCA_SUCCESS"),

  CREATE_USBTOKEN_SUCCESS("CREATE_USBTOKEN_SUCCESS"),

  CREATE_OTP_SUCCESS("CREATE_OTP_SUCCESS"),

  RECEIVE_CUSTOMER_INFORMATION("RECEIVE_CUSTOMER_INFORMATION"),

  /**
   * The success refund.
   */
  REFUND_SUCCESS("REFUND_SUCCESS"),
  CREATE_UPDATE_SUCCESS("CREATE_UPDATE_SUCCESS"),
  LOGOUT_SUCCESS("LOGOUT_SUCCESS"),
  DELETE_SUCCESS("DELETE_SUCCESS"),
  PASS_SUCCESS("PASS_SUCCESS"),
  UPDATE_SORT_SUCCESS("UPDATE_SORT_SUCCESS"),
  BROWSE_SUCCESS("BROWSE_SUCCESS"),
  CHANG_ICON("CHANG_ICON"),
  OTP_SUCCESS("OTP_SUCCESS"),
  CHECK_OTP_SUCCESS("CHECK_OTP_SUCCESS"),
  NOTIFICATION_SUCCESS("NOTIFICATION_SUCCESS");

  /**
   * The code.
   */
  private String code;

  /**
   * Instantiates a new api status.
   *
   * @param code the code
   */
  ApiStatus(String code) {
    this.code = code;
  }
}
