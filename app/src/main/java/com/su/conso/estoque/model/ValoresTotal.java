package com.su.conso.estoque.model;

public class ValoresTotal {

    private int Quantindade_P_total ;
    private double lucro_Previsto_total ;
    private double valor_Total ;
    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantindade_P_total() {
        return Quantindade_P_total;
    }

    public void setQuantindade_P_total(int quantindade_P_total) {
        Quantindade_P_total = quantindade_P_total;
    }

    public double getLucro_Previsto_total() {
        return lucro_Previsto_total;
    }

    public void setLucro_Previsto_total(double lucro_Previsto_total) {
        this.lucro_Previsto_total = lucro_Previsto_total;
    }

    public double getValor_Total() {
        return valor_Total;
    }

    public void setValor_Total(double valor_Total) {
        this.valor_Total = valor_Total;
    }
}
