package com.example.foodorder.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.foodorder.Adapter.CategoryAdaptor;
import com.example.foodorder.Fragments.CartListFragment;
import com.example.foodorder.Adapter.PopularAdaptor;
import com.example.foodorder.Domain.CategoryDomain;
import com.example.foodorder.Domain.FoodDomain;
import com.example.foodorder.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//    private RecyclerView.Adapter adapter, adapterPopular;
    private PopularAdaptor adapterPopular;
    private CategoryAdaptor adapter;
    private RecyclerView recyclerViewCategoryList, recyclerViewPopularList;
    private ActionBar toolbar;
    private View button;
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewCategory(); //categoryPic
        recyclerViewPopular();
        //toolbar = getSupportActionBar();

        button = findViewById(R.id.seeAll);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CategoryActivity.class));
            }
        });

        bt = (Button) findViewById(R.id.toShare);

        bt.setOnClickListener(new View.OnClickListener () {

            @Override
            public void onClick(View v){
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String body = "Descarca aplicatia pentru a primi o comanda gratuita!";
                String sub = "Prima comanda gratis!";
                myIntent.putExtra(Intent.EXTRA_SUBJECT,sub);
                myIntent.putExtra(Intent.EXTRA_TEXT,body);
                startActivity(Intent.createChooser(myIntent, "Share Using"));
            }
        });

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //toolbar.setTitle("Shop");
    }



    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> categories = new ArrayList<>();
        categories.add(new CategoryDomain("Salads", "cat_salads"));
        categories.add(new CategoryDomain("Coffee", "cat_coffee"));
        categories.add(new CategoryDomain("Pizza", "cat_pizza"));
        categories.add(new CategoryDomain("Burger", "cat_burger"));

        adapter = new CategoryAdaptor(categories);
        recyclerViewCategoryList.setAdapter(adapter);
    }

    private void recyclerViewPopular() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList = findViewById(R.id.recyclerView2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);
        ArrayList<FoodDomain> foodList = new ArrayList<>();
        foodList.add(new FoodDomain("Halloumi Burger", "burger1", "halloumi, hummus, salata, sosuri", 32d));
        foodList.add(new FoodDomain("Veggie Pizza", "pizza1", "ciuperci, masline negre, mozzarella, porumb, dovlecel, sosuri", 36.7d));
        foodList.add(new FoodDomain("Salata de vara", "salad1", "salata, rosii, masline, crutoane, castraveti, ardei, dressing", 23.5d));
        foodList.add(new FoodDomain("Frappucino", "coffee1", "espresso, frisca, caramel, lapte", 18d));

        adapterPopular = new PopularAdaptor(foodList);
        recyclerViewPopularList.setAdapter(adapterPopular);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent(MainActivity.this,MainActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_cart:
                    fragment = new CartListFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}