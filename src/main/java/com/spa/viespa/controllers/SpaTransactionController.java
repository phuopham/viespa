/**
 * Author: ng-hoangnam
 * Date: 20/12/2022
 * Description:
 */

package com.spa.viespa.controllers;

import com.spa.viespa.entities.ResponseObject;
import com.spa.viespa.entities.SpaTransaction;
import com.spa.viespa.entities.TransactionDTO;
import com.spa.viespa.services.SpaTransactionService;
import com.spa.viespa.services.TCLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/transaction")
public class SpaTransactionController {
    private final SpaTransactionService transactionService;
    private TCLService tclService;

    @Autowired
    public SpaTransactionController(SpaTransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<ResponseObject> getTransactions(){
        return transactionService.getSpaTransactions();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseObject> addNewTransaction(@RequestBody TransactionDTO transaction) {
        SpaTransaction spaTransaction = transactionService.addNewSpaTransaction(transaction);
        ResponseObject courseLine = tclService.addNewTransactionCourseLine(spaTransaction);
        return ResponseEntity.status(HttpStatus.OK).body(courseLine);
    }

//    @PutMapping(path = "{transactionId}")
//    public ResponseEntity<ResponseObject> updateSpaTransaction(
//            @PathVariable("transactionId") Long id,
//            @RequestParam(required = false) Long customerId,
//            @RequestParam(required = false) Long courseId,
//            @RequestParam(required = false) Double price,
//            @RequestParam(required = false) String note) {
//        return transactionService.updateSpaTransaction(id, customerId, courseId, price, note);
//    }
}
