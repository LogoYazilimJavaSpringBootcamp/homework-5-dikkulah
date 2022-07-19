package com.payment.controller;

import com.payment.dto.PaymentDto;
import com.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("payment")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    ResponseEntity<PaymentDto> createPayment(@RequestBody PaymentDto request){
        return ResponseEntity.ok().body(paymentService.createPayment(request));
    }

    @GetMapping("{email}")
    ResponseEntity<PaymentDto> getPaymentByEmail(@PathVariable String email){
        return ResponseEntity.ok().body(paymentService.getPaymentByEmail(email));
    }


}
