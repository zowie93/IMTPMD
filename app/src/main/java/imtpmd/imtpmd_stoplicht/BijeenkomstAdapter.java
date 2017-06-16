package imtpmd.imtpmd_stoplicht;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zowie on 16/06/2017.
 */

public class BijeenkomstAdapter extends ArrayAdapter<Bijeenkomst> {

    Context context;

    public BijeenkomstAdapter(Context context, ArrayList<Bijeenkomst> bijeenkomsten) {
        super(context, 0, bijeenkomsten);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Bijeenkomst bijeenkomst = getItem(position);

        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService (Context.LAYOUT_INFLATER_SERVICE);
        View viewRow = layoutInflater.inflate(R.layout.list_item, null, true);

        TextView bijeenkomst_username = (TextView) convertView.findViewById(R.id.bijeenkomst_username);
        TextView bijeenkomst_title = (TextView) convertView.findViewById(R.id.bijeenkomst_title);
        TextView bijeenkomst_desc = (TextView) convertView.findViewById(R.id.bijeenkomst_desc);
        TextView bijeenkomst_time = (TextView) convertView.findViewById(R.id.bijeenkomst_time);

        assert bijeenkomst != null;
        bijeenkomst_username.setText(bijeenkomst.getUser().getNumber());
        bijeenkomst_title.setText(bijeenkomst.getName());
        bijeenkomst_desc.setText(bijeenkomst.getDescription());
        bijeenkomst_time.setText(bijeenkomst.getStarting_at().toString());

        return viewRow;


//        Bijeenkomst bijeenkomst = getItem(position);
//
//        if (convertView == null) {
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
//        }
//
//        TextView bijeenkomst_username = (TextView) convertView.findViewById(R.id.bijeenkomst_username);
//        TextView bijeenkomst_title = (TextView) convertView.findViewById(R.id.bijeenkomst_title);
//        TextView bijeenkomst_desc = (TextView) convertView.findViewById(R.id.bijeenkomst_desc);
//        TextView bijeenkomst_time = (TextView) convertView.findViewById(R.id.bijeenkomst_time);
//
//        bijeenkomst_username.setText(bijeenkomst.getUser().getNumber());
//        bijeenkomst_title.setText(bijeenkomst.getName());
//        bijeenkomst_desc.setText(bijeenkomst.getDescription());
//        bijeenkomst_time.setText(bijeenkomst.getStarting_at().toString());
//
//        return convertView;

    }
}
