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

import java.util.*;

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

    @Test
    void update_status_tarefa_sucesso() throws TarefaException {
        Tarefa tarefa = getTarefa();
        Mockito.when(tarefaRepository.get(tarefa.getCodigo())).thenReturn(Optional.of(tarefa));
        Mockito.when(tarefaRepository.create(tarefa)).thenReturn(tarefa);

        Tarefa tarefa1 = tarefaService.updateStatus(tarefa, Boolean.TRUE);
        assertThat(tarefa1.getStatus()).isTrue();
    }
    @Test
    void update_status_tarefa_exception() throws TarefaException {
        Tarefa tarefa = getTarefa();
        Mockito.when(tarefaRepository.get(tarefa.getCodigo())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> tarefaService.updateStatus(tarefa,Boolean.TRUE))
                .isInstanceOf(TarefaException.class)
                .hasMessageContaining("Tarefa não existe");

    }

    @Test
    void getAll_tarefas_prioridade_alta(){
        List<Tarefa> tarefas = Arrays.asList(tarefa,getTarefa(PrioridadeEnum.ALTA));
        Mockito.when(tarefaRepository.getAll()).thenReturn(tarefas);

        List<Tarefa> all = tarefaService.getAll(PrioridadeEnum.ALTA, Boolean.FALSE);
        assertThat(all).isNotEmpty();
        assertThat(all.size()).isEqualTo(1);
        assertThat(all.get(0).getPrioridade()).isEqualTo(PrioridadeEnum.ALTA);
        assertThat(all.get(0).getStatus()).isEqualTo(Boolean.FALSE);

    }
    @Test
    void getAll_tarefas_status_true(){
        List<Tarefa> tarefas = Arrays.asList(tarefa,getTarefa(PrioridadeEnum.ALTA));
        Mockito.when(tarefaRepository.getAll()).thenReturn(tarefas);

        List<Tarefa> all = tarefaService.getAll(PrioridadeEnum.BAIXA, Boolean.TRUE);
        assertThat(all).isNotEmpty();
        assertThat(all.size()).isEqualTo(1);
        assertThat(all.get(0).getPrioridade()).isEqualTo(PrioridadeEnum.BAIXA);
        assertThat(all.get(0).getStatus()).isEqualTo(Boolean.TRUE);
    }

    private static Tarefa getTarefa() {
        Tarefa tarefa = new Tarefa();
        tarefa.setCodigo(UUID.randomUUID());
        tarefa.setStatus(Boolean.TRUE);
        tarefa.setDescricao("Acordar cedo");
        tarefa.setPrioridade(PrioridadeEnum.BAIXA);
        tarefa.setHorarioTarefa(Calendar.getInstance().getTime());
        return tarefa;
    }private static Tarefa getTarefa(PrioridadeEnum prioridadeEnum) {
        Tarefa tarefa = new Tarefa();
        tarefa.setCodigo(UUID.randomUUID());
        tarefa.setStatus(Boolean.FALSE);
        tarefa.setDescricao("Acordar cedo");
        tarefa.setPrioridade(prioridadeEnum);
        tarefa.setHorarioTarefa(Calendar.getInstance().getTime());
        return tarefa;
    }
}