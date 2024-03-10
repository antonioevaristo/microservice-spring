package com.ae.microservice.integration.repository;

import com.ae.microservice.integration.entity.TarefaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ITarefaRepositoryEntity extends MongoRepository<TarefaEntity, String> {
}
