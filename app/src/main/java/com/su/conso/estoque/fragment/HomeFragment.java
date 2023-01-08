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
    private ImageButton btnNewP,btnInfo;
    private Button btnSalvarSaldo;
    private ConstraintLayout constraintLayout;
    private AlertDialog alertDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        txt_QP = view.findViewById(R.id.txt_QP);
        txt_VT = view.findViewById(R.id.txt_VT);
        txt_LP = view.findViewById(R.id.txt_LP);
        btnNewP = view.findViewById(R.id.novoProduto);
        btnInfo = view.findViewById(R.id.btn_info);
        constraintLayout = view.findViewById(R.id.constraint_alert);

        btnNewP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            novoProduto(view);

            }
        });



        return  view;
    }

    public void novoProduto(View view){
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity(),R.style.TemaAlertdialo);
        View viewAlert = LayoutInflater.from(getActivity()).inflate(R.layout.alert_dalog, constraintLayout);
        alert.setCancelable(false);
        alert.setView(viewAlert);

       configAlertDialog(viewAlert);

        alertDialog = alert.create();
        alertDialog.show();
    }

    public void configAlertDialog(View view){

        btnSalvarSaldo = view.findViewById(R.id.btn_salvar_newP);
        editValor_uni = view.findViewById(R.id.editTxt_Valor_newP);
        editQuantidade = view.findViewById(R.id.editTxt_quantidade_newP);
        editNome = view.findViewById(R.id.editTxt_nome_newP);
        editLucro = view.findViewById(R.id.editTxt_lucro_newP);

        btnSalvarSaldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    //String editSaldoValidado = String.valueOf(editSaldo.getRawValue());
                    if (!editNome.getText().toString().isEmpty()) {
                            if (!editQuantidade.getText().toString().isEmpty()) {
                                if (!editLucro.getText().toString().isEmpty()) {
                                    if (!editValor_uni.getText().toString().isEmpty() && !editValor_uni.getText().toString().equals("0")) {
                                        //safdslajflkdssafasf
                                        double saldo = Double.parseDouble(editValor_uni.getText().toString());
                                        Toast.makeText(getActivity(), "Salvo", Toast.LENGTH_SHORT).show();

                                    }
                                }
                        }
                    }
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Tente novamente.", Toast.LENGTH_SHORT).show();
                }
            }
        });
//vamos ver se vai pegar agr
    }
}