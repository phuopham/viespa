/**
 * Author: ng-hoangnam
 * Date: 19/12/2022
 * Description:
 */

package com.spa.viespa.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class ResponseObject {
    private String status;
    private String message;
    private Object data;
}
