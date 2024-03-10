package com.ae.microservice.integration.repository;

import com.ae.microservice.domain.model.Tarefa;
import com.ae.microservice.domain.repository.ITarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class TarefaRepository implements ITarefaRepository {

    private final ITarefaRepositoryEntity tarefaRepositoryEntity;

    @Override
    public Tarefa create(Tarefa tarefa) {
        return null;
    }

    @Override
    public Optional<Tarefa> get(UUID codigo) {
        return Optional.empty();
    }

    @Override
    public Optional<Tarefa> get(String descricao) {
        return Optional.empty();
    }

    @Override
    public List<Tarefa> getAll() {
        return null;
    }

    @Override
    public void detele(UUID codigo) {

    }
}
