package com.ae.microservice.domain.repository;

import com.ae.microservice.domain.model.Tarefa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ITarefaRepository {

    Tarefa create(Tarefa tarefa);
    Optional<Tarefa> get(UUID codigo);
    Optional<Tarefa> get(String descricao);
    List<Tarefa> getAll();

    void detele(UUID codigo);

}
