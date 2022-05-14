package com.example.foodorder.Activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorder.Adapter.CategoryAdaptor;
import com.example.foodorder.Adapter.PopularAdaptor;
import com.example.foodorder.Domain.CategoryDomain;
import com.example.foodorder.Domain.FoodDomain;
import com.example.foodorder.Fragments.CartFragment;
import com.example.foodorder.Fragments.DashboardFragment;
import com.example.foodorder.Fragments.HomeFragment;
import com.example.foodorder.Fragments.NotificationsFragment;
import com.example.foodorder.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterPopular;
    private RecyclerView recyclerViewPopularList;
    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        recyclerViewPopular();
        //toolbar = getSupportActionBar();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //toolbar.setTitle("Shop");
    }

    private void recyclerViewPopular() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerViewPopularList = findViewById(R.id.recyclerView2);
        recyclerViewPopularList.setLayoutManager(layoutManager);
        ArrayList<FoodDomain> foodList = new ArrayList<>();
        foodList.add(new FoodDomain("Halloumi Burger", "burger1", "halloumi, hummus, salata, sosuri", 32d));
        foodList.add(new FoodDomain("Veggie Pizza", "pizza1", "ciuperci, masline negre, mozzarella, porumb, dovlecel, sosuri", 36.7d));
        foodList.add(new FoodDomain("Salata de vara", "salad1", "salata, rosii, masline, crutoane, castraveti, ardei, dressing", 23.5d));
        foodList.add(new FoodDomain("Frappucino", "coffee1", "espresso, frisca, caramel, lapte", 18d));
        foodList.add(new FoodDomain("Burger", "burger1", "halloumi, hummus, salata, sosuri", 32d));
        foodList.add(new FoodDomain("Pizza", "pizza1", "ciuperci, masline negre, mozzarella, porumb, dovlecel, sosuri", 36.7d));
        foodList.add(new FoodDomain("Salata", "salad1", "salata, rosii, masline, crutoane, castraveti, ardei, dressing", 23.5d));
        foodList.add(new FoodDomain("Cafea", "coffee1", "espresso, frisca, caramel, lapte", 18d));

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
                    //toolbar.setTitle("Home");
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_dashboard:
                    //toolbar.setTitle("Dashboard");
                    fragment = new DashboardFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_cart:
                    //toolbar.setTitle("Cart");
                    fragment = new CartFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_notifications:
                    //toolbar.setTitle("Notifications");
                    fragment = new NotificationsFragment();
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