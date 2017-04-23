package com.example.josef.passnopass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;


public class Insert_class extends AppCompatActivity {

    EditText classE;
    String input, classes;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_class);
        Button addB = (Button) findViewById(R.id.addingBut);
        classE = (EditText) findViewById(R.id.classText);
        intent = new Intent();

        addB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input = classE.getText().toString();

                if(input.equalsIgnoreCase("")){
                    classE.setError("Enter a class name");
                }
                else{
                    classes = input;
                    intent.putExtra("RESULT_STRING", classes);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

}
