package com.eselman.productosml.products;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.eselman.productosml.R;
import com.eselman.productosml.landing.ProductLandingActivity;
import com.eselman.productosml.model.Product;
import com.eselman.productosml.productdetail.ProductDetailActivity;
import com.eselman.productosml.utils.DividerItemDecoration;
import com.eselman.productosml.utils.RecyclerTouchListener;

import java.util.List;

public class SearchProductsActivity extends AppCompatActivity implements ProductsContract.ProductsView {
    private RecyclerView productsRecyclerView;
    private RecyclerView.Adapter productsRecyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ProductsPresenter productsPresenter;
    private String query;
    private List<Product> products;
    private ProgressBar searchProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_products);
        productsRecyclerView = (RecyclerView) findViewById(R.id.productsRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        productsRecyclerView.setLayoutManager(layoutManager);
        query = getIntent().getStringExtra("query");
        DividerItemDecoration itemDecor = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        productsRecyclerView.addItemDecoration(itemDecor);
        productsRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                productsRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent productDetailIntent = new Intent(SearchProductsActivity.this, ProductDetailActivity.class);
                Product product = products.get(position);
                Bundle productDetailBundle = new Bundle();
                productDetailBundle.putSerializable("product", product);
                productDetailIntent.putExtra("productBundle", productDetailBundle);
                productDetailIntent.putExtra("query", query);
                startActivity(productDetailIntent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        searchProgressBar = findViewById(R.id.searchProgressBar);

        getSupportActionBar().setTitle("Listado de Productos");
        getSupportActionBar().setHomeButtonEnabled(true);

        productsPresenter = new ProductsPresenter(this, query, this);
        productsPresenter.searchProducts();
    }

    @Override
    public void showProducts(List<Product> products) {
        this.products = products;
        if (!products.isEmpty()) {
            productsRecyclerViewAdapter = new ProductsAdapter(products);
            productsRecyclerView.setAdapter(productsRecyclerViewAdapter);
        } else {
            new AlertDialog.Builder(SearchProductsActivity.this)
                    .setTitle("No hay Resultados")
                    .setMessage("No se encontraron productos para la busqueda ingresada")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            dialog.dismiss();
                            Intent landingIntent = new Intent(SearchProductsActivity.this, ProductLandingActivity.class);
                            startActivity(landingIntent);
                        }
                    })
                    .show();
        }
        hideProgressBar();
    }

    @Override
    public void showErrorMessage(String message) {
        hideProgressBar();
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressBar() {
        searchProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        searchProgressBar.setVisibility(View.GONE);
    }
}
