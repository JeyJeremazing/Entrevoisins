package com.openclassrooms.entrevoisins.ui.neighbour_list;


import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

import com.openclassrooms.entrevoisins.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ListNeighbourActivityTest {

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityTestRule = new ActivityTestRule<>(ListNeighbourActivity.class);

    @Test
    public void VerifAffichageChampsTest() {
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.add_neighbour),
                        childAtPosition(
                                allOf(withId(R.id.main_content),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                2),
                        isDisplayed()));
        floatingActionButton.perform(click());




        ViewInteraction editText3 = onView(
                allOf(withId(R.id.name),
                        withParent(withParent(withId(R.id.nameLyt))),
                        isDisplayed()));
        editText3.check(matches(withText("")));

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.phoneNumber),
                        withParent(withParent(withId(R.id.phoneNumberLyt))),
                        isDisplayed()));
        editText4.check(matches(withText("")));

        ViewInteraction editText5 = onView(
                allOf(withId(R.id.address),
                        withParent(withParent(withId(R.id.addressLyt))),
                        isDisplayed()));
        editText5.check(matches(withText("")));

        ViewInteraction editText6 = onView(
                allOf(withId(R.id.aboutMeView),
                        withParent(withParent(withId(R.id.aboutMeLyt))),
                        isDisplayed()));
        editText6.check(matches(withText("")));

        ViewInteraction button = onView(
                allOf(withId(R.id.create),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class))),
                        isDisplayed()));
        button.check(matches(not(isEnabled())));

        pressBack();
    }

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
