package br.ulbra.appcalculasalario;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    RadioGroup rgopcoes;
    EditText txtSalario, txtMeta;
    Button btCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        txtSalario = findViewById(R.id.txtSalario);
        rgopcoes = findViewById(R.id.rgopcoes);
        btCalcular = findViewById(R.id.btCalcular);
        txtMeta = findViewById(R.id.txtMeta);

        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String texto = txtMeta.getText().toString();
                double salario = Double.parseDouble(txtSalario.getText().toString());
                int op = rgopcoes.getCheckedRadioButtonId();
                double meta = Double.parseDouble(texto);
                double novo_salario = 0;


                if (op == R.id.rb30)
                    novo_salario = salario + (salario * 0.3);
                else if (op == R.id.rb40)
                    novo_salario = salario + (salario * 0.4);
                else
                    novo_salario = salario + (salario * 0.5);


                double faltaParaMeta = calcularFaltaParaMeta(novo_salario, meta);


                AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
                dialogo.setTitle("Novo salário:");
                dialogo.setMessage("Seu novo salário é : R$ " + novo_salario +
                        "\nSua meta de salário é: R$ " + meta +
                        "\nFaltam R$ " + faltaParaMeta + " para alcançar sua meta.");
                dialogo.setNeutralButton("OK", null);
                dialogo.show();
            }


            private double calcularFaltaParaMeta(double salarioAtual, double meta) {
                if (salarioAtual >= meta) {
                    return 0;
                } else {
                    return meta - salarioAtual;
                }
            }
        });
    }
}