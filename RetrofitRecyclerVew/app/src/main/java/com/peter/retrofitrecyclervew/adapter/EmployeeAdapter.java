package com.peter.retrofitrecyclervew.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.peter.retrofitrecyclervew.HomeActivity;
import com.peter.retrofitrecyclervew.MainActivity;
import com.peter.retrofitrecyclervew.R;
import com.peter.retrofitrecyclervew.model.Employee;

import java.util.ArrayList;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeHolder>{


    private ArrayList<Employee> dataList;
    private Context applicationContext;
    private ArrayList<Employee> empDataList;

    public EmployeeAdapter(Context applicationContext, ArrayList<Employee> empDataList) {
        this.applicationContext=applicationContext;
        this.empDataList=empDataList;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public EmployeeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_list_item,parent,false);
        return new EmployeeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeHolder holder, int position) {


        Employee employee=dataList.get(position);
        holder.bind(employee);
//        holder.name.setText(employee.getName());
//        holder.email.setText(employee.getEmail());
//        holder.phone.setText(employee.getPhone());


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class EmployeeHolder extends RecyclerView.ViewHolder{

        TextView name, email, phone;

        public EmployeeHolder(@NonNull final View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.employee_name);
            email=itemView.findViewById(R.id.employee_email);
            phone=itemView.findViewById(R.id.employee_phone);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   //Employee emp=new Employee();
                    int position=getAdapterPosition();
                    Employee mimi = dataList.get(position);
                   Intent intent=new Intent(applicationContext, HomeActivity.class);
                    intent.putExtra("employeeObject",mimi);
                    applicationContext.startActivity(intent);
                    Toast.makeText(itemView.getContext(), "You pressed : "+position, Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void bind(Employee employee) {
            name.setText(employee.getName());
            email.setText(employee.getEmail());
            phone.setText(employee.getPhone());
        }
    }
}
