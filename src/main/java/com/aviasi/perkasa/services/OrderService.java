package com.aviasi.perkasa.services;

import com.aviasi.perkasa.DTO.OrderDTO;
import com.aviasi.perkasa.DTO.TravelDTO;
import com.aviasi.perkasa.DTO.UserDTO;
import com.aviasi.perkasa.models.Orders;
import com.aviasi.perkasa.models.Travel;
import com.aviasi.perkasa.models.User;
import com.aviasi.perkasa.repositories.OrderRepository;
import com.aviasi.perkasa.repositories.TravelRepository;
import com.aviasi.perkasa.repositories.UserRepository;
import com.aviasi.perkasa.utils.AutoGenerateID;
import com.aviasi.perkasa.utils.Constants;
import com.aviasi.perkasa.utils.ParseDate;
import com.aviasi.perkasa.utils.Validasi;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TravelRepository travelRepository;

    @Autowired
    Orders orders;

    @Autowired
    User user;

    @Autowired
    OrderDTO orderDTO;

    @Autowired
    UserDTO userDTO;

    @Autowired
    AutoGenerateID autoGenerateID;

    @Autowired
    Gson gson;

    @Autowired
    ParseDate parseDate;

    @Autowired
    Validasi validasi;

    // getall
    public Map<String, Object> getall() {
        Map<String, Object> ret = new HashMap<>();
        try {
            List<Orders> all = orderRepository.findAll();

            if (all.size() > 0) {

                ret.put(Constants.LIST, all);
                ret.put(Constants.TOTAL, all.size());
                ret.put(Constants.STATUS, Constants.SUCCESS);
                ret.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);

            } else {
                ret.put(Constants.STATUS, " Order " + Constants.NOTFOUND);
                ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            }
            return ret;
        } catch (Exception e) {
            ret.put(Constants.STATUS, Constants.FAILED);
            ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            return ret;
        }
    }

//    public Map<String, Object> getalldua() {
//        Map<String, Object> ret = new HashMap<>();
//        List<Map<String, Object>> orderList = new ArrayList<>();
//
//
//        try {
////            List<Orders> all = orderRepository.findAll();
//            orderList = orderRepository.listOrder();
//
//
//            if (orderList.size() > 0) {
//
//                ret.put(Constants.LIST, orderList);
//                ret.put(Constants.TOTAL, orderList.size());
//                ret.put(Constants.STATUS, Constants.SUCCESS);
//                ret.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);
//
//            } else {
//                ret.put(Constants.STATUS, " Order " + Constants.NOTFOUND);
//                ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
//            }
//            return ret;
//        } catch (Exception e) {
//            ret.put(Constants.STATUS, Constants.FAILED);
//            ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
//            return ret;
//        }
//    }

    // getByid
    public Map<String, Object> byid(long id) {
        Map<String, Object> ret = new HashMap<>();
        try {
            Optional<Orders> getID = orderRepository.findById(id);

            if (getID.isPresent()) {
                ret.put(Constants.DATA, getID);
                ret.put(Constants.STATUS, Constants.STATUS);
                ret.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);
            } else {
                ret.put(Constants.STATUS, "Order " + Constants.NOTFOUND);
                ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            }
            return ret;

        } catch (Exception e) {
            ret.put(Constants.STATUS, Constants.FAILED);
            ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            return ret;
        }
    }

//    public Map<String, Object> byiddua(long id) {
//        Map<String, Object> ret = new HashMap<>();
//        Optional<Orders> getID = orderRepository.findById(id);
//
//
//
//        try {
//
//            if (getID.isPresent()) {
//                orders.setOrder_id(getID.get().getOrder_id());
//                orders.setOrder_idsecond(getID.get().getOrder_idsecond());
//                orders.setOrder_status(getID.get().getOrder_status());
//                orders.setOrder_waktu(getID.get().getOrder_waktu());
//                orders.setUser(getID.get().getUser());
//                List<Travel> listTravel = travelRepository.travelByOrderid(getID.get().getOrder_id());
////                orders.setTravel(listTravel);
//
//                ret.put(Constants.DATA, orders);
//                ret.put("List travel", orderDTO);
//                ret.put(Constants.STATUS, Constants.SUCCESS);
//                ret.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);
//
//            } else {
//
//                ret.put(Constants.STATUS, Constants.NOTFOUND);
//                ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
//            }
//
//            return ret;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            ret.put(Constants.STATUS, Constants.NOTFOUND);
//            ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
//            return ret;
//        }
//    }


    // delete
    public Map<String, Object> delete(long id) {

        Map<String, Object> ret = new HashMap<>();
        Optional<Orders> getID = orderRepository.findById(id);
        if (getID.isPresent()) {
            try {

                orders.setOrder_id(getID.get().getOrder_id());
                orderRepository.deleteById(getID.get().getOrder_id());
                ret.put(Constants.STATUS, Constants.SUCCESS);
                ret.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);

            } catch (Exception e) {
                ret.put(Constants.STATUS, Constants.NOTFOUND);
                ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            }
        } else {
            ret.put(Constants.STATUS, Constants.NOTFOUND);
            ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
        }

        return ret;

    }

    // save
    public Map<String, Object> save(Map<String, Object> req) {
        Map<String, Object> ret = new HashMap<>();
        Map<String, Object> validasi_user;

        try {

            Map<String, Object> ordermap = (Map<String, Object>) req.get(Constants.DATA);
//            for ( Object data : ordermap.entrySet()) {
//                System.out.println(data);
//            }

            Orders cek;

            //CONVERT OBJECT TO JSON STRING
            String jsonString = new ObjectMapper().writeValueAsString(ordermap);
            orderDTO = gson.fromJson(jsonString, OrderDTO.class); //  json to object java
            System.out.println(orderDTO.getOrder_idsecond());
            System.out.println(orderDTO.getOrder_status());
            System.out.println(orderDTO.getOrder_waktu());
            System.out.println(orderDTO.getId());

            Optional<User> getID = userRepository.findById(orderDTO.getId()); // filtering id pada objek user
            System.out.println(getID.get().getId());
            System.out.println(getID.get().getName());
            System.out.println(getID.get().getEmail());
            System.out.println(getID.get().getPassword());
            System.out.println(getID.get().getRole());


            if (getID.isPresent()) {

                Optional<Orders> cekduplicate = orderRepository.findByOrder_idsecond(orderDTO.getOrder_idsecond());

                if (cekduplicate.isPresent()) {
                    ret.put(Constants.STATUS, "Id Second Already Exsist");
                    ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
                } else {
                    user.setId(orderDTO.getId());
                    orders.setOrder_id(autoGenerateID.orderID()); // // genereate id
                    orders.setOrder_idsecond(autoGenerateID.orderIDsecond()); // genereate id
//                    orders.setOrder_idsecond(orderDTO.getOrder_idsecond());
                    orders.setOrder_status(orderDTO.getOrder_status());
                    orders.setOrder_waktu(parseDate.parseDate(orderDTO.getOrder_waktu()));
                    validasi_user = validasi.iduser(orderDTO.getId());
                    orders.setUser(user);

                    if (validasi_user.get(Constants.STATUS_CODE).equals("00")) {

                        cek = orderRepository.save(orders);

                        if (cek != null) {
                            ret.put(Constants.STATUS, Constants.SUCCESS);
                            ret.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);
                        } else {
                            ret.put(Constants.STATUS, "Nga bisa di save coyyy " + Constants.FAILED);
                            ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
                        }

                    } else {
                        ret.put(Constants.STATUS, "Id belum di input " + Constants.FAILED);
                        ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
                    }

                }

            } else {
                ret.put(Constants.STATUS, "ID Orders " + Constants.NOTFOUND);
                ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            }
            return ret;

        } catch (Exception e) {
            ret.put(Constants.STATUS, "Catch Nga bisa " + Constants.FAILED);
            ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            return ret;
        }

    }


    // edit
    public Map<String, Object> edit(Map<String, Object> req, long id) {
        Map<String, Object> ret = new HashMap<>();
        try {

            Optional<Orders> getID = orderRepository.findById(id);

            if (getID.isPresent()) {
                Map<String, Object> ordermap = (Map<String, Object>) req.get(Constants.DATA);

                //CONVERT OBJECT TO JSON STRING
                String jsonString = new ObjectMapper().writeValueAsString(ordermap);
                orderDTO = gson.fromJson(jsonString, OrderDTO.class);

                orders.setOrder_id(getID.get().getOrder_id());
                orders.setOrder_idsecond(orderDTO.getOrder_idsecond());
                orders.setOrder_status(orderDTO.getOrder_status());
                orders.setOrder_waktu(parseDate.parseDate(orderDTO.getOrder_waktu()));

                int cek = orderRepository.update(orders);

                if (cek > 0) {
                    ret.put(Constants.STATUS, Constants.SUCCESS);
                    ret.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);
                } else {
                    ret.put(Constants.STATUS, Constants.FAILED);
                    ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
                }
                return ret;

            } else {
                ret.put(Constants.STATUS, "ID nga ada " + Constants.NOTFOUND);
                ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            }

            return ret;

        } catch (Exception e) {
            ret.put(Constants.STATUS, Constants.NOTFOUND);
            ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            return ret;
        }
    }


}
