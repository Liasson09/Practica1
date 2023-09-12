package utp.soria.practica1;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Comidas extends AppCompatActivity {
    Spinner spinnerTipoMascota, spinnerRaza;
    EditText edtNombreMascota, edtEdadMascota, edtPesoMascota;
    Button btnCalcular;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comidas);
        spinnerTipoMascota = findViewById(R.id.spinnerTipoMascota);
        spinnerRaza = findViewById(R.id.spinnerRaza);
        edtNombreMascota = findViewById(R.id.edtNombreMascota);
        edtEdadMascota = findViewById(R.id.edtEdadMascota);
        edtPesoMascota = findViewById(R.id.edtPesoMascota);
        btnCalcular = findViewById(R.id.btnCalcular);

        // Configura el Spinner de Tipo de Mascota
        tipo_raza.configurarSpinnerTipoMascota(this, spinnerTipoMascota);

        // Maneja la selección del Spinner de Tipo de Mascota
        spinnerTipoMascota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tipoMascotaSeleccionado = parent.getItemAtPosition(position).toString();

                // Configura el Spinner de Raza en función del tipo de mascota seleccionado
                tipo_raza.configurarSpinnerRaza(Comidas.this, spinnerRaza, tipoMascotaSeleccionado);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Manejo cuando no se selecciona ningún elemento
            }
        });
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (edtNombreMascota.getText().toString().equals("")||edtEdadMascota.getText().toString().equals("")||edtPesoMascota.getText().toString().equals("")) {
                    Toast.makeText(Comidas.this, "Debe ingresar todos los datos", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(Comidas.this, Resultado_Comidas.class);
                    //guardar en variable los datos para llevarlos al segundo activity
                    intent.putExtra("txtMascota", edtNombreMascota.getText().toString());
                    intent.putExtra("edtEdad", edtEdadMascota.getText().toString());
                    intent.putExtra("edtPeso", edtPesoMascota.getText().toString());
                    intent.putExtra("spinnerTipoMascota", spinnerTipoMascota.getSelectedItem().toString());
                    intent.putExtra("spinnerRaza", spinnerRaza.getSelectedItem().toString());
                    startActivity(intent);
                }
            }
        });
    }
    public static double calcularComidaPorPeso(Context context, String tipoMascota, double pesoKilos) {
        double cantidadComidaKilos = 0;

        if (tipoMascota.equals(context.getString(R.string.perro))) {
            // Fórmula para perros
            cantidadComidaKilos = pesoKilos * 0.025;
        } else if (tipoMascota.equals(context.getString(R.string.gato))) {
            // Fórmula para gatos
            if (pesoKilos >= 4 && pesoKilos < 6) {
                cantidadComidaKilos = pesoKilos * 0.03;
            } else if (pesoKilos >= 6 && pesoKilos < 8) {
                cantidadComidaKilos = pesoKilos * 0.02;
            } else if (pesoKilos >= 8) {
                cantidadComidaKilos = pesoKilos * 0.02;
            }
        }
        return cantidadComidaKilos;
    }

    public static String obtenerRecomendacionComida(Context context, double cantidadComidaKilos, String tipoMascota) {
        String recomendacion = "";

        if (tipoMascota.equals(context.getString(R.string.perro))) {
            // Recomendación para perros
            if (cantidadComidaKilos <= 0.1) {
                recomendacion = "Dale una pequeña porción de comida. (" + cantidadComidaKilos + " KG)";
            } else if (cantidadComidaKilos <= 0.3) {
                recomendacion = "Dale una porción moderada de comida. (" + cantidadComidaKilos + " KG)";
            } else {
                recomendacion = "Dale una porción grande de comida. (" + cantidadComidaKilos + " KG)";
            }
        } else if (tipoMascota.equals(context.getString(R.string.gato))) {
            // Recomendación para gatos
            if (cantidadComidaKilos <= 0.05) {
                recomendacion = "Dale una pequeña porción de comida. (" + cantidadComidaKilos + " KG)";
            } else if (cantidadComidaKilos <= 0.15) {
                recomendacion = "Dale una porción moderada de comida. (" + cantidadComidaKilos + " KG)";
            } else {
                recomendacion = "Dale una porción grande de comida. (" + cantidadComidaKilos + " KG)";
            }
        }
        return recomendacion;
    }
}