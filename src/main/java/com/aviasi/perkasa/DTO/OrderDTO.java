package com.aviasi.perkasa.DTO;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDTO {

    private long order_id;
    private long order_idsecond;
    private String order_status;
    private String order_waktu;
    private long id;

//    private List<TravelDTO> travel;

    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public long getOrder_idsecond() {
        return order_idsecond;
    }

    public void setOrder_idsecond(long order_idsecond) {
        this.order_idsecond = order_idsecond;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getOrder_waktu() {
        return order_waktu;
    }

    public void setOrder_waktu(String order_waktu) {
        this.order_waktu = order_waktu;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

//    public List<TravelDTO> getTravel() {
//        return travel;
//    }
//
//    public void setTravel(List<TravelDTO> travel) {
//        this.travel = travel;
//    }
}
