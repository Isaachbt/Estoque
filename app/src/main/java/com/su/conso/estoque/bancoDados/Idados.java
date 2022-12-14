package com.su.conso.estoque.bancoDados;

import com.su.conso.estoque.model.DadosProdutos;

import java.util.List;

public interface Idados {

    public boolean salvar(DadosProdutos dados);
    public boolean atualizar(DadosProdutos dados);
    public boolean deletar(DadosProdutos dados);
    public List<DadosProdutos> listar();
}
