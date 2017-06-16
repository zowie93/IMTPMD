package imtpmd.imtpmd_stoplicht;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by zowie on 16/06/2017.
 */

public class BijeenkomstActivity extends Activity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_first);
        populateBijeenkomstList();
    }

    private void populateBijeenkomstList() {
        // Construct the data source
        ArrayList<Bijeenkomst> arrayOfBijeenkomsten = Bijeenkomst.getBijeenkomsten();
        // Create the adapter to convert the array to views
        BijeenkomstAdapter adapter = new BijeenkomstAdapter(this, arrayOfBijeenkomsten);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.bijeenkomst_list);
        listView.setAdapter(adapter);
    }
}
