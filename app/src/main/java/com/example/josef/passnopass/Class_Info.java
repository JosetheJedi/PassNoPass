package com.example.josef.passnopass;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Class_Info extends AppCompatActivity {

    ListView list;

    // holds all the classes
    ArrayList<String> assignments;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class__info);

        list = (ListView) findViewById(R.id.work);

        assignments = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, assignments);

        list.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
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
                String string = data.getStringExtra("RESULT_STRING");
                assignments.add(string);
                adapter.notifyDataSetChanged();
            }
        }
    }
}
