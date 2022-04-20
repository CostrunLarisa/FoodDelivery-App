package com.example.foodorder.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodorder.Domain.CategoryDomain;
import com.example.foodorder.R;

import java.util.ArrayList;

public class CategoryAdaptor extends RecyclerView.Adapter<CategoryAdaptor.ViewHolderFragment> {
    ArrayList<CategoryDomain> categoriesDomain;

    public CategoryAdaptor(ArrayList<CategoryDomain> categoriesDomain) {
        this.categoriesDomain = categoriesDomain;
    }

    @Override
    public ViewHolderFragment onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category,parent, false);
        return new ViewHolderFragment(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFragment holder, int position) {
        holder.categoryName.setText(categoriesDomain.get(position).getTitle());
        String picUrl = "";
        switch (position) {
            case 0: {
                picUrl = "salad";
                break;
            }case 1: {
                picUrl = "coffee";
                break;
            }case 2: {
                picUrl = "pizza";
                break;
            }case 3: {
                picUrl = "burger";
                break;
            }
        }
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.categoryPath);
    }

    @Override
    public int getItemCount() {
        return categoriesDomain.size();
    }

    public class ViewHolderFragment extends RecyclerView.ViewHolder{
        TextView categoryName;
        ImageView categoryPath;
        ConstraintLayout mainLayout;
        public ViewHolderFragment(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.categoryName);
            categoryPath = itemView.findViewById(R.id.categoryPic);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
