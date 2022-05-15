package com.eduazevedo.calculadoradeimc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView text_result;
    private EditText edt_peso, edt_altura;

    DecimalFormat df = new DecimalFormat("#,###.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciarComponentes();

    }

    public void verificaDados(View view) {
        String peso = edt_peso.getText().toString().trim();
        String altura = edt_altura.getText().toString().trim();

        if (!peso.isEmpty()) {
            if (!altura.isEmpty()) {
                ocultarTeclado();
                double result = Double.parseDouble(peso) / Math.pow(Double.parseDouble(altura), 2);
                text_result.setVisibility(View.VISIBLE);
                if (result < 18.5) {
                    text_result.setText("Classificação:\nMagreza\nIMC: " + df.format(result));
                } else if (result >= 18.5 && result<= 24.9) {
                    text_result.setText("Classificação:\nNormal\nIMC: " + df.format(result));
                } else if (result > 24.9 && result <= 29.9) {
                    text_result.setText("Classificação:\nSobrepeso\nIMC: " + df.format(result));
                } else if (result >= 30 && result <= 39.9) {
                    text_result.setText("Classificação:\nObesidade\nIMC: " + df.format(result));
                } else {
                    text_result.setText("Classificação:\nObesidade GRAVE\nIMC: " + df.format(result));
                }
            } else {
                edt_altura.requestFocus();
                edt_altura.setError("Campo obrigatório!");
            }

        } else {
            edt_peso.requestFocus();
            edt_peso.setError("Campo obrigatório!");
        }
    }

    private void iniciarComponentes() {
        edt_peso = findViewById(R.id.edt_peso);
        edt_altura = findViewById(R.id.edt_altura);
        text_result = findViewById(R.id.text_result);
    }

    private void ocultarTeclado () {
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                edt_peso.getWindowToken(), 0
        );
    }
}