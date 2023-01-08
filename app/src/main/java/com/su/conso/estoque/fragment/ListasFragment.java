package com.su.conso.estoque.fragment;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.su.conso.estoque.R;
import com.su.conso.estoque.adapter.Adapter;
import com.su.conso.estoque.adapter.RecyclerItemClick;
import com.su.conso.estoque.bancoDados.DadosDAO;
import com.su.conso.estoque.model.DadosProdutos;

import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListasFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListasFragment newInstance(String param1, String param2) {
        ListasFragment fragment = new ListasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private RecyclerView recyclerView;
    private Adapter adapter;
    private List<DadosProdutos> listProduto;
    private DadosProdutos dadosP;
    private  DadosDAO dadosDAO;
    private ConstraintLayout constraintLayout;
    private android.app.AlertDialog alertDialog;
    private EditText editNome,editValor_uni,editQuantidade,editLucro;
    private ImageButton btnClose;
    private Button btnAtualzarP;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_listas, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        constraintLayout = view.findViewById(R.id.constraint_alert);
        carregarListaProduto();
        config();
        dadosP = new DadosProdutos();
        dadosDAO = new DadosDAO(getActivity());



        return  view;
    }

    public void carregarListaProduto(){
        DadosDAO dadosDAO = new DadosDAO(getActivity());
        listProduto = dadosDAO.listar();

        adapter = new Adapter(listProduto);

        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        Collections.reverse(listProduto);

    }

    private void config() {

        recyclerView.addOnItemTouchListener(new RecyclerItemClick(getActivity(), recyclerView,
                new RecyclerItemClick.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                         dadosP = listProduto.get(position);
                         editaProduto(dadosP);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());

                        dadosP = listProduto.get(position);

                        dialog.setTitle("Excluir");
                        dialog.setIcon(R.drawable.ic_delete);
                        dialog.setMessage("Tem certeza que deseja excluir a: '" + dadosP.getNome() + "'?");

                        dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (dadosDAO.deletar(dadosP)) {
                                    carregarListaProduto();
                                    Toast.makeText(getActivity(), "Apagado com sucesso", Toast.LENGTH_SHORT).show();
                                } else {

                                }
                            }
                        });
                        dialog.setNegativeButton("Não", null);

                        dialog.create();
                        dialog.show();

                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                }));
    }

    private void editaProduto(DadosProdutos dadosAtualizar) {
        android.app.AlertDialog.Builder alert = new android.app.AlertDialog.Builder(getActivity(),R.style.TemaAlertdialo);
        View viewAlert = LayoutInflater.from(getActivity()).inflate(R.layout.alert_dalog, constraintLayout);
        alert.setCancelable(false);
        alert.setView(viewAlert);

        atualizarProduto(viewAlert,dadosAtualizar);

        alertDialog = alert.create();
        alertDialog.show();
    }

    private void atualizarProduto(View view, DadosProdutos dadosAtualizar) {

        btnAtualzarP = view.findViewById(R.id.btn_salvar_newP);
        editValor_uni = view.findViewById(R.id.editTxt_Valor_newP);
        editQuantidade = view.findViewById(R.id.editTxt_quantidade_newP);
        editNome = view.findViewById(R.id.editTxt_nome_newP);
        editLucro = view.findViewById(R.id.editTxt_lucro_newP);
        btnClose = view.findViewById(R.id.btnCloseNew_p);

        editNome.setText(dadosAtualizar.getNome());
        editValor_uni.setText(String.valueOf( dadosAtualizar.getValor_uni()));
        editQuantidade.setText(String.valueOf(dadosAtualizar.getQuantindade_P()));
        editLucro.setText(String.valueOf(dadosAtualizar.getLucro_Previsto_uni()));

//        btnAtualzarP.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                try {
//                    //String editSaldoValidado = String.valueOf(editSaldo.getRawValue());
//                    if (!editNome.getText().toString().isEmpty()) {
//                        if (!editQuantidade.getText().toString().isEmpty()) {
//                            if (!editLucro.getText().toString().isEmpty()) {
//                                if (!editValor_uni.getText().toString().isEmpty()) {
//                                    DadosProdutos produtos = new DadosProdutos();
//                                    produtos.setNome(editNome.getText().toString());
//                                    produtos.setValor_uni(Double.parseDouble(editValor_uni.getText().toString()));
//                                    produtos.setQuantindade_P(Integer.parseInt(editQuantidade.getText().toString()));
//                                    produtos.setLucro_Previsto_uni(Double.parseDouble(editLucro.getText().toString()));
//
//                                    if (dadosDAO.salvar(produtos)){
//                                        msg("Salvo com sucesso!");
//                                        alertDialog.dismiss();
//                                    }else{
//                                        msg("Erro!");
//                                    }
//                                }
//                            }
//                        }
//                    }
//                } catch (Exception e) {
//                    Toast.makeText(getActivity(), "Tente novamente.", Toast.LENGTH_SHORT).show();
//                    msg(String.valueOf(e.getStackTrace()));
//                }
//            }
//        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }

    private void atualizarQuantidade(int op){
        if(op == 1){
            dadosP.setQuantindade_P(+1);
            if (dadosDAO.atualizar(dadosP)){
                Toast.makeText(getActivity(), "Salvo", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getActivity(), "Erro", Toast.LENGTH_SHORT).show();
            }
            carregarListaProduto();
        }
    }
}