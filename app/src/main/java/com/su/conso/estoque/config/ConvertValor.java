package com.su.conso.estoque.config;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class ConvertValor {
    public String convert(String valor){
        if (valor == null) return  "";
        String s = valor;
        String cleanString = s.replaceAll("[,]", ".");
        BigDecimal parsed = new BigDecimal(cleanString).setScale(2, BigDecimal.ROUND_FLOOR).divide(new BigDecimal(100), BigDecimal.ROUND_FLOOR);
        String formatted = NumberFormat.getCurrencyInstance().format(parsed);
        return formatted;
    }
}
