package com.example.calculatorexample.service.impl;

import com.example.calculatorexample.model.Action;
import com.example.calculatorexample.repository.ActionRepository;
import com.example.calculatorexample.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActionServiceImpl implements ActionService {

    private ActionRepository actionRepository;

    @Autowired
    public ActionServiceImpl(ActionRepository actionRepository) {
        this.actionRepository = actionRepository;
    }

    @Override
    public Action save(Action action) {
        return actionRepository.save(action);
    }
}
