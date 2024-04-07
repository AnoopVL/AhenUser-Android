package com.avl.ahenuser;

public class ListedDrivingSchools {
    private String dsName;
    private String address;
    private String phone;
    private String email;
    private String name;
    public ListedDrivingSchools() {
        // Default constructor
    }

//    public ListedDrivingSchools(String dsName, String address, String phone, String email, String name) {
//        this.dsName = dsName;
//        this.address = address;
//        this.phone = phone;
//        this.email = email;
//        this.name = name;
//    }

    public String getDsName() {
        return dsName;
    }

    public void setDsName(String dsName) {
        this.dsName = dsName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

