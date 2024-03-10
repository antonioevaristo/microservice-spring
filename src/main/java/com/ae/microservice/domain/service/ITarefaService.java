package com.ae.microservice.domain.service;

import com.ae.microservice.domain.enums.PrioridadeEnum;
import com.ae.microservice.domain.exception.TarefaException;
import com.ae.microservice.domain.model.Tarefa;

import java.util.List;
import java.util.UUID;

public interface ITarefaService {

    Tarefa create(Tarefa tarefa) throws TarefaException;
    Tarefa updateStatus(Tarefa tarefa,Boolean status) throws TarefaException;
    List<Tarefa> getAll(PrioridadeEnum prioridadeEnum, Boolean status);
    Tarefa get(String descricao) throws TarefaException;
    Tarefa get(UUID codigo) throws TarefaException;
    void delete(UUID codigo) throws TarefaException;

}
