package com.RentACar.CLRSoft.user.userService;

import com.RentACar.CLRSoft.user.entity.User;
import com.RentACar.CLRSoft.user.userRequest.UserAddRequest;
import com.RentACar.CLRSoft.user.userRequest.UserQuitRequest;
import com.RentACar.CLRSoft.user.userRequest.UserUpdateRequest;
import com.RentACar.CLRSoft.user.userResponse.FindByIdUserResponse;
import com.RentACar.CLRSoft.user.userResponse.UserResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<UserResponse> findAll();
    public User update(UserUpdateRequest userUpdateRequest);
    public User delete(int id);
    public FindByIdUserResponse findById(int id);
    public FindByIdUserResponse userAdd(UserAddRequest userAddRequest);
    public User userQuit(UserQuitRequest userQuitRequest);
    public ArrayList<User> activeUsers();
}
