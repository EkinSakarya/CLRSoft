package com.RentACar.CLRSoft.user.restApi;

import com.RentACar.CLRSoft.user.entity.User;
import com.RentACar.CLRSoft.user.userRequest.UserAddRequest;
import com.RentACar.CLRSoft.user.userRequest.UserQuitRequest;
import com.RentACar.CLRSoft.user.userRequest.UserUpdateRequest;
import com.RentACar.CLRSoft.user.userResponse.FindByIdUserResponse;
import com.RentACar.CLRSoft.user.userResponse.UserResponse;
import com.RentACar.CLRSoft.user.userService.IUserService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService)
    {
        this.userService=userService;
    }
    @GetMapping("/getusers")
    public List<UserResponse> findAll()
    {
        return userService.findAll();
    }

    @PostMapping("/useradd")
    public FindByIdUserResponse userAdd(@RequestBody UserAddRequest userAddRequest)
    {
       return userService.userAdd(userAddRequest);
    }
    @GetMapping("getuserbyid/{id}")
    public FindByIdUserResponse findById(@PathVariable int id )
    {
       return userService.findById(id);
    }

    @PutMapping("userupdate/{id}")
    public User UpdateUser(@RequestBody UserUpdateRequest userUpdateRequest, @PathVariable int id )
    {
        userUpdateRequest.setId(id);
        return userService.update(userUpdateRequest);
    }
    @PutMapping("activeuser/{id}")
    public User quitUser(@RequestBody UserQuitRequest userQuitRequest, @PathVariable int id )
    {
        userQuitRequest.setId(id);
        return userService.userQuit(userQuitRequest);
    }
    @PutMapping("deleteuser/{id}")
    public User userDelete(@PathVariable int id)
    {
        return userService.delete(id);
    }
    @PostMapping("activeusers")
    public ArrayList<User> activeUsers()
    {
        return userService.activeUsers();
    }

}
