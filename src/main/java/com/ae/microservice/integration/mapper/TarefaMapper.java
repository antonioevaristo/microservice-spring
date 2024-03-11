package com.ae.microservice.integration.mapper;

import com.ae.microservice.domain.model.Tarefa;
import com.ae.microservice.integration.entity.TarefaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class TarefaMapper {
    public static final TarefaMapper INSTANCE = Mappers.getMapper(TarefaMapper.class);

    @Mapping(target = "tarefaEntity.id",ignore = true)
    public abstract TarefaEntity tarefaToTarefaEntity(Tarefa tarefa);
    public abstract Tarefa tarefaEntityToTarefa(TarefaEntity tarefaEntity);
}
