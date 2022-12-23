/**
 * Author: ng-hoangnam
 * Date: 20/12/2022
 * Description:
 */

package com.spa.viespa.controllers;

import com.spa.viespa.entities.ResponseObject;
import com.spa.viespa.entities.SpaTransaction;
import com.spa.viespa.services.TCLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/transaction")
public class TCLController {
    private TCLService tclService;

    @Autowired
    public TCLController(TCLService tclService){
        this.tclService = tclService;
    }


    public ResponseObject addNewTransactionCourseLine(SpaTransaction spaTransaction) {
        return tclService.addNewTransactionCourseLine(spaTransaction);
    }
}
