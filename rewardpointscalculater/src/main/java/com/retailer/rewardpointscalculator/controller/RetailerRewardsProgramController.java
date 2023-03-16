package com.retailer.rewardpointscalculator.controller;

import com.retailer.rewardpointscalculator.model.CustomerRewards;
import com.retailer.rewardpointscalculator.service.RetailerRewardsProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customerRewards")
public class RetailerRewardsProgramController {

    @Autowired
    RetailerRewardsProgramService retailerRewardsProgramService;


    @GetMapping(value = "/{customerId}/rewards",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerRewards> getRewardsByCustomerId(@PathVariable("customerId") Long customerId){
        CustomerRewards customerRewards = retailerRewardsProgramService.getRewardsByCustomerId(customerId);
        return new ResponseEntity<>(customerRewards, HttpStatus.OK);
    }

}
