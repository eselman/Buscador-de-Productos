package com.eselman.productosml.products;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eselman.productosml.R;
import com.eselman.productosml.model.Product;
import com.eselman.productosml.utils.CurrencyUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder> {
    private List<Product> products;

    public static class ProductsViewHolder extends RecyclerView.ViewHolder {
        public ImageView thumbnail;
        public TextView price;
        public TextView title;

        public ProductsViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            price = itemView.findViewById(R.id.price);
            title = itemView.findViewById(R.id.title);
        }
    }

    public ProductsAdapter(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.search_product_item, viewGroup, false);
        ProductsViewHolder productsViewHolder = new ProductsViewHolder(view);
        return productsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsViewHolder productsViewHolder, int position) {
        Product product = products.get(position);
        String currencySymbol = CurrencyUtils.getInstance().getCurrencySymbol(product.getCurrencyId());
        productsViewHolder.price.setText(currencySymbol + " " + product.getPrice().toString());
        productsViewHolder.title.setText(product.getTitle());
        Picasso.get().load(product.getThumbnail()).placeholder(R.mipmap.ic_launcher).resize(200,200).into(productsViewHolder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
