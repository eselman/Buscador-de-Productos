package com.eselman.productosml.landing;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.eselman.productosml.R;
import com.eselman.productosml.products.SearchProductsActivity;

import org.apache.commons.lang3.StringUtils;

public class ProductLandingActivity extends AppCompatActivity {
    private EditText searchQuery;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_landing);
        searchQuery = findViewById(R.id.searchQuery);
        searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!StringUtils.isEmpty(searchQuery.getText().toString())){
                    Intent searchIntent = new Intent(ProductLandingActivity.this, SearchProductsActivity.class);
                    searchIntent.putExtra("query", searchQuery.getText().toString());
                    startActivity(searchIntent);
                } else {
                    new AlertDialog.Builder(ProductLandingActivity.this)
                            .setTitle("Cadena de Busqueda Invalida")
                            .setMessage("Ingrese un texto para buscar productos")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int i) {
                                    dialog.dismiss();
                                }
                            })
                            .show();              }
           }
        });
        getSupportActionBar().setTitle("Buscar Productos");
    }
}
