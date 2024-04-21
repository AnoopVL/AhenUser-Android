package com.avl.ahenuser;

public class BookingRequests {
    private String dsName; // Store the ID of the selected driving school
    private String userName;
    private String userPhone;
    private String timeSlot;
    private String status; // Can be "pending", "accepted", or "rejected"
    private String requestId;

    public BookingRequests() {
        // Default constructor
    }


    public BookingRequests(String requestId, String dsName, String userName, String userPhone, String timeSlot) {
        this.requestId = requestId;
        this.dsName = dsName;
        this.userName = userName;
        this.userPhone = userPhone;
        this.timeSlot = timeSlot;
        this.status = "pending"; // Set initial status as pending
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getDsName() {
        return dsName;
    }

    public void setDsName(String dsName) {
        this.dsName = dsName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
