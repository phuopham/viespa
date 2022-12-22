/**
 * Author: ng-hoangnam
 * Date: 20/12/2022
 * Description:
 */

package com.spa.viespa.services;

import com.spa.viespa.entities.ResponseObject;
import com.spa.viespa.entities.SpaTransaction;
import com.spa.viespa.repositories.SpaTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpaTransactionService {

    private final SpaTransactionRepository transactionRepository;

    @Autowired
    public SpaTransactionService(SpaTransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public ResponseEntity<ResponseObject> getSpaTransactions() {
        List<SpaTransaction> all = transactionRepository.findAll();
        return all.isEmpty() ?
                ResponseObject.response("Data not found") :
                ResponseObject.response("Data of transaction table", all);
    }

    public ResponseEntity<ResponseObject> addNewSpaTransaction(SpaTransaction spaTransaction) {
        Optional<SpaTransaction> duplicatedName = transactionRepository.findById(spaTransaction.getId());
        if (duplicatedName.isPresent()) {
            ResponseObject.response("Internal error. Try again");
        }

        //Save
        transactionRepository.save(spaTransaction);

        return ResponseObject.response("Insert data successfully", spaTransaction);
    }

//    @Transactional
//    public ResponseEntity<ResponseObject> updateSpaTransaction(Long id,
//                                                               Long customerId,
//                                                               Long courseId,
//                                                               Double price,
//                                                               String note) {
//
//        Optional<SpaTransaction> spaTransaction = transactionRepository.findById(id);
//        if (spaTransaction.isEmpty()) return ResponseObject
//                .response("Service with ID: [" + id + "] does not exist");
//
//        SpaTransaction target = spaTransaction.get();
//
//        if (courseId!=null && !Objects.equals(target.getCourse().getId(), courseId)) {
//            target.setCourse(transactionRepository.findCourseById(courseId).get());
//        }
//
//        if (!Objects.equals(target.getCustomer().getId(), customerId)) {
//            target.setCustomer(transactionRepository.findCustomerById(customerId).get());
//        }
//
//        if (note != null && note.length() > 0 && !Objects.equals(target.getNote(), note)) {
//            target.setNote(note);
//        }
//
//        if (price != null && price > 0 && !Objects.equals(target.getPrice(), price)) {
//            target.setPrice(price);
//        }
//
//        return ResponseObject.response("Update data successfully", "");
//    }
}
