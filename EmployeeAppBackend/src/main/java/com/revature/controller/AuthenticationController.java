package com.revature.controller;

import com.revature.dto.LoginDTO;
import com.revature.model.User;
import com.revature.service.JWTService;
import com.revature.service.UserService;
import io.javalin.Javalin;
import io.javalin.http.Handler;

public class AuthenticationController implements  Controller{
    private UserService userservice;
    private  LoginDTO loginInfo;
    private JWTService jwtservice;

    public AuthenticationController() {
        this.userservice = new UserService();
        this.loginInfo =new LoginDTO();
        this.jwtservice = JWTService.getInstance();
    }

    private Handler login =(ctx)->{

        System.out.println("Login function invoked");
        loginInfo = ctx.bodyAsClass(LoginDTO.class);
        User user = userservice.login(loginInfo.getUsername(),loginInfo.getPassword());
        String jwt =this.jwtservice.createJWT(user);
        ctx.header("Access-Control-Expose-Headers","*");

        ctx.header("Token",jwt);
        ctx.json(user);

    };


    @Override
    public void mapEndpoints(Javalin app) {
         app.post("/login", login);
    }
}
