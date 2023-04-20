package com.example.loant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    //El array que llena numero de cuotas y tipo prestamos
    String[] numcuotasS = {"12","24"};
    String[] tipoprests = {"Housing","vehicle"};
    //Variable que contiene el numero de cuotas y tipo prestamo
    String selNumcuotas, selTipoprest;
    //Instanciando los elementos que tienen id
    EditText name, email, valueprest,date;
    TextView valuedeuda,valuecuota;
    Spinner numcuotas, tipoprest;
    Button calculate, clean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Referenciar los objetos instanciados
        name = findViewById(R.id.etname);
        email = findViewById(R.id.etemail);
        valueprest = findViewById(R.id.etvalueprest);
        date = findViewById(R.id.etdate);
        numcuotas = findViewById(R.id.spnumcuotas);
        tipoprest = findViewById(R.id.tipoprest);
        calculate = findViewById(R.id.btmcalculate);
        clean = findViewById(R.id.btmclean);
        valuedeuda = findViewById(R.id.tvvaluedeuda);
        valuecuota = findViewById(R.id.tvvaluecuota);
        //Generar el arrayAdapter para recibir la informacion del array target
        ArrayAdapter adpnumcuotasS = new ArrayAdapter<>(this, android.R.layout.simple_list_item_checked,numcuotasS);
        //Pasar los datos del adapter adpTarget al spinner(target)
        numcuotas.setAdapter(adpnumcuotasS);
        //Generar el evento para cuando se seleccione un destino
        numcuotas.setOnItemSelectedListener(this);
        //Segundo Spinner
        ArrayAdapter adptipoprests = new ArrayAdapter<>(this, android.R.layout.simple_list_item_checked,tipoprests);
        tipoprest.setAdapter(adptipoprests);
        tipoprest.setOnItemSelectedListener(this);


        //Generar el evemto click del boton Limpiar
        clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Limpiar los elementos
                name.setText("");
                email.setText("");
                valueprest.setText("");
                date.setText("");
                valuedeuda.setText("");
                valuecuota.setText("");

                Toast.makeText(getApplicationContext(), "Successfully deleted data", Toast.LENGTH_SHORT).show();
            }

        });
        //Generar el boton calcular

        calculate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Obtener los valores de los campos
                String mvalorPrestamo = valueprest.getText().toString();
                String fecha = date.getText().toString();
                if(mvalorPrestamo.isEmpty() || fecha.isEmpty()){
                    Toast.makeText(getApplicationContext(), "You must enter all the data", Toast.LENGTH_SHORT).show();
                    return;
                }
                int numCuotas = Integer.parseInt(numcuotas.getSelectedItem().toString());
                String tipoPrestamo = tipoprest.getSelectedItem().toString();
                double valorPrestamo = Double.parseDouble(valueprest.getText().toString());
                double interes = 0.0;
                if (tipoPrestamo.equals("Housing")) {
                    interes = valorPrestamo * 0.01;
                } else if (tipoPrestamo.equals("vehicle")) {
                    interes = valorPrestamo * 0.015;
                }

                double valorDeuda = valorPrestamo + (interes * numCuotas);
                double valorCuota = valorDeuda / numCuotas;

                //Dar el formato como se vaya a mostra
                DecimalFormat numberFormat = new DecimalFormat("###,###.##");
                // Mostrar los resultados en la vista
                valuedeuda.setText(numberFormat.format(valorDeuda));
                valuecuota.setText(numberFormat.format(valorCuota));
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        selNumcuotas = numcuotasS[i];
        selTipoprest = tipoprests[i];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}