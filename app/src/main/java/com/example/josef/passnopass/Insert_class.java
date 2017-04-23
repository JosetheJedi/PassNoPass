package com.example.josef.passnopass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;


public class Insert_class extends AppCompatActivity {

    EditText classE, classNum;
    String input, classes;
    Intent intent;
    int inputNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_class);
        Button addB = (Button) findViewById(R.id.addingBut);
        classE = (EditText) findViewById(R.id.classText);
        classNum = (EditText) findViewById(R.id.courseNumE);
        intent = new Intent();

        addB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input = classE.getText().toString();
                inputNum = Integer.parseInt(classNum.getText().toString());

                if(input.equalsIgnoreCase("")){
                    classE.setError("Enter a class name");
                }
                else if(inputNum == 0){
                    classNum.setError("Enter a class number");
                }
                else{
                    classes = input;
                    intent.putExtra("RESULT_STRING", classes);
                    intent.putExtra("Num", inputNum);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

}
