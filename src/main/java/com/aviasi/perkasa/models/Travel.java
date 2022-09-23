package com.aviasi.perkasa.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "travel")
@Component
public class Travel {

    @Id
    private Long travel_id;

    @Column(name = "category")
    private String travel_category;

    @Column(name = "deskripsi")
    private String travel_deskripsi;




//    @OneToOne
//    @JoinColumn(name = "travel_id")
//    private Orders order;





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

    public String getTravel_deskripsi() {
        return travel_deskripsi;
    }

    public void setTravel_deskripsi(String travel_deskripsi) {
        this.travel_deskripsi = travel_deskripsi;
    }

//    public Orders getOrder() {
//        return order;
//    }
//
//    public void setOrder(Orders order) {
//        this.order = order;
//    }
}
