package com.example.calculatorexample.service;

import com.example.calculatorexample.model.Action;
import org.springframework.stereotype.Service;

@Service
public interface ActionService {
    Action save(Action action);
}
