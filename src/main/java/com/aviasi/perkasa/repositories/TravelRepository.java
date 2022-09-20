package com.aviasi.perkasa.repositories;

import com.aviasi.perkasa.models.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface TravelRepository extends JpaRepository<Travel, Long> {

    @Query(value = "SELECT IFNULL(MAX(CONVERT(travel_id, SIGNED INTEGER)), 0) AS kode FROM travel", nativeQuery = true)
    long generateTravelId();

    @Query(value = "select COUNT(travel_id) as count from travel where travel_id =:travelid",nativeQuery = true)
    int cektravelid (@Param("travelid") long travelid);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update travel set category=:#{#travel.travel_category}, kd_travel=:#{#travel.travel_kode} where travel_id=:#{#travel.travel_id}", nativeQuery = true)
    int update(@Param("travel") Travel travel);

}
