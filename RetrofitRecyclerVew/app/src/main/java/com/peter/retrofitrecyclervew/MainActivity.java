package com.peter.retrofitrecyclervew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.peter.retrofitrecyclervew.adapter.EmployeeAdapter;
import com.peter.retrofitrecyclervew.model.Employee;
import com.peter.retrofitrecyclervew.model.EmployeeList;
import com.peter.retrofitrecyclervew.network.ApiService;
import com.peter.retrofitrecyclervew.network.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EmployeeAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.employee_recycler_view);
        fetchEmployees();
    }

    private void fetchEmployees() {
        /*Create handle for the RetrofitInstance interface*/
        ApiService service = RetrofitInstance.getRetrofitInstance().create(ApiService.class);

        /*Call the method with parameter in the interface to get the employee data*/
        Call<EmployeeList> call = service.getEmployeeData(100);


        call.enqueue(new Callback<EmployeeList>() {
            @Override
            public void onResponse(Call<EmployeeList> call, Response<EmployeeList> response) {
                generateEmployeeList(response.body().getEmployeeArrayList());
            }

            @Override
            public void onFailure(Call<EmployeeList> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    private void generateEmployeeList(ArrayList<Employee> employeeArrayList) {
//    }

    /*Method to generate List of employees using RecyclerView with custom adapter*/
    private void generateEmployeeList(ArrayList<Employee> empDataList) {
        recyclerView = (RecyclerView) findViewById(R.id.employee_recycler_view);

        adapter = new EmployeeAdapter(getApplicationContext(),empDataList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }

}
