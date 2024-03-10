package com.ae.microservice.integration.entity;

import com.ae.microservice.domain.enums.PrioridadeEnum;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Data
@Document(collation = "Tarefa")
public class TarefaEntity {
    @Id
    private String id;
    private UUID codigo;
    private String descricao;
    private PrioridadeEnum prioridade;
    private Date horarioTarefa;
    private Boolean status;
}
