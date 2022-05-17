package com.example.foodorder.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.foodorder.Adapter.CartListAdapter;
import com.example.foodorder.Adapter.PopularAdaptor;
import com.example.foodorder.Helper.ManagementCart;
import com.example.foodorder.Interface.ChangeNumberItemsListener;
import com.example.foodorder.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CartListFragment extends Fragment {
//    private RecyclerView.Adapter adapter;
    private CartListAdapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    TextView totalFeeTxt, deliveryTxt, totalTxt, emptyTxt, finalTxt, finishBtn;
    private double tax;
    private ScrollView scrollView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        managementCart = new ManagementCart(this.getContext());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }
    private void initView() {
        recyclerViewList = this.getView().findViewById(R.id.recyclerView);
        totalFeeTxt = this.getView().findViewById(R.id.totalFeeTxt);
        deliveryTxt = this.getView().findViewById(R.id.deliveryTxt);
        totalTxt = this.getView().findViewById(R.id.totalTxt);
        emptyTxt = this.getView().findViewById(R.id.emptyTxt);
        scrollView = this.getView().findViewById(R.id.scrollView3);
        recyclerViewList = this.getView().findViewById(R.id.cartView);
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdapter(managementCart.getListCart(), this.getContext(), new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                CalculateCart();
            }
        });

        recyclerViewList.setAdapter(adapter);
        if (managementCart.getListCart().isEmpty()) {
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        } else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void CalculateCart() {
        double percentTax = 0.02;
        double delivery = 10;

        tax = Math.round((managementCart.getTotalFee() * percentTax) * 100) / 100;
        double total = Math.round((managementCart.getTotalFee() + tax + delivery) * 100) / 100;
        double itemTotal = Math.round(managementCart.getTotalFee() * 100) / 100;

        totalFeeTxt.setText(itemTotal + " lei");
        deliveryTxt.setText(delivery + " lei");
        totalTxt.setText(total + " lei");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initList();
        CalculateCart();
    }
}