package com.revature.controller;

import com.revature.exception.*;
import io.javalin.Javalin;
import io.javalin.http.ExceptionHandler;

import java.lang.IllegalArgumentException;
import java.util.logging.Handler;

public class ExceptionController implements Controller{
    private ExceptionHandler failedLogin=(e,ctx)->{
        ctx.status(400);
        ctx.json(e.getMessage());

    };

        private ExceptionHandler UnauthorizedResponse=(e,ctx)->{
            ctx.status(400);
            ctx.json(e.getMessage());

        };
    private ExceptionHandler UserNotFoundException=(e,ctx)->{
        ctx.status(400);
        ctx.json(e.getMessage());

    };

    private ExceptionHandler IllegalArgumentException=(e,ctx)->{
        ctx.status(400);
        ctx.json(e.getMessage());

    };
    private ExceptionHandler InvalidImageException=(e,ctx)->{
        ctx.status(400);
        ctx.json(e.getMessage());

    };
    private ExceptionHandler ImageNotFoundException=(e,ctx)->{
        ctx.status(400);
        ctx.json(e.getMessage());

    };
    @Override
    public void mapEndpoints(Javalin app) {

        app.exception(FailedLoginException.class,failedLogin);
        app.exception(UnauthorizedResponse.class,UnauthorizedResponse);
        app.exception(UserNotFoundException.class,UserNotFoundException);
        app.exception(IllegalArgumentException.class,IllegalArgumentException);
        app.exception(InvalidImageException.class,InvalidImageException);
        app.exception(ImageNotFoundException.class,ImageNotFoundException);
    }
}
