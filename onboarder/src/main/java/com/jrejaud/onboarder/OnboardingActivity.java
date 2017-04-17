package com.jrejaud.onboarder;

import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.github.jrejaud.viewpagerindicator2.CirclePageIndicator;

import java.io.Serializable;
import java.util.List;

public class OnboardingActivity extends AppCompatActivity implements OnboardingFragment.onOnboardingButtonClickListener {


    private final static String BACKGROUND_IMAGE_RES_ID = "BACKGROUND_IMAGE_RES_ID";
    private final static String BACKGROUND_COLOR_RES_ID = "BACKGROUND_COLOR_RES_ID";
    private final static String ONBOARDING_FRAGMENT_LIST = "ONBOARDING_FRAGMENT_LIST";
    /** Whether the user can swipe the screen left or right to move between fragments */
    public final static String SWIPING_ENABLED = "SWIPING_ENABLED";
    /** Whether the pagination dots at the bottom of the activity should be down of not */
    public final static String HIDE_DOT_PAGINATION = "HIDE_DOT_PAGINATION";

    private List<OnboardingPage> onboardingPages;

    private OnboardingFragmentPagerAdapter onboardingFragmentPagerAdapter;

    private ViewPager viewPager;

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
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        //Try to hide the support action bar
        getSupportActionBar().hide();

        //Extract Data from the Bundle
        Bundle bundle = getIntent().getExtras();

        boolean swipingEnabled = bundle.getBoolean(SWIPING_ENABLED, true);
        boolean hideDotPagination = bundle.getBoolean(HIDE_DOT_PAGINATION, false);
        int backgroundImageResId = bundle.getInt(BACKGROUND_IMAGE_RES_ID, -1);
        int backgroundColorResId = bundle.getInt(BACKGROUND_COLOR_RES_ID, -1);

        onboardingPages = (List<OnboardingPage>) bundle.getSerializable(ONBOARDING_FRAGMENT_LIST);

        viewPager = (ViewPager) findViewById(R.id.onboarding_viewpager);
        CirclePageIndicator circlePageIndicator = (CirclePageIndicator)findViewById(R.id.onboadring_page_indicator);

        onboardingFragmentPagerAdapter = new OnboardingFragmentPagerAdapter(getSupportFragmentManager());

        //Set the view pager
        if (swipingEnabled) {
            viewPager = (ViewPager) findViewById(R.id.onboarding_viewpager);
            viewPager.setAdapter(onboardingFragmentPagerAdapter);

            //Set the dot pagination. It can only be set if swiping is enabled.
            circlePageIndicator = (CirclePageIndicator)findViewById(R.id.onboadring_page_indicator);
            if (!hideDotPagination) {
                circlePageIndicator.setViewPager(viewPager);
            } else {
                circlePageIndicator.setVisibility(View.GONE);
            }

        } else {
            //Non-swiping version
            viewPager.setVisibility(View.GONE);
            circlePageIndicator.setVisibility(View.GONE);

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.onboarding_layout, onboardingFragmentPagerAdapter.getItem(0));
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fragmentTransaction.commit();
        }

        //Set the background Image or Color
        ImageView onboardingBackground = (ImageView) findViewById(R.id.onboarding_background_image);
        if (backgroundImageResId !=-1) {
            onboardingBackground.setImageResource(backgroundImageResId);
        } else if (backgroundColorResId !=-1) {
            getWindow().getDecorView().setBackgroundColor(getResources().getColor(backgroundColorResId));
        }

    }

    /** Convenience method invoked by the user to make to the next page in the list (if there are any left) */
    public void goToNextFragment(final int currentPosition) {
        //If there are no more pages left, then just finish the onboarding
        if (currentPosition+1>=onboardingFragmentPagerAdapter.getCount()) {
            finish();
            return;
        }

        viewPager.post(new Runnable() {
            @Override
            public void run() {
                if (onboardingFragmentPagerAdapter != null) {
                    onboardingFragmentPagerAdapter.notifyDataSetChanged();
                }
                viewPager.setCurrentItem(currentPosition+1);
            }
        });
    }

    private class OnboardingFragmentPagerAdapter extends FragmentPagerAdapter {

        public OnboardingFragmentPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            //Build a new fragment from an Onboarding Page (Since Fragment bundles don't seem to be serializable
            return OnboardingFragment.newInstance(onboardingPages.get(position),position);
        }

        @Override
        public int getCount() {
            return onboardingPages.size();
        }
    }

    @Override
    public void onOnboardingClick(int position) {
        //By default, make clicking the button just take the user to the next page
        goToNextFragment(position);

        //Extend Onboarding Activity Click and override this method to make it do stuff
        Log.e(OnboardingActivity.class.getSimpleName(),"You need to extend Onboarding Activity and override onOnboardingClick to make it do something besides move to the next fragment and finish the onboarding when its on the last fragment.");
    }
}
