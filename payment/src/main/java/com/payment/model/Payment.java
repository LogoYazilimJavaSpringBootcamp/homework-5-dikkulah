package com.payment.model;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String userEmail;
    private String cardNumber;
    private Integer securityCode;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private BigDecimal amount;
    private LocalDateTime paymentTime;


}
