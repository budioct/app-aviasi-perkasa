package com.aviasi.perkasa.repositories;

import com.aviasi.perkasa.models.Orders;
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
public interface OrderRepository extends JpaRepository<Orders, Long> {

    @Query("select o.order_idsecond from Orders as o where o.order_id  = ?1")
    Optional<Orders> findByOrder_idsecond(long idsecond);

    //Auto generate id
    @Query(value = "SELECT IFNULL(MAX(CONVERT(order_id, SIGNED INTEGER)), 0) AS kode FROM orders", nativeQuery = true)
    long generateOrderId();

    //Auto generate id second
    @Query(value = "SELECT IFNULL(MAX(CONVERT(order_idsecond, SIGNED INTEGER)), 0) AS kodesecond FROM orders", nativeQuery = true)
    long generateOrderIdSecond();

    @Query(value = "select COUNT(order_id) as count from orders where order_id=:orderid", nativeQuery = true)
    int cekorderid (@Param("orderid") long orderid);

    @Query(value = "select COUNT(order_idsecond) as countsecond from orders where order_idsecond=:orderidsecond", nativeQuery = true)
    int cekorderidsecond (@Param("orderidsecond") long orderidsecond);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value ="update orders set order_idsecond=:#{#orders.order_idsecond}, status=:#{#orders.order_status}, waktu=:#{#orders.order_waktu}, user_id=:#{#orders.user.id} where order_id=:#{#orders.order_id}" ,nativeQuery = true)
    int update(@Param("orders") Orders orders);


    @Query(value = "select o.order_id as order_id, o.order_idsecond as order_idsecond, o.status as order_status, o.waktu as order_waktu, u.id as user_id from orders o left join user u on (o.user_id = u.id)", nativeQuery = true)
    List<Map<String, Object>> listOrder();


}
