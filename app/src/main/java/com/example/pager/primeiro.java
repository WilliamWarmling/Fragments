package com.example.pager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link primeiro#newInstance} factory method to
 * create an instance of this fragment.
 */
public class primeiro extends Fragment {
    Button btnSomar, btnSubtrair, btnLimpar;
    EditText horaInicial, horaFinal, minutoInical, minutoFinal;
    TextView resultadoHora, doisPontos, resultadoMinuto;
    int horaI;
    int horaF;
    int minI;
    int minF;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public primeiro() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment primeiro.
     */
    // TODO: Rename and change types and number of parameters
    public static primeiro newInstance(String param1, String param2) {
        primeiro fragment = new primeiro();
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
        View v = inflater.inflate(R.layout.fragment_primeiro, container, false);
        horaInicial = v.findViewById(R.id.horaInicial);
        horaFinal = v.findViewById(R.id.horaFinal);
        minutoInical = v.findViewById(R.id.minutoInicial);
        minutoFinal = v.findViewById(R.id.minutoFinal);

        resultadoHora = v.findViewById(R.id.resultadoHora);
        doisPontos = v.findViewById(R.id.doisPontos);
        doisPontos.setVisibility(View.INVISIBLE);
        resultadoMinuto = v.findViewById(R.id.resultadoMinuto);

        btnSomar = v.findViewById(R.id.btnSomar);
        btnSubtrair = v.findViewById(R.id.btnSubtrair);
        btnLimpar = v.findViewById(R.id.btnLimpar);

        btnSomar.setOnClickListener(click -> {
            somar();
        });

        btnSubtrair.setOnClickListener(click -> {
            subtrair();
        });

        btnLimpar.setOnClickListener(click -> {
            limpar();
        });
        return v;
    }

    public void somar() {
        cataCampos();

        int rH = horaI + horaF;
        int rM = minI + minF;

        while (rM > 59) {
            rM -= 60;
            rH++;
        }

        resultadoHora.setText(rH + "");
        doisPontos.setVisibility(View.VISIBLE);
        resultadoMinuto.setText(rM + "");
    }

    public void subtrair() {
        cataCampos();

        while (minF > 59) {
            minF -= 60;
            horaF++;
        }

        while (minI > 59) {
            minI -= 60;
            horaI++;
        }

        int rH = horaF - horaI;
        int rM = minF - minI;

        if (horaI > horaF) {
            rH = horaI - horaF;
            rM = minI - minF;
        }

        while (rM < 0) {
            rM += 60;
            rH--;
        }

        while (rM > 59) {
            rM -= 60;
            rH++;
            horaF++;
        }

        resultadoHora.setText(rH + "");
        doisPontos.setVisibility(View.VISIBLE);
        resultadoMinuto.setText(rM + "");
    }

    public void limpar() {
        horaInicial.setText(null);
        horaFinal.setText(null);
        minutoInical.setText(null);
        minutoFinal.setText(null);

        resultadoHora.setText(null);
        doisPontos.setVisibility(View.INVISIBLE);
        resultadoMinuto.setText(null);
    }

    public void cataCampos() {
        try {
            horaI = Integer.parseInt(horaInicial.getText().toString());
            horaF = Integer.parseInt(horaFinal.getText().toString());
            minI = Integer.parseInt(minutoInical.getText().toString());
            minF = Integer.parseInt(minutoFinal.getText().toString());
        } catch (Exception e) {
            horaI = 0;
            horaF = 0;
            minI = 0;
            minF = 0;
            Toast.makeText(getContext(), "Preencha corretamente todos os campos! (Rendeu um fandangos)", Toast.LENGTH_SHORT).show();
        }

    }
}