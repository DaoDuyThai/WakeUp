package com.wakeup.model;

import android.widget.ImageView;

public class Mission {
    private ImageView missionIcon;
    private String status;

    public Mission() {
    }

    public Mission(ImageView missionIcon, String status) {
        this.missionIcon = missionIcon;
        this.status = status;
    }

    public ImageView getMissionIcon() {
        return missionIcon;
    }

    public void setMissionIcon(ImageView missionIcon) {
        this.missionIcon = missionIcon;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}