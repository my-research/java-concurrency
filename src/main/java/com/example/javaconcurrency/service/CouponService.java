package com.example.javaconcurrency.service;

import com.example.javaconcurrency.Coupon;
import com.example.javaconcurrency.repository.CouponRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CouponService {
    private final CouponRepository repository;

    public CouponService(CouponRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void decrease(Long id, Long quantity) {
        Coupon coupon = repository.findById(id).orElseThrow();

        coupon.decrease(quantity);

        repository.save(coupon);
    }
}
