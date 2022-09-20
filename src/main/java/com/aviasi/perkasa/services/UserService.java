package com.aviasi.perkasa.services;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.aviasi.perkasa.DTO.UserDTO;
import com.aviasi.perkasa.models.User;
import com.aviasi.perkasa.repositories.UserRepository;
import com.aviasi.perkasa.utils.AutoGenerateID;
import com.aviasi.perkasa.utils.Constants;
import com.aviasi.perkasa.utils.GenerateJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.scanner.Constant;

import java.util.*;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    User user;

    @Autowired
    UserDTO userDTO;

    @Autowired
    AutoGenerateID autoGenerateID;

    @Autowired
    Gson gson;

    // find email (login)
    public Map<String, Object> findEmail(String email, String password) {
        Map<String, Object> ret = new HashMap<>();
        Optional<User> cek = userRepository.findUserByEmail(email);

        if (cek.isPresent()) {
            if (password.equals(cek.get().getPassword())) {

                String Token = GenerateJWT.createToken(email);

                ret.put(Constants.TOKEN, Token);
                ret.put("role", cek.get().getRole());
                ret.put(Constants.STATUS, "login berhasil");
                ret.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);
            } else {
                ret.put(Constants.STATUS, "password salah");
                ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            }
        } else {
            ret.put(Constants.STATUS, "user tidak terdaftar");
            ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
        }
        return ret;

    }

    // save
    public Map<String, Object> save(Map<String, Object> req) {
        Map<String, Object> ret = new HashMap<>();

        try {
            Map<String, Object> usermap = (Map<String, Object>) req.get(Constants.DATA);

            User cek;

            //CONVERT OBJECT TO JSON STRING
            String jsonString = new ObjectMapper().writeValueAsString(usermap);
            userDTO = gson.fromJson(jsonString, UserDTO.class);

            System.out.println(userDTO.getEmail());

            Optional<User> cekEmailDuplicate = userRepository.findUserByEmail(userDTO.getEmail());

            if (cekEmailDuplicate.isPresent()) {
                ret.put(Constants.STATUS, "Email Already Exsist");
                ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            } else {
                user.setId(autoGenerateID.userID());
                user.setName(userDTO.getName());
                user.setPassword(userDTO.getPassword());
                user.setEmail(userDTO.getEmail());
                user.setNophone(userDTO.getNophone());
                user.setRole(userDTO.getRole());

                cek = userRepository.save(user);

                if (cek != null) {
                    ret.put(Constants.STATUS, Constants.SUCCESS);
                    ret.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);
                } else {
                    ret.put(Constants.STATUS, Constants.FAILED);
                    ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
                }

            }

            return ret;

        } catch (Exception e) {
            ret.put(Constants.STATUS, Constants.FAILED);
            ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            return ret;
        }
    }

    // edit
    public Map<String, Object> edit(Map<String, Object> req, long id) {
        Map<String, Object> ret = new HashMap<>();
        try {
            Optional<User> getID = userRepository.findById(id);

            if (getID.isPresent()) {
                Map<String, Object> usermap = (Map<String, Object>) req.get(Constants.DATA);

                //CONVERT OBJECT TO JSON STRING
                String jsonString = new ObjectMapper().writeValueAsString(usermap);
                userDTO = gson.fromJson(jsonString, UserDTO.class);
                System.out.println(userDTO.getEmail());

                user.setId(getID.get().getId());
                user.setName(userDTO.getName());
                user.setPassword(userDTO.getPassword());
                user.setEmail(userDTO.getEmail());
                user.setNophone(userDTO.getNophone());
//                user.setRole(userDTO.getRole());

                int cek = userRepository.update(user);

                if (cek > 0) {
                    ret.put(Constants.STATUS, Constants.SUCCESS);
                    ret.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);
                } else {
                    ret.put(Constants.STATUS, Constants.FAILED);
                    ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
                }
                return ret;
            } else {
                ret.put(Constants.STATUS, Constants.NOTFOUND);
                ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            }

            return ret;

        } catch (Exception e) {
            ret.put(Constants.STATUS, Constants.FAILED);
            ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            return ret;
        }

    }

    // delete
    public Map<String, Object> delete(long id) {

        Map<String, Object> ret = new HashMap<>();
        Optional<User> getID = userRepository.findById(id);

        if (getID.isPresent()) {
            try {
                user.setId(getID.get().getId());
                userRepository.deleteById(getID.get().getId());
                ret.put(Constants.STATUS, Constants.SUCCESS);
                ret.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);

            } catch (Exception e) {
                ret.put(Constants.STATUS, Constants.FAILED);
                ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            }

        } else {
            ret.put(Constants.STATUS, Constants.NOTFOUND);
            ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
        }

        return ret;
    }

    // get by id
    public Map<String, Object> getById(long id) {
        Map<String, Object> ret = new HashMap<>();
        Optional<User> getID = userRepository.findById(id);

        if (getID.isPresent()) {
            ret.put(Constants.DATA, getID);
            ret.put(Constants.STATUS, Constants.SUCCESS);
            ret.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);
        } else {
            ret.put(Constants.STATUS, Constants.NOTFOUND);
            ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
        }
        return ret;
    }

    public Map<String, Object> getAll() {
        Map<String, Object> ret = new HashMap<>();
        List<User> userList = new ArrayList<>();

        userList = userRepository.findAll();
        System.out.println(userList);

        if (userList.size() > 0) {
            ret.put(Constants.LIST, userList);
            ret.put(Constants.TOTAL, userList.size());
            ret.put(Constants.STATUS, Constants.SUCCESS);
            ret.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);
        } else {
            ret.put(Constants.STATUS, Constants.NOTFOUND);
            ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
        }

        return ret;
    }


}
