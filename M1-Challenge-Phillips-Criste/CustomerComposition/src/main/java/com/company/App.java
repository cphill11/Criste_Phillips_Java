package com.company;

public class App {
    public static void main(String[] args) {
        Customer customer = new Customer();

        customer.setFirstName("Criste");
        customer.setLastName("Phillips");
        customer.setEmail("c@gmail.com");
        customer.setPhoneNumber(1234567890);
        customer.setRewardsMember(true);
        customer.setShippingStreet1("1234");
        customer.setShippingStreet2("West End");
        customer.setShippingCity("Flint");
        customer.setShippingState("Michigan");
        customer.setShippingZip("12345-6780");


        customer.setBillingStreet1("1234");
        customer.setBillingStreet2("West End");
        customer.setBillingCity("Flint");
        customer.setBillingState("Michigan");
        customer.setBillingZip("12345-6780");

        customer.personalInfo();
    }
}
