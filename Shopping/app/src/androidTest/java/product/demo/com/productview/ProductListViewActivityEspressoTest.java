package product.demo.com.productview;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import product.demo.com.productview.view.ProductListViewActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ProductListViewActivityEspressoTest {

    @Rule
    public ActivityTestRule<ProductListViewActivity> mActivityTestRule = new ActivityTestRule<>(ProductListViewActivity.class);

    @Test
    public void productListViewActivityEspressoTest() {
        ViewInteraction textView = onView(
                allOf(withId(R.id.title), withText("Food"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listViewProduct),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Food")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.title), withText("Food"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listViewProduct),
                                        0),
                                0),
                        isDisplayed()));
        textView2.check(matches(withText("Food")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.title), withText("Sandwich"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listViewProduct),
                                        2),
                                1),
                        isDisplayed()));
        textView3.check(matches(withText("Sandwich")));

        ViewInteraction imageView = onView(
                allOf(withId(R.id.thumbnail), withContentDescription("android:contentDescription="),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listViewProduct),
                                        1),
                                0),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.title), withText("Drink"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listViewProduct),
                                        5),
                                0),
                        isDisplayed()));
        textView4.check(matches(withText("Drink")));

        ViewInteraction imageView2 = onView(
                allOf(withId(R.id.thumbnail), withContentDescription("android:contentDescription="),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listViewProduct),
                                        3),
                                0),
                        isDisplayed()));
        imageView2.check(matches(isDisplayed()));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
