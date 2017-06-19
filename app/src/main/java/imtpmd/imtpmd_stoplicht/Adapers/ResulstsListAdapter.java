package imtpmd.imtpmd_stoplicht.Adapers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import imtpmd.imtpmd_stoplicht.Models.Feedback;
import imtpmd.imtpmd_stoplicht.R;

public class ResulstsListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Feedback> feedback;

    public ResulstsListAdapter(Context context, ArrayList<Feedback> feedback) {
        this.context = context;
        this.feedback = feedback;
    }


    @Override
    public int getCount() {
        return this.feedback.size();
    }

    @Override
    public Feedback getItem(int position) {
        return this.feedback.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.feedback.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).inflate(R.layout.results_review_item, parent, false);
        }

        Feedback feedback = this.getItem(position);

        ImageView feedback_emotion        = (ImageView) convertView.findViewById(R.id.feedback_emotion);
        TextView  feedback_student_number = (TextView)  convertView.findViewById(R.id.feedback_student_number);
        TextView  feedback_description    = (TextView)  convertView.findViewById(R.id.feedback_description);
        TextView  feedback_timstamp       = (TextView)  convertView.findViewById(R.id.feedback_timestamp);

        feedback_emotion.setImageResource(context.getResources().getIdentifier(feedback.getEmotion().getSlug(), "drawable", context.getPackageName()));
        feedback_student_number.setText(feedback.getUser().getNumber());
        feedback_description.setText(feedback.getDescription());
        feedback_timstamp.setText(feedback.getCreated_at().toString());


        return convertView;
    }
}
