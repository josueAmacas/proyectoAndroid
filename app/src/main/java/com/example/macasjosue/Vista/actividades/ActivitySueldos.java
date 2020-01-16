package com.example.macasjosue.Vista.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.macasjosue.R;

public class ActivitySueldos extends AppCompatActivity implements View.OnClickListener {

    TextView sueldoNormal,sueldoExtra,tazaimpuesto, sueldoNeto, horasExtra;
    EditText horasTrabajadas, precioHora;
    Button calcular;
    int htrabajadas, precioh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sueldos);
        cargarComponentes();
    }

    private void  cargarComponentes(){
        sueldoNormal = findViewById(R.id.lblSueldoN);
        sueldoExtra= findViewById(R.id.lblSueldoExtra);
        tazaimpuesto = findViewById(R.id.lblTazaImpuesto);
        sueldoNeto = findViewById(R.id.lblSueldoNeto);
        horasTrabajadas = findViewById(R.id.txtHTrabajadas);
        precioHora = findViewById(R.id.txtPrecioHora);
        horasExtra = findViewById(R.id.lblHExtras);
        calcular = findViewById(R.id.btnCalcularSueldos);
        calcular.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        htrabajadas = Integer.parseInt(horasTrabajadas.getText().toString());
        precioh = Integer.parseInt(precioHora.getText().toString());
        int snormal =  0;
        int horasE = 0;
        int sextra = 0;
        int tazaI = 0;
        int sneto = 0;

        if(htrabajadas < 41){
            snormal = htrabajadas * precioh;
            sueldoNormal.setText("El sueldo normal es: " + snormal);
            horasExtra.setText("Las horas extra son: "+ 0);
            sueldoExtra.setText("El sueldo extra es: " + 0);

        }else {
            snormal = 40 * precioh;
            sueldoNormal.setText("El sueldo normal es: "+ snormal);
            horasE = htrabajadas - 40;
            horasExtra.setText("Las horas extra son: "+horasE);
            sextra = (int) (horasE * precioh * 1.5);
            sueldoExtra.setText("El sueldo extra es: "+ sextra);
        }

        if(snormal > 200){
            tazaI = (int) ((snormal - 200) * 0.10);
            tazaimpuesto.setText("La taza de impuesto es: " + tazaI);
        }else {
            tazaimpuesto.setText("La taza de impuesto es: " + 0);
        }

        sneto = snormal + sextra - tazaI;
        sueldoNeto.setText("El sueldo neto es: " +sneto);

    }
}
