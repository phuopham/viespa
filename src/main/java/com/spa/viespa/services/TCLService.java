/**
 * Author: ng-hoangnam
 * Date: 20/12/2022
 * Description:
 */

package com.spa.viespa.services;

import com.spa.viespa.entities.ServiceBundle;
import com.spa.viespa.entities.SpaTransaction;
import com.spa.viespa.entities.TransactionCourseLine;
import com.spa.viespa.repositories.TCLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public void addNewTransactionCourseLine(SpaTransaction transaction) {
        //Save
        List<TransactionCourseLine> tCLList = new ArrayList<>();
        List<ServiceBundle> serviceBundles = transaction.getCourse().getJoinServices().stream().collect(Collectors.toList());
        serviceBundles.forEach(serviceBundle -> serviceBundle.getJoinSkills().forEach(skill -> {
            tCLList.add(new TransactionCourseLine(transaction.getCustomer(), transaction.getCourse(), serviceBundle, skill));
        }));
        
        tCLRepository.saveAll(tCLList);
    }
}
