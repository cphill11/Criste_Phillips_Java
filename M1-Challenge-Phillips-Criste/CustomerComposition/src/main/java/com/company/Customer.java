package com.company;

public class Customer {
    private String firstName = "";
    private String lastName = "";
    private String email = "";
    private int phoneNumber = 0;
    private boolean rewardsMember = true;

    private String shippingStreet1;
    private String shippingStreet2;
    private String shippingCity;
    private String shippingState;
    private String shippingZip;

    private String billingStreet1;
    private String billingStreet2;
    private String billingCity;
    private String billingState;
    private String billingZip;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isRewardsMember() {
        return rewardsMember;
    }

    public void setRewardsMember(boolean rewardsMember) {
        this.rewardsMember = rewardsMember;
    }

    public String getShippingStreet1() {
        return shippingStreet1;
    }

    public void setShippingStreet1(String shippingStreet1) {
        this.shippingStreet1 = shippingStreet1;
    }

    public String getShippingStreet2() {
        return shippingStreet2;
    }

    public void setShippingStreet2(String shippingStreet2) {
        this.shippingStreet2 = shippingStreet2;
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    public String getShippingState() {
        return shippingState;
    }

    public void setShippingState(String shippingState) {
        this.shippingState = shippingState;
    }

    public String getShippingZip() {
        return shippingZip;
    }

    public void setShippingZip(String shippingZip) {
        this.shippingZip = shippingZip;
    }

    public String getBillingStreet1() {
        return billingStreet1;
    }

    public void setBillingStreet1(String billingStreet1) {
        this.billingStreet1 = billingStreet1;
    }

    public String getBillingStreet2() {
        return billingStreet2;
    }

    public void setBillingStreet2(String billingStreet2) {
        this.billingStreet2 = billingStreet2;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingState() {
        return billingState;
    }

    public void setBillingState(String billingState) {
        this.billingState = billingState;
    }

    public String getBillingZip() {
        return billingZip;
    }

    public void setBillingZip(String billingZip) {
        this.billingZip = billingZip;
    }

    public void personalInfo() {
        System.out.println("My name is: " + firstName + " " + lastName + ".  My email is: " + email + ".  My phone number is: " + phoneNumber + ". Am I rewards member? " + rewardsMember + ".  My shipping address is: " + shippingStreet1 + " " + shippingStreet2 + " " + shippingCity + ", " + shippingState + " " + shippingZip + ".  My billing address is: " + billingStreet1 + " " + billingStreet2 + " " + billingCity + ", " + billingState + " " + billingZip);

    }
}
