package imtpmd.imtpmd_stoplicht;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import imtpmd.imtpmd_stoplicht.API.API;
import imtpmd.imtpmd_stoplicht.Adapers.MeetingListAdapter;
import imtpmd.imtpmd_stoplicht.Models.Meeting;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MeetingsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MeetingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeetingsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public boolean isFABOpen = false;

    public MeetingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Bijeenkomsten");
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MeetingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MeetingsFragment newInstance(String param1, String param2) {
        MeetingsFragment fragment = new MeetingsFragment();
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
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_meetings, container, false);

        ArrayList<Meeting> meetings = API.getAllMeetings();

        final MeetingListAdapter adapter = new MeetingListAdapter(getActivity(), meetings);
        ListView meetingsListView = (ListView) view.findViewById(R.id.meetingsListView);

        meetingsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment reviewFragment = new MeetingReviewFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("meeting_id", adapter.getItem(position).getId());
                reviewFragment.setArguments(bundle);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.flContent, reviewFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        meetingsListView.setAdapter(adapter);

        final FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        final FloatingActionButton fab1 = (FloatingActionButton) view.findViewById(R.id.fab1);

        fab.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if (!isFABOpen) {
                    showFABMenu();
                } else {
                    closeFABMenu();
                }
            }

            private void showFABMenu() {
                isFABOpen = true;
                fab1.animate().translationY(-getResources().getDimension(R.dimen.standard_65));
                fab.animate().rotation(135);
            }

            private void closeFABMenu() {
                isFABOpen = false;
                fab1.animate().translationY(0);
                fab.animate().rotation(0);
            }
        });

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFABOpen) {
                    Fragment addMeetingFragment = new AddMeetingFragment();
                    FragmentTransaction addMeetingTrans = getFragmentManager().beginTransaction();
                    addMeetingTrans.replace(R.id.flContent, addMeetingFragment);
                    addMeetingTrans.addToBackStack(null);
                    addMeetingTrans.commit();

                }
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
