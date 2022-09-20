package com.aviasi.perkasa.repositories;

import com.aviasi.perkasa.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);

    //Auto generate id
    @Query(value = "SELECT IFNULL(MAX(CONVERT(id, SIGNED INTEGER)), 0) AS kode FROM user", nativeQuery = true)
    long generateUserId();

    @Query(value = "select COUNT(id) as count from user where id =:userid",nativeQuery = true)
    int cekuserid (@Param("userid") long userid);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update user set email=:#{#user.email}, name=:#{#user.name}, nophone=:#{#user.nophone}, password=:#{#user.password} " +
            " where id=:#{#user.id}"  ,nativeQuery = true)
    int update(@Param("user") User user);


}
