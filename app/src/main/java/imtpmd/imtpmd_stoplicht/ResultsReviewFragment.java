package imtpmd.imtpmd_stoplicht;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;


import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;

import imtpmd.imtpmd_stoplicht.API.API;
import imtpmd.imtpmd_stoplicht.Adapers.MeetingListAdapter;
import imtpmd.imtpmd_stoplicht.Adapers.ResulstsListAdapter;
import imtpmd.imtpmd_stoplicht.Models.Feedback;
import imtpmd.imtpmd_stoplicht.Models.FeedbackStats;
import imtpmd.imtpmd_stoplicht.Models.Meeting;

import static android.R.attr.entries;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ResultsReviewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ResultsReviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultsReviewFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    PieChart pieChart ;
    ArrayList<Entry> entries ;
    ArrayList<String> PieEntryLabels ;
    PieDataSet pieDataSet ;
    PieData pieData ;

    private OnFragmentInteractionListener mListener;

    public ResultsReviewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Feedback");
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ResultsReviewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ResultsReviewFragment newInstance(String param1, String param2) {
        ResultsReviewFragment fragment = new ResultsReviewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().setTitle("Resultaat");

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_results_review, container, false);

        Bundle bundle = this.getArguments();
        final int meeting_id = bundle.getInt("meeting_id");

        ArrayList<Feedback> feedback = API.getFeedbackByMeetingId(meeting_id);
        Meeting meeting = API.getMeetingById(meeting_id);

        TextView review_meeting_name = (TextView) view.findViewById(R.id.result_meeting_name);
        review_meeting_name.setText(meeting.getName());

        final ResulstsListAdapter adapter = new ResulstsListAdapter(getActivity(), feedback);
        ListView feedbackListView = (ListView) view.findViewById(R.id.feedbackListView);
        feedbackListView.setAdapter(adapter);

        pieChart = (PieChart) view.findViewById(R.id.chart);

        pieChart.setDrawSliceText(false);
        pieChart.setDescription(null);
        pieChart.getLegend().setEnabled(false);

        entries = new ArrayList<>();

        PieEntryLabels = new ArrayList<String>();

        AddValuesToPIEENTRY(API.getFeedbackStatsByMeetingId(meeting_id));

        AddValuesToPieEntryLabels();

        pieDataSet = new PieDataSet(entries, "");

        pieData = new PieData(PieEntryLabels, pieDataSet);
        pieData.setValueTextSize(25f);
        pieData.setValueTextColor(Color.WHITE);
        pieData.setValueFormatter(new ValueFormatter() {

            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return Math.round(value)+"";
            }
        });

        pieDataSet.setColors(new int [] {
            Color.rgb(19, 145, 128), Color.rgb(253, 166, 57), Color.rgb(252, 68, 75)
        });

        pieChart.setData(pieData);

        // Inflate the layout for this fragment
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

    public void AddValuesToPIEENTRY(FeedbackStats feedbackStats){

        if (feedbackStats.getBlij() > 0) {
            entries.add(new BarEntry(feedbackStats.getBlij(), 0));
        }

        if (feedbackStats.getNeutraal() > 0) {
            entries.add(new BarEntry(feedbackStats.getNeutraal(), 1));
        }

        if (feedbackStats.getVerdrietig() > 0) {
            entries.add(new BarEntry(feedbackStats.getVerdrietig(), 2));
        }
    }

    public void AddValuesToPieEntryLabels(){

        PieEntryLabels.add("Blij");
        PieEntryLabels.add("Neutraal");
        PieEntryLabels.add("Verdrietig");

    }
}
