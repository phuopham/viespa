package com.spa.viespa.configs;

import com.spa.viespa.entities.EResponse;
import com.spa.viespa.entities.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ControllerExceptionHandler {
    /**
     * Tất cả các Exception không được khai báo sẽ được xử lý tại đây
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseObject handleAllException(Exception ex, WebRequest request) {
        // quá trình kiểm soat lỗi diễn ra ở đây
        return new ResponseObject(EResponse.ERROR, ex.getLocalizedMessage());
    }

    /**
     * IndexOutOfBoundsException sẽ được xử lý riêng tại đây
     */
    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseObject OutboundException(Exception ex, WebRequest request) {
        return new ResponseObject(EResponse.ERROR, "Đối tượng không tồn tại");
    }

//    HANDLE RUNTIME ERROR CỦA E ĐỨC
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseObject SpaRuntimeException(Exception ex, WebRequest request) {
        return new ResponseObject(EResponse.ERROR, "Runtime Error");
    }

}
