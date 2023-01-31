package com.su.conso.estoque.actvity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.su.conso.estoque.databinding.LayoutInfoAlertBinding;

public class InfoActivity extends AppCompatActivity {

    private LayoutInfoAlertBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LayoutInfoAlertBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnFecharInfo.setOnClickListener(view1 -> {
            finish();
        });
    }
}