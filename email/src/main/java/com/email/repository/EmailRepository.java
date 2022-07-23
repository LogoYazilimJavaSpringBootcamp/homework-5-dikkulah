package com.email.repository;

import com.email.dto.EmailDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends MongoRepository<EmailDto,Long> {

}
