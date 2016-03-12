# Onboarder
Onboarding Library for Android
Inspired by [Onboard](https://github.com/mamaral/Onboard) for iOS.  
This is a simple library to quickly create Onboarding inside your app.  

A (very) simple example
=====

<img src="https://github.com/jrejaud/Onboarder/blob/master/page1.png?raw=true" height="400px" />
<img src="https://github.com/jrejaud/Onboarder/blob/master/page2.png?raw=true" height="400px" />
<img src="https://github.com/jrejaud/Onboarder/blob/master/page3.png?raw=true" height="400px" />


Usage
=====

###Onboarding Pages
First, build **Onboarding Pages**. Each page relates to an **Onboarding Fragment** inside your **Onboarding Activitiy**.  
You can pass a *title*, *body text*, and *drawable resource int*, as well as *button text*.  
If you do not want a button in your **Onboarding Fragment**, user the Constructor method with no *button text* parameter.  
```
//Building Onboarding Pages
OnboardingPage page1 = new OnboardingPage("First screen title","This is important information, pay attention",R.drawable.logo);
OnboardingPage page2 = new OnboardingPage(null,"This is a robut.",R.drawable.logo,"Okay, tell me more");
OnboardingPage page3 = new OnboardingPage("The Robot again!","Hey look, it's the robot again! \nMaybe he wants to be your friend.",R.drawable.logo,"We're done here");
```

####Optional Settings
```
//Optionally set the title and body text colors for a specific page.
page1.setTitleTextColor(R.color.white);
page1.setBodyTextColor(R.color.white);

page2.setBodyTextColor(R.color.white);

page3.setTitleTextColor(R.color.white);
page3.setBodyTextColor(R.color.white);
```

```
//Finally, add all the Onboarding Pages to a list
List<OnboardingPage> onboardingPages = new LinkedList<>();
onboardingPages.add(page1);
onboardingPages.add(page2);
onboardingPages.add(page3);
```

###Onboarding Activity
####Setting a color as background
```
//Create a bundle for the Onboarding Activity
Bundle onboardingActivityBundle = OnboardingActivity.newBundleColorBackground(R.color.greenBackground, onboardingPages);
```

####Setting a color as background
```
//Create a bundle for the Onboarding Activity
Bundle onboardingActivityBundle = OnboardingActivity..newBundleImageBackground(R.drawable.backgroundPicture, onboardingPages);
```

####Optional Settings
```
//Optionally set if the user can swipe between fragments. True by default.
onboardingActivityBundle.putBoolean(OnboardingActivity.SWIPING_ENABLED,true);

//Optionally set if the activity has dot pagination at the bottom of the screen (only available when swiping is enabled). True by default.
onboardingActivityBundle.putBoolean(OnboardingActivity.HIDE_DOT_PAGINATION,false);
```

####Starting the Onboarding Activity
```
//Start the Onboarding Activity
Intent intent = new Intent(this,OnboardingActivity.class);
intent.putExtras(onboardingActivityBundle);
startActivity(intent);
```
####Custom onButtonClick actions
By default, clicking a button in an **Onboarding Fragment** will move to the next one in the list.  
If the **Onboarding Activity** is on the last **Onboarding Fragment**, it will simple `finish()` itself.  

If you want something different to happen when the user clicks a button in an **Onboarding Fragment**, extend the **Onboarding Activitiy** and override the `onOnboardingClick` method.  

It will pass the fragment position inside of the list that the user clicked.
```
public class MyOnboardingActivity extends OnboardingActivity {

    @Override
    public void onOnboardingClick(int position) {
        Log.d("TAG", "Position is: " + position);
    }
}
```
Next, simply start your **Onboarding Activity** instead of the normal one.

```
//Start your version of the Onboarding Activity
Intent intent = new Intent(this,MyOnboardingActivity.class);
intent.putExtras(onboardingActivityBundle);
startActivity(intent);
```


Installation
=====
Onboarder uses [JitPack](https://jitpack.io/#jrejaud/Onboarder)
Gradle Installation:

Add it in your root build.gradle at the end of repositories:
```
	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```

Add the dependencies:
```
dependencies {
		compile 'com.github.User:Repo:Tag'
	}
```

For more info, check the [JitPack page](https://jitpack.io/#jrejaud/Onboarder)
