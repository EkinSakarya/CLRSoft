package com.RentACar.CLRSoft;

import com.RentACar.CLRSoft.customer.entity.Customer;
import com.RentACar.CLRSoft.user.entity.User;
import com.RentACar.CLRSoft.user.userDal.UserDal;

import java.util.List;

public class Validator {


    public boolean isNumeric(String text) {
        try {
            Long.parseLong(text);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean isMail(String text) {
        return text.contains("@") && text.contains(".com");
    }

    public boolean isNumber(String text) {
        return isNumeric(text) && text.length() == 10;
    }

    public boolean isUserEmailValid(List<User> users, String email) {
        for (User usersAll : users) {
            if (usersAll.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
    public boolean isCustomerEmailValid(List<Customer> customers, String email) {
        for (Customer customer : customers) {
            if (customer.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public boolean isUserEmailValid(List<User> users, String email, int id) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                if (id != user.getId()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isCustomerEmailValid(List<Customer> customers, String email, int id) {
        for (Customer customer : customers) {
            if (customer.getEmail().equals(email)) {
                if (id != customer.getId()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isUserPhoneValid(List<User> users, String phoneNumber) {
        for (User user : users) {
            if (user.getPhoneNumber().equals(phoneNumber)) {
                return true;
            }
        }
        return false;
    }

    public boolean isCustomerPhoneValid(List<Customer> customers, String phoneNumber) {
        for (Customer customer : customers) {
            if (customer.getPhoneNumber().equals(phoneNumber)) {
                return true;
            }
        }
        return false;
    }


    public boolean isUserPhoneValid(List<User> users, String phoneNumber, int id) {
        for (User user : users) {
            if (user.getPhoneNumber().equals(phoneNumber)) {
                if (id != user.getId()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isCustomerPhoneValid(List<Customer> customers, String phoneNumber, int id) {
        for (Customer customer : customers) {
            if (customer.getPhoneNumber().equals(phoneNumber)) {
                if (id != customer.getId()) {
                    return true;
                }
            }
        }
        return false;
    }

}
