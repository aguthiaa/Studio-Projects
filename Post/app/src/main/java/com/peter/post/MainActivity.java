package com.peter.post;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.peter.post.models.Post;
import com.peter.post.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView viewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewData=findViewById(R.id.textView1);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        final Call<List<Post>> call = service.loadPost();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()){


                    List<Post> posts =response.body();
                    for (Post post: posts) {
                        String content="";
                        content += "ID: " +post.getId()+"\n";
                        content += "User Id: "+post.getUserId()+"\n";
                        content += "Title:  "+post.getTitle()+"\n";
                        content += "Body: "+post.getBody()+"\n\n";

                        viewData.append(content);

                    }


                }
                else {
                    Toast.makeText(MainActivity.this, "Error while retrieving data", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                String error =t.getMessage();
                Toast.makeText(MainActivity.this, error, Toast.LENGTH_LONG).show();

            }
        });
    }
}
