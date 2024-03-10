package com.ae.microservice.domain.service;


import com.ae.microservice.domain.enums.PrioridadeEnum;
import com.ae.microservice.domain.exception.TarefaException;
import com.ae.microservice.domain.model.Tarefa;
import com.ae.microservice.domain.repository.ITarefaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

class TarefaServiceTest {

    @InjectMocks
    public TarefaService tarefaService;

    private Tarefa tarefa;

    @Mock
    public ITarefaRepository tarefaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        tarefa = getTarefa();
    }

    @Test
    void create_tarefa_sucesso() throws TarefaException {
        Tarefa tarefa = getTarefa();
        Mockito.when(tarefaRepository.get(tarefa.getCodigo())).thenReturn(Optional.empty());
        Mockito.when(tarefaRepository.create(Mockito.any())).thenReturn(tarefa);

        Tarefa tarefa1 = tarefaService.create(tarefa);
        assertThat(tarefa).isEqualTo(tarefa1);
    }

    @Test
    void create_tarefa_exception() throws TarefaException {
        Tarefa tarefa = getTarefa();
        Mockito.when(tarefaRepository.get(tarefa.getCodigo())).thenReturn(Optional.of(tarefa));
        assertThatThrownBy(() -> tarefaService.create(tarefa))
                .isInstanceOf(TarefaException.class)
                .hasMessageContaining("Tarefa já cadastrada.");
    }

    private static Tarefa getTarefa() {
        Tarefa tarefa = new Tarefa();
        tarefa.setCodigo(UUID.randomUUID());
        tarefa.setStatus(Boolean.FALSE);
        tarefa.setDescricao("Acordar cedo");
        tarefa.setPrioridade(PrioridadeEnum.ALTA);
        tarefa.setHorarioTarefa(Calendar.getInstance().getTime());
        return tarefa;
    }
}