package com.example.slide5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.slide5.model.Employee;
import com.example.slide5.sqlite.EmployeeDao;

public class AddOrEditEmployeeActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etEmployeeId, etName, etSalary;
    private Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_edit_employee);

        etEmployeeId = findViewById(R.id.etEmployeeId);
        etName = findViewById(R.id.etName);
        etSalary = findViewById(R.id.etSalary);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

        findViewById(R.id.btnListEmployee).setOnClickListener(this);

        readEmployee();
    }

    public void readEmployee(){
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        if(bundle == null){
            return;
        }
        String id = bundle.getString("id");

        EmployeeDao dao = new EmployeeDao(this);
        Employee emp = dao.getById(id);

        etEmployeeId.setText(emp.getId());
        etName.setText(emp.getName());
        etSalary.setText(""+ emp.getSalary());

        btnSave.setText("update");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSave:
                EmployeeDao dao = new EmployeeDao(this);
                Employee emp = new Employee();

                emp.setId(etEmployeeId.getText().toString());
                emp.setName(etName.getText().toString());
                emp.setSalary(Float.parseFloat(etSalary.getText().toString()));

                if (btnSave.getText().equals("Save")){
                    dao.insert(emp);
                }else{
                    dao.update(emp);
                }

                dao.insert(emp);
                Toast.makeText(this, "New employee has been saved", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}