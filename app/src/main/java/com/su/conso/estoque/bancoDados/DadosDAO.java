package com.su.conso.estoque.bancoDados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.su.conso.estoque.model.DadosProdutos;

import java.util.ArrayList;
import java.util.List;

public class DadosDAO implements Idados{

        private SQLiteDatabase escreve;
        private SQLiteDatabase le;
        private Context context;


        public DadosDAO(Context context){
            this.context = context;
            BancoDados bd = new BancoDados(context);
            escreve = bd.getWritableDatabase();
            le = bd.getReadableDatabase();

        }

        @Override
        public boolean salvar(DadosProdutos dados) {
            ContentValues cv = new ContentValues();
            cv.put("nome",dados.getNome());
            cv.put("valor_uni",dados.getValor_uni());
            cv.put("kg_uni",dados.getKg_uni());
            cv.put("lucro_Previsto_uni",dados.getLucro_Previsto_uni());
            cv.put("valor_Total",dados.getValor_Total());
            cv.put("lucro_Previsto_total",dados.getLucro_Previsto_total());
            cv.put("Quantindade_P",dados.getQuantindade_P());
            cv.put("Quantindade_P_total",dados.getQuantindade_P_total());

            try{
                escreve.insert(BancoDados.NOME_TABELA,null,cv);
                Log.i("INFO","salvo com sucesso");
            }catch (Exception e){
                Log.e("INFO","Erro ao salvar");
                return false;
            }
            return true;
        }

        @Override
        public boolean atualizar(DadosProdutos dados) {
            ContentValues cv = new ContentValues();
            cv.put("nome",dados.getNome());
            cv.put("valor_uni",dados.getValor_uni());
            cv.put("kg_uni",dados.getKg_uni());
            cv.put("lucro_Previsto_uni",dados.getLucro_Previsto_uni());
            cv.put("valor_Total",dados.getValor_Total());
            cv.put("lucro_Previsto_total",dados.getLucro_Previsto_total());
            cv.put("Quantindade_P",dados.getQuantindade_P());
            cv.put("Quantindade_P_total",dados.getQuantindade_P_total());
            String[] argas = {String.valueOf(dados.getId())};
            try{
                escreve.update(BancoDados.NOME_TABELA,cv,"id=?",argas);

            }catch (Exception e){
                return false;
            }
            return true;
        }

        @Override
        public boolean deletar(DadosProdutos dados) {
            String[] argas = {String.valueOf(dados.getId())};
            try{
                escreve.delete(BancoDados.NOME_TABELA,"id=?",argas);
            }catch (Exception e){
                return false;
            }
            return true;
        }


    public List<DadosProdutos> listar() {
            List<DadosProdutos> dadosList = new ArrayList<>();
            //recuperando os dados da tabela
            String sql = "SELECT * FROM "+BancoDados.NOME_TABELA+" ;";
            Cursor c = le.rawQuery(sql,null);

            while (c.moveToNext()){
                DadosProdutos dados = new DadosProdutos();
                int columnIndex = c.getColumnIndex("id");
                int[] columnIndexArray = {
                        c.getColumnIndex("nome"),
                        c.getColumnIndex("valor_uni"),
                        c.getColumnIndex("kg_uni"),
                        c.getColumnIndex("lucro_Previsto_uni"),
                        c.getColumnIndex("valor_Total"),
                        c.getColumnIndex("lucro_Previsto_total"),
                        c.getColumnIndex("Quantindade_P"),
                        c.getColumnIndex("Quantindade_P_total"),
                };

                String nome = c.getString(Integer.parseInt(String.valueOf(columnIndexArray[0])));
                String valor_uni = c.getString(Integer.parseInt(String.valueOf(columnIndexArray[1])));
                String kg_uni = c.getString(Integer.parseInt(String.valueOf(columnIndexArray[2])));
                String lucro_Previsto_uni = c.getString(Integer.parseInt(String.valueOf(columnIndexArray[3])));
                String valor_Total = c.getString(Integer.parseInt(String.valueOf(columnIndexArray[4])));
                String lucro_Previsto_total = c.getString(Integer.parseInt(String.valueOf(columnIndexArray[5])));
                String Quantindade_P = c.getString(Integer.parseInt(String.valueOf(columnIndexArray[6])));
                String Quantindade_P_total = c.getString(Integer.parseInt(String.valueOf(columnIndexArray[7])));

                Long id = c.getLong(columnIndex);

                dados.setId(id);
                dados.setNome(nome);
                dados.setValor_uni(Double.parseDouble(valor_uni));
                dados.setKg_uni(Double.parseDouble(kg_uni));
                dados.setLucro_Previsto_uni(Double.parseDouble(lucro_Previsto_uni));
                dados.setValor_Total(Double.parseDouble(valor_Total));
                dados.setLucro_Previsto_total(Double.parseDouble(lucro_Previsto_total));
                dados.setQuantindade_P(Integer.parseInt(Quantindade_P));
                dados.setQuantindade_P_total(Integer.parseInt(Quantindade_P_total));

                dadosList.add(dados);
            }
            return dadosList;
        }
}
