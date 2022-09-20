package com.aviasi.perkasa.DTO;

import org.springframework.stereotype.Component;

@Component
public class AirPortDTO {

    private long airport_id;
    private String airport_name;
    private String airport_country;

    public long getAirport_id() {
        return airport_id;
    }

    public void setAirport_id(long airport_id) {
        this.airport_id = airport_id;
    }

    public String getAirport_name() {
        return airport_name;
    }

    public void setAirport_name(String airport_name) {
        this.airport_name = airport_name;
    }

    public String getAirport_country() {
        return airport_country;
    }

    public void setAirport_country(String airport_country) {
        this.airport_country = airport_country;
    }


}
