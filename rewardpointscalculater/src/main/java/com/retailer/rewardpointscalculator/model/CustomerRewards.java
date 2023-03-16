package com.retailer.rewardpointscalculator.model;

public class CustomerRewards {
    private long customerId;
	private long oneMonthBeforeRewardPoints;
    private long twoMonthsBeforeRewardPoints;
    private long threeMonthsBeforeRewardPoints;
    private long totalRewardPoints;

    public long getCustomerId() {
        return customerId;
    }

    public long getOneMonthBeforeRewardPoints() {
		return oneMonthBeforeRewardPoints;
	}

	public void setOneMonthBeforeRewardPoints(long oneMonthBeforeRewardPoints) {
		this.oneMonthBeforeRewardPoints = oneMonthBeforeRewardPoints;
	}

	public long getTwoMonthsBeforeRewardPoints() {
		return twoMonthsBeforeRewardPoints;
	}

	public void setTwoMonthsBeforeRewardPoints(long twoMonthsBeforeRewardPoints) {
		this.twoMonthsBeforeRewardPoints = twoMonthsBeforeRewardPoints;
	}

	public long getThreeMonthsBeforeRewardPoints() {
		return threeMonthsBeforeRewardPoints;
	}

	public void setThreeMonthsBeforeRewardPoints(long threeMonthsBeforeRewardPoints) {
		this.threeMonthsBeforeRewardPoints = threeMonthsBeforeRewardPoints;
	}

	public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    

    public long getTotalRewardPoints() {
        return totalRewardPoints;
    }

    public void setTotalRewardPoints(long totalRewardPoints) {
        this.totalRewardPoints = totalRewardPoints;
    }
}
