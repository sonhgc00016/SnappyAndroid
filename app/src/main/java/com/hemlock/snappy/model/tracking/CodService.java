package com.hemlock.snappy.model.tracking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CodService {

    @SerializedName("cost")
    @Expose
    private Integer cost;
    @SerializedName("amount")
    @Expose
    private Integer amount;

    public CodService(Integer cost, Integer amount) {
        this.cost = cost;
        this.amount = amount;
    }

    /**
     * @return The cost
     */
    public Integer getCost() {
        return cost;
    }

    /**
     * @param cost The cost
     */
    public void setCost(Integer cost) {
        this.cost = cost;
    }

    /**
     * @return The amount
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * @param amount The amount
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

}
