package com.su.conso.estoque.model;

public class DadosProdutos {

    private String nome;
    private long id;
    private double valor_uni;
    private double kg_uni;
    private double lucro_Previsto_uni ;
    private double valor_Total ;
    private double lucro_Previsto_total ;
    private int Quantindade_P ;
    private int Quantindade_P_total ;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getValor_uni() {
        return valor_uni;
    }

    public void setValor_uni(double valor_uni) {
        this.valor_uni = valor_uni;
    }

    public double getKg_uni() {
        return kg_uni;
    }

    public void setKg_uni(double kg_uni) {
        this.kg_uni = kg_uni;
    }

    public double getLucro_Previsto_uni() {
        return lucro_Previsto_uni;
    }

    public void setLucro_Previsto_uni(double lucro_Previsto_uni) {
        this.lucro_Previsto_uni = lucro_Previsto_uni;
    }

    public double getValor_Total() {
        return valor_Total;
    }

    public void setValor_Total(double valor_Total) {
        this.valor_Total = valor_Total;
    }

    public double getLucro_Previsto_total() {
        return lucro_Previsto_total;
    }

    public void setLucro_Previsto_total(double lucro_Previsto_total) {
        this.lucro_Previsto_total = lucro_Previsto_total;
    }

    public int getQuantindade_P() {
        return Quantindade_P;
    }

    public void setQuantindade_P(int quantindade_P) {
        Quantindade_P = quantindade_P;
    }

    public int getQuantindade_P_total() {
        return Quantindade_P_total;
    }

    public void setQuantindade_P_total(int quantindade_P_total) {
        Quantindade_P_total = quantindade_P_total;
    }
}
