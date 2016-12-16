package com.hemlock.snappy.model.tracking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Services {

    @SerializedName("signature")
    @Expose
    private Object signature;
    @SerializedName("shipping_cost")
    @Expose
    private Integer shippingCost;
    @SerializedName("scheduled")
    @Expose
    private Object scheduled;
    @SerializedName("package")
    @Expose
    private Package _package;
    @SerializedName("name_vi")
    @Expose
    private String nameVi;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("is_receiver_pay")
    @Expose
    private Boolean isReceiverPay;
    @SerializedName("cod_service")
    @Expose
    private CodService codService;

    public Services(Object signature, Integer shippingCost, Object scheduled, Package _package, String nameVi, String name, Boolean isReceiverPay, CodService codService) {
        this.signature = signature;
        this.shippingCost = shippingCost;
        this.scheduled = scheduled;
        this._package = _package;
        this.nameVi = nameVi;
        this.name = name;
        this.isReceiverPay = isReceiverPay;
        this.codService = codService;
    }

    /**
     * @return The signature
     */
    public Object getSignature() {
        return signature;
    }

    /**
     * @param signature The signature
     */
    public void setSignature(Object signature) {
        this.signature = signature;
    }

    /**
     * @return The shippingCost
     */
    public Integer getShippingCost() {
        return shippingCost;
    }

    /**
     * @param shippingCost The shipping_cost
     */
    public void setShippingCost(Integer shippingCost) {
        this.shippingCost = shippingCost;
    }

    /**
     * @return The scheduled
     */
    public Object getScheduled() {
        return scheduled;
    }

    /**
     * @param scheduled The scheduled
     */
    public void setScheduled(Object scheduled) {
        this.scheduled = scheduled;
    }

    /**
     * @return The _package
     */
    public Package getPackage() {
        return _package;
    }

    /**
     * @param _package The package
     */
    public void setPackage(Package _package) {
        this._package = _package;
    }

    /**
     * @return The nameVi
     */
    public String getNameVi() {
        return nameVi;
    }

    /**
     * @param nameVi The name_vi
     */
    public void setNameVi(String nameVi) {
        this.nameVi = nameVi;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The isReceiverPay
     */
    public Boolean getIsReceiverPay() {
        return isReceiverPay;
    }

    /**
     * @param isReceiverPay The is_receiver_pay
     */
    public void setIsReceiverPay(Boolean isReceiverPay) {
        this.isReceiverPay = isReceiverPay;
    }

    /**
     * @return The codService
     */
    public CodService getCodService() {
        return codService;
    }

    /**
     * @param codService The cod_service
     */
    public void setCodService(CodService codService) {
        this.codService = codService;
    }

}
