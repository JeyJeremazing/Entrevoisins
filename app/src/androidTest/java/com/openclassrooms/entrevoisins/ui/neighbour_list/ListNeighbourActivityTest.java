package com.openclassrooms.entrevoisins.ui.neighbour_list;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.CoreMatchers.allOf;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.openclassrooms.entrevoisins.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ListNeighbourActivityTest {


    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityTestRule = new ActivityTestRule<>(ListNeighbourActivity.class);

    @Test
    public void listNeighbourActivityTest2() {
        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.item_list_delete_button),
                        childAtPosition(
                                allOf(withId(R.id.constraint),
                                        childAtPosition(
                                                withId(R.id.list_neighbours),
                                                0)),
                                2),
                        isDisplayed()));
        appCompatImageButton.perform(click());
        ViewInteraction name = onView(
                allOf(withId(R.id.item_list_name),
                        childAtPosition(
                                allOf(withId(R.id.constraint),
                                        childAtPosition(
                                                withId(R.id.list_neighbours),
                                                0)),
                                1),
                        isDisplayed()));
        name.check(matches(withText("Jack")));

    }


    @Test
    public void clickForLaunchTheDetailScreen() {
        onView(withContentDescription("listOfNeighbours")).perform(actionOnItemAtPosition(0, click()));

        onView(ViewMatchers.withId(R.id.pageDetail)).check(matches(isDisplayed()));
    }


    @Test
    public void userNameFilling() {
        onView(withContentDescription("listOfNeighbours")).perform(actionOnItemAtPosition(0, click()));

        onView(allOf(withId(R.id.nameText), withText("Caroline!")));

    }

    @Test
    public void favouriteIsDisplayed() {

        onView(withContentDescription("listOfNeighbours")).perform(actionOnItemAtPosition(0, click()));

        onView(ViewMatchers.withId(R.id.pageDetail)).check(matches(isDisplayed()));

        onView(withId(R.id.floatingActionButton)).perform(click());

        pressBack();

        onView(withId(R.id.container)).perform(swipeLeft());


        onView(withContentDescription("listOfFavourites")).check(withItemCount(1));

        onView(withContentDescription("listOfFavourites")).perform(actionOnItemAtPosition(0, click()));

        onView(allOf(withId(R.id.item_list_name), withText("Caroline!")));

        // onView(withId(R.id.nameText)).check(matches(withText("Caroline")));


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
