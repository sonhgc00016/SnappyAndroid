package com.hemlock.snappy.model.tracking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Package {

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
