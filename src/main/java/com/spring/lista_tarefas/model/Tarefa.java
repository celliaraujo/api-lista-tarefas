package com.spring.lista_tarefas.model;


import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class Tarefa {
    private Long id = 1L;
    private String titulo;
    private String descricao;
    private Boolean concluida;
    private OffsetDateTime dataCriacao;

    public Tarefa(Long id, String titulo, String descricao){
        this.setId(id);
        this.titulo = titulo;
        this.descricao = descricao;
        this.concluida = false;
        this.dataCriacao = OffsetDateTime.now(ZoneOffset.UTC);

    }

    public void alterarTitulo(String novoTitulo){
        validarCampo(novoTitulo);
        this.titulo = novoTitulo;
    }

    public void alterarDescricao(String novaDescricao){
        validarCampo(novaDescricao);
        this.descricao = novaDescricao;
    }

    public void concluir(){
        this.concluida = true;
    }

    private void validarCampo(String novoTexto){
        if(novoTexto == null || novoTexto.isBlank()){
            throw new IllegalArgumentException("Campo não pode estar em branco.");
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Boolean isConcluida() {
        return concluida;
    }

    public OffsetDateTime getDataCriacao() {
        return dataCriacao;
    }
}
