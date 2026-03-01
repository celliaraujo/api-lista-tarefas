package com.spring.lista_tarefas.config;

import com.spring.lista_tarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {
    private final TarefaRepository tarefaRepository;

    @Autowired
    public DataLoader(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        tarefaRepository.salvar("Estudar Spring Boot", "Ler documentação oficial");
        tarefaRepository.salvar("Praticar API REST", "Criar endpoints de tarefas");
        tarefaRepository.salvar("Testar com Swagger", "Explorar os endpoints pelo navegador");
        tarefaRepository.salvar("Configurar Swagger", "Adicionar dependência springdoc");
        tarefaRepository.salvar("Criar DTOs", "Separar entrada e saída da API");

    }
}
