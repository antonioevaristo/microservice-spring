package com.ae.microservice.rest.controller;

import com.ae.microservice.domain.exception.TarefaException;
import com.ae.microservice.domain.model.Tarefa;
import com.ae.microservice.domain.service.ITarefaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/tarefa")
@RequiredArgsConstructor
public class TarefaController {

    private final ITarefaService tarefaService;

    @PostMapping
    public ResponseEntity<Tarefa> criandoTarefa(@RequestBody Tarefa tarefa) throws TarefaException {
    return new ResponseEntity<>(tarefaService.create(tarefa), HttpStatus.CREATED);
    }
}
