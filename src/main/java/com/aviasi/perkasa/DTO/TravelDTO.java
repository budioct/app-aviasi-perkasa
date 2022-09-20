package com.aviasi.perkasa.DTO;

import org.springframework.stereotype.Component;

@Component
public class TravelDTO {

    private long id;
    private String travel_category;
    private String travel_kode;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTravel_category() {
        return travel_category;
    }

    public void setTravel_category(String travel_category) {
        this.travel_category = travel_category;
    }

    public String getTravel_kode() {
        return travel_kode;
    }

    public void setTravel_kode(String travel_kode) {
        this.travel_kode = travel_kode;
    }
}
