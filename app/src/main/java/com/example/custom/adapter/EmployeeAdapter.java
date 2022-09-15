package com.example.custom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.custom.R;
import com.example.custom.model.Employee;

import java.util.List;

public class EmployeeAdapter extends BaseAdapter {
    private Context context;
    private List<Employee> list;

    public EmployeeAdapter(Context context, List<Employee> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        if (position <0)
                return 0;
        return list.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_employee_item, null);
        }

        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvBrief = view.findViewById(R.id.tvBrief);
        ImageView ivImage = view.findViewById(R.id.ivImage);

        Employee emp = list.get(i);
        tvName.setText(emp.getName());
        tvBrief.setText(emp.getBrief());
        ivImage.setImageResource(emp.getImageId());


        return view;
    }
}
