package com.aviasi.perkasa.utils;

import com.aviasi.perkasa.repositories.AirPortRepository;
import com.aviasi.perkasa.repositories.TravelRepository;
import com.aviasi.perkasa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AutoGenerateID {

    /**
     * belum di isi,, tunggu repository jadi
     */


    @Autowired
    AirPortRepository airPortRepository;

//
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

    public long airPortID() {
        long banyakData = airPortRepository.generateAirPortId();
        long nomorBerikutnya = banyakData + 1;
        long urutan =0 ;
        if (banyakData == 0) {
            urutan = 1;
        } else {
            urutan = nomorBerikutnya;
        }

        return urutan;
    }

//    public long etiketID() {
//        long banyakData = etiketRepository.generateEtiketId();
//        long nomorBerikutnya = banyakData + 1;
//        long urutan =0 ;
//        if (banyakData == 0) {
//            urutan = 1;
//        } else {
//            urutan = nomorBerikutnya;
//        }
//
//        return urutan;
//    }
//
//    public long fromToID() {
//        long banyakData = fromToRepository.generateFromToId();
//        long nomorBerikutnya = banyakData + 1;
//        long urutan =0 ;
//        if (banyakData == 0) {
//            urutan = 1;
//        } else {
//            urutan = nomorBerikutnya;
//        }
//
//        return urutan;
//    }
//
//    public long orderID() {
//        long banyakData = orderRepository.generateOrderId();
//        long nomorBerikutnya = banyakData + 1;
//        long urutan =0 ;
//        if (banyakData == 0) {
//            urutan = 1;
//        } else {
//            urutan = nomorBerikutnya;
//        }
//
//        return urutan;
//    }
//
//    public long paymentID() {
//        long banyakData = paymentRepository.generatePaymentId();
//        long nomorBerikutnya = banyakData + 1;
//        long urutan =0 ;
//        if (banyakData == 0) {
//            urutan = 1;
//        } else {
//            urutan = nomorBerikutnya;
//        }
//
//        return urutan;
//    }
//
//    public long quoteID() {
//        long banyakData = quoteRepository.generateQuoteId();
//        long nomorBerikutnya = banyakData + 1;
//        long urutan =0 ;
//        if (banyakData == 0) {
//            urutan = 1;
//        } else {
//            urutan = nomorBerikutnya;
//        }
//
//        return urutan;
//    }
//
    public long travelID() {
        long banyakData = travelRepository.generateTravelId();
        long nomorBerikutnya = banyakData + 1;
        long urutan =0 ;
        if (banyakData == 0) {
            urutan = 1;
        } else {
            urutan = nomorBerikutnya;
        }

        return urutan;
    }

    public long userID() {
        long banyakData = userRepository.generateUserId();
        long nomorBerikutnya = banyakData + 1;
        long urutan =0 ;
        if (banyakData == 0) {
            urutan = 1;
        } else {
            urutan = nomorBerikutnya;
        }

        return urutan;
    }



}
