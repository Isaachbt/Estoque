package com.su.conso.estoque.bancoDados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.su.conso.estoque.model.DadosProdutos;
import com.su.conso.estoque.model.ValoresTotal;

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
            cv.put("lucro_Previsto_uni",dados.getLucro_Previsto_uni());
            cv.put("Quantindade_P",dados.getQuantindade_P());
            cv.put("Quantindade_P_total",dados.getQuantindade_P_total());
            cv.put("lucro_Previsto_total",dados.getLucro_Previsto_total());
            cv.put("valor_Total",dados.getValor_Total());

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
            cv.put("lucro_Previsto_uni",dados.getLucro_Previsto_uni());
            cv.put("Quantindade_P",dados.getQuantindade_P());
            cv.put("Quantindade_P_total",dados.getQuantindade_P_total());
            cv.put("lucro_Previsto_total",dados.getLucro_Previsto_total());
            cv.put("valor_Total",dados.getValor_Total());
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

                int[] index = {
                        c.getColumnIndex("nome"),
                        c.getColumnIndex("valor_uni"),
                        c.getColumnIndex("lucro_Previsto_uni"),
                        c.getColumnIndex("Quantindade_P"),
                        c.getColumnIndex("Quantindade_P_total"),
                        c.getColumnIndex("lucro_Previsto_total"),
                        c.getColumnIndex("valor_Total")
                };

                String nome = c.getString(index[0]);
                String valor_uni = c.getString(index[1]);
                String lucro_Previsto_uni = c.getString(index[2]);
                String Quantindade_P = c.getString(index[3]);
                String Quantindade_P_total = c.getString(index[4]);
                String lucro_Previsto_total = c.getString(index[5]);
                String valor_Total = c.getString(index[6]);

                Long id = c.getLong(columnIndex);

                dados.setId(id);
                dados.setNome(nome);
                dados.setValor_uni(Double.parseDouble(valor_uni));
                dados.setLucro_Previsto_uni(Double.parseDouble(lucro_Previsto_uni));
                dados.setQuantindade_P(Integer.parseInt(Quantindade_P));
                dados.setLucro_Previsto_total(Double.parseDouble(lucro_Previsto_total));
                dados.setValor_Total(Double.parseDouble(valor_Total));
                dados.setQuantindade_P_total(Integer.parseInt(Quantindade_P_total));

                dadosList.add(dados);
            }
            return dadosList;
        }
}
