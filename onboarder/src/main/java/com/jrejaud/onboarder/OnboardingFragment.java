package com.jrejaud.onboarder;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    /** The title which is displayed at the top of the fragment */
    private String title;

    /** The body text which is displayed in the middle of the fragment */
    private String bodyText;

    /** The image resource which is displayed in the middle of the fragment, above the text */
    private @DrawableRes int imageResId;

//    private OnFragmentInteractionListener mListener;

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
    public static OnboardingFragment newInstance(OnboardingPage onboardingPage) {
        OnboardingFragment fragment = new OnboardingFragment();
        Bundle args = new Bundle();
        args.putString(TITLE, onboardingPage.getTitle());
        args.putString(BODY_TEXT, onboardingPage.getBodyText());
        args.putInt(IMAGE_RESOURCE_ID, onboardingPage.getImageResId());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //TODO get a reference to the activity here
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_onboarding, container, false);

        // Inflate the layout for this fragment
        Bundle bundle = getArguments();

        title = bundle.getString(TITLE,null);
        bodyText = bundle.getString(BODY_TEXT, null);
        imageResId = bundle.getInt(IMAGE_RESOURCE_ID,-1);

        TextView titleTextView = (TextView) view.findViewById(R.id.onboarding_fragment_title);
        TextView bodyTextView = (TextView) view.findViewById(R.id.onboarding_fragment_body_text);
        ImageView imageView = (ImageView) view.findViewById(R.id.onboarding_fragment_image);

        //Set the title
        if (title!=null) {
            titleTextView.setText(title);
        } else {
            titleTextView.setVisibility(View.GONE);
        }

        //Set the body text
        if (bodyText!=null) {
            bodyTextView.setText(bodyText);
        } else {
            bodyTextView.setVisibility(View.GONE);
        }

        //Set the image
        if (imageResId!=-1) {
            imageView.setImageResource(imageResId);
        } else {
            imageView.setVisibility(View.GONE);
        }

        return view;
    }

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p/>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
