
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;



/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;

    private ListNeighbourActivity mActivity;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(withContentDescription("listOfNeighbours"))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(withContentDescription("listOfNeighbours")).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(withContentDescription("listOfNeighbours"))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(withContentDescription("listOfNeighbours")).check(withItemCount(ITEMS_COUNT-1));
    }
    @Test
    public void clickForLaunchTheDetailScreen() {
        onView(withContentDescription("listOfNeighbours")).perform(actionOnItemAtPosition(0, click()));

        onView(ViewMatchers.withId(R.id.pageDetail)).check(matches(isDisplayed()));
    }


    @Test
    public void userNameFilling() {
        onView(withContentDescription("listOfNeighbours")).perform(actionOnItemAtPosition(0, click()));

        onView(withId(R.id.nameText)).check(matches(withText("Caroline")));


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

        onView(withId(R.id.nameText)).check(matches(withText("Caroline")));


    }


}