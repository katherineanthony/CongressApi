package com.example.tacoapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
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
    private TextView repParty;
    private TextView repPositionTitle;
    private TextView senParty;
    private TextView senPositionTitle;
    //private Switch republican;
    //private Switch democrat;
   // private boolean isRepublican;
    //private boolean isDemocratic;
    private int range;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wireWidgets();
        setListeners();

        //isRepublican=false;
        //isDemocratic=false;
    }

    private void setListeners() {
        /**republican.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isRepublican=true;
            }
        });
        democrat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isDemocratic=true;
            }
        });*/
        ranSenator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(CongressService.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                CongressService congressService = retrofit.create(CongressService.class);

                /**boolean isRepublican2;
                String result= "";
                if(isRepublican){
                    isRepublican2=true;
                    result="republican";
                    range=53;
                }
                else if(isDemocratic){
                    isRepublican2=false;
                    result="democratic";
                    range=45;
                }
                else{
                    range=80;
                }*/



                Call<Congress> senCall = congressService.getRandomRole("true","senator");

                senCall.enqueue(new Callback<Congress>() {
                    @Override
                    public void onResponse(Call<Congress> call, Response<Congress> response) {
                        /**
                         ANY CODE THAT DEPENDS ON THE RESULT OF THE
                         SEARCH HAS TO GO HERE */
                        //int senatorNumber = (int)(Math.random()*range + 1);

                        Congress foundCongress = response.body();
                        // check if the body isn't null
                        if(foundCongress != null)
                        {
                            Object[] objs = (foundCongress.getObjects());
                            int length = (int)((objs.length -1)*Math.random() + 1);
                            Person personReturned = objs[length].getPerson();
                            String name = personReturned.getName();

                            senParty.setText(objs[length].getParty().toString());
                            senPositionTitle.setText(objs[length].getTitle().toString());
                            senReturn.setText(name);


                            //partyTextView.setText(objs[0].getParty()) or whatever
                            // ^ do the same with title yay

                        }

                    }

                    @Override
                    public void onFailure(Call<Congress> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(),
                                Toast.LENGTH_SHORT).show();
                        Log.d("sumthingThanksToJackson", "onFailure: " + t.getMessage());

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

                /**boolean isRepublican2;
                String result= "";
                if(isRepublican){
                    isRepublican2=true;
                    result="republican";
                    range=100;
                }
                else if(isDemocratic){
                    isRepublican2=false;
                    result="democratic";
                    range=100;
                }
                else{
                    range=50;
                }
                //String representativeNumber = Integer.toString((int)(Math.random()*range + 1));*/

                Call<Congress> repCall = congressService.getRandomRole("true","representative");


                repCall.enqueue(new Callback<Congress>() {
                    @Override
                    public void onResponse(Call<Congress> call, Response<Congress> response) {
                        /**
                         ANY CODE THAT DEPENDS ON THE RESULT OF THE
                         SEARCH HAS TO GO HERE */
                        //int representativeNumber = (int)(Math.random()* + 1);

                        Congress foundCongress = response.body();
                        // check if the body isn't null
                        if(foundCongress != null)
                        {
                            Object[] objs = (foundCongress.getObjects());
                            int length = (int)((objs.length -1)*Math.random() + 1);
                            Person personReturned = objs[length].getPerson();
                            String name = personReturned.getName();

                            repParty.setText(objs[length].getParty());
                            repPositionTitle.setText(objs[length].getTitle());
                            repReturn.setText(name);
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
        repReturn=findViewById(R.id.textView_main_nameRep);
        senReturn=findViewById(R.id.textView_main_nameSen);
        senPositionTitle=findViewById(R.id.textView_main_titleSen);
        senParty=findViewById(R.id.textView_main_partySen);
        repParty=findViewById(R.id.textView_main_partyRep);
        repPositionTitle=findViewById(R.id.textView_main_titleRep);
        //republican=findViewById(R.id.switch_main_republican);
        //democrat=findViewById(R.id.switch_main_democrat);



    }
}
