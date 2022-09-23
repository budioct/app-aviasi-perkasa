package com.aviasi.perkasa.services;

import com.aviasi.perkasa.DTO.OrderDTO;
import com.aviasi.perkasa.DTO.TravelDTO;
import com.aviasi.perkasa.models.Orders;
import com.aviasi.perkasa.models.Travel;
import com.aviasi.perkasa.repositories.OrderRepository;
import com.aviasi.perkasa.repositories.TravelRepository;
import com.aviasi.perkasa.utils.AutoGenerateID;
import com.aviasi.perkasa.utils.Constants;
import com.aviasi.perkasa.utils.Validasi;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TravelService {

    @Autowired
    TravelRepository travelRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    Travel travel;

    @Autowired
    Orders orders;

    @Autowired
    TravelDTO travelDTO;

    @Autowired
    OrderDTO orderDTO;

    @Autowired
    AutoGenerateID autoGenerateID;

    @Autowired
    Gson gson;

    @Autowired
    Validasi validasi;

    // get all
//    public Map<String, Object> getall() {
//        Map<String, Object> ret = new HashMap<>();
//
//        try {
//            List<Travel> all = travelRepository.findAll();
//
//            if (all.size() > 0) {
//                ret.put(Constants.LIST, all);
//                ret.put(Constants.TOTAL, all.size());
//                ret.put(Constants.STATUS, Constants.SUCCESS);
//                ret.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);
//            } else {
//                ret.put(Constants.STATUS, "Travel " + Constants.NOTFOUND);
//                ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
//            }
//
//            return ret;
//
//        } catch (Exception e) {
//
//            ret.put(Constants.STATUS, Constants.FAILED);
//            ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
//            return ret;
//
//        }
//
//    }

    // costume
//    public Map<String, Object> getalldua() {
//        Map<String, Object> ret = new HashMap<>();
//        List<Map<String, Object>> travelList = new ArrayList<>();
//
//        try {
////            List<Travel> all = travelRepository.findAll();
//            travelList = travelRepository.listTravel();
//
//            if (travelList.size() > 0) {
//                ret.put(Constants.LIST, travelList);
//                ret.put(Constants.TOTAL, travelList.size());
//                ret.put(Constants.STATUS, Constants.SUCCESS);
//                ret.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);
//            } else {
//                ret.put(Constants.STATUS, "Travel " + Constants.NOTFOUND);
//                ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
//            }
//
//            return ret;
//
//        } catch (Exception e) {
//
//            ret.put(Constants.STATUS, Constants.FAILED);
//            ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
//            return ret;
//
//        }
//
//    }


    // get by id
//    public Map<String, Object> byid(long id) {
//
//        Map<String, Object> ret = new HashMap<>();
//
//        try {
//
//            Optional<Travel> getID = travelRepository.findById(id);
//
//            if (getID.isPresent()) {
//                ret.put(Constants.DATA, getID);
//                ret.put(Constants.STATUS, Constants.SUCCESS);
//                ret.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);
//            } else {
//                ret.put(Constants.STATUS, "Travel " + Constants.NOTFOUND);
//                ret.put(Constants.STATUS_CODE, "Travel " + Constants.FAILED_CODE);
//            }
//
//            return ret;
//
//        } catch (Exception e) {
//
//            ret.put(Constants.STATUS, Constants.FAILED);
//            ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
//            return ret;
//
//        }
//
//    }

    // delete
//    public Map<String, Object> delete(long id) {
//
//        Map<String, Object> ret = new HashMap<>();
//        Optional<Travel> getID = travelRepository.findById(id);
//
//        if (getID.isPresent()) {
//
//            try {
//                travel.setTravel_id(getID.get().getTravel_id());
//                travelRepository.deleteById(getID.get().getTravel_id());
//                ret.put(Constants.STATUS, Constants.SUCCESS);
//                ret.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);
//
//            } catch (Exception e) {
//                ret.put(Constants.STATUS, Constants.NOTFOUND);
//                ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
//            }
//        } else {
//            ret.put(Constants.STATUS_CODE, Constants.NOTFOUND);
//            ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
//        }
//
//        return ret;
//    }

    // save
//    public Map<String, Object> save(Map<String, Object> req) {
//
//        Map<String, Object> ret = new HashMap<>();
//
//        try {
//            Map<String, Object> travelmap = (Map<String, Object>) req.get(Constants.DATA);
//            Travel cek;
//
//            //CONVERT OBJECT TO JSON STRING
//            String jsonString = new ObjectMapper().writeValueAsString(travelmap);
//            travelDTO = gson.fromJson(jsonString, TravelDTO.class);
//
//            Optional<Orders> getID = orderRepository.findById(orderDTO.getOrder_id());
//
//            if (getID.isPresent()) {
//
//                orders.setOrder_id(travelDTO.getOrder_id());
//                travel.setTravel_id(autoGenerateID.travelID());
//                travel.setTravel_category(travelDTO.getTravel_category());
//                travel.setTravel_kode(travelDTO.getTravel_kode());
//                travel.setOrders(orders);
//
//                cek = travelRepository.save(travel);
//
//                if (cek != null) {
//                    ret.put(Constants.STATUS, Constants.SUCCESS);
//                    ret.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);
//                } else {
//                    ret.put(Constants.STATUS, Constants.FAILED);
//                    ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
//                }
//
//
//            } else {
//                ret.put(Constants.STATUS, "ID Orders " + Constants.NOTFOUND);
//                ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
//
//            }
//
//            return ret;
//
//        } catch (Exception e) {
//            ret.put(Constants.STATUS, Constants.FAILED);
//            ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
//            return ret;
//        }
//
//    }

    // edit
//    public Map<String, Object> edit(Map<String, Object> req, long id) {
//
//        Map<String, Object> ret = new HashMap<>();
//
//        try {
//
//            Optional<Travel> getID = travelRepository.findById(id);
//            if (getID.isPresent()) {
//
//                Map<String, Object> travelmap = (Map<String, Object>) req.get(Constants.DATA);
//
//                //CONVERT OBJECT TO JSON STRING
//                String jsonString = new ObjectMapper().writeValueAsString(travelmap);
//                travelDTO = gson.fromJson(jsonString, TravelDTO.class);
//
//                travel.setTravel_id(getID.get().getTravel_id());
//                travel.setTravel_category(travelDTO.getTravel_category());
//                travel.setTravel_kode(travel.getTravel_kode());
//
//                int cek = travelRepository.update(travel);
//
//                if (cek > 0) {
//                    ret.put(Constants.STATUS, Constants.SUCCESS);
//                    ret.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);
//                } else {
//                    ret.put(Constants.STATUS, Constants.FAILED);
//                    ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
//                }
//
//                return ret;
//
//            } else{
//                ret.put(Constants.STATUS, "ID nga ada" + Constants.NOTFOUND);
//                ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
//            }
//
//            return ret;
//
//        } catch (Exception e) {
//
//            ret.put(Constants.STATUS, Constants.NOTFOUND);
//            ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
//            return  ret;
//        }
//
//
//    }

    public Map<String, Object> getAll() {
        Map<String, Object> ret = new HashMap<>();
        List<Travel> travelList = new ArrayList<>();

        travelList = travelRepository.findAll();

        if (travelList.size() > 0) {
            ret.put(Constants.LIST, travelList);
            ret.put(Constants.TOTAL, travelList.size());
            ret.put(Constants.STATUS, Constants.SUCCESS);
            ret.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);
        } else {
            ret.put(Constants.STATUS, Constants.NOTFOUND);
            ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
        }

        return ret;

    }


    public Map<String, Object> getById(long id) {
        Map<String, Object> ret = new HashMap<>();
        Optional<Travel> getID = travelRepository.findById(id);

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

    public Map<String, Object> save(Map<String, Object> req) {
        Map<String, Object> ret = new HashMap<>();

        try {
            Map<String, Object> travelmap = (Map<String, Object>) req.get(Constants.DATA);

            Travel cek;

            //CONVERT OBJECT TO JSON STRING
            String jsonString = new ObjectMapper().writeValueAsString(travelmap);
            travelDTO = gson.fromJson(jsonString, TravelDTO.class);

            Optional<Travel> cekCategoryDuplicate = travelRepository.findByTravel_category(travelDTO.getTravel_category());

            if (cekCategoryDuplicate.isPresent()) {
                ret.put(Constants.STATUS, "Category Already Exsist");
                ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            } else {
                travel.setTravel_id(autoGenerateID.travelID());
                travel.setTravel_category(travelDTO.getTravel_category());
                travel.setTravel_deskripsi(travelDTO.getTravel_deskripsi());

                cek = travelRepository.save(travel);

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

    public Map<String, Object> edit(Map<String, Object> req, long id) {
        Map<String, Object> ret = new HashMap<>();
        try {
            Optional<Travel> getID = travelRepository.findById(id);

            if (getID.isPresent()) {
                Map<String, Object> travelmap = (Map<String, Object>) req.get(Constants.DATA);

                //CONVERT OBJECT TO JSON STRING
                String jsonString = new ObjectMapper().writeValueAsString(travelmap);
                travelDTO = gson.fromJson(jsonString, TravelDTO.class);

                travel.setTravel_id(getID.get().getTravel_id());
                travel.setTravel_category(travelDTO.getTravel_category());
                travel.setTravel_deskripsi(travelDTO.getTravel_deskripsi());

                int cek = travelRepository.update(travel);

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

    public Map<String, Object> delete(long id) {

        Map<String, Object> ret = new HashMap<>();
        Optional<Travel> getID = travelRepository.findById(id);

        if (getID.isPresent()) {
            try {
                travel.setTravel_id(getID.get().getTravel_id());
                travelRepository.deleteById(getID.get().getTravel_id());
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


}
