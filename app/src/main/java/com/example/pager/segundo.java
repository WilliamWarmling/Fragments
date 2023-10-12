package com.example.pager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link segundo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class segundo extends Fragment {
    Random gerador = new Random();
    Button btnGerar, btnConfirmar;
    EditText entrada;
    TextView saida;
    int numeroGerado = 0;
    int tentativas = 5;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public segundo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment segundo.
     */
    // TODO: Rename and change types and number of parameters
    public static segundo newInstance(String param1, String param2) {
        segundo fragment = new segundo();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_segundo, container, false);
        entrada = v.findViewById(R.id.entrada);
        saida = v.findViewById(R.id.saida);
        btnGerar = v.findViewById(R.id.btnGerar);
        btnGerar = v.findViewById(R.id.btnGerar);
        btnConfirmar = v.findViewById(R.id.btnConfirmar);

        btnGerar.setOnClickListener(click -> {
            gerar();
        });

        btnConfirmar.setOnClickListener(click -> {
            confirmar();
        });
        return v;
    }

    public void gerar() {
        saida.setText("");
        tentativas = 5;
        numeroGerado = gerador.nextInt(100) + 1;
    }

    public void confirmar() {
        int digito = Integer.parseInt(entrada.getText().toString());

        if (tentativas > 0) {
            if (digito > numeroGerado) {
                saida.setText("O número gerado é menor. Você possui " + tentativas + " chances.");
                tentativas--;
            } else if (digito < numeroGerado) {
                saida.setText("O número gerado é maior. Você possui " + tentativas + " chances.");
                tentativas--;
            } else {
                saida.setText("Você acertou! O número gerado é " + numeroGerado);
                tentativas = 0;
            }
            entrada.setText("");
        } else {
            saida.setText("Suas tentativas acabaram. O número gerado era " + numeroGerado);
        }
    }
}