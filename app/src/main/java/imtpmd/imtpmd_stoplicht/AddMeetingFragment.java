package imtpmd.imtpmd_stoplicht;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import imtpmd.imtpmd_stoplicht.API.API;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddMeetingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddMeetingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddMeetingFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private SharedPreferences sharedPreferences;

    private OnFragmentInteractionListener mListener;

    public AddMeetingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddMeetingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddMeetingFragment newInstance(String param1, String param2) {
        AddMeetingFragment fragment = new AddMeetingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().setTitle("Nieuwe Bijeenkomst");

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();
        final View view = inflater.inflate(R.layout.fragment_add_meeting, container, false);

        this.sharedPreferences = getActivity().getSharedPreferences("imtpmd.imtpmd_stoplicht", Context.MODE_PRIVATE);
        final String studentnumber = sharedPreferences.getString("studentnumber", "Freek Vonk");

        TextView number = (TextView) view.findViewById(R.id.create_new_studentnumber);
        number.setText(studentnumber);

        // Data enzo
        final EditText fromDate = (EditText) view.findViewById(R.id.from_date);
        final EditText toDate   = (EditText) view.findViewById(R.id.to_date);

        // Beetje tijd enzo
        final EditText fromTime = (EditText) view.findViewById(R.id.from_time);
        final EditText toTime   = (EditText) view.findViewById(R.id.to_time);

        final TextView title       = (TextView) view.findViewById(R.id.create_new_title);
        final TextView description = (TextView) view.findViewById(R.id.create_new_description);
        final Button   versturen   = (Button)   view.findViewById(R.id.toevoegen);
        final Button   annuleren   = (Button)   view.findViewById(R.id.annuleren);


        versturen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                API.createNewMeeting(
                    studentnumber,
                    title.getText().toString(),
                    description.getText().toString(),
                    fromDate.getText() + " " + fromTime.getText(),
                    toDate.getText()   + " " + toTime.getText()
                );

                Toast.makeText(getActivity(), "De bijeenkomst is succesvol toegevoegd.", Toast.LENGTH_LONG).show();

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
                getFragmentManager().popBackStack();
            }
        });

        // From date click listener enzo
        fromDate.setOnClickListener(new View.OnClickListener() {

            // Lekker overriden mag allemaal
            @Override
            public void onClick(View v) {
                DialogFragment fromDateFragment = new DatePickerFragmentFrom();
                fromDateFragment.show(getFragmentManager(), "datePicker");
            }
        });

        toDate.setOnClickListener(new View.OnClickListener() {

            // Lekker overriden mag allemaal
            @Override
            public void onClick(View v) {
                DialogFragment toDateFragment = new DatePickerFragmentTo();
                toDateFragment.show(getFragmentManager(), "datePicker");
            }
        });

        // From time click listener enzo
        fromTime.setOnClickListener(new View.OnClickListener() {

            // Lekker overriden mag allemaal
            @Override
            public void onClick(View v) {
                DialogFragment fromTimeFragment = new TimePickerFragmentFrom();
                fromTimeFragment.show(getFragmentManager(), "timePicker");
            }
        });

        // From time click listener enzo
        toTime.setOnClickListener(new View.OnClickListener() {

            // Lekker overriden mag allemaal
            @Override
            public void onClick(View v) {
                DialogFragment toTimeFragment = new TimePickerFragmentTo();
                toTimeFragment.show(getFragmentManager(), "timePicker");
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

    public static class DatePickerFragmentFrom extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            String date = new SimpleDateFormat("dd/MM/yy").format(new Date(year, month, day));
            ((TextView) getActivity().findViewById(R.id.from_date)).setText(date);
        }
    }

    public static class DatePickerFragmentTo extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            String date = new SimpleDateFormat("dd/MM/yy").format(new Date(year, month, day));
            ((TextView) getActivity().findViewById(R.id.to_date)).setText(date);
        }
    }

    public static class TimePickerFragmentFrom extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            return new TimePickerDialog(getActivity(), this, hour, minute, true);
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            String time = new SimpleDateFormat("HH:mm").format(new Date(0, 0, 0, hourOfDay, minute, 0));

            ((TextView) getActivity().findViewById(R.id.from_time)).setText(time);
        }
    }

    public static class TimePickerFragmentTo extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            return new TimePickerDialog(getActivity(), this, hour, minute, true);
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            String time = new SimpleDateFormat("HH:mm").format(new Date(0, 0, 0, hourOfDay, minute, 0));

            ((TextView) getActivity().findViewById(R.id.to_time)).setText(time);
        }
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
