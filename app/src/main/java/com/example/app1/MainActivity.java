package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private Button guardar;
    private Button mostrar;
    private TextView textoContenido;
    private EditText editText1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        guardar = findViewById( R.id.guardarButton );
        mostrar = findViewById( R.id.button2 );
        editText1 = findViewById(R.id.edittext1);
        textoContenido = findViewById( R.id.textView );
        guardar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    OutputStreamWriter fout=
                            new OutputStreamWriter(
                                    openFileOutput("datosUsuario.txt", Context.MODE_PRIVATE));

                    fout.write(editText1.getText().toString());
                    fout.close();
                }
                catch (Exception ex)
                {
                    Log.e("Ficheros", "Error al escribir fichero a memoria interna");
                }

            }
        } );
        mostrar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    BufferedReader fin =
                            new BufferedReader(
                                    new InputStreamReader(
                                            openFileInput("datosUsuario.txt")));

                    String texto = fin.readLine();
                    textoContenido.setText( texto );
                    fin.close();
                }
                catch (Exception ex)
                {
                    Log.e("Ficheros", "Error al leer fichero desde memoria interna");
                }
            }
        } );
    }
}