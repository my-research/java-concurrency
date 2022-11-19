package com.example.javaconcurrency;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    private String name;
    @Getter
    private Long quantity;

    public Coupon(String name, Long quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public void decrease(Long quantity) {
        this.quantity -= quantity;
    }
}
