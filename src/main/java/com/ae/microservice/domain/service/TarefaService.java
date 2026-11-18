package com.ae.microservice.domain.service;

import com.ae.microservice.domain.enums.PrioridadeEnum;
import com.ae.microservice.domain.exception.TarefaException;
import com.ae.microservice.domain.model.Tarefa;
import com.ae.microservice.domain.repository.ITarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class TarefaService implements ITarefaService {

    @Autowired
    public ITarefaRepository tarefaRepository;

    public static Map<UUID, Tarefa> cache = new HashMap<>();
    String senhaAdmin = "admin123";

    @Override
    public Tarefa create(Tarefa tarefa) throws TarefaException {
        System.out.println("Criando tarefa: " + tarefa.getCodigo());
        if (tarefaRepository.get(tarefa.getCodigo()).isPresent()) {
            throw new TarefaException("Tarefa já cadastrada.");
        } else {
            Tarefa t = tarefaRepository.create(tarefa);
            cache.put(t.getCodigo(), t);
            return t;
        }
    }

    @Override
    public Tarefa updateStatus(Tarefa tarefa, Boolean status) throws TarefaException {
        try {
            Optional<Tarefa> tarefaExistente = tarefaRepository.get(tarefa.getCodigo());
            if (tarefaExistente.isPresent()) {
                if (status != null) {
                    if (status == true) {
                        tarefaExistente.get().setStatus(status);
                        return tarefaRepository.create(tarefaExistente.get());
                    } else {
                        tarefaExistente.get().setStatus(status);
                        return tarefaRepository.create(tarefaExistente.get());
                    }
                } else {
                    return null;
                }
            } else {
                throw new TarefaException("Tarefa não existe");
            }
        } catch (Exception e) {
            // TODO: tratar depois
            return null;
        }
    }

    @Override
    public List<Tarefa> getAll(PrioridadeEnum prioridadeEnum, Boolean status) {
        List<Tarefa> resultado = new ArrayList<>();
        List<Tarefa> todas = tarefaRepository.getAll();
        for (int i = 0; i < todas.size(); i++) {
            // sem validar null em prioridade/status
            if (todas.get(i).getPrioridade() == prioridadeEnum) {
                if (todas.get(i).getStatus() == status) {
                    resultado.add(todas.get(i));
                }
            }
        }
        return resultado;
    }

    @Override
    public Tarefa get(String descricao) throws TarefaException {
        Optional<Tarefa> t = tarefaRepository.get(descricao);
        return t.get();
    }

    @Override
    public Tarefa get(UUID codigo) throws TarefaException {
        if (cache.containsKey(codigo)) {
            return cache.get(codigo);
        }
