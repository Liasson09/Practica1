package utp.soria.practica1;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;


public class tipo_raza {
    public static void configurarSpinnerTipoMascota(Context context, Spinner spinner) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                context,
                R.array.tipo_mascota,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public static void configurarSpinnerRaza(Context context, Spinner spinner, String tipoMascotaSeleccionado) {
        List<String> razas = obtenerRazasPorTipo(context, tipoMascotaSeleccionado);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                context,
                android.R.layout.simple_spinner_item,
                razas
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
    private static List<String> obtenerRazasPorTipo(Context context, String tipoMascota) {
        List<String> razas = new ArrayList<>();
        if (tipoMascota.equals(context.getString(R.string.perro))) {
            razas.add("Labrador Retriever");
            razas.add("Pastor Alemán");
            razas.add("Golden Retriever");
            razas.add("Bulldog Francés");
            razas.add("Poodle");
            razas.add("Beagle");
            razas.add("Rottweiler");
            razas.add("Bulldog Inglés");
            razas.add("Perro Salchicha");
            razas.add("Siberian Husky");
            razas.add("Pomeranian");
            razas.add("Shih Tzu");
            razas.add("Gran Danés");
            razas.add("Chihuahua");
        } else if (tipoMascota.equals(context.getString(R.string.gato))) {
            razas.add("Siamés");
            razas.add("Persa");
            razas.add("Ragdoll");
            razas.add("Birmano");
            razas.add("Angora Turco");
            razas.add("Americano de Pelo Corto");
            razas.add("Van Turco");
            razas.add("Abisinio");
        }
        return razas;
    }
}