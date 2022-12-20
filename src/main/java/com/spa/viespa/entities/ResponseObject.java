/**
 * Author: Aptech - Team 1 - Project Semester 2
 * Date: 18/12/2022
 * Description: VieSpa Management Project
 */

package com.spa.viespa.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class ResponseObject {
    private EResponse status = EResponse.SUCCESS;
    private String message;
    private Object data;

    public ResponseObject(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public ResponseObject(EResponse status, String message) {
        this.status = status;
        this.message = message;
    }

    public static ResponseEntity<ResponseObject> response(String message, Object data) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(message, data));
    }

    public static ResponseEntity<ResponseObject> response(String message) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(EResponse.ERROR, message, ""));
    }
}


