package com.jrejaud.onboarder;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;

import java.io.Serializable;

/**
 * Created by jrejaud on 3/11/16.
 */
public class OnboardingPage implements Serializable {
    private String title;
    private @ColorRes int titleTextColor = -1;
    private String bodyText;
    private @ColorRes int bodyTextColor = -1;
    private @DrawableRes
    int imageResId = -1;

    int maxImageHeight = -1;

    private String buttonText = null;

    protected String getTitle() {
        return title;
    }

    protected String getBodyText() {
        return bodyText;
    }

    protected int getImageResId() {
        return imageResId;
    }

    protected String getButtonText() {
        return buttonText;
    }

    protected int getTitleTextColor() {
        return titleTextColor;
    }

    public void setTitleTextColor(int titleTextColor) {
        this.titleTextColor = titleTextColor;
    }

    protected int getBodyTextColor() {
        return bodyTextColor;
    }

    public void setBodyTextColor(int bodyTextColor) {
        this.bodyTextColor = bodyTextColor;
    }



    public OnboardingPage(@Nullable String title,@Nullable String bodyText) {
        this.title = title;
        this.bodyText = bodyText;
        this.imageResId = -1;
        this.buttonText = null;
    }

    public OnboardingPage(@Nullable String title,@Nullable String bodyText, int imageResId) {
        this.title = title;
        this.bodyText = bodyText;
        this.imageResId = imageResId;
        this.buttonText = null;
    }

    public OnboardingPage(@Nullable String title,@Nullable String bodyText, int imageResId,  @Nullable String buttonText) {
        this.title = title;
        this.bodyText = bodyText;
        this.buttonText = buttonText;
        this.imageResId = imageResId;
    }


    public OnboardingPage(@Nullable String title,@Nullable String bodyText, @Nullable String buttonText) {
        this.title = title;
        this.bodyText = bodyText;
        this.buttonText = buttonText;
    }

    protected int getMaxImageHeight() {
        return maxImageHeight;
    }

    public void setMaxImageHeight(int maxImageHeight) {
        this.maxImageHeight = maxImageHeight;
    }



}
