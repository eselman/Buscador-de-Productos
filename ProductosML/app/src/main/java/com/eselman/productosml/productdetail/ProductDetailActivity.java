package com.eselman.productosml.productdetail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.eselman.productosml.R;
import com.eselman.productosml.model.Product;
import com.eselman.productosml.products.SearchProductsActivity;
import com.eselman.productosml.utils.CurrencyUtils;
import com.squareup.picasso.Picasso;

public class ProductDetailActivity extends AppCompatActivity {
    private ImageView productImage;
    private TextView condition;
    private TextView  soldQuantity;
    private TextView productTitle;
    private TextView productPrice;
    private TextView availableQuantity;
    private String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        getSupportActionBar().setTitle("Detalle del Producto");
        getSupportActionBar().setHomeButtonEnabled(true);

        productImage = findViewById(R.id.productImage);
        condition = findViewById(R.id.condition);
        soldQuantity = findViewById(R.id.soldQuantity);
        productTitle = findViewById(R.id.productTitle);
        productPrice = findViewById(R.id.productPrice);
        availableQuantity = findViewById(R.id.availableQuantity);

        query = getIntent().getStringExtra("query");
        Product product = (Product)getIntent().getBundleExtra("productBundle").getSerializable("product");

        if(product != null) {
            loadProduct(product);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent searchIntent = new Intent(this, SearchProductsActivity.class);
                searchIntent.putExtra("query", query);
                startActivity(searchIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void loadProduct(Product product) {
        Picasso.get().load(product.getThumbnail()).resize(500,500).into(productImage);
        condition.setText(product.getCondition().toUpperCase());
        soldQuantity.setText(product.getSoldQuantity().toString());
        productTitle.setText(product.getTitle());
        String currencySymbol = CurrencyUtils.getInstance().getCurrencySymbol(product.getCurrencyId());
        productPrice.setText(currencySymbol + " " + product.getPrice().toString());
        availableQuantity.setText(product.getAvailableQuantity().toString());
    }
}
