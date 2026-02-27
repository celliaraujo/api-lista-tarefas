package com.spring.lista_tarefas.controller;

import com.spring.lista_tarefas.dto.CriarTarefaRequest;
import com.spring.lista_tarefas.model.Tarefa;
import com.spring.lista_tarefas.service.TarefaService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    private final TarefaService tarefaService;


    @Autowired
    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @PostMapping
    public ResponseEntity<Tarefa> criar(@RequestBody CriarTarefaRequest request){
        Tarefa novaTarefa = tarefaService.criarTarefa(request.getTitulo(), request.getDescricao());
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTarefa);
    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> listar(){
        return ResponseEntity.ok(tarefaService.listarTarefas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscarPorId(@PathVariable Long id){
        Optional<Tarefa> tarefaOpt = tarefaService.buscarTarefaPorId(id);
        if(tarefaOpt.isPresent()){
            return ResponseEntity.ok(tarefaOpt.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizar(@PathVariable Long id, Tarefa tarefa){
        Optional<Tarefa> tarefaOpt = tarefaService.buscarTarefaPorId(id);
        if(tarefaOpt.isPresent()){
            Optional<Tarefa> atualizada = tarefaService.atualizarTarefa(id, tarefa);
            return ResponseEntity.ok(atualizada.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        Optional<Tarefa> encontradaOpt = tarefaService.buscarTarefaPorId(id);
        boolean removida = tarefaService.deletarTarefa(id);

        if(removida) return ResponseEntity.noContent().build();
        else return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/concluir")
    public ResponseEntity<Tarefa> concluir(@PathVariable Long id){
        Optional<Tarefa> encontradaOpt = tarefaService.buscarTarefaPorId(id);
        if(encontradaOpt.isPresent()){
            Tarefa tarefa = encontradaOpt.get();
            tarefa.concluir();
            return ResponseEntity.ok(tarefa);
        }
        return ResponseEntity.notFound().build();

    }

}
