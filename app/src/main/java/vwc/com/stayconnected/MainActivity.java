package vwc.com.stayconnected;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.Objects;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar appToolbar;
    private TabLayout tabLayout;
    private boolean isPermissionGranted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkforPermissions();

        if(isPermissionGranted)
        {
            // ToolBar
            appToolbar = (Toolbar) findViewById(R.id.app_toolbar);
            setSupportActionBar(appToolbar);
            Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
            appToolbar.setTitle("Phone");

            // Side Navigation Bar
            mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
            mDrawerLayout.addDrawerListener(mToggle);
            mToggle.syncState();
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            // Tab Layout
            tabLayout = (TabLayout) findViewById(R.id.tabLayout);
            ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
            viewPager.setOffscreenPageLimit(5);
            ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
            viewPagerAdapter.addfragments(new Phone_Fragment(), "Phone");
            viewPagerAdapter.addfragments(new Music_Fragment(), "Music");
            viewPagerAdapter.addfragments(new Internal_Storage_Fragment(), "Internal Storage");
            viewPagerAdapter.addfragments(new Gallery_Fragment(), "Gallery");
            viewPagerAdapter.addfragments(new Messages_Fragment(), "Messages");
            viewPager.setAdapter(viewPagerAdapter);
            tabLayout.setupWithViewPager(viewPager);
            tabLayout.setSelectedTabIndicatorColor(getColor(R.color.indigoDark));

            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    if(position == 0)
                    {
                        appToolbar.getMenu().clear();
                        appToolbar.inflateMenu(R.menu.toolbar_menu);
                        appToolbar.setTitle("Phone");
                    }
                    else if(position == 1)
                    {
                        appToolbar.getMenu().clear();
                        appToolbar.inflateMenu(R.menu.toolbar_menu);
                        appToolbar.setTitle("Music");
                    }
                    else if(position == 2)
                    {
                        appToolbar.getMenu().clear();
                        appToolbar.inflateMenu(R.menu.toolbar_menu);
                        appToolbar.setTitle("Internal Storage");
                    }
                    else if(position == 3)
                    {
                        appToolbar.getMenu().clear();
                        appToolbar.inflateMenu(R.menu.toolbar_menu);
                        appToolbar.setTitle("Gallery");
                    }
                    else if(position == 4)
                    {
                        appToolbar.getMenu().clear();
                        appToolbar.inflateMenu(R.menu.toolbar_menu);
                        appToolbar.setTitle("Messages");
                    }

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    if (tab.getPosition() == 0)
                    {
                        mDrawerLayout.setBackgroundResource(R.color.colorPrimaryDark);
                        appToolbar.setBackgroundResource(R.color.colorPrimaryDark);
                        tabLayout.setBackgroundResource(R.color.colorPrimaryDark);
                        tabLayout.setSelectedTabIndicatorColor(getColor(R.color.indigoDark));
                        Utils.darkenStatusBar(MainActivity.this, R.color.colorPrimaryDark);
                        //Toast.makeText(getApplicationContext(), tab.getText().toString(), LENGTH_SHORT).show();
                    }
                    else if (tab.getPosition() == 1)
                    {
                        mDrawerLayout.setBackgroundResource(R.color.orange);
                        appToolbar.setBackgroundResource(R.color.orange);
                        tabLayout.setBackgroundResource(R.color.orange);
                        tabLayout.setSelectedTabIndicatorColor(getColor(R.color.orangeDark));
                        Utils.darkenStatusBar(MainActivity.this, R.color.orange);
                        //Toast.makeText(getApplicationContext(), tab.getText().toString(), LENGTH_SHORT).show();
                    }
                    else if (tab.getPosition() == 2)
                    {
                        mDrawerLayout.setBackgroundResource(R.color.purple);
                        appToolbar.setBackgroundResource(R.color.purple);
                        tabLayout.setBackgroundResource(R.color.purple);
                        tabLayout.setSelectedTabIndicatorColor(getColor(R.color.purpleDark));
                        Utils.darkenStatusBar(MainActivity.this, R.color.purple);
                        //Toast.makeText(getApplicationContext(), tab.getText().toString(), LENGTH_SHORT).show();
                    }
                    else if (tab.getPosition() == 3)
                    {
                        mDrawerLayout.setBackgroundResource(R.color.yellowDark);
                        appToolbar.setBackgroundResource(R.color.yellowDark);
                        tabLayout.setBackgroundResource(R.color.yellowDark);
                        tabLayout.setSelectedTabIndicatorColor(getColor(R.color.orangish_yellow));
                        Utils.darkenStatusBar(MainActivity.this, R.color.yellowDark);
                        //Toast.makeText(getApplicationContext(), tab.getText().toString(), LENGTH_SHORT).show();
                    }
                    else if (tab.getPosition() == 4)
                    {
                        mDrawerLayout.setBackgroundResource(R.color.tomato);
                        appToolbar.setBackgroundResource(R.color.tomato);
                        tabLayout.setBackgroundResource(R.color.tomato);
                        tabLayout.setSelectedTabIndicatorColor(getColor(R.color.tomatoDark));
                        Utils.darkenStatusBar(MainActivity.this, R.color.tomato);
                        //Toast.makeText(getApplicationContext(), tab.getText().toString(), LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        }


    }

    public void checkforPermissions()
    {
        String[] permissions = {Manifest.permission.READ_CONTACTS,
                Manifest.permission.READ_CALL_LOG,
                Manifest.permission.WRITE_CONTACTS,
                Manifest.permission.WRITE_CALL_LOG,
                Manifest.permission.READ_EXTERNAL_STORAGE};

        if(ContextCompat.checkSelfPermission(this, permissions[0]) == PackageManager.PERMISSION_DENIED &&
                ContextCompat.checkSelfPermission(this, permissions[1]) == PackageManager.PERMISSION_DENIED &&
                ContextCompat.checkSelfPermission(this, permissions[2]) == PackageManager.PERMISSION_DENIED &&
                ContextCompat.checkSelfPermission(this, permissions[3]) == PackageManager.PERMISSION_DENIED &&
                ContextCompat.checkSelfPermission(this, permissions[4]) == PackageManager.PERMISSION_DENIED)
        {
            ActivityCompat.requestPermissions(this, permissions, 1);
            isPermissionGranted = true;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item))
        {
            return true;
        }

        switch (item.getItemId())
        {
            case R.id.drawer_layout:
                if (item.getItemId() == R.id.toolbar_menu_add)
                {
                    Toast.makeText(this, "Add", LENGTH_SHORT).show();
                }
                else if (item.getItemId() == R.id.toolbar_menu_search)
                {
                    Toast.makeText(this, "Search", LENGTH_SHORT).show();
                }
                break;
            case R.id.contact_list_container:
                if (item.getItemId() == R.id.toolbar_menu_add)
                {
                    Toast.makeText(this, "Add Contacts", LENGTH_SHORT).show();
                }
                else if (item.getItemId() == R.id.toolbar_menu_search)
                {
                    Toast.makeText(this, "Search Contacts", LENGTH_SHORT).show();
                }
                break;
            case R.id.history_list_container:
                if (item.getItemId() == R.id.toolbar_menu_add)
                {
                    Toast.makeText(this, "Add History", LENGTH_SHORT).show();
                }
                else if (item.getItemId() == R.id.toolbar_menu_search)
                {
                    Toast.makeText(this, "Search History", LENGTH_SHORT).show();
                }
                break;
        }



        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        if(grantResults[0] == -1)
        {
            isPermissionGranted = false;
            Toast.makeText(this, "Please Grant Permission to Continue.", Toast.LENGTH_LONG).show();
            Snackbar mySnackbar = Snackbar.make(findViewById(R.id.relative_layout_main_activity), "Permission Denied", Snackbar.LENGTH_INDEFINITE);
            mySnackbar.setAction("Grant Access", new MyUndoListener());
            mySnackbar.show();
        }
    }

    public class MyUndoListener implements View.OnClickListener{ @Override public void onClick(View v) { checkforPermissions(); }}

    public static class Utils {

        // The public static function which can be called from other classes
        public static void darkenStatusBar(Activity activity, int color) {
            activity.getWindow().setStatusBarColor(darkenColor(ContextCompat.getColor(activity, color)));
        }

        // Code to darken the color supplied (mostly color of toolbar)
        private static int darkenColor(int color) {
            float[] hsv = new float[3];
            Color.colorToHSV(color, hsv);
            hsv[2] *= 0.9f;
            return Color.HSVToColor(hsv);
        }
    }


}

