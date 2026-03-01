package com.spring.lista_tarefas.repository;

import com.spring.lista_tarefas.model.Tarefa;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TarefaRepository {

    private Map<Long, Tarefa> banco = new HashMap<>();
    private Long sequence = 1L;

    //criar tarefa
    public Tarefa salvar(String titulo, String descricao){
        Tarefa tarefa = new Tarefa(sequence++, titulo, descricao);
        banco.put(tarefa.getId(), tarefa);
        return tarefa;
    }

    //buscar por id
    public Optional<Tarefa> buscarPorId(Long id){
        return Optional.ofNullable(banco.get(id));
    }

    //listar todas
    public List<Tarefa> listar(){
        return new ArrayList<>(banco.values());
    }

    //atualizar tarefa
    public Optional<Tarefa> atualizar(Long id, String titulo, String descricao){
        if(banco.containsKey(id)){
            Tarefa encontrada = banco.get(id);
            encontrada.alterarTitulo(titulo);
            encontrada.alterarDescricao(descricao);
            return Optional.of(encontrada);
        }
        return Optional.empty();
    }

    //deletar tarefa
    public boolean deletar(Long id){
        return banco.remove(id) != null;
    }





}
