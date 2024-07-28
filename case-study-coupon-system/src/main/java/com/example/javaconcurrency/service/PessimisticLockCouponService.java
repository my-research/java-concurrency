package com.example.javaconcurrency.service;

import com.example.javaconcurrency.Coupon;
import com.example.javaconcurrency.repository.CouponRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PessimisticLockCouponService {

    private final CouponRepository repository;

    public PessimisticLockCouponService(CouponRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void decrease(Long id, Long quantity) {
        Coupon coupon = repository.findByIdWithLock(id);

        coupon.decrease(quantity);

        repository.save(coupon);
    }
}
