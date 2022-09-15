package com.example.slide5;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.slide5.adapter.EmloyeeAdapter;
import com.example.slide5.model.Employee;
import com.example.slide5.sqlite.DBHelper;
import com.example.slide5.sqlite.EmployeeDao;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EmloyeeAdapter emloyeeAdapter;
    private ListView lvEmployees;
    private String employeeId;
    private List<Employee> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        DBHelper dbHelper = new DBHelper(this);
//        SQLiteDatabase database = dbHelper.getReadableDatabase();
//        database.close();

        findViewById(R.id.btnEdit).setOnClickListener(this);
        findViewById(R.id.btnDelete).setOnClickListener(this);
        findViewById(R.id.btnInsert).setOnClickListener(this);

        lvEmployees = findViewById(R.id.lvEmployees);
        EmployeeDao dao = new EmployeeDao(this);
        list= dao.getAll();

        emloyeeAdapter = new EmloyeeAdapter(this,list);
        lvEmployees.setAdapter(emloyeeAdapter);
        lvEmployees.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Employee emp = list.get(i);
                employeeId = emp.getId();

            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onResume() {
        super.onResume();
        EmployeeDao dao = new EmployeeDao(this);
        List<Employee> updateList = dao.getAll();

        list.clear();
        updateList.forEach(item->list.add(item));
        emloyeeAdapter.notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, AddOrEditEmployeeActivity.class);
        switch (view.getId()) {
            case R.id.btnInsert:
                startActivity(intent);
                break;
            case R.id.btnEdit:
                if(employeeId == null) {
                    Toast.makeText(this, "Employee id must be selected", Toast.LENGTH_SHORT).show();
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("id", employeeId);
                intent.putExtra("data",bundle);
                startActivity(intent);
                break;
            case R.id.btnDelete:
                if(employeeId == null){
                    Toast.makeText(this, "Employee id must be selected", Toast.LENGTH_SHORT).show();
                    EmployeeDao dao = new EmployeeDao(this);
                    dao.delete(employeeId);
                    employeeId = null;

                    onResume();
                    Toast.makeText(this, "Employee was delete", Toast.LENGTH_SHORT).show();
                    break;
                }
        }
    }
}