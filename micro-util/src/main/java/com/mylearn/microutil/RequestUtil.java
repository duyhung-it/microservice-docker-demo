/*******************************************************************************
 * (C) Copyright Global CyberSoft (GCS) 2019. All rights reserved. Proprietary
 * and confidential.
 ******************************************************************************/
package com.mylearn.microutil;

import com.mylearn.microutil.configure.LocaleResolver;
import com.mylearn.microutil.enums.ApiError;
import com.mylearn.microutil.enums.ApiStatus;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

/**
 * The Class RequestUtil.
 *
 * @author <a href="mailto:huykq.hq@hitachiconsulting.com">huyquach</a>
 */
@Service
public class RequestUtil {



  /**
   * The Message source.
   */
  @Autowired
  private MessageSource messageSource;

  /**
   * The Locale resolver.
   */
  @Autowired
  private LocaleResolver localeResolver;

  /**
   * Get locale message string.
   *
   * @param code    the code
   * @param request the request
   * @return the string
   */
  public String getLocaleMessage(String code, HttpServletRequest request) {
    return messageSource.getMessage(code, null, localeResolver.resolveLocale(request));
  }

  /**
   * Get locale message string.
   *
   * @param status  the status
   * @param request the request
   * @return the string
   */
  public String getLocaleMessage(ApiStatus status, HttpServletRequest request) {
    return messageSource.getMessage(status.getCode(), null, localeResolver.resolveLocale(request));
  }

  /**
   * Get locale message string.
   *
   * @param error   the error
   * @param request the request
   * @return the string
   */
  public String getLocaleMessage(ApiError error, HttpServletRequest request) {
    return messageSource.getMessage(error.getCode(), null, localeResolver.resolveLocale(request));
  }




}
