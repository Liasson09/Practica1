package utp.soria.practica1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class Principal extends AppCompatActivity {
    TextView txt1;
    CardView cardComidas, cardControlVacunas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        txt1 = findViewById(R.id.txtNombre);
        cardComidas = findViewById(R.id.cardComidas);
        cardControlVacunas = findViewById(R.id.cardControlVacunas);
        //traer nombre del usuario
        String datos ="Bienvenido: " + getIntent().getStringExtra("txtUsuario");
        txt1.setText(datos);

        //Selección de las opciones
        cardComidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Actividad de "Consulta de Comidas"
                Intent intent = new Intent(Principal.this, Comidas.class);
                startActivity(intent);
            }
        });

        cardControlVacunas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Actividad de "Control de Vacunas"
                Intent intent = new Intent(Principal.this, Vacunas.class);
                startActivity(intent);
            }
        });

    }
}