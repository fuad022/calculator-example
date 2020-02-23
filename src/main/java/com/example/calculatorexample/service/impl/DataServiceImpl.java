package com.example.calculatorexample.service.impl;

import com.example.calculatorexample.model.Data;
import com.example.calculatorexample.repository.DataRepository;
import com.example.calculatorexample.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataServiceImpl implements DataService {

    private DataRepository dataRepository;

    @Autowired
    public DataServiceImpl(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public Data save(Data data) {
        return dataRepository.save(data);
    }
}
