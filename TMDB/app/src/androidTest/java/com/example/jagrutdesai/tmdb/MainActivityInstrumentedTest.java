package com.example.jagrutdesai.tmdb;

import android.app.Activity;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingPolicies;
import android.support.test.espresso.IdlingResource;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.jagrutdesai.tmdb.Activities.MainActivity;
import com.example.jagrutdesai.tmdb.Adapter.MovieAdapter;
import com.example.jagrutdesai.tmdb.Models.Movie;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

/**
 * Created by jagrut.desai on 8/3/17.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest

public class MainActivityInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityIntentTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp(){

    }

    @Test
    public void testSearchFunctionality() {
        int waitingTime  = 10;
        Activity activity = mainActivityIntentTestRule.getActivity();
        onView(withId(R.id.search_edittext)).perform(typeText("Top Gun"), closeSoftKeyboard());

        // Make sure Espresso does not time out
        IdlingPolicies.setMasterPolicyTimeout(waitingTime * 6, TimeUnit.SECONDS);
        IdlingPolicies.setIdlingResourceTimeout(waitingTime * 6, TimeUnit.SECONDS);

        // Now we wait
        IdlingResource idlingResource = new ElapsedTimeIdlingResource(waitingTime);
        Espresso.registerIdlingResources(idlingResource);

        RecyclerView listView = activity.findViewById(R.id.movie_list);
        MovieAdapter adapter = (MovieAdapter) listView.getAdapter();
        List<Movie> movies =  adapter.getMovieList();
        Movie movie = movies.get(0);
        String movietitle = movie.getTitle();

        //unregister idling resource.
        Espresso.unregisterIdlingResources(idlingResource);
        assertEquals("Top Gun", movietitle);
    }

    public class ElapsedTimeIdlingResource implements IdlingResource {
        private final long startTime;
        private final long waitingTime;
        private ResourceCallback resourceCallback;

        public ElapsedTimeIdlingResource(long waitingTime) {
            this.startTime = System.currentTimeMillis();
            this.waitingTime = waitingTime;
        }

        @Override
        public String getName() {
            return ElapsedTimeIdlingResource.class.getName() + ":" + waitingTime;
        }

        @Override
        public boolean isIdleNow() {
            long elapsed = System.currentTimeMillis() - startTime;
            boolean idle = (elapsed >= waitingTime);
            if (idle) {
                resourceCallback.onTransitionToIdle();
            }
            return idle;
        }

        @Override
        public void registerIdleTransitionCallback(
                ResourceCallback resourceCallback) {
            this.resourceCallback = resourceCallback;
        }
    }
}
