package com.example.josef.passnopass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Insert_Assignment extends AppCompatActivity {

    Spinner assign;
    EditText grade, total;
    Button addB;
    Intent resultIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert__assignment);

        assign = (Spinner) findViewById(R.id.assign_spin);
        grade = (EditText) findViewById(R.id.gradeE);
        total = (EditText) findViewById(R.id.totalE);
        addB = (Button) findViewById(R.id.button);

        resultIntent = new Intent();

        addB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = grade.getText().toString();
                String totalResult = total.getText().toString();

                if(result.equalsIgnoreCase("")){
                    grade.setError("Enter a valid grade");
                }
                else if(totalResult.equalsIgnoreCase("")){
                    total.setError("Enter a valid total");
                }
                else{
                    resultIntent.putExtra("Grade", result);
                    resultIntent.putExtra("Total", totalResult);
                    resultIntent.putExtra("Assignment", assign.getSelectedItem().toString());
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }
        });
    }
}
