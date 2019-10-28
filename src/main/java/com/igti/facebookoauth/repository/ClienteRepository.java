package com.igti.facebookoauth.repository;

import com.igti.facebookoauth.entity.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClienteRepository extends MongoRepository<Cliente, String> {
}
