package com.wangjiegulu.capmvp.ui.main;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;

import com.wangjiegulu.capmvp.R;
import com.wangjiegulu.capmvp.base.AppImmediateSchedulerRule;
import com.wangjiegulu.capmvp.inject.viewer.ViewerComponent;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 27/03/2018.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class, false, false);

    @Rule
    public AppImmediateSchedulerRule appImmediateSchedulerRule = new AppImmediateSchedulerRule();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        ActivityLifecycleMonitorRegistry.getInstance().addLifecycleCallback((activity, stage) -> {
            if (activity instanceof MainActivity && Stage.PRE_ON_CREATE == stage) {
                ((MainActivity) activity).setCreateViewerComponent(() -> mock(ViewerComponent.class));
                ((MainActivity) activity).presenter = mock(MainPresenter.class);
            }
        });

    }

    @Test
    public void requestUserRepositories_normal() throws Throwable {
        activityRule.launchActivity(null);
        List<GithubRepositoryMainVO> list = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            GithubRepositoryMainVO githubRepositoryVO = mock(GithubRepositoryMainVO.class);
            doReturn("name_" + i).when(githubRepositoryVO).getName();
            doReturn(0 != i ? i * 123 : null).when(githubRepositoryVO).getStargazersCount();
            System.out.println("requestUserRepositories_normal, githubRepository.getStargazersCount(): " + githubRepositoryVO.getStargazersCount());

            list.add(githubRepositoryVO);
        }
        activityRule.runOnUiThread(() -> activityRule.getActivity().onRequestUserRepositories(list));

        // child view count > 0
        onView(withId(R.id.activity_main_rv)).check(ViewAssertions.matches(ViewMatchers.hasMinimumChildCount(1)));
        // unknown stargazers count is null (when i = 0)
        onView(withText(R.string.unknown_stargazers_count)).check(ViewAssertions.matches(isDisplayed()));
        onView(withText("1.2k")).check(ViewAssertions.matches(isDisplayed()));
        // click item and show toast
        onView(withId(R.id.activity_main_rv)).perform(RecyclerViewActions.scrollToPosition(3), RecyclerViewActions.actionOnItemAtPosition(3, click()));
        onView(withText("name_3 clicked")).inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView())))).check(ViewAssertions.matches(isDisplayed()));

    }

    @Test
    public void requestUserRepositories_empty() throws Throwable {
        activityRule.launchActivity(null);

        activityRule.runOnUiThread(() -> activityRule.getActivity().onRequestUserRepositories(null));

        onView(withId(R.id.activity_main_rv)).check(ViewAssertions.matches(ViewMatchers.hasChildCount(0)));
    }

}
