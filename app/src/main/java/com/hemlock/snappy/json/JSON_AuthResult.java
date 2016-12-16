package com.hemlock.snappy.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hemlock.snappy.model.User;

/**
 * Created by lookonlyatme on 12/16/16.
 */

public class JSON_AuthResult {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private Boolean message;
    @SerializedName("data")
    @Expose
    private User user;

    public Boolean getMessage() {
        return message;
    }

    public void setMessage(Boolean message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
