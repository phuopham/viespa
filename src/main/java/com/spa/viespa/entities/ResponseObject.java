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

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class ResponseObject {
    private ResponseMessage status = ResponseMessage.SUCCESS;
    private String message;
    private Object data;

    public ResponseObject(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public ResponseObject(ResponseMessage status, String message) {
        this.status = status;
        this.message = message;
    }
}

