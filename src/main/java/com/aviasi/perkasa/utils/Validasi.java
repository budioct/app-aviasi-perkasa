package com.aviasi.perkasa.utils;

import com.aviasi.perkasa.repositories.AirPortRepository;
import com.aviasi.perkasa.repositories.TravelRepository;
import com.aviasi.perkasa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Validasi {

    /**
     * belum di isi,, tunggu repository jadi
     */

    @Autowired
    AirPortRepository airPortRepository;

//    @Autowired
//    EtiketRepository etiketRepository;
//
//    @Autowired
//    FromToRepository fromToRepository;
//
//    @Autowired
//    OrderRepository orderRepository;
//
//    @Autowired
//    PaymentRepository paymentRepository;
//
//    @Autowired
//    QuoteRepository quoteRepository;

    @Autowired
    TravelRepository travelRepository;

    @Autowired
    UserRepository userRepository;

    public Map<String, Object> idtravel(long travel_id){


        Map<String, Object> response = new HashMap<>();
        try{
            int cektravel = travelRepository.cektravelid(travel_id);

            if (cektravel > 0) {
                response.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);

                return response;

            } else  {
                response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
                response.put(Constants.STATUS, "travel not found");

                return response;
            }


        }catch (Exception e){

            response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            response.put(Constants.STATUS, "travel not found");

            return response;
        }

    }


    public Map<String, Object> idairport(long airport_id){


        Map<String, Object> response = new HashMap<>();
        try{
            int cekairport = airPortRepository.cekairportid(airport_id);

            if (cekairport > 0) {
                response.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);

                return response;

            } else  {
                response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
                response.put(Constants.STATUS, "airport not found");

                return response;
            }


        }catch (Exception e){

            response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            response.put(Constants.STATUS, "airport not found");

            return response;
        }

    }

    Map<String, Object> iduser(long id_user){

        Map<String, Object> response = new HashMap<>();

        try{

            int cekuser = userRepository.cekuserid(id_user);

            if (cekuser > 0){
                response.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);
                return response;
            }else{
                response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
                response.put(Constants.STATUS, "user not found");
                return response;
            }

        }catch (Exception e) {
            response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            response.put(Constants.STATUS, "user not found");
        }

        return response;
    }






}
