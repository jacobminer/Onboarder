package com.jrejaud.onboarder;

import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.github.jrejaud.viewpagerindicator2.CirclePageIndicator;

import java.io.Serializable;
import java.util.List;

public class OnboardingActivity extends AppCompatActivity {
    private final static String BACKGROUND_IMAGE_RES_ID = "BACKGROUND_IMAGE_RES_ID";
    private @DrawableRes int backgroundImageResId;
    private final static String BACKGROUND_COLOR_RES_ID = "BACKGROUND_COLOR_RES_ID";
    private @ColorRes int backgroundColorResId;

    private final static String ONBOARDING_FRAGMENT_LIST = "ONBOARDING_FRAGMENT_LIST";
    private List<OnboardingPage> onboardingPages;

    public final static String SWIPING_ENABLED = "SWIPING_ENABLED";

    /** Whether the user can swipe the screen left or right to move between fragments */
    private boolean swipingEnabled;

    /** Whether the pagination dots at the bottom of the activity should be down of not */
    private final static String HIDE_DOT_PAGINATION = "HIDE_DOT_PAGINATION";
    private boolean hideDotPagination;

    //region Static Factory Methods
    public static Bundle newBundleImageBackground(@DrawableRes int backgroundImageResId, @NonNull List<OnboardingPage> onboardingPages) {
        Bundle bundle = new Bundle();
        bundle.putInt(BACKGROUND_IMAGE_RES_ID, backgroundImageResId);

        //Lists are serializable in Java
        bundle.putSerializable(ONBOARDING_FRAGMENT_LIST, (Serializable) onboardingPages);
        return bundle;
    }

    public static Bundle newBundleColorBackground(@ColorRes int backgroundColorResId, @NonNull List<OnboardingPage> onboardingPages) {
        Bundle bundle = new Bundle();
        bundle.putInt(BACKGROUND_COLOR_RES_ID, backgroundColorResId);

        //Lists are serializable in Java
        bundle.putSerializable(ONBOARDING_FRAGMENT_LIST, (Serializable) onboardingPages);
        return bundle;
    }

    public static Bundle newBundleImageBackground(@DrawableRes int backgroundImageResId, boolean swipingEnabled, boolean hideDotPagination, @NonNull List<OnboardingPage> onboardingPages) {
        Bundle bundle = new Bundle();
        bundle.putInt(BACKGROUND_IMAGE_RES_ID, backgroundImageResId);
        bundle.putBoolean(SWIPING_ENABLED, swipingEnabled);
        bundle.putBoolean(HIDE_DOT_PAGINATION, hideDotPagination);

        //Lists are serializable in Java
        bundle.putSerializable(ONBOARDING_FRAGMENT_LIST, (Serializable) onboardingPages);
        return bundle;
    }

    public static Bundle newBundleColorBackground(@ColorRes int backgroundColorResId, boolean swipingEnabled, boolean hideDotPagination, @NonNull List<OnboardingPage> onboardingPages) {
        Bundle bundle = new Bundle();
        bundle.putInt(BACKGROUND_COLOR_RES_ID, backgroundColorResId);
        bundle.putBoolean(SWIPING_ENABLED, swipingEnabled);
        bundle.putBoolean(HIDE_DOT_PAGINATION, hideDotPagination);

        //Lists are serializable in Java
        bundle.putSerializable(ONBOARDING_FRAGMENT_LIST, (Serializable) onboardingPages);
        return bundle;
    }
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        //Extract Data from the Bundle
        Bundle bundle = getIntent().getExtras();

        //Todo need to implement a non-swiping version
        swipingEnabled = bundle.getBoolean(SWIPING_ENABLED, true);
        hideDotPagination = bundle.getBoolean(HIDE_DOT_PAGINATION, false);
        backgroundImageResId = bundle.getInt(BACKGROUND_IMAGE_RES_ID, -1); //-1 means that no image was passed
        backgroundColorResId = bundle.getInt(BACKGROUND_COLOR_RES_ID,-1);
        onboardingPages = (List<OnboardingPage>) bundle.getSerializable(ONBOARDING_FRAGMENT_LIST);

        //Set the view pager
        //TODO Implement a non-swiping version
        ViewPager viewPager = (ViewPager) findViewById(R.id.onboarding_viewpager);
        viewPager.setAdapter(new OnboardingFragmentPagerAdapter(getSupportFragmentManager()));

        //Set the dot pagination
        CirclePageIndicator circlePageIndicator = (CirclePageIndicator)findViewById(R.id.onboadring_page_indicator);
        if (!hideDotPagination) {
            circlePageIndicator.setViewPager(viewPager);
        } else {
            circlePageIndicator.setVisibility(View.GONE);
        }

        //Set the background Image or Color
        ImageView onboardingBackground = (ImageView) findViewById(R.id.onboarding_background_image);
        if (backgroundImageResId!=-1) {
            onboardingBackground.setImageResource(backgroundImageResId);
        } else if (backgroundColorResId!=-1) {
            getWindow().getDecorView().setBackgroundColor(getResources().getColor(backgroundColorResId));
        }

    }

    private class OnboardingFragmentPagerAdapter extends FragmentPagerAdapter {

        public OnboardingFragmentPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            //Build a new fragment from an Onboarding Page (Since Fragment bundles don't seem to be serializable
            return OnboardingFragment.newInstance(onboardingPages.get(position));
        }

        @Override
        public int getCount() {
            return onboardingPages.size();
        }
    }
}
