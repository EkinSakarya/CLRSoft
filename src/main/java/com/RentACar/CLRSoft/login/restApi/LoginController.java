package com.RentACar.CLRSoft.login.restApi;

import com.RentACar.CLRSoft.login.loginService.ILoginService;
import com.RentACar.CLRSoft.login.loginRequest.LoginRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

    private final ILoginService loginService;

    public LoginController(ILoginService loginService)
    {
        this.loginService=loginService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest )
    {

        return loginService.login(loginRequest);

    }

}
