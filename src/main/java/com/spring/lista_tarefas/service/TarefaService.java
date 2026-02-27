package com.spring.lista_tarefas.service;

import com.spring.lista_tarefas.model.Tarefa;
import com.spring.lista_tarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    @Autowired
    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public Tarefa criarTarefa(String titulo, String descricao){
        return tarefaRepository.salvar(titulo, descricao);
    }

    public Optional<Tarefa> atualizarTarefa(Long id, Tarefa tarefa){
        return tarefaRepository.atualizar(id, tarefa);
    }

    public List<Tarefa> listarTarefas(){
        return tarefaRepository.listar();
    }

    public Optional<Tarefa> buscarTarefaPorId(Long id){
        return tarefaRepository.buscarPorId(id);
    }

    public Optional<Tarefa> concluirTarefa(Long id){
        Optional<Tarefa> tarefaEncontrada = tarefaRepository.buscarPorId(id);
        if(tarefaEncontrada.isPresent()){
            Tarefa tarefa = tarefaEncontrada.get();
            tarefa.concluir();
            return Optional.of(tarefa);
        }
        return Optional.empty();
    }

    public boolean deletarTarefa(Long id){
        return tarefaRepository.deletar(id);
    }
}
