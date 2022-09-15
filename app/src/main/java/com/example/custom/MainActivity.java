package com.example.custom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.custom.adapter.EmployeeAdapter;
import com.example.custom.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EmployeeAdapter adapter;
    private List<Employee> list;

    public MainActivity() {
        list = new ArrayList<>();

        list.add(new Employee(1, R.drawable.ic_baseline_accessibility_new_24, "Chinh", "Lecturer"));
        list.add(new Employee(2, R.drawable.ic_baseline_build_24 , "Loan", "Lecturer"));
        list.add(new Employee(3, R.drawable.ic_baseline_accessibility_new_24, "Cưn", "Lecturer"));
        list.add(new Employee(4, R.drawable.ic_baseline_build_24, "Tuyet Loan", "Lecturer"));

        adapter = new EmployeeAdapter(this, list);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lv = findViewById(R.id.lvEmployees);
        lv.setAdapter(adapter);
    }
}