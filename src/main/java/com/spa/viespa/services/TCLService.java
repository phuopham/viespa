/**
 * Author: ng-hoangnam
 * Date: 20/12/2022
 * Description:
 */

package com.spa.viespa.services;

import com.spa.viespa.entities.EResponse;
import com.spa.viespa.entities.ResponseObject;
import com.spa.viespa.entities.ServiceBundle;
import com.spa.viespa.entities.SpaTransaction;
import com.spa.viespa.entities.TransactionCourseLine;
import com.spa.viespa.repositories.TCLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TCLService {

    private final TCLRepository tCLRepository;

    @Autowired
    public TCLService(TCLRepository tCLRepository) {
        this.tCLRepository = tCLRepository;
    }

    public TransactionCourseLine getTransactionCourseLine(Long id) {
        return tCLRepository.findById(id).orElseThrow(() -> new RuntimeException("Error"));
    }

    @Transactional
    public ResponseObject addNewTransactionCourseLine(SpaTransaction transaction) {
        //Save
        List<TransactionCourseLine> tCLList = new ArrayList<>();
        List<ServiceBundle> serviceBundles = new ArrayList<>(transaction.getCourse().getJoinServices());
        serviceBundles
                .forEach(serviceBundle -> serviceBundle
                        .getJoinSkills()
                        .forEach(skill -> tCLList
                                .add(new TransactionCourseLine(
                                        transaction.getCustomer(),
                                        transaction.getCourse(),
                                        serviceBundle,
                                        skill))));
        
        tCLRepository.saveAll(tCLList);
        return new ResponseObject(
                EResponse.SUCCESS,"Create Transaction successfully"
        );
    }
}
