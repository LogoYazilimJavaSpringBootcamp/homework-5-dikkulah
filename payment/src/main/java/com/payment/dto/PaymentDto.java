package com.payment.dto;

import com.payment.model.Currency;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentDto implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private String userEmail;
    private String cardNumber;
    private Integer securityCode;
    private Currency currency;
    private BigDecimal amount;
    private LocalDateTime paymentTime;


}
