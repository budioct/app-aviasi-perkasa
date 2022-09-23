package com.aviasi.perkasa.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
@Component
public class Orders {

    @Id
    private Long order_id;

    @Column(name = "order_idsecond")
    private Long order_idsecond;

    @Column(name = "status")
    private String order_status;

    @Column(name = "waktu")
    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy HH:mm",
            timezone = "GMT+7")
    @Temporal(TemporalType.TIMESTAMP)
    private Date order_waktu;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;




//    @OneToOne(mappedBy = "order")
//    private Travel travel;



    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public Long getOrder_idsecond() {
        return order_idsecond;
    }

    public void setOrder_idsecond(Long order_idsecond) {
        this.order_idsecond = order_idsecond;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public Date getOrder_waktu() {
        return order_waktu;
    }

    public void setOrder_waktu(Date order_waktu) {
        this.order_waktu = order_waktu;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public Travel getTravel() {
//        return travel;
//    }

//    public void setTravel(Travel travel) {
//        this.travel = travel;
//    }
}
