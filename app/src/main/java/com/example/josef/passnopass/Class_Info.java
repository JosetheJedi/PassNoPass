package com.example.josef.passnopass;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Class_Info extends AppCompatActivity {

    ListView list;
    double pointsPossible, pointsEarned;
    double totalPossible = 0, totalearned = 0;
    TextView gradeT;

    // holds all the classes
    ArrayList<String> assignments;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class__info);

        pointsPossible = 0;
        pointsEarned = 0;

        list = (ListView) findViewById(R.id.work);
        gradeT = (TextView) findViewById(R.id.total_grade);
        gradeT.setText("0/0");

        assignments = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, assignments);

        list.setAdapter(adapter);

        FloatingActionButton fabClass = (FloatingActionButton) findViewById(R.id.floatClass);
        fabClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Inserting();
            }
        });
    }

    public void Inserting(){
        Intent insertClass = new Intent(this, Insert_Assignment.class);
        startActivityForResult(insertClass, 1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String grade = data.getStringExtra("Grade");
                String total = data.getStringExtra("Total");
                String type = data.getStringExtra("Assignment");

                pointsPossible += Double.parseDouble(grade);
                pointsEarned += Double.parseDouble(total);

                totalPossible += pointsEarned;
                totalearned += pointsPossible;
                String entry = type + ": " + grade + "/" + total;

                assignments.add(entry);
                adapter.notifyDataSetChanged();

                String letter = "";

                double percent = (totalearned/totalPossible) * 100.0;

                System.out.println("percent is " + percent + "FROM " + totalearned);

                if( percent >= 90){
                    letter = "A";
                }
                else if((percent < 90) && (percent >= 80)){
                    letter = "B";
                }
                else if(percent < 90 && percent >= 70){
                    letter = "C";
                }
                else if(percent < 70 && percent >= 60){
                    letter = "D";
                }
                else {
                    letter = "F";
                }

                String output = "Grade: " + letter + " \t\t | \t\t" + totalearned + " / " + totalPossible + "| Percentage: " + percent;

                gradeT.setText(output);
            }
        }
    }
}
