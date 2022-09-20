package com.aviasi.perkasa.models;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "travel")
@Component
public class Travel {

    @Id
    private Long travel_id;

    @Column(name = "category")
    private String travel_category;

    @Column(name = "kd_travel")
    private String travel_kode;

    public Long getTravel_id() {
        return travel_id;
    }

    public void setTravel_id(Long travel_id) {
        this.travel_id = travel_id;
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
