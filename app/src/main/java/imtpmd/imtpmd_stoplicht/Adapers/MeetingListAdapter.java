package imtpmd.imtpmd_stoplicht.Adapers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import imtpmd.imtpmd_stoplicht.Models.Meeting;
import imtpmd.imtpmd_stoplicht.R;

public class MeetingListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Meeting> meetings;

    public MeetingListAdapter(Context context, ArrayList<Meeting> meetings) {
        this.context = context;
        this.meetings = meetings;
    }


    @Override
    public int getCount() {
        return this.meetings.size();
    }

    @Override
    public Meeting getItem(int position) {
        return this.meetings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.meetings.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).inflate(R.layout.list_item, parent, false);
        }

        Meeting meeting = (Meeting) this.getItem(position);

        TextView meeting_user_number = (TextView) convertView.findViewById(R.id.meeting_user_number);
        TextView meeting_name        = (TextView) convertView.findViewById(R.id.meeting_name);
        TextView meeting_description = (TextView) convertView.findViewById(R.id.meeting_description);
        TextView meeting_time        = (TextView) convertView.findViewById(R.id.meeting_time);

        meeting_user_number.setText(meeting.getUser().getNumber());
        meeting_name.setText(meeting.getName());
        meeting_description.setText(meeting.getDescription());
        meeting_time.setText(meeting.getStarting_at().toString());

        return convertView;
    }
}
