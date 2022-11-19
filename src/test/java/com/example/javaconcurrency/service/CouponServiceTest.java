package com.example.javaconcurrency.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.javaconcurrency.Coupon;
import com.example.javaconcurrency.repository.CouponRepository;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CouponServiceTest {

    public static final long COUPON_ID = 1L;

    @Autowired
    private PessimisticLockCouponService sut;

    @Autowired
    private CouponRepository repository;

    @BeforeEach
    void setUp() {
        repository.save(new Coupon("jang", 100L));
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void 하나_감소() {
        sut.decrease(COUPON_ID, 1L);

        Coupon actual = repository.findById(COUPON_ID).orElseThrow();
        assertThat(actual.getQuantity()).isEqualTo(99);
    }

    @Test
    void 동시에_100_개_감소() throws InterruptedException {
        int threadCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    sut.decrease(COUPON_ID, 1L);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        Coupon coupon = repository.findById(COUPON_ID).orElseThrow();

        // 100 - (100 * 1) = 0
        assertEquals(0, coupon.getQuantity());
    }
}