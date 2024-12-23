package com.RentACar.CLRSoft.user.userDal;

import com.RentACar.CLRSoft.user.entity.User;

import java.util.List;

public interface IUserDal {
    List<User> findAll();
    public void add(User user);
    public void update(User user);
    public void delete(User user);
    User findById(int id);
}
