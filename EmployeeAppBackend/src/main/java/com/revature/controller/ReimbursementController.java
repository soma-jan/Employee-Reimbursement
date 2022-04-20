package com.revature.controller;

import com.revature.dto.ReimburseDTO;
import com.revature.dto.ResponseRemDTO;
import com.revature.dto.ResponsegetRemdto;
import com.revature.exception.UnauthorizedResponse;
import com.revature.service.JWTService;
import com.revature.service.ReimbursementService;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.javalin.http.UploadedFile;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.apache.tika.Tika;

import java.io.InputStream;
import java.util.List;


public class ReimbursementController implements Controller {
    private JWTService jwtservice;
    private ReimbursementService reimbursementservice;

    public ReimbursementController() {
        this.jwtservice =JWTService.getInstance();
        this.reimbursementservice =new ReimbursementService();
    }

    private Handler addReimbursement=(ctx)->{
        String jwt = ctx.header("Authorization").split(" ")[1];

        Jws<Claims> token = this.jwtservice.parseJwt(jwt);
        System.out.println(token.getBody());
        String userid= ctx.pathParam("user_id");
        int id = Integer.parseInt(userid);
        if (!token.getBody().get("user_id").equals(id)) {
            throw new UnauthorizedResponse("You cannot add an assignment for another user");
        }
        // 2. Get the image from the form parameters as well and detect the file type (MIME type)
        System.out.println(ctx.uploadedFiles());
        UploadedFile file = ctx.uploadedFile("image");
        InputStream is = file.getContent(); // This represents the bytes for the file


       // ReimburseDTO rdto= ctx.bodyAsClass(ReimburseDTO.class);
        ReimburseDTO rdto= new ReimburseDTO();
        rdto.setReimb_amount(Integer.parseInt(ctx.formParam("reimb_amount")));
        rdto.setReimb_desc(ctx.formParam("reimb_desc"));
        rdto.setReimb_type(ctx.formParam( "reimb_type"));
        rdto.setImage(is);
        ResponsegetRemdto getDto =this.reimbursementservice.addReimbursement(userid,rdto);
        ctx.status(201);
        ctx.json(getDto);

    };

    private Handler getAllReimbursements = (ctx) -> {
        String jwt = ctx.header("Authorization").split(" ")[1];
        Jws<Claims> token = this.jwtservice.parseJwt(jwt);
        if (!token.getBody().get("user_role").equals("MANAGER")) {
            throw new UnauthorizedResponse("You must be a manager to access this endpoint");
        }
        List<ResponsegetRemdto> reimbursements = this.reimbursementservice.getAllReimbursements();
        ctx.json(reimbursements);
    };
    private Handler getAssignmentImage = (ctx) -> {


        //String userId = ctx.pathParam("user_id");
        String remId = ctx.pathParam("reimburse_id");
        InputStream image = this.reimbursementservice.getReimburseImage(remId);

        Tika tika = new Tika();
        String mimeType = tika.detect(image);

        ctx.header("Content-Type", mimeType); // tell the client what type of image is being sent in the response
        ctx.result(image);
    };
    private Handler UpdateReimbursement = (ctx) -> {
        String jwt = ctx.header("Authorization").split(" ")[1];
        Jws<Claims> token = this.jwtservice.parseJwt(jwt);

        if (!token.getBody().get("user_role").equals("MANAGER")) {
            throw new UnauthorizedResponse("You must be logged in as a manager");
        }

        String remId = ctx.pathParam("reimburse_id");
        String status = ctx.queryParam("status");
        int userId = token.getBody().get("user_id", Integer.class);

        if (status == null) {
            throw new IllegalArgumentException("You need to either approve or deny the reimbursement");
        }

        ResponsegetRemdto getDto =this.reimbursementservice.updateReimbursement(remId,status,userId);
        ctx.status(201);
        ctx.json(getDto);
    };

    private Handler getSpecificUserreimbursements = (ctx) -> {
        String jwt = ctx.header("Authorization").split(" ")[1];
        Jws<Claims> token = this.jwtservice.parseJwt(jwt);

        if (!token.getBody().get("user_role").equals("EMPLOYEE")) {
            throw new UnauthorizedResponse("You must be a employee to access this endpoint");
        }

        String userId = ctx.pathParam("user_id");
        int id = Integer.parseInt(userId);
        if (!token.getBody().get("user_id").equals(id)) {
            throw new UnauthorizedResponse("You cannot obtain reimbursement that don't belong to yourself");
        }

        List<ResponsegetRemdto> rdtos = this.reimbursementservice.getAllReimbursements(id);
        ctx.json(rdtos);
    };

    @Override
    public void mapEndpoints(Javalin app) {
        app.post("/users/{user_id}/reimbursement", addReimbursement); // specific user
        app.get("/users/{user_id}/reimbursements", getSpecificUserreimbursements); // specific user
        app.get("/rembursements", getAllReimbursements);//MANAGER
        app.get("/reimbursements/{reimburse_id}/image", getAssignmentImage);
        app.patch("/rembursement/{reimburse_id}", UpdateReimbursement); // trainers
    }
}
