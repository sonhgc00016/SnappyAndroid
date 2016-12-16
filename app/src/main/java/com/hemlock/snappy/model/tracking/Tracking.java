
package com.hemlock.snappy.model.tracking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

class From {

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
    private String id;
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

    public From(String provinceId, String phoneNumber, String name, String id, String fullAddress, String districtId, String communeId, String address) {
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
    public String getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(String id) {
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


public class Tracking {

    @SerializedName("updates")
    @Expose
    private List<Update> updates = null;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("to")
    @Expose
    private To to;
    @SerializedName("services")
    @Expose
    private Services services;
    @SerializedName("last_update")
    @Expose
    private LastUpdate lastUpdate;
    @SerializedName("inserted_at")
    @Expose
    private String insertedAt;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("from")
    @Expose
    private From from;
    @SerializedName("current_status")
    @Expose
    private String currentStatus;
    @SerializedName("business_id")
    @Expose
    private Integer businessId;
    @SerializedName("balance_adjustment")
    @Expose
    private Integer balanceAdjustment;

    public Tracking(List<Update> updates, String updatedAt, To to, Services services, LastUpdate lastUpdate, String insertedAt, String id, From from, String currentStatus, Integer businessId, Integer balanceAdjustment) {
        this.updates = updates;
        this.updatedAt = updatedAt;
        this.to = to;
        this.services = services;
        this.lastUpdate = lastUpdate;
        this.insertedAt = insertedAt;
        this.id = id;
        this.from = from;
        this.currentStatus = currentStatus;
        this.businessId = businessId;
        this.balanceAdjustment = balanceAdjustment;
    }

    /**
     * @return The updates
     */
    public List<Update> getUpdates() {
        return updates;
    }

    /**
     * @param updates The updates
     */
    public void setUpdates(List<Update> updates) {
        this.updates = updates;
    }

    /**
     * @return The updatedAt
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt The updated_at
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * @return The to
     */
    public To getTo() {
        return to;
    }

    /**
     * @param to The to
     */
    public void setTo(To to) {
        this.to = to;
    }

    /**
     * @return The services
     */
    public Services getServices() {
        return services;
    }

    /**
     * @param services The services
     */
    public void setServices(Services services) {
        this.services = services;
    }

    /**
     * @return The lastUpdate
     */
    public LastUpdate getLastUpdate() {
        return lastUpdate;
    }

    /**
     * @param lastUpdate The last_update
     */
    public void setLastUpdate(LastUpdate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * @return The insertedAt
     */
    public String getInsertedAt() {
        return insertedAt;
    }

    /**
     * @param insertedAt The inserted_at
     */
    public void setInsertedAt(String insertedAt) {
        this.insertedAt = insertedAt;
    }

    /**
     * @return The id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The from
     */
    public From getFrom() {
        return from;
    }

    /**
     * @param from The from
     */
    public void setFrom(From from) {
        this.from = from;
    }

    /**
     * @return The currentStatus
     */
    public String getCurrentStatus() {
        return currentStatus;
    }

    /**
     * @param currentStatus The current_status
     */
    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    /**
     * @return The businessId
     */
    public Integer getBusinessId() {
        return businessId;
    }

    /**
     * @param businessId The business_id
     */
    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    /**
     * @return The balanceAdjustment
     */
    public Integer getBalanceAdjustment() {
        return balanceAdjustment;
    }

    /**
     * @param balanceAdjustment The balance_adjustment
     */
    public void setBalanceAdjustment(Integer balanceAdjustment) {
        this.balanceAdjustment = balanceAdjustment;
    }

}

class Update {

    @SerializedName("updated_by")
    @Expose
    private String updatedBy;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("location")
    @Expose
    private String location;

    public Update(String updatedBy, String updatedAt, String status, String note, String location) {
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
        this.status = status;
        this.note = note;
        this.location = location;
    }

    /**
     * @return The updatedBy
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * @param updatedBy The updated_by
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * @return The updatedAt
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt The updated_at
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * @return The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return The note
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note The note
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * @return The location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location The location
     */
    public void setLocation(String location) {
        this.location = location;
    }

}