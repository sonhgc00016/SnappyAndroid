
package com.hemlock.snappy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

class CodService {

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


class LastUpdate {

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

    public LastUpdate(String updatedBy, String updatedAt, String status, String note, String location) {
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

class Package {

    @SerializedName("weight")
    @Expose
    private Integer weight;
    @SerializedName("snippet")
    @Expose
    private String snippet;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("is_liquid_dangerous")
    @Expose
    private Boolean isLiquidDangerous;
    @SerializedName("is_fragile")
    @Expose
    private Boolean isFragile;
    @SerializedName("is_default")
    @Expose
    private Boolean isDefault;
    @SerializedName("id")
    @Expose
    private Integer id;

    public Package(Integer weight, String snippet, Integer quantity, String name, Boolean isLiquidDangerous, Boolean isFragile, Boolean isDefault, Integer id) {
        this.weight = weight;
        this.snippet = snippet;
        this.quantity = quantity;
        this.name = name;
        this.isLiquidDangerous = isLiquidDangerous;
        this.isFragile = isFragile;
        this.isDefault = isDefault;
        this.id = id;
    }

    /**
     * @return The weight
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * @param weight The weight
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    /**
     * @return The snippet
     */
    public String getSnippet() {
        return snippet;
    }

    /**
     * @param snippet The snippet
     */
    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    /**
     * @return The quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * @param quantity The quantity
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
     * @return The isLiquidDangerous
     */
    public Boolean getIsLiquidDangerous() {
        return isLiquidDangerous;
    }

    /**
     * @param isLiquidDangerous The is_liquid_dangerous
     */
    public void setIsLiquidDangerous(Boolean isLiquidDangerous) {
        this.isLiquidDangerous = isLiquidDangerous;
    }

    /**
     * @return The isFragile
     */
    public Boolean getIsFragile() {
        return isFragile;
    }

    /**
     * @param isFragile The is_fragile
     */
    public void setIsFragile(Boolean isFragile) {
        this.isFragile = isFragile;
    }

    /**
     * @return The isDefault
     */
    public Boolean getIsDefault() {
        return isDefault;
    }

    /**
     * @param isDefault The is_default
     */
    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

}

class Services {

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

class To {

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