package com.aviasi.perkasa.DTO;

import org.springframework.stereotype.Component;

@Component
public class TravelDTO {

    private long travel_id;
    private String travel_category;
    private String travel_deskripsi;
//    private long order_id; // id order

    public long getTravel_id() {
        return travel_id;
    }

    public void setTravel_id(long travel_id) {
        this.travel_id = travel_id;
    }

    public String getTravel_category() {
        return travel_category;
    }

    public void setTravel_category(String travel_category) {
        this.travel_category = travel_category;
    }

    public String getTravel_deskripsi() {
        return travel_deskripsi;
    }

    public void setTravel_deskripsi(String travel_deskripsi) {
        this.travel_deskripsi = travel_deskripsi;
    }
}
