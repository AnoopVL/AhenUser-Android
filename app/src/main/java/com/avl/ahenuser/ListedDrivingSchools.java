package com.avl.ahenuser;

public class ListedDrivingSchools {
    private String name;
    private String address;
    private String phoneNumber;
    // You can add more fields as required

    public ListedDrivingSchools() {
        // Default constructor
    }

    public ListedDrivingSchools(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

