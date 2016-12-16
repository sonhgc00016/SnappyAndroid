package com.hemlock.snappy.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hemlock.snappy.model.tracking.Tracking;

import java.util.ArrayList;

/**
 * Created by lookonlyatme on 12/14/16.
 */

public class JSON_ListTrackingResult {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("trackings")
    @Expose
    private ArrayList<Tracking> trackings = null;
    @SerializedName("message")
    @Expose
    private String message;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public ArrayList<Tracking> getTrackings() {
        return trackings;
    }

    public void setTrackings(ArrayList<Tracking> trackings) {
        this.trackings = trackings;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
