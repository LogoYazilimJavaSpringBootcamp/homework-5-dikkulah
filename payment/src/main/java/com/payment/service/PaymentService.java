package com.payment.service;

import com.payment.dto.PaymentDto;
import com.payment.model.Payment;
import com.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final ModelMapper modelMapper;

    public PaymentDto createPayment(PaymentDto request) {
        log.info(request.toString());
        paymentRepository.save(modelMapper.map(request, Payment.class));
        return request;
    }

    public PaymentDto getPaymentByEmail(String email) {
        return modelMapper.map(paymentRepository.findByUserEmail(email), PaymentDto.class);
    }
}
