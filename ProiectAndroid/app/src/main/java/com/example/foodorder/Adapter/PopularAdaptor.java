package com.example.foodorder.Adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodorder.Activity.ShowDetailActivity;
import com.example.foodorder.Domain.FoodDomain;
import com.example.foodorder.R;

import java.util.ArrayList;
import java.util.List;

public class PopularAdaptor extends RecyclerView.Adapter<PopularAdaptor.ViewHolderFragment> implements Filterable {
    ArrayList<FoodDomain> popularFood;
    ArrayList<FoodDomain> popularFoodFull;

    public PopularAdaptor(ArrayList<FoodDomain> popularFood) {
        this.popularFood = popularFood;
        popularFoodFull =  new ArrayList<>(popularFood);
    }

    @Override
    public ViewHolderFragment onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular, parent, false);
        return new ViewHolderFragment(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFragment holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(popularFood.get(position).getTitle());
        holder.price.setText(String.valueOf(popularFood.get(position).getPrice()));
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(popularFood.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
                intent.putExtra("object", (Parcelable) popularFood.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return popularFood.size();
    }

//    @Override
    public Filter getFilter() {
        return foodFilter;
    }

    private Filter foodFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<FoodDomain> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(popularFoodFull); // display all food when no text detected
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (FoodDomain item : popularFoodFull) {
                    if (item.getTitle().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            popularFood.clear();
            popularFood.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolderFragment extends RecyclerView.ViewHolder {
        TextView title, price, addBtn;
        ImageView pic;

        public ViewHolderFragment(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            pic = itemView.findViewById(R.id.pic);
            addBtn = itemView.findViewById(R.id.addBtn);
        }
    }
}
