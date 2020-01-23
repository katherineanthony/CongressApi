package com.example.tacoapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button ranRep;
    private Button ranSenator;
    private TextView repReturn;
    private TextView senReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wireWidgets();
        setListeners();
    }
//we wanna do the thingy with both so like there's two buttons and when there isn't one it will generate the other
    private void setListeners() {
        ranSenator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(CongressService.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                CongressService congressService = retrofit.create(CongressService.class);

                Call<Congress> senCall = congressService.getRandomRole("senator");

                senCall.enqueue(new Callback<Congress>() {
                    @Override
                    public void onResponse(Call<Congress> call, Response<Congress> response) {
                        /**
                         ANY CODE THAT DEPENDS ON THE RESULT OF THE
                         SEARCH HAS TO GO HERE */

                        Congress foundCongress = response.body();
                        // check if the body isn't null
                        if(foundCongress != null)
                        {
                            senReturn.setText(foundCongress.toString());

                        }

                    }

                    @Override
                    public void onFailure(Call<Congress> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(),
                                Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        ranRep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(CongressService.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                CongressService congressService = retrofit.create(CongressService.class);

                Call<Congress> repCall = congressService.getRandomRole("representative");


                repCall.enqueue(new Callback<Congress>() {
                    @Override
                    public void onResponse(Call<Congress> call, Response<Congress> response) {
                        /**
                         ANY CODE THAT DEPENDS ON THE RESULT OF THE
                         SEARCH HAS TO GO HERE */

                        Congress foundCongress = response.body();
                        // check if the body isn't null
                        if(foundCongress != null)
                        {
                            repReturn.setText(foundCongress.toString());
                        }

                    }

                    @Override
                    public void onFailure(Call<Congress> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(),
                                Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

    }


    private void wireWidgets() {
        ranRep=findViewById(R.id.button_main_rep);
        ranSenator=findViewById(R.id.button_main_senator);
        repReturn=findViewById(R.id.textView_main_rep);
        senReturn=findViewById(R.id.textView_main_senator);


    }
}
