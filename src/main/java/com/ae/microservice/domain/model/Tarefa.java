package com.ae.microservice.domain.model;

import com.ae.microservice.domain.enums.PrioridadeEnum;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class Tarefa {

    private UUID codigo;
    private String descricao;
    private PrioridadeEnum prioridade;
    private Date horarioTarefa;
    private Boolean status;
}
