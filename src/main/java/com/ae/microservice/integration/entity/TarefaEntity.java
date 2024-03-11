package com.ae.microservice.integration.entity;

import com.ae.microservice.domain.enums.PrioridadeEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Document
public class TarefaEntity {
    @Id
    private String id;
    @NonNull
    private String codigo;
    @NonNull
    private String descricao;
    @NonNull
    private PrioridadeEnum prioridade;
    private Date horarioTarefa;
    @NonNull
    private Boolean status;
}
