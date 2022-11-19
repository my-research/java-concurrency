package com.example.javaconcurrency.repository;

import com.example.javaconcurrency.Coupon;
import javax.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends CrudRepository<Coupon, Long> {

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "select c from Coupon c where c.id=:id")
    Coupon findByIdWithLock(Long id);
}
