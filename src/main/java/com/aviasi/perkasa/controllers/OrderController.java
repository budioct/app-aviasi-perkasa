package com.aviasi.perkasa.controllers;

import com.aviasi.perkasa.services.OrderService;
import com.aviasi.perkasa.utils.CheckToken;
import com.aviasi.perkasa.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(Constants.API_ORDER)
public class OrderController {

    @Autowired
    OrderService orderService;

    Map<String, Object> response = new HashMap<>();

    Map<String, Object> resChecktoken = new HashMap<>();


    // get all
    @GetMapping("getall")
    public ResponseEntity<Map<String, Object>> getAll(
            @RequestHeader(required = false) String email,
            @RequestHeader(required = false) String token
    ) {
        resChecktoken = CheckToken.checkToken(token, email);
        try {

            if (resChecktoken.get(Constants.STATUS_CODE).equals("00")) {
                response = orderService.getall();
//                response = orderService.getalldua();
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

    // get by id
    @GetMapping("getbyid/{id}")
    public ResponseEntity<Map<String, Object>> getbyid(
            @PathVariable("id") final long id,
            @RequestHeader(required = false) String email,
            @RequestHeader(required = false) String token
    ) {

        resChecktoken = CheckToken.checkToken(token, email);
        try {
            if (resChecktoken.get(Constants.STATUS_CODE).equals("00")) {
                response = orderService.byid(id);
//                response = orderService.byiddua(id);
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

    // save
    @PostMapping("save")
    public ResponseEntity<Map<String, Object>> save(
            @RequestBody final Map<String, Object> req,
            @RequestHeader(required = false) String email,
            @RequestHeader(required = false) String token
    ) {
        resChecktoken = CheckToken.checkToken(token, email);
        try {

            if (resChecktoken.get(Constants.STATUS_CODE).equals("00")) {
                response = orderService.save(req);
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


    // delete
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Map<String, Object>> delete(
            @PathVariable("id") final long id,
            @RequestHeader(required = false) String email,
            @RequestHeader(required = false) String token
    ){

        resChecktoken = CheckToken.checkToken(token, email);
        try{

            if (resChecktoken.get(Constants.STATUS_CODE).equals("00")){
                response = orderService.delete(id);
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
    public ResponseEntity<Map<String, Object>> edit(
            @RequestBody final Map<String, Object> req,
            @PathVariable("id") final long id,
            @RequestHeader(required = false) String email,
            @RequestHeader(required = false) String token
    ){

        resChecktoken = CheckToken.checkToken(token,email);

        try{

            if (resChecktoken.get(Constants.STATUS_CODE).equals("00")){
                response = orderService.edit(req, id);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response = resChecktoken;
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

        }catch(Exception e){
            e.printStackTrace();
            response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            response.put(Constants.STATUS, Constants.FAILED);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        }


    }




}
