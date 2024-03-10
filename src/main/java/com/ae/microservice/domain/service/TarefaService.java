package com.ae.microservice.domain.service;

import com.ae.microservice.domain.enums.PrioridadeEnum;
import com.ae.microservice.domain.exception.TarefaException;
import com.ae.microservice.domain.model.Tarefa;
import com.ae.microservice.domain.repository.ITarefaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TarefaService implements ITarefaService{

    private final ITarefaRepository tarefaRepository;
    @Override
    public Tarefa create(Tarefa tarefa) throws TarefaException {
        if (tarefaRepository.get(tarefa.getCodigo()).isPresent()) {
            throw new TarefaException("Tarefa já cadastrada.");
        } else {
            return tarefaRepository.create(tarefa);
        }
    }

    @Override
    public Tarefa updateStatus(Tarefa tarefa, Boolean status) throws TarefaException {
        Optional<Tarefa> tarefaExistente = tarefaRepository.get(tarefa.getCodigo());
        if (tarefaExistente.isPresent()) {
            tarefaExistente.get().setStatus(status);
            return tarefaRepository.create(tarefaExistente.get());
        } else {
            throw new TarefaException("Tarefa não existe");
        }
    }

    @Override
    public List<Tarefa> getAll(PrioridadeEnum prioridadeEnum, Boolean status) {
        return tarefaRepository.getAll()
                .stream()
                .filter(tarefa -> tarefa.getPrioridade().equals(prioridadeEnum))
                .filter(tarefa -> tarefa.getStatus().equals(status))
                .toList();
    }

    @Override
    public Tarefa get(String descricao) throws TarefaException {
        return tarefaRepository.get(descricao).orElseThrow(() -> new TarefaException("Tarefa não encontrada."));
    }

    @Override
    public Tarefa get(UUID codigo) throws TarefaException {
        return getTarefaCodigo(codigo);
    }

    @Override
    public void delete(UUID codigo) throws TarefaException {
         Tarefa tarefa = getTarefaCodigo(codigo);
         tarefaRepository.detele(tarefa.getCodigo());
    }

    private Tarefa getTarefaCodigo(UUID codigo) throws TarefaException {
        return tarefaRepository.get(codigo).orElseThrow(() -> new TarefaException("Tarefa não encontrada."));
    }

}
