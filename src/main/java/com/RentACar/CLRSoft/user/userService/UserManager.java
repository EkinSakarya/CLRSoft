package com.RentACar.CLRSoft.user.userService;


import com.RentACar.CLRSoft.Validator;
import com.RentACar.CLRSoft.customer.customerDal.CustomerDal;
import com.RentACar.CLRSoft.customer.customerDal.ICustomerDal;
import com.RentACar.CLRSoft.user.entity.User;
import com.RentACar.CLRSoft.user.userDal.IUserDal;
import com.RentACar.CLRSoft.user.userRequest.UserAddRequest;
import com.RentACar.CLRSoft.user.userRequest.UserQuitRequest;
import com.RentACar.CLRSoft.user.userRequest.UserUpdateRequest;
import com.RentACar.CLRSoft.user.userResponse.FindByIdUserResponse;
import com.RentACar.CLRSoft.user.userResponse.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserManager implements IUserService {

    private final IUserDal userDal;
    private final ICustomerDal customerDal;
    private final ModelMapper modelMapper;
    private final Validator validator=new Validator();


    public UserManager(IUserDal userDal, ModelMapper modelMapper, ICustomerDal customerDal) {
        this.userDal = userDal;
        this.customerDal = customerDal;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserResponse> findAll() {

        List<User> users = userDal.findAll();

        return users.stream().map(
                        user -> modelMapper.map(user, UserResponse.class)).
                collect(Collectors.toList());
    }

    @Override
    public FindByIdUserResponse findById(int id) {
        User user = userDal.findById(id);
        if (user == null) {
            return null;
        }
        return modelMapper.map(user, FindByIdUserResponse.class);
    }

    @Override
    public FindByIdUserResponse userAdd(UserAddRequest userAddRequest) {

        if(!validator.isNumber(userAddRequest.getPhoneNumber())||!validator.isMail(userAddRequest.getEmail()))
        {
            return null;
        }
        if(validator.isUserEmailValid(userDal.findAll(),userAddRequest.getEmail())||validator.isUserPhoneValid(userDal.findAll(),userAddRequest.getPhoneNumber()))
        {
            return null;
        }
        if(validator.isCustomerEmailValid(customerDal.findAll(),userAddRequest.getEmail())||validator.isCustomerPhoneValid(customerDal.findAll(),userAddRequest.getPhoneNumber()))
        {
            return null;
        }

        User newUser = modelMapper.map(userAddRequest, User.class);
        newUser.setActive(true);
        this.userDal.add(newUser);
        return this.findById(newUser.getId());

    }

    @Override
    public User update(UserUpdateRequest userUpdateRequest) {

        User oldUser = userDal.findById(userUpdateRequest.getId());

        if (oldUser == null) {
            return null;
        }
        if(!validator.isNumber(userUpdateRequest.getPhoneNumber())||!validator.isMail(userUpdateRequest.getEmail()))
        {
            return null;
        }
        if(validator.isUserEmailValid(userDal.findAll(),userUpdateRequest.getEmail(),userUpdateRequest.getId())||validator.isUserPhoneValid(userDal.findAll(),userUpdateRequest.getPhoneNumber(),userUpdateRequest.getId()))
        {
            return null;
        }

        User updatedUser = modelMapper.map(userUpdateRequest, User.class);
        updatedUser.setActive(oldUser.isActive());
        userDal.update(updatedUser);
        return updatedUser;
    }

    @Override
    public User userQuit(UserQuitRequest userQuitRequest) {

        User oldUser = userDal.findById(userQuitRequest.getId());

        if (oldUser == null) {
            return null;
        }

        User updatedUser = modelMapper.map(userQuitRequest, User.class);

        updatedUser.setName(oldUser.getName());
        updatedUser.setEmail(oldUser.getEmail());
        updatedUser.setPhoneNumber(oldUser.getPhoneNumber());
        updatedUser.setSurname(oldUser.getSurname());
        updatedUser.setPassword(oldUser.getPassword());
        userDal.update(updatedUser);

        return updatedUser;
    }

    @Override
    public ArrayList<User> activeUsers() {
        List<User> users =userDal.findAll();
        ArrayList<User> activeUsers=new ArrayList<User>();
        for(User user:users)
        {
            if(user.isActive())
            {
                activeUsers.add(user);
            }
        }
        return activeUsers;
    }

    @Override
    public User delete(int id) {
        User user = userDal.findById(id);
        if (user == null) {
            return null;
        }
        userDal.delete(user);
        return user;

    }


}
