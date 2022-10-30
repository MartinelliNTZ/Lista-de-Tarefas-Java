package com.example.listadetarefas.model;

import java.io.Serializable;

public class Tarefa implements Serializable {
    private Long id;
    private String nomeTarefa;

    public Tarefa() {
    }

    public Tarefa(String nomeTarefa) {
        this.nomeTarefa = nomeTarefa;
    }

    public Tarefa( String nomeTarefa, Long id) {
        this.id = id;
        this.nomeTarefa = nomeTarefa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeTarefa() {
        return nomeTarefa;
    }

    public void setNomeTarefa(String nomeTarefa) {
        this.nomeTarefa = nomeTarefa;
    }
}
