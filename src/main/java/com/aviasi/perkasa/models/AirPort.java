package com.aviasi.perkasa.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "airport")
@Component
public class AirPort {

    @Id
    private Long airport_id;

    @Column(name = "name")
    private String airport_name;

    @Column(name = "country")
    private String airport_country;

    public Long getAirport_id() {
        return airport_id;
    }

    public void setAirport_id(Long airport_id) {
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
