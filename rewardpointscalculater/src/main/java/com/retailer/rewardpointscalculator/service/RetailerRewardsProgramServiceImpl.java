package com.retailer.rewardpointscalculator.service;

import com.retailer.rewardpointscalculator.constants.Constants;
import com.retailer.rewardpointscalculator.entity.Customer;
import com.retailer.rewardpointscalculator.entity.CustomerTransaction;
import com.retailer.rewardpointscalculator.model.CustomerRewards;
import com.retailer.rewardpointscalculator.repository.CustomerRepository;
import com.retailer.rewardpointscalculator.repository.CustomerTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RetailerRewardsProgramServiceImpl implements RetailerRewardsProgramService{

    @Autowired
    CustomerTransactionRepository customerTransactionRepository;


    @Autowired
    CustomerRepository customerRepository;

    public CustomerRewards getRewardsByCustomerId(Long customerId) {

        Customer customer = customerRepository.findByCustomerId(customerId);
      /*  if(customer == null)
        {
            throw new RuntimeException("Invalid / Missing customer Id ");
        }*/

        Timestamp lastMonthTimestamp = getDateBasedOnOffSetDays(Constants.daysInMonths);
        Timestamp lastSecondMonthTimestamp = getDateBasedOnOffSetDays(2*Constants.daysInMonths);
        Timestamp lastThirdMonthTimestamp = getDateBasedOnOffSetDays(3*Constants.daysInMonths);

        List<CustomerTransaction> lastMonthTransactions = customerTransactionRepository.findAllByCustomerIdAndTransactionDateBetween(
                customerId, lastMonthTimestamp, Timestamp.from(Instant.now()));
        List<CustomerTransaction> lastSecondMonthTransactions = customerTransactionRepository
                .findAllByCustomerIdAndTransactionDateBetween(customerId, lastSecondMonthTimestamp, lastMonthTimestamp);
        List<CustomerTransaction> lastThirdMonthTransactions = customerTransactionRepository
                .findAllByCustomerIdAndTransactionDateBetween(customerId, lastThirdMonthTimestamp,
                        lastSecondMonthTimestamp);

        Long lastMonthRewardPoints = getTransactionRewards(lastMonthTransactions);
        Long lastSecondMonthRewardPoints = getTransactionRewards(lastSecondMonthTransactions);
        Long lastThirdMonthRewardPoints = getTransactionRewards(lastThirdMonthTransactions);

        CustomerRewards customerRewards = new CustomerRewards();
        customerRewards.setCustomerId(customerId);
        customerRewards.setOneMonthBeforeRewardPoints(lastMonthRewardPoints);
        customerRewards.setTwoMonthsBeforeRewardPoints(lastSecondMonthRewardPoints);
        customerRewards.setThreeMonthsBeforeRewardPoints(lastThirdMonthRewardPoints);
        customerRewards.setTotalRewardPoints(lastMonthRewardPoints + lastSecondMonthRewardPoints + lastThirdMonthRewardPoints);

        return customerRewards;

    }

    private Long getTransactionRewards(List<CustomerTransaction> customerTransactions) {
        return customerTransactions.stream().map(customerTransaction -> calculateRewards(customerTransaction))
                .collect(Collectors.summingLong(r -> r.longValue()));
    }

    private Long calculateRewards(CustomerTransaction t) {
        if (t.getTransactionAmount() > Constants.firstRewardLimit && t.getTransactionAmount() <= Constants.secondRewardLimit) {
            return Math.round(t.getTransactionAmount() - Constants.firstRewardLimit);
        } else if (t.getTransactionAmount() > Constants.secondRewardLimit) {
            return Math.round(t.getTransactionAmount() - Constants.secondRewardLimit) * 2
                    + (Constants.secondRewardLimit - Constants.firstRewardLimit);
        } else
            return 0l;

    }

    public Timestamp getDateBasedOnOffSetDays(int days) {
        return Timestamp.valueOf(LocalDateTime.now().minusDays(days));
    }

}
