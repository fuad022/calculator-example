package com.example.calculatorexample.service;

import com.example.calculatorexample.model.Data;
import org.springframework.stereotype.Service;

@Service
public interface DataService {
    Data save(Data data);
}
