package com.mylearn.microutil;

import com.google.gson.Gson;
import com.mylearn.microutil.enums.ApiError;
import com.mylearn.microutil.enums.ApiStatus;
import com.mylearn.microutil.RequestUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Slf4j
@NoArgsConstructor
public class ApiController {


    private  Gson gson;


    private  RequestUtil requestUtil;


    private  Translator translator;

    @Autowired
    public ApiController(Gson gson, RequestUtil requestUtil, Translator translator) {
        this.gson = gson;
        this.requestUtil = requestUtil;
        this.translator = translator;
    }

    protected ResponseEntity<Object> ok(Object data) {
        ApiMessage apiMessage = ApiMessage
                .builder()
                .success(true)
                .code("200")
                .message("OK")
                .data(data)
                .build();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>(apiMessage, headers,HttpStatus.OK);
    }

    protected ResponseEntity<Object> otp(Object data) {
        ApiMessage apiMessage = ApiMessage
                .builder()
                .success(true)
                .code("200")
                .message(translator.toLocale(ApiStatus.OTP_SUCCESS.getCode()))
                .data(data)
                .build();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>(apiMessage, headers,HttpStatus.OK);
    }

    protected ResponseEntity<Object> checkOtp(Object data) {
        ApiMessage apiMessage = ApiMessage
                .builder()
                .success(true)
                .code("200")
                .message(translator.toLocale(ApiStatus.CHECK_OTP_SUCCESS.getCode()))
                .data(data)
                .build();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>(apiMessage, headers,HttpStatus.OK);
    }

    protected ResponseEntity<String> browseSuccess(Object data) {
        ApiMessage apiMessage = ApiMessage
                .builder()
                .success(true)
                .code("200")
                .message(translator.toLocale(ApiStatus.BROWSE_SUCCESS.getCode()))
                .data(data)
                .build();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>(gson.toJson(apiMessage), headers,HttpStatus.OK);
    }

    protected ResponseEntity<String> createUpdateSuccess(Object data) {
        ApiMessage apiMessage = ApiMessage
                .builder()
                .success(true)
                .code("200")
                .message(translator.toLocale(ApiStatus.CREATE_UPDATE_SUCCESS.getCode()))
                .data(data)
                .build();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>(gson.toJson(apiMessage), headers,HttpStatus.OK);
    }

    protected ResponseEntity<String> logoutSuccess(Object data) {
        ApiMessage apiMessage = ApiMessage
                .builder()
                .success(true)
                .code("200")
                .message(translator.toLocale(ApiStatus.LOGOUT_SUCCESS.getCode()))
                .data(data)
                .build();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>(gson.toJson(apiMessage), headers,HttpStatus.OK);
    }

    protected ResponseEntity<String> changIcon(Object data) {
        ApiMessage apiMessage = ApiMessage
                .builder()
                .success(true)
                .code("200")
                .message(translator.toLocale(ApiStatus.CHANG_ICON.getCode()))
                .data(data)
                .build();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>(gson.toJson(apiMessage), headers,HttpStatus.OK);
    }

    protected ResponseEntity<String> notificationSuccess(Object data) {
        ApiMessage apiMessage = ApiMessage
                .builder()
                .success(true)
                .code("200")
                .message(translator.toLocale(ApiStatus.NOTIFICATION_SUCCESS.getCode()))
                .data(data)
                .build();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>(gson.toJson(apiMessage), headers,HttpStatus.OK);
    }

    protected ResponseEntity<String> deleteSuccess(Object data) {
        ApiMessage apiMessage = ApiMessage
                .builder()
                .success(true)
                .code("200")
                .message(translator.toLocale(ApiStatus.DELETE_SUCCESS.getCode()))
                .data(data)
                .build();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>(gson.toJson(apiMessage), headers,HttpStatus.OK);
    }

    protected ResponseEntity<String> updateSortSuccess(Object data) {
        ApiMessage apiMessage = ApiMessage
                .builder()
                .success(true)
                .code("200")
                .message(translator.toLocale(ApiStatus.UPDATE_SORT_SUCCESS.getCode()))
                .data(data)
                .build();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>(gson.toJson(apiMessage), headers,HttpStatus.OK);
    }

    protected ResponseEntity<String> error(String code,HttpStatus status, String message,  String data, HttpServletRequest request) {
        ApiMessage apiMessage = ApiMessage.builder()
                .code(code)
                .success(false)
                .message(getMessage(message,request))
                .data(data)
                .build();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>(gson.toJson(apiMessage),headers, status);
    }

    protected ResponseEntity<String> error(ApiError error, HttpServletRequest request) {
        ApiMessage apiMessage = ApiMessage
                .builder()
                .code(error.getCode())
                .success(false)
                .message(getMessage(error.getCode(), request))
                .build();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>(gson.toJson(apiMessage),headers, error.getStatus());
    }

    protected String getMessage(String code, HttpServletRequest request) {
        return requestUtil.getLocaleMessage(code, request);
    }
    /**
     * Ok response entity.
     *
     * @param status  the status
     * @param request the request
     * @return the response entity
     */
    protected ResponseEntity<Object> ok(ApiStatus status, HttpServletRequest request) {
        ApiMessage apiMessage = ApiMessage.builder().success(true).code(status.getCode())
                .message(getMessage(status.getCode(), request)).build();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>(apiMessage,headers, HttpStatus.OK);
    }

    protected ResponseEntity<Object> okObject(Object data) {
        ApiMessage apiMessage = ApiMessage
                .builder()
                .success(true)
                .code("200")
                .message("OK")
                .data(gson.toJson(data))
                .build();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>(apiMessage, headers,HttpStatus.OK);
    }
    protected ResponseEntity<String> ok(String message,HttpServletRequest request) {
        ApiMessage apiMessage = ApiMessage
                .builder()
                .success(true)
                .code("200")
                .message(getMessage(message, request))
                .data(null)
                .build();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>(gson.toJson(apiMessage), headers,HttpStatus.OK);
    }
    protected ResponseEntity<String> okMessage(String message) {
        ApiMessage apiMessage = ApiMessage
                .builder()
                .success(true)
                .code("200")
                .message(message)
                .data(null)
                .build();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>(gson.toJson(apiMessage), headers,HttpStatus.OK);
    }

    protected ResponseEntity<String> updatePassSuccess(Object data) {
        ApiMessage apiMessage = ApiMessage
                .builder()
                .success(true)
                .code("200")
                .message(translator.toLocale(ApiStatus.PASS_SUCCESS.getCode()))
                .data(data)
                .build();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>(gson.toJson(apiMessage), headers,HttpStatus.OK);
    }

}
