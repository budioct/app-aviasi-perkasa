package com.aviasi.perkasa.repositories;

import com.aviasi.perkasa.models.AirPort;
import com.aviasi.perkasa.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface AirPortRepository extends JpaRepository<AirPort, Long> {

//    @Query(value = "select name=:#{#airPort.airport_name} from airport where name =:airPort.airport_name" ,nativeQuery = true)
    @Query("select s from AirPort  as s where  s.airport_name = ?1")
    Optional<AirPort> findByAirport_name(String name);

    //Auto generate id
    @Query(value = "SELECT IFNULL(MAX(CONVERT(airport_id, SIGNED INTEGER)), 0) AS kode FROM airport", nativeQuery = true)
    long generateAirPortId();


    @Query(value = "select COUNT(airport_id) as count from airport where airport_id =:airportid", nativeQuery = true)
    int cekairportid (@Param("airportid") long airportid);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update airport set name=:#{#airport.airport_name}, country=:#{#airport.airport_country}  where airport_id=:#{#airport.airport_id}", nativeQuery = true)
    int update(@Param("airport") AirPort airPort);


}
