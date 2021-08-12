package com.android.example.superheroindex;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.example.superheroindex.adapter.HeroAdapter;
import com.android.example.superheroindex.model.Hero;
import com.android.example.superheroindex.model.MyPojo;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static boolean flag = true;

    LinearLayout linearLayout;


    ProgressBar progressBar;

    JsonPlaceHolderApi jsonPlaceHolderApi;

    private EditText searchQueryEditText;

    private TextView emptyViewText;

    RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView =findViewById(R.id.heroListRecyclerView);

        searchQueryEditText = findViewById(R.id.searchEditText);

        Button searchButton = findViewById(R.id.searchButton);

        emptyViewText = findViewById(R.id.noSearchTextView);

        emptyViewText.setVisibility(View.VISIBLE);

        progressBar = new ProgressBar(this);

        linearLayout = findViewById(R.id.linearLayout);

        linearLayout.setVisibility(View.INVISIBLE);



        View.OnClickListener mOnClickListener = v -> searchButtonClicked();


        searchButton.setOnClickListener(mOnClickListener);

    }
    private void searchButtonClicked(){

        hideKeyboard(MainActivity.this);

        mRecyclerView.setVisibility(View.INVISIBLE);

        String BASE_URL = "https://superheroapi.com/api/1713222108862189/search/";
       String input = searchQueryEditText.getText().toString().trim();

        if(input.equals("")){
            Toast.makeText(this,"Error No Input", Toast.LENGTH_SHORT).show();
            System.out.println("we in if condition");
            return;
        }

        emptyViewText.setVisibility(View.INVISIBLE);
        linearLayout.setVisibility(View.VISIBLE);

       Retrofit retrofit = new Retrofit.Builder()
               .baseUrl(BASE_URL)
               .addConverterFactory(GsonConverterFactory.create())
               .build();

        jsonPlaceHolderApi  =retrofit.create(JsonPlaceHolderApi.class);

       showHeroStats(input);


    }

    private void showHeroStats(String query){
        Call<MyPojo> call = jsonPlaceHolderApi.getHeroes(query);

        call.enqueue(new Callback<MyPojo>() {

            @Override
            public void onResponse(@NotNull Call<MyPojo> call, @NotNull Response<MyPojo> response) {
               if(!response.isSuccessful()){
                   Log.i("Connection Error","Code: " + response.code());
                   emptyViewText.setVisibility(View.VISIBLE);
                   linearLayout.setVisibility(View.INVISIBLE);
                    return;
              }


                MyPojo pojo = response.body();


                assert pojo != null;

                if(pojo.heroExist.equals("error")) {
                    Toast.makeText(getApplicationContext(),"Error No such Hero ", Toast.LENGTH_SHORT).show();
                    emptyViewText.setVisibility(View.VISIBLE);
                    linearLayout.setVisibility(View.INVISIBLE);
                    return;
                }

                ArrayList<Hero> myHero = pojo.getHeroes();

                HeroAdapter heroAdapter = new HeroAdapter(myHero, clickedItemIndex -> {
                   if(flag) {
                        flag = false;
                    Log.i("we are here","onClickRetrofitMethod"+String.valueOf(flag));
                        startFragment(myHero.get(clickedItemIndex));
                        //flag = true;
                   }
                });

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());

                mRecyclerView.setLayoutManager(linearLayoutManager);

                mRecyclerView.setHasFixedSize(true);

                mRecyclerView.setVisibility(View.VISIBLE);
                emptyViewText.setVisibility(View.INVISIBLE);
                linearLayout.setVisibility(View.INVISIBLE);

                mRecyclerView.setAdapter(heroAdapter);


            }

            @Override
            public void onFailure(@NotNull Call<MyPojo> call, @NotNull Throwable t) {
                Toast.makeText(MainActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                emptyViewText.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.INVISIBLE);
            }
        });

    }
    private void startFragment(Hero selectedHero){
        Fragment currentFragment = FightFragment.newInstance(selectedHero);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragmentHolder, currentFragment, "LOGIN_TAG")
                .addToBackStack("")
                .commit();


    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager inputManager = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        // check if no view has focus:
        View currentFocusedView = activity.getCurrentFocus();
        if (currentFocusedView != null) {
            inputManager.hideSoftInputFromWindow(currentFocusedView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}