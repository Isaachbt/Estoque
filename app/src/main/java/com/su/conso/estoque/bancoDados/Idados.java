package com.su.conso.estoque.bancoDados;

import com.su.conso.estoque.model.DadosProdutos;
import com.su.conso.estoque.model.ValoresTotal;

import java.util.List;

public interface Idados {

    public boolean salvar(DadosProdutos dados);
    public boolean atualizar(DadosProdutos dados);
    public boolean deletar(DadosProdutos dados);
    public boolean salvarTotal(ValoresTotal valores);
    public boolean atualizarTotal(ValoresTotal valores);
    public boolean recuperarDados(ValoresTotal valores);
    public List<DadosProdutos> listar();
}
