package com.aviasi.perkasa.services;

import com.aviasi.perkasa.DTO.AirPortDTO;
import com.aviasi.perkasa.models.AirPort;
import com.aviasi.perkasa.repositories.AirPortRepository;
import com.aviasi.perkasa.utils.AutoGenerateID;
import com.aviasi.perkasa.utils.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AirPortService {

    @Autowired
    AirPortRepository airPortRepository;

    @Autowired
    AirPort airPort;

    @Autowired
    AirPortDTO airPortDTO;

    @Autowired
    AutoGenerateID autoGenerateID;

    @Autowired
    Gson gson;

    // getAll
    public Map<String, Object> getAll(){
        Map<String, Object> ret = new HashMap<>();
        List<AirPort> airPortList = new ArrayList<>();

        airPortList = airPortRepository.findAll();
        System.out.println(airPortList);

        if (airPortList.size() > 0){
            ret.put(Constants.LIST, airPortList);
            ret.put(Constants.TOTAL, airPortList.size());
            ret.put(Constants.STATUS, Constants.SUCCESS);
            ret.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);
        }else {
            ret.put(Constants.STATUS, Constants.NOTFOUND);
            ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
        }

        return ret;

    }

    // get by id
    public Map<String, Object> getById(long id){
        Map<String, Object> ret = new HashMap<>();
        Optional<AirPort> getID = airPortRepository.findById(id);

        if (getID.isPresent()){
            ret.put(Constants.DATA, getID);
            ret.put(Constants.STATUS, Constants.SUCCESS);
            ret.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);
        }else {
            ret.put(Constants.STATUS, Constants.NOTFOUND);
            ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
        }

        return ret;

    }

    // save
    public Map<String, Object> save(Map<String, Object> req){
        Map<String, Object> ret = new HashMap<>();

        try{

            Map<String, Object> airportmap = (Map<String, Object>) req.get(Constants.DATA);
            AirPort cek;

            //CONVERT OBJECT TO JSON STRING
            String jsonString = new ObjectMapper().writeValueAsString(airportmap);
            airPortDTO = gson.fromJson(jsonString, AirPortDTO.class);

            Optional<AirPort> cekNameDuplicate = airPortRepository.findByAirport_name(airPortDTO.getAirport_name());

            if (cekNameDuplicate.isPresent()){
                ret.put(Constants.STATUS, "Name Already Exsist");
                ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            }else{
                airPort.setAirport_id(autoGenerateID.airPortID());
                airPort.setAirport_name(airPortDTO.getAirport_name());
                airPort.setAirport_country(airPortDTO.getAirport_country());

                cek = airPortRepository.save(airPort);

                if (cek != null){
                    ret.put(Constants.STATUS, Constants.SUCCESS);
                    ret.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);
                }else {
                    ret.put(Constants.STATUS, Constants.FAILED);
                    ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
                }
            }

            return ret;


        }catch (Exception e){
            ret.put(Constants.STATUS, Constants.FAILED);
            ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            return ret;
        }

    }

    // edit
    public Map<String, Object> edit(Map<String, Object> req, long id){

        Map<String, Object> ret = new HashMap<>();
        try{

            Optional<AirPort> getID = airPortRepository.findById(id);
            if (getID.isPresent()){
                Map<String, Object> airportmap = (Map<String, Object>) req.get(Constants.DATA);

                //CONVERT OBJECT TO JSON STRING
                String jsonString = new ObjectMapper().writeValueAsString(airportmap);
                airPortDTO = gson.fromJson(jsonString, AirPortDTO.class);

                System.out.println(airPortDTO.getAirport_name());
                System.out.println(airPortDTO.getAirport_country());

                airPort.setAirport_id(getID.get().getAirport_id());
                airPort.setAirport_name(airPortDTO.getAirport_name());
                airPort.setAirport_country(airPortDTO.getAirport_country());

                int cek = airPortRepository.update(airPort);

                if (cek > 0){
                    ret.put(Constants.STATUS, Constants.SUCCESS);
                    ret.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);
                }else {
                    ret.put(Constants.STATUS, Constants.FAILED);
                    ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
                }
                return ret;
            }else {
                ret.put(Constants.STATUS, Constants.NOTFOUND);
                ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            }
            return ret;

        }catch (Exception e){
            ret.put(Constants.STATUS, Constants.NOTFOUND);
            ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            return ret;
        }

    }

    // delete
    public Map<String, Object> delete(long id){
        Map<String, Object> ret = new HashMap<>();
        Optional<AirPort> getID = airPortRepository.findById(id);

        if (getID.isPresent()){
            try{

                airPort.setAirport_id(getID.get().getAirport_id());
                airPortRepository.deleteById(getID.get().getAirport_id());
                ret.put(Constants.STATUS, Constants.SUCCESS);
                ret.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);

            }catch (Exception e){
                ret.put(Constants.STATUS, Constants.FAILED);
                ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            }
        }else {
            ret.put(Constants.STATUS, Constants.NOTFOUND);
            ret.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
        }
        return ret;

    }


}
