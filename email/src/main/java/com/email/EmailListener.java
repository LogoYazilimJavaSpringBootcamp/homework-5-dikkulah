package com.email;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailListener {
    private final EmailRepository emailRepository;

    @RabbitListener(queues = "movieAdvisor.email")
    public void emailListener(EmailDto emailDto) {
        log.info(emailDto.getTo()+"  "+ emailDto.getFrom()+" mailli kullanıcı "+emailDto.getTitle()+" isimli filmi ekledi.");
        emailRepository.save(emailDto);
    }
}
