package com.movie.dto;

import com.movie.model.type.Currency;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentDto implements Serializable {
    private Long id;
    private String userEmail;
    private @NotBlank String cardNumber;
    private @NotBlank Integer securityCode;
    private Currency currency;
    private BigDecimal amount;
    private LocalDateTime paymentTime;
}
