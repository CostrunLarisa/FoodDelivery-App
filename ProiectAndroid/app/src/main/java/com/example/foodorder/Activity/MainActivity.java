package com.example.foodorder.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.foodorder.Adapter.CategoryAdaptor;
import com.example.foodorder.Adapter.PopularAdaptor;
import com.example.foodorder.Domain.CategoryDomain;
import com.example.foodorder.Domain.FoodDomain;
import com.example.foodorder.Fragments.CartFragment;
import com.example.foodorder.Fragments.HomeFragment;
import com.example.foodorder.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter, adapterPopular;
    private RecyclerView recyclerViewCategoryList, recyclerViewPopularList;
    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewCategory();
        recyclerViewPopular();
        //toolbar = getSupportActionBar();

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
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_cart:
                    fragment = new CartFragment();
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