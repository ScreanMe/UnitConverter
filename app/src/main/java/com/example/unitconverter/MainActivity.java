package com.example.unitconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setAdapter(adapter);

        final int[] pos = {0};
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(
                    AdapterView<?> parent, View view, int position, long id) {
                pos[0] = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        Button btn = (Button) findViewById(R.id.convert_btn);
        TextView textView = (TextView) findViewById(R.id.txt_result);
        final EditText txtEntry = (EditText) findViewById(R.id.text_input);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (txtEntry.getText().toString().trim().length() > 0) {
                    String textValue = txtEntry.getText().toString();
                    double lastValue = Double.parseDouble(textValue);
                    double ft, m;
                    if (lastValue < 0){
                        textView.setText("Please Enter Valid Value");
                    }
                    else {

                        if (pos[0] == 0) {
                            m = lastValue * 0.3048;
                            textView.setText(String.format("%.2f", m) + " meters");
                        } else {
                            ft = lastValue * 3.28084;
                            textView.setText(String.format("%.2f", ft) + " feet");
                        }
                    }
                } else {
                    textView.setText("Please Enter Value");
                }
            }
        });


    }
}