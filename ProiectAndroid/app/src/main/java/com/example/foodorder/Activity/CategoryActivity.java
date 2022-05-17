package com.example.foodorder.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorder.Adapter.PopularAdaptor;
import com.example.foodorder.Domain.FoodDomain;
import com.example.foodorder.Fragments.CartListFragment;
import com.example.foodorder.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    private ArrayList<FoodDomain> foodList;

    private PopularAdaptor adapterPopular;
    private RecyclerView recyclerViewPopularList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        recyclerViewPopular();

        EditText editText = findViewById(R.id.edit_text);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void filter(String text) {
        ArrayList<FoodDomain> filteredList = new ArrayList<>();

        for (FoodDomain item : foodList) {
            if (item.getTitle().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        adapterPopular.filterList(filteredList);

    }

    private void recyclerViewPopular() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerViewPopularList = findViewById(R.id.recyclerView2);
        recyclerViewPopularList.setLayoutManager(layoutManager);
        foodList = new ArrayList<>();
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
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL);
//        recyclerViewPopularList.addItemDecoration(dividerItemDecoration);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent(CategoryActivity.this, MainActivity.class);
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