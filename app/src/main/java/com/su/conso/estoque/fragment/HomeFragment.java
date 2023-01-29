package com.su.conso.estoque.fragment;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.su.conso.estoque.R;
import com.su.conso.estoque.bancoDados.DadosDAO;
import com.su.conso.estoque.config.ConvertValor;
import com.su.conso.estoque.databinding.FragmentHomeBinding;
import com.su.conso.estoque.model.DadosProdutos;
import com.su.conso.estoque.model.ValoresTotal;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
    private TextView txt_QP,txt_VT,txt_LP;
    private EditText editNome,editValor_uni,editQuantidade,editLucro;
    private ImageButton btnNewP,btnInfo,btnClose;
    private Button btnSalvarProduto;
    private ConstraintLayout constraintLayout;
    private AlertDialog alertDialog;
    private DadosDAO dadosDAO;
    private Double valorTotalRecu,lucroPrevTotalRecup;
    private int quantidadeTotaRecuperada;
    //private FragmentHomeBinding binding;
    private FragmentHomeBinding binding;

    private  DadosProdutos dadosRecuperado = new DadosProdutos();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        View view = binding.getRoot();

        btnInfo = view.findViewById(R.id.btn_info);
        constraintLayout = view.findViewById(R.id.constraint_alert);
        dadosDAO = new DadosDAO(getActivity());
        recuperarValores();


        binding.novoProduto.setOnClickListener(view1 -> novoProduto());


        return  view;
    }

    public void novoProduto(){
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity(),R.style.TemaAlertdialo);
        View viewAlert = LayoutInflater.from(getActivity()).inflate(R.layout.alert_dalog, constraintLayout);
        alert.setCancelable(false);
        alert.setView(viewAlert);

       configAlertDialog(viewAlert);

        alertDialog = alert.create();
        alertDialog.show();
    }

    public void configAlertDialog(View view){

        btnSalvarProduto = view.findViewById(R.id.btn_salvar_newP);
        editValor_uni = view.findViewById(R.id.editTxt_Valor_newP);
        editQuantidade = view.findViewById(R.id.editTxt_quantidade_newP);
        editNome = view.findViewById(R.id.editTxt_nome_newP);
        editLucro = view.findViewById(R.id.editTxt_lucro_newP);
        btnClose = view.findViewById(R.id.btnCloseNew_p);

        btnSalvarProduto.setOnClickListener(view1 -> {

            try {
                //String editSaldoValidado = String.valueOf(editSaldo.getRawValue());
                if (!editNome.getText().toString().isEmpty()) {
                        if (!editQuantidade.getText().toString().isEmpty()) {
                            if (!editLucro.getText().toString().isEmpty()) {
                                if (!editValor_uni.getText().toString().isEmpty()) {
                                    ValoresTotal valoresTotal = new ValoresTotal();
                                    DadosProdutos produtos = new DadosProdutos();


                                    double valorUni = Double.parseDouble(editValor_uni.getText().toString());
                                    double valorTotal = (valorUni + dadosRecuperado.getValor_uni());
                                    int quantidade = Integer.parseInt(editQuantidade.getText().toString());
                                    int quantidadeTotal = (quantidade + dadosRecuperado.getQuantindade_P_total());
                                    double lucro = Double.parseDouble(editLucro.getText().toString());
                                    double lucroTotal = (lucro * quantidade) + dadosRecuperado.getLucro_Previsto_total();

                                    produtos.setNome(editNome.getText().toString());
                                    produtos.setValor_uni(valorUni);
                                    produtos.setValor_Total(valorTotal);
                                    produtos.setLucro_Previsto_uni(lucro);
                                    produtos.setLucro_Previsto_total(lucroTotal);
                                    produtos.setQuantindade_P(quantidade);
                                    produtos.setQuantindade_P_total(quantidadeTotal);

                                if (dadosDAO.salvar(produtos)){
                                    recuperarValores();
                                    alertDialog.dismiss();
                                }else{
                                    msg("erro");
                                }
                                }
                            }
                    }
                }
            } catch (Exception e) {
                Toast.makeText(getActivity(), "Tente novamente.", Toast.LENGTH_SHORT).show();
                msg(String.valueOf(e.getStackTrace()));
            }

        });

        btnClose.setOnClickListener(view12 -> alertDialog.dismiss());
    }

    private void recuperarValores(){


        for (int i = 0; i < dadosDAO.listar().size();i++){
            System.out.println("oi"+i);
             dadosRecuperado = (DadosProdutos) dadosDAO.listar().get(i);
        }
        ConvertValor convertValor = new ConvertValor();
        binding.txtLP.setText(convertValor.convert(String.valueOf(dadosRecuperado.getLucro_Previsto_total())));
        binding.txtVT.setText((convertValor.convert(String.valueOf(dadosRecuperado.getValor_Total()))));
        binding.txtQP.setText((convertValor.convert(String.valueOf(dadosRecuperado.getQuantindade_P_total()))));



    }

    private void info(){

    }

    private void msg(String txt){
        Toast.makeText(getActivity(), txt, Toast.LENGTH_SHORT).show();
    }
}