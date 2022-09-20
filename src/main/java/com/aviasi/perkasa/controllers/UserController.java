package com.aviasi.perkasa.controllers;

import com.aviasi.perkasa.services.UserService;
import com.aviasi.perkasa.utils.CheckToken;
import com.aviasi.perkasa.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(Constants.API_USER)
public class UserController {

    @Autowired
    UserService userService;

    Map<String, Object> response = new HashMap<>();

    Map<String, Object> resChecktoken = new HashMap<>();

    @PostMapping("login")
    public ResponseEntity<Map<String, Object>> login(
            @RequestBody final Map<String, Object> request
    ) {

        try {
            String email = request.get("email").toString();
            String password = request.get("password").toString();

            response = userService.findEmail(email, password);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            e.printStackTrace();
            response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            response.put(Constants.STATUS, Constants.FAILED);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("save")
    public ResponseEntity<Map<String, Object>> save(
            @RequestBody final Map<String, Object> req,
            @RequestHeader(required = false) String email,
            @RequestHeader(required = false) String token
    ) {

        resChecktoken = CheckToken.checkToken(token, email);
        try {

            if (resChecktoken.get(Constants.STATUS_CODE).equals("00")) {
                response = userService.save(req);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response = resChecktoken;
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            response.put(Constants.STATUS, Constants.FAILED);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("getbyid/{id}")
    public ResponseEntity<Map<String, Object>> getByid(
            @PathVariable("id") final long id,
            @RequestHeader(required = false) String email,
            @RequestHeader(required = false) String token
    ) {
        resChecktoken = CheckToken.checkToken(token, email);
        try {
            if (resChecktoken.get(Constants.STATUS_CODE).equals("00")) {
                response = userService.getById(id);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response = resChecktoken;
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            response.put(Constants.STATUS, Constants.FAILED);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("getall")
    public ResponseEntity<Map<String, Object>> getall(
            @RequestHeader(required = false) String email,
            @RequestHeader(required = false) String token
    ) {
        resChecktoken = CheckToken.checkToken(token, email);
        try {
            if (resChecktoken.get(Constants.STATUS_CODE).equals("00")) {
                response = userService.getAll();
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response = resChecktoken;
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            response.put(Constants.STATUS, Constants.FAILED);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Map<String,Object>>delete(
            @PathVariable("id") final long id,
            @RequestHeader(required = false)String email,
            @RequestHeader(required = false)String token

    ){
        resChecktoken = CheckToken.checkToken(token,email);
        try{

            if(resChecktoken.get(Constants.STATUS_CODE).equals("00")){

                response = userService.delete(id);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else {

                response = resChecktoken;
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }



        }catch (Exception e){
            e.printStackTrace();
            response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            response.put(Constants.STATUS, Constants.FAILED);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);


        }
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<Map<String,Object>> edit(
            @RequestBody final Map<String, Object> req,
            @PathVariable("id") final long id,
            @RequestHeader(required = false) String email,
            @RequestHeader(required = false) String token
    ){
        resChecktoken = CheckToken.checkToken(token, email);
        try{
            if (resChecktoken.get(Constants.STATUS_CODE).equals("00")){
                response = userService.edit(req, id);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else {
                response = resChecktoken;
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            e.printStackTrace();
            response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            response.put(Constants.STATUS, Constants.FAILED);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }


}
