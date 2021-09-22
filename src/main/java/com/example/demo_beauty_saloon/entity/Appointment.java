package com.example.demo_beauty_saloon.entity;

import java.time.LocalDateTime;

/**
 * Appointment entity
 *
 */
public class Appointment {

    private long appId;
    private long userId;
    private long masterId;
    private long serviceId;
    private LocalDateTime timestamp;
    private String comment;
    private String paymentStatus;
    private String status;
    private String emailNeeded;

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getMasterId() {
        return masterId;
    }

    public void setMasterId(long masterId) {
        this.masterId = masterId;
    }

    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmailNeeded() {
        return emailNeeded;
    }

    public void setEmailNeeded(String emailNeeded) {
        this.emailNeeded = emailNeeded;
    }

    public String toString() {
        return "Appointment{" +
                "appId=" + appId +
                ", userId='" + userId + '\'' +
                ", masterId='" + masterId + '\'' +
                ", serviceId='" + serviceId + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", comment='" + comment + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", status='" + status + '\'' +
                ", emailNeeded='" + emailNeeded + '\'' +
                '}';
    }

}
