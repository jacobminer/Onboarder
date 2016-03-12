package com.jrejaud.onboarder;

import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;

import java.io.Serializable;

/**
 * Created by jrejaud on 3/11/16.
 */
public class OnboardingPage implements Serializable {
    private String title;
    private String bodyText;
    private @DrawableRes
    int imageResId;

    public interface onButtonClickedListener {
        void onButtonClicked();
    }

    //Set these to null by default and set the value if the user sets a button
    private String buttonText = null;
    private onButtonClickedListener onButtonClickedListener = null;

    public String getTitle() {
        return title;
    }

    public String getBodyText() {
        return bodyText;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getButtonText() {
        return buttonText;
    }

    public OnboardingPage(@Nullable String title,@Nullable String bodyText) {
        this.title = title;
        this.bodyText = bodyText;
    }

    public OnboardingPage(@Nullable String title,@Nullable String bodyText, int imageResId) {
        this.title = title;
        this.bodyText = bodyText;
        this.imageResId = imageResId;
    }

    public OnboardingPage.onButtonClickedListener getOnButtonClickedListener() {
        return onButtonClickedListener;
    }

    public void setButton(String buttonText, OnboardingPage.onButtonClickedListener onButtonClickedListener) {
        this.buttonText = buttonText;
        this.onButtonClickedListener = onButtonClickedListener;
    }

}
