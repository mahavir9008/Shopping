package product.demo.com.productview;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import product.demo.com.productview.model.Product;
import product.demo.com.productview.utility.Constant;
import product.demo.com.productview.view.ProductDetailActivity;

import static org.hamcrest.Matchers.notNullValue;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class ActivityIntentDataTest {

    @Rule
    public ActivityTestRule<ProductDetailActivity> rule = new ActivityTestRule<ProductDetailActivity>(ProductDetailActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Product product = new Product();
            product.setName("Bread");
            product.setUrl(Constant.PRODUCT_SERVER_URL);
            InstrumentationRegistry.getTargetContext();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.putExtra(Constant.PRODUCT_NAME, product.getName());
            intent.putExtra(Constant.PRODUCT_PRICE, product.getSalePrice().getAmount());
            intent.putExtra(Constant.PRODUCT_URL, product.getUrl());
            return intent;
        }
    };

    @Test
    public void ensureIntentDataIsDisplayed() throws Exception {
        ProductDetailActivity activity = rule.getActivity();
        Assert.assertNotNull(activity.getIntent().getExtras().getString(Constant.PRODUCT_NAME), notNullValue());
        Assert.assertEquals(activity.getIntent().getExtras().getString(Constant.PRODUCT_NAME), "bread");
        Assert.assertEquals(activity.getIntent().getExtras().getString(Constant.PRODUCT_SERVER_URL), Constant.PRODUCT_URL);
    }
}