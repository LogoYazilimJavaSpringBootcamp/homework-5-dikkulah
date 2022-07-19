package com.movie.client;


import com.movie.dto.PaymentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "payment-service",url = "http://localhost:8082/payment")
public interface PaymentClient {
    @PostMapping
    ResponseEntity<PaymentDto> createPayment(@RequestBody PaymentDto request);
    @GetMapping("{email}")
    ResponseEntity<PaymentDto> getPaymentByEmail(@PathVariable String email);
}
