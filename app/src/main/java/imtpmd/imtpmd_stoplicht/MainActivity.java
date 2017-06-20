package imtpmd.imtpmd_stoplicht;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MeetingReviewFragment.OnFragmentInteractionListener  {

    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private SharedPreferences sharedPreferences;

    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.sharedPreferences = this.getSharedPreferences("imtpmd.imtpmd_stoplicht", Context.MODE_PRIVATE);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();

        mDrawer.addDrawerListener(drawerToggle);

        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);

        this.selectDrawerItem(nvDrawer.getMenu().findItem(R.id.nav_meetings_fragment));

        TextView menu = (TextView) nvDrawer.getHeaderView(0).findViewById(R.id.menu_studentnumber);
        menu.setText(sharedPreferences.getString("studentnumber", "Freek Vonk"));
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open,  R.string.drawer_close);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }


    public void selectDrawerItem(MenuItem menuItem) {
        Fragment fragment = null;

        AppCompatActivity activity = this;

        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.nav_meetings_fragment:
                fragmentClass = MeetingsFragment.class;
                break;
            case R.id.nav_results_fragment:
                fragmentClass = ResultsFragment.class;
                break;
            case R.id.nav_credits_fragment:
                fragmentClass = CreditsFragment.class;
                break;
            case R.id.nav_logout:
                SharedPreferences.Editor editor = this.sharedPreferences.edit().remove("studentnumber");
                editor.commit();

                Intent intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                Toast.makeText(activity, "U bent succesvol uitgelogd.", Toast.LENGTH_LONG).show();


                startActivity(intent);

                return;
            default:
                fragmentClass = MeetingsFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        menuItem.setChecked(true);
        mDrawer.closeDrawers();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onBackPressed() {
        this.selectDrawerItem(nvDrawer.getMenu().findItem(R.id.nav_meetings_fragment));
    }
}
