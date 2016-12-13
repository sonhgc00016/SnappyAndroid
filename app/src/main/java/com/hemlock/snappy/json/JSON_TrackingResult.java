package com.hemlock.snappy.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hemlock.snappy.model.Tracking;

/**
 * Created by lookonlyatme on 12/9/16.
 */

public class JSON_TrackingResult {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("tracking")
    @Expose
    private Tracking tracking;
    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Tracking getTracking() {
        return tracking;
    }

    public void setTracking(Tracking tracking) {
        this.tracking = tracking;
    }
}
