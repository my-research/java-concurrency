package com.example.javaconcurrency.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.example.javaconcurrency.Coupon;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class CouponRepositoryTest {

    @Autowired
    private CouponRepository sut;

    @Test
    void name() {
        Coupon aa = sut.save(new Coupon("aa", 100L));
        System.out.println("aa = " + aa);
        Long id = aa.getId();

        aa.decrease(1L);

        Coupon save = sut.save(new Coupon(id, "gh", aa.getQuantity()));
        System.out.println("save = " + save);
    }
}