package com.jrejaud.onboarder;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnboardingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OnboardingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OnboardingFragment extends Fragment implements Serializable {

    // the fragment initialization parameters
    private static final String TITLE = "TITLE";
    private static final String BODY_TEXT = "BODY_TEXT";
    private static final String IMAGE_RESOURCE_ID = "IMAGE_RESOURCE_ID";
    private static final String POSITION = "POSITION";
    private static final String  BUTTON_TEXT = "BUTTON_TEXT";

    public OnboardingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param title The title which is displayed at the top of the fragment.
     * @param bodyText The body text which is displayed in the middle of the fragment.
     * @param imageResId The image resource which is displayed in the middle of the fragment, above the text
     * @return A new instance of fragment OnboardingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OnboardingFragment newInstance(OnboardingPage onboardingPage, int position) {
        OnboardingFragment fragment = new OnboardingFragment();
        Bundle args = new Bundle();
        args.putString(TITLE, onboardingPage.getTitle());
        args.putString(BODY_TEXT, onboardingPage.getBodyText());
        args.putInt(IMAGE_RESOURCE_ID, onboardingPage.getImageResId());
        args.putInt(POSITION, position);
        args.putString(BUTTON_TEXT, onboardingPage.getButtonText());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_onboarding, container, false);

        // Inflate the layout for this fragment
        Bundle bundle = getArguments();

        /* The title which is displayed at the top of the fragment */
        String title = bundle.getString(TITLE, null);
        /* The body text which is displayed in the middle of the fragment */
        String bodyText = bundle.getString(BODY_TEXT, null);
        /* The image resource which is displayed in the middle of the fragment, above the text */
        int imageResId = bundle.getInt(IMAGE_RESOURCE_ID, -1);
        /* The position that the fragment is in adapter */
        final int position = bundle.getInt(POSITION, 0);
        /* The button text (if the user set any) */
        String buttonText = bundle.getString(BUTTON_TEXT, null);

        TextView titleTextView = (TextView) view.findViewById(R.id.onboarding_fragment_title);
        TextView bodyTextView = (TextView) view.findViewById(R.id.onboarding_fragment_body_text);
        ImageView imageView = (ImageView) view.findViewById(R.id.onboarding_fragment_image);
        Button button = (Button) view.findViewById(R.id.onboarding_fragment_button);

        //Set the title
        if (title !=null) {
            titleTextView.setText(title);
        } else {
            titleTextView.setVisibility(View.GONE);
        }

        //Set the body text
        if (bodyText !=null) {
            bodyTextView.setText(bodyText);
        } else {
            bodyTextView.setVisibility(View.GONE);
        }

        //Set the image
        if (imageResId !=-1) {
            imageView.setImageResource(imageResId);
        } else {
            imageView.setVisibility(View.GONE);
        }

        //Set the button text and link it to the method
        if (buttonText!=null) {
            button.setText(buttonText);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickOnboardingButton(position);
                }
            });
        } else {
            button.setVisibility(View.GONE);
        }

        return view;
    }

    private onOnboardingButtonClickListener buttonClickListener;

    private void clickOnboardingButton(int position) {
        buttonClickListener.onOnboardingClick(position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        buttonClickListener = (onOnboardingButtonClickListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        buttonClickListener = null;
    }

    public interface onOnboardingButtonClickListener {
        void onOnboardingClick(int position);
    }
}
