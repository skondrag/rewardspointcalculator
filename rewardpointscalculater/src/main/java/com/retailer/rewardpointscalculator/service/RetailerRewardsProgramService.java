package com.retailer.rewardpointscalculator.service;

import com.retailer.rewardpointscalculator.model.CustomerRewards;

public interface RetailerRewardsProgramService {

    public CustomerRewards getRewardsByCustomerId(Long customerId);
}
