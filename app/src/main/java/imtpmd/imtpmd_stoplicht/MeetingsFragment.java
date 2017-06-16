package imtpmd.imtpmd_stoplicht;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;

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

    public MeetingsFragment() {
        // Required empty public constructor
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

        final ArrayList<Meeting> meetings = new ArrayList<Meeting>();

        // TODO get from API.
        meetings.add(new Meeting(1, 1, "Bijeenkomst 1", "Omschrijving bijeenkomst 1", new Date(), new Date(), new Date(), new Date()));
        meetings.add(new Meeting(2, 2, "Bijeenkomst 2", "Omschrijving bijeenkomst 2", new Date(), new Date(), new Date(), new Date()));
        meetings.add(new Meeting(3, 3, "Bijeenkomst 3", "Omschrijving bijeenkomst 3", new Date(), new Date(), new Date(), new Date()));
        meetings.add(new Meeting(4, 4, "Bijeenkomst 4", "Omschrijving bijeenkomst 4", new Date(), new Date(), new Date(), new Date()));

        MeetingListAdapter adapter = new MeetingListAdapter(getActivity(), meetings);
        ListView meetingsListView = (ListView) view.findViewById(R.id.meetingsListView);
        meetingsListView.setAdapter(adapter);

//        ListView meetingsListView = (ListView) view.findViewById(R.id.meetingsListView);
//
//        BaseAdapter listViewAdapter = new ArrayAdapter<Meeting>(getActivity(),android.R.layout.simple_list_item_2, android.R.id.text1, meetings) {
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent) {
//
//                View view = super.getView(position, convertView, parent);
//
//                Meeting meeting = meetings.get(position);
//                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
//                TextView text2 = (TextView) view.findViewById(android.R.id.text2);
//
//                text1.setText(meeting.getName());
//                text2.setText(meeting.getDescription());
//
//                return view;
//
//            }
//        };
//
//        meetingsListView.setAdapter(listViewAdapter);

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
