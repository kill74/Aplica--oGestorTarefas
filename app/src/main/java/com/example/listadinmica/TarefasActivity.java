package com.example.listadinmica;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TarefasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefas);

        // Inicializa os componentes do layout
        EditText editTextTarefa = findViewById(R.id.editTextTarefa);
        Button buttonAdicionar = findViewById(R.id.buttonAdicionar);
        LinearLayout listaTarefas = findViewById(R.id.listaTarefas);
        Button buttonRemoverConcluidas = findViewById(R.id.buttonRemoverConcluidas);

        // Configura o botão para adicionar tarefas
        buttonAdicionar.setOnClickListener(v -> {
            String descricao = editTextTarefa.getText().toString();
            if (!descricao.isEmpty()) {
                // Cria a visualização da tarefa
                LinearLayout layoutTarefa = new LinearLayout(this);
                layoutTarefa.setOrientation(LinearLayout.HORIZONTAL);

                TextView textViewTarefa = new TextView(this);
                textViewTarefa.setText(descricao);

                CheckBox checkBox = new CheckBox(this);
                checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    if (isChecked) {
                        textViewTarefa.setPaintFlags(textViewTarefa.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    } else {
                        textViewTarefa.setPaintFlags(textViewTarefa.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
                    }
                });

                layoutTarefa.addView(checkBox);
                layoutTarefa.addView(textViewTarefa);

                listaTarefas.addView(layoutTarefa);
                editTextTarefa.setText(""); // Limpa o campo de texto
            }
        });

        // Configura o botão para remover tarefas concluídas
        buttonRemoverConcluidas.setOnClickListener(v -> {
            for (int i = listaTarefas.getChildCount() - 1; i >= 0; i--) {
                View tarefaView = listaTarefas.getChildAt(i);
                CheckBox checkBox = (CheckBox) ((LinearLayout) tarefaView).getChildAt(0);
                if (checkBox.isChecked()) {
                    listaTarefas.removeViewAt(i);
                }
            }
        });
    }
}
