package imtpmd.imtpmd_stoplicht;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import imtpmd.imtpmd_stoplicht.API.API;
import imtpmd.imtpmd_stoplicht.Models.Meeting;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MeetingReviewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MeetingReviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeetingReviewFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private int emotion_id;

    private ImageView verdrietig;
    private ImageView neutraal;
    private ImageView blij;

    private SharedPreferences sharedPreferences;

    private OnFragmentInteractionListener mListener;

    public MeetingReviewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MeetingReviewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MeetingReviewFragment newInstance(String param1, String param2) {
        MeetingReviewFragment fragment = new MeetingReviewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Evaluatie");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_meeting_review, container, false);

        this.sharedPreferences = this.getActivity().getSharedPreferences("imtpmd.imtpmd_stoplicht", Context.MODE_PRIVATE);

        Bundle bundle = this.getArguments();
        final int meeting_id = bundle.getInt("meeting_id");

        Meeting meeting = API.getMeetingById(meeting_id);

        TextView review_meeting_name = (TextView) view.findViewById(R.id.review_meeting_name);
        review_meeting_name.setText(meeting.getName());

        this.emotion_id = 3;

        this.verdrietig = (ImageView) view.findViewById(R.id.review_verdrietig);
        this.neutraal   = (ImageView) view.findViewById(R.id.review_neutraal);
        this.blij       = (ImageView) view.findViewById(R.id.review_blij);

        Button annuleren = (Button) view.findViewById(R.id.review_annuleren);
        Button versturen = (Button) view.findViewById(R.id.review_versturen);

        verdrietig.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                emotion_id = 3;

                verdrietig.setImageResource(R.drawable.verdrietig);
                neutraal.setImageResource(R.drawable.neutraal_active);
                blij.setImageResource(R.drawable.blij_active);

            }
        });

        neutraal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                emotion_id = 2;

                verdrietig.setImageResource(R.drawable.verdrietig_active);
                neutraal.setImageResource(R.drawable.neutraal);
                blij.setImageResource(R.drawable.blij_active);
            }
        });

        blij.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                emotion_id = 1;

                verdrietig.setImageResource(R.drawable.verdrietig_active);
                neutraal.setImageResource(R.drawable.neutraal_active);
                blij.setImageResource(R.drawable.blij);
            }
        });


        versturen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TextView review_description = (TextView) view.findViewById(R.id.review_description);

                String description = review_description.getText().toString();
                String username    = sharedPreferences.getString("studentnumber", "Freek Vonk");

                API.giveFeedback(meeting_id, emotion_id, username, description);

                Toast.makeText(getActivity(), "Uw feedback is succesvol opgeslagen!", Toast.LENGTH_LONG).show();

                Fragment meetingsFragment = new MeetingsFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.flContent, meetingsFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        annuleren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment meetingsFragment = new MeetingsFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.flContent, meetingsFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
