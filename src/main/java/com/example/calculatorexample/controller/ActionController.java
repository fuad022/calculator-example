package com.example.calculatorexample.controller;

import com.example.calculatorexample.beans.*;
import com.example.calculatorexample.model.Action;
import com.example.calculatorexample.model.Data;
import com.example.calculatorexample.service.ActionService;
import com.example.calculatorexample.service.DataService;
import com.example.calculatorexample.wsdl.WsdlOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api")
public class ActionController {

    private ActionService actionService;
    private DataService dataService;
    private Action action;
    private final static Logger LOG = LogManager.getLogger();
    private static int counter;
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd.MM HH:mm:ss");
    private static final String REQUEST_TO_JSON = "Request to JSON";
    private static final String REQUEST_TO_JSON_ZERO = "Divided by zero!";
    private static final String REQUEST_TO_SOAP = "Request to SOAP";
    private static final String REQUEST_FROM_SOAP = "Request from SOAP";

    @Autowired
    public ActionController(ActionService actionService, DataService dataService) {
        this.actionService = actionService;
        this.dataService = dataService;
    }

    @PostMapping("/add")
    public AddResponseBean add(@Valid @RequestBody AddRequestBean requestBean) {
        AddResponseBean responseBean = new AddResponseBean();
        counter++;

        String timestampRequestToJSON = formatter.format(new Date());
        postAction();
        postData(timestampRequestToJSON, counter, REQUEST_TO_JSON);

        String timestampRequestToSOAP = formatter.format(new Date());
        postData(timestampRequestToSOAP, counter, REQUEST_TO_SOAP);
        int addResult = WsdlOperation.getInstance().getAddResultFromSoap(requestBean.getIntA(), requestBean.getIntB());
        responseBean.setAddResult(addResult);

        String timestampRequestFromSOAP = formatter.format(new Date());
        postData(timestampRequestFromSOAP, counter, REQUEST_FROM_SOAP);
        return responseBean;
    }

    @PostMapping("/divide")
    public DivideResponseBean divide(@Valid @RequestBody DivideRequestBean requestBean) {
        DivideResponseBean responseBean = new DivideResponseBean();
        counter++;

        if (requestBean.getIntB() != 0) {
            String timestampRequestToJSON = formatter.format(new Date());
            postAction();
            postData(timestampRequestToJSON, counter, REQUEST_TO_JSON);

            String timestampRequestToSOAP = formatter.format(new Date());
            postData(timestampRequestToSOAP, counter, REQUEST_TO_SOAP);
            int divideResult = WsdlOperation.getInstance().getDivideResultFromSoap(requestBean.getIntA(), requestBean.getIntB());
            responseBean.setDivideResult(divideResult);

            String timestampRequestFromSOAP = formatter.format(new Date());
            postData(timestampRequestFromSOAP, counter, REQUEST_FROM_SOAP);
            return responseBean;
        } else {
            String timestampRequestToJSON = formatter.format(new Date());
            postAction();
            postData(timestampRequestToJSON, counter, REQUEST_TO_JSON_ZERO);
            return responseBean;
        }
    }

    @PostMapping("/multiply")
    public MultiplyResponseBean multiply(@Valid @RequestBody MultiplyRequestBean requestBean) {
        MultiplyResponseBean responseBean = new MultiplyResponseBean();
        counter++;

        String timestampRequestToJSON = formatter.format(new Date());
        postAction();
        postData(timestampRequestToJSON, counter, REQUEST_TO_JSON);

        String timestampRequestToSOAP = formatter.format(new Date());
        postData(timestampRequestToSOAP, counter, REQUEST_TO_SOAP);
        int multiplyResult = WsdlOperation.getInstance().getMultiplyResultFromSoap(requestBean.getIntA(), requestBean.getIntB());
        responseBean.setMultiplyResult(multiplyResult);

        String timestampRequestFromSOAP = formatter.format(new Date());
        postData(timestampRequestFromSOAP, counter, REQUEST_FROM_SOAP);
        return responseBean;
    }

    @PostMapping("/subtract")
    public SubtractResponseBean subtract(@Valid @RequestBody SubtractRequestBean requestBean) {
        SubtractResponseBean responseBean = new SubtractResponseBean();
        counter++;

        String timestampRequestToJSON = formatter.format(new Date());
        postAction();
        postData(timestampRequestToJSON, counter, REQUEST_TO_JSON);

        String timestampRequestToSOAP = formatter.format(new Date());
        postData(timestampRequestToSOAP, counter, REQUEST_TO_SOAP);
        int subtractResult = WsdlOperation.getInstance().getSubtractResultFromSoap(requestBean.getIntA(), requestBean.getIntB());
        responseBean.setSubtractResult(subtractResult);

        String timestampRequestFromSOAP = formatter.format(new Date());
        postData(timestampRequestFromSOAP, counter, REQUEST_FROM_SOAP);
        return responseBean;
    }

    private void postAction() {
        action = new Action();
        action.setInsertDate(new Date());
        actionService.save(action);
    }

    private void postData(String timestamp, int counter, String requestStaticString) {
        LOG.info("[{}] - Call {}. " + requestStaticString, timestamp, counter);
        Data data = new Data(new Date(), "[" + timestamp + "]" + " - Call " + counter + ". " + requestStaticString);
        data.setAction(action);
        dataService.save(data);
    }
}
