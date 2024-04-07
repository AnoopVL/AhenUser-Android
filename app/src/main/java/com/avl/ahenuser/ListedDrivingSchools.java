package com.avl.ahenuser;

public class ListedDrivingSchools {
    private String dsName;
    private String address;
    private String phone;
    // You can add more fields as required

    public ListedDrivingSchools() {
        // Default constructor
    }

    public ListedDrivingSchools(String dsName, String address, String phone) {
        this.dsName = dsName;
        this.address = address;
        this.phone = phone;
    }

    public String getName() { return dsName; }

    public void setName(String dsName) {
        this.dsName = dsName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phone;
    }

    public void setPhoneNumber(String phone) {
        this.phone = phone;
    }
}

