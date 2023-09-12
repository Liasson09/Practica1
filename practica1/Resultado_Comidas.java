package utp.soria.practica1;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Resultado_Comidas extends AppCompatActivity {
    TextView txtResultadoTitulo, txtResultadoDetalle;
    Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_comidas);

        // Inicializa las vistas
        txtResultadoTitulo = findViewById(R.id.txtResultadoTitulo);
        txtResultadoDetalle = findViewById(R.id.txtResultadoDetalle);
        btnVolver = findViewById(R.id.btnVolver);

        String nombreMascota = getIntent().getStringExtra("txtMascota");
        String edad = getIntent().getStringExtra("edtEdad");
        String pesoStr = getIntent().getStringExtra("edtPeso");
        double peso = Double.parseDouble(pesoStr); // Convertir a double
        String tipoMascota = getIntent().getStringExtra("spinnerTipoMascota");
        String tipoRaza = getIntent().getStringExtra("spinnerRaza");

        // Calcula la cantidad de comida recomendada
        double cantidadComida = Comidas.calcularComidaPorPeso(this, tipoMascota, peso);

        // Construye el mensaje de datos y recomendación de comida
        String datos = "Nombre de la mascota: " + nombreMascota +
                "\nEdad: " + edad +
                "\nPeso: " + pesoStr + " gramos" +  // Mostrar el peso en gramos
                "\nTipo de mascota: " + tipoMascota +
                "\nTipo de raza: " + tipoRaza;

        // Agregar la recomendación de comida al mensaje
        String recomendacionComida = Comidas.obtenerRecomendacionComida(this, cantidadComida, tipoMascota);

        // Combinar los datos y la recomendación en un solo mensaje
        String mensajeCompleto = datos + "\n\nRecomendación de comida: " + recomendacionComida;

        // Establece el texto en txtResultadoDetalle
        txtResultadoDetalle.setText(mensajeCompleto);

        // Manejar el botón Volver
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        RatingBar ratingBar = findViewById(R.id.ratingBar);
        final TextView graciasTextView = findViewById(R.id.graciasTextView);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (fromUser) {
                    graciasTextView.setText("¡Gracias por tu calificación de " + rating + " estrellas!");
                }
            }
        });
    }
}