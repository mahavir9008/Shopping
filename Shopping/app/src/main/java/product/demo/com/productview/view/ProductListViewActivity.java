package product.demo.com.productview.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;
import product.demo.com.productview.R;
import product.demo.com.productview.adapter.ProductListAdapter;
import product.demo.com.productview.model.Product;
import product.demo.com.productview.model.ProductType;
import product.demo.com.productview.presenters.Presenter;
import product.demo.com.productview.services.ProductService;
import product.demo.com.productview.utility.Constant;
import product.demo.com.productview.utility.InternetConnection;

public class ProductListViewActivity extends AppCompatActivity {

    @InjectView(R.id.listViewProduct)
    ListView mListViewProduct;

    ProductListAdapter productListAdapter;
    Presenter presenter;
    ProductService productService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        if (InternetConnection.checkConnection(this)) {
            productService = new ProductService();
            presenter = new Presenter(this, productService);
            presenter.loadProductItem();
        } else {
            Toast.makeText(this, getString(R.string.internet_status), Toast.LENGTH_LONG).show();
            finish();
        }
    }

    /**
     * \
     *
     * @param position - get position item of the listView which user clicked,
     *                 pass the data through intent to display in another activity
     */
    @OnItemClick(R.id.listViewProduct)
    public void onItemSelect(int position) {

        Product product = productListAdapter.getItem(position);
        if (product.getUrl() != null) {
            Intent detailIntent = new Intent(this, ProductDetailActivity.class);
            detailIntent.putExtra(Constant.PRODUCT_NAME, product.getName());
            detailIntent.putExtra(Constant.PRODUCT_PRICE, product.getSalePrice().getAmount());
            detailIntent.putExtra(Constant.PRODUCT_URL, product.getUrl());
            detailIntent.putExtra(Constant.PRODUCT_CURRENCY_TYPE, product.getSalePrice().getCurrency());
            startActivity(detailIntent);
        }

    }

    /**
     * @param products - got data from the server
     *                 categorize the data into header and body part
     *                 populate in the listView
     */
    public void displayProductItem(List<ProductType> products) {
        productListAdapter = new ProductListAdapter(this);
        for (int i = 0; i < products.size(); i++) {
            Product product = new Product();
            product.setName(products.get(i).getName());
            productListAdapter.addSectionHeaderItem(product);
            for (int j = 0; j < products.get(i).getProducts().size(); j++) {
                Product product1 = products.get(i).getProducts().get(j);
                productListAdapter.addItem(product1);
            }
        }
        mListViewProduct.setAdapter(productListAdapter);
    }
}
