package com.aviasi.perkasa.repositories;

import com.aviasi.perkasa.DTO.TravelDTO;
import com.aviasi.perkasa.models.AirPort;
import com.aviasi.perkasa.models.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface TravelRepository extends JpaRepository<Travel, Long> {

    @Query("select t from Travel as t where t.travel_category = ?1")
    Optional<Travel> findByTravel_category(String category);

    @Query(value = "SELECT IFNULL(MAX(CONVERT(travel_id, SIGNED INTEGER)), 0) AS kode FROM travel", nativeQuery = true)
    long generateTravelId();

    @Query(value = "select COUNT(travel_id) as count from travel where travel_id=:travelid",nativeQuery = true)
    int cektravelid (@Param("travelid") long travelid);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update travel set category=:#{#travel.travel_category}, deskripsi=:#{#travel.travel_deskripsi} where travel_id=:#{#travel.travel_id}", nativeQuery = true)
    int update(@Param("travel") Travel travel);

//    @Query(value = "select t.travel_id, t.category as travel_category, t.kd_travel as travel_kode, o.status as order_status, o.waktu as order_waktu, u.name as user_name, u.email as user_email  from travel t left join orders o on o.order_id = t.order_id left join user u on u.id = o.order_id", nativeQuery = true)
//    List<Map<String, Object>> listTravel();

    // select id by user_id tabel orders
//    @Query(value = "select t.travel_id, t.category as travel_category, t.deskripsi as travel_deskripsi from travel t left join orders o on (t.travel_id = o.order_id) where o.user_id =:order_id", nativeQuery = true)
    @Query(value = "select t.travel_id , t.category as travel_category , t.deskripsi as travel_deskripsi from travel t left join orders o on (t.travel_id = o.order_id) where o.travel_id =:order_id", nativeQuery = true)
    List<Travel> travelByOrderid(@Param("order_id") long order_id);



}
