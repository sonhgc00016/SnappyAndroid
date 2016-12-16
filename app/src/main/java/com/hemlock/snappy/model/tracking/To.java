package com.hemlock.snappy.model.tracking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class To {

    @SerializedName("province_id")
    @Expose
    private String provinceId;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("id")
    @Expose
    private Object id;
    @SerializedName("full_address")
    @Expose
    private String fullAddress;
    @SerializedName("district_id")
    @Expose
    private String districtId;
    @SerializedName("commune_id")
    @Expose
    private String communeId;
    @SerializedName("address")
    @Expose
    private String address;

    public To(String provinceId, String phoneNumber, String name, Object id, String fullAddress, String districtId, String communeId, String address) {
        this.provinceId = provinceId;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.id = id;
        this.fullAddress = fullAddress;
        this.districtId = districtId;
        this.communeId = communeId;
        this.address = address;
    }

    /**
     * @return The provinceId
     */
    public String getProvinceId() {
        return provinceId;
    }

    /**
     * @param provinceId The province_id
     */
    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    /**
     * @return The phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber The phone_number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
     * @return The id
     */
    public Object getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Object id) {
        this.id = id;
    }

    /**
     * @return The fullAddress
     */
    public String getFullAddress() {
        return fullAddress;
    }

    /**
     * @param fullAddress The full_address
     */
    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    /**
     * @return The districtId
     */
    public String getDistrictId() {
        return districtId;
    }

    /**
     * @param districtId The district_id
     */
    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    /**
     * @return The communeId
     */
    public String getCommuneId() {
        return communeId;
    }

    /**
     * @param communeId The commune_id
     */
    public void setCommuneId(String communeId) {
        this.communeId = communeId;
    }

    /**
     * @return The address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address The address
     */
    public void setAddress(String address) {
        this.address = address;
    }

}
