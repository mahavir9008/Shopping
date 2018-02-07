package product.demo.com.productview.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.ButterKnife;
import butterknife.InjectView;
import product.demo.com.productview.R;
import product.demo.com.productview.utility.Constant;

public class ProductDetailActivity extends AppCompatActivity {

    @InjectView(R.id.productName)
    TextView txtProductName;
    @InjectView(R.id.productPrice)
    TextView txtProductPrice;
    @InjectView(R.id.productImage)
    ImageView imgProductImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        ButterKnife.inject(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setData();
    }

    /**
     * get the data from intent and display to user
     */

    private void setData() {
        txtProductName.setText(getIntent().getExtras().getString(Constant.PRODUCT_NAME));
        txtProductPrice.setText(getIntent().getExtras().getString(Constant.PRODUCT_CURRENCY_TYPE) + Constant.SINGLE_SPACE + getIntent().getExtras().getString(Constant.PRODUCT_PRICE));
        Glide.with(this)
                .load(Constant.PRODUCT_SERVER_URL + getIntent().getExtras().getString(Constant.PRODUCT_URL))
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.imagenotfound)
                .into(imgProductImage);

    }

    /**
     *
     * @return - true when home button pressed
     */
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
