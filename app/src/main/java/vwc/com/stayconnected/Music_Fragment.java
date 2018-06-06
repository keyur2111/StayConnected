package vwc.com.stayconnected;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class Music_Fragment extends android.support.v4.app.Fragment {

    ArrayList<String> arrayList;
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    Vibrator vibrator;
    int SELECTED_ID = -1;

    public Music_Fragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View second_fragment_view = inflater.inflate(R.layout.music_fragment, container, false);
        setHasOptionsMenu(true);

        listView = (ListView) second_fragment_view.findViewById(R.id.music_listview);
        vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);

        if(ActivityCompat.checkSelfPermission(getContext(),Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            Snackbar mySnackbar = Snackbar.make(second_fragment_view, "Permission Denied", Snackbar.LENGTH_SHORT);
            mySnackbar.setAction("Grant Access", new MyUndoListener());
            mySnackbar.setDuration(10000);
            mySnackbar.show();
        }
        else
        {
            // Do Stuff Here. (Permission Granted)
            //set_State_BottomNavigationBar();
            showMusic();
        }

        return second_fragment_view;
    }

    public void set_State_BottomNavigationBar() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) this.getActivity().findViewById(R.id.music_bottom_navigation_bar);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (save_tab_state.getState() == 0)
        {
            fragmentTransaction.replace(R.id.tab_phone_fragment_layout,new Recent_List_Fragment()).commit();
            bottomNavigationView.setSelectedItemId(R.id.recent_items);
        }
        else if(save_tab_state.getState() == 1)
        {
            fragmentTransaction.replace(R.id.tab_phone_fragment_layout,new History_List_Fragment()).commit();
            bottomNavigationView.setSelectedItemId(R.id.history_list);
        }
        else if(save_tab_state.getState() == 2)
        {
            fragmentTransaction.replace(R.id.tab_phone_fragment_layout,new Contact_List_Fragment()).commit();
            bottomNavigationView.setSelectedItemId(R.id.contact_list);
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                switch (item.getItemId())
                {
                    case R.id.songs_list:
                        if(save_tab_state.getState() != 0)
                        {
                            fragmentTransaction.replace(R.id.tab_phone_fragment_layout,new Recent_List_Fragment()).commit();
                            save_tab_state.setTabState(0);
                        }
                        break;
                    case R.id.music_player:
                        if(save_tab_state.getState() != 1)
                        {
                            fragmentTransaction.replace(R.id.tab_phone_fragment_layout, new History_List_Fragment()).commit();
                            save_tab_state.setTabState(1);

                        }
                        break;
                    case R.id.albums_list:
                        if(save_tab_state.getState() != 2)
                        {
                            fragmentTransaction.replace(R.id.tab_phone_fragment_layout, new Contact_List_Fragment()).commit();
                            save_tab_state.setTabState(2);
                        }
                        break;
                    case R.id.playlists_list:
                        if(save_tab_state.getState() != 2)
                        {
                            fragmentTransaction.replace(R.id.tab_phone_fragment_layout, new Contact_List_Fragment()).commit();
                            save_tab_state.setTabState(2);
                        }
                        break;
                }
                return true;
            }
        });

    }

    public class MyUndoListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(getActivity(),new String[] {Manifest.permission.READ_EXTERNAL_STORAGE }, 1);
            }
        }
    }

    public void showMusic() {
        arrayList = new ArrayList<>();
        getMusic();
        if (arrayList != null) {
            Collections.sort(arrayList);
            arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, arrayList){
                @NonNull
                @Override public View getView(final int position, final View convertView, @NonNull final ViewGroup parent) {
                    LayoutInflater layoutInflater = getLayoutInflater();
                    @SuppressLint({"ViewHolder", "InflateParams"}) final View view = layoutInflater.inflate(R.layout.music_listitem_layout, null, true);
                    final RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.relativelayout_selected_item);
                    TextView textTitle = (TextView) view.findViewById(R.id.item_title);
                    TextView textArtist = (TextView) view.findViewById(R.id.item_artist);
                    TextView textLength = (TextView) view.findViewById(R.id.item_length);
                    Button buttonMore = (Button) view.findViewById(R.id.item_menu_button);

                    final String[] song = arrayList.get(position).split("\n");

                    String finalTimerString = "", secondsString, minutesString, songTitle, songArtist;

                    // Custom Length of Title.
                    if(song[0].length() > 20) { songTitle = song[0].substring(0, 20); songTitle = songTitle + "..."; } else { songTitle = song[0]; }

                    // Custom Length of Artist.
                    if(song[1].length() > 32) { songArtist = song[1].substring(0, 32); songArtist = songArtist + "..."; } else { songArtist = song[1]; }

                    //Convert total duration into time
                    int hours = (int) (Long.parseLong(song[2]) / (1000 * 60 * 60));
                    int minutes = (int) (Long.parseLong(song[2]) % (1000 * 60 * 60)) / (1000 * 60);
                    int seconds = (int) ((Long.parseLong(song[2]) % (1000 * 60 * 60)) % (1000 * 60) / 1000);

                    // Add hours if there
                    if (hours > 0) { finalTimerString = hours + ":"; }

                    // Pre appending 0 to minutes if it is one digit
                    if (minutes < 10) { minutesString = "0" + minutes; } else { minutesString = "" + minutes;}

                    // Pre appending 0 to seconds if it is one digit
                    if (seconds < 10) { secondsString = "0" + seconds; } else { secondsString = "" + seconds; }

                    finalTimerString = finalTimerString + minutesString + ":" + secondsString;

                    textTitle.setText(songTitle);
                    textArtist.setText(songArtist);
                    textLength.setText(finalTimerString);

                    buttonMore.setOnClickListener(new View.OnClickListener() {
                        @Override public void onClick(View v) { Toast.makeText(getContext(), song[0] + " : More", Toast.LENGTH_SHORT).show(); }
                    });


                    relativeLayout.setOnClickListener(new View.OnClickListener() {
                        @Override public void onClick(View v) {

                            if (SELECTED_ID != position)
                            {
                                v.setBackgroundResource(R.color.tomatoLight);
                                if(SELECTED_ID != -1)
                                {
                                    listView.getAdapter().getView(position,convertView,parent).setBackgroundResource(R.color.orange);
                                }
                                SELECTED_ID = position;
                            }
                            else
                            {
                                v.setBackgroundResource(R.color.orange);
                            }
                            Toast.makeText(getContext(), song[0], Toast.LENGTH_SHORT).show(); vibrator.vibrate(50); }
                    });

                    return view;
                }
            };
            listView.setAdapter(arrayAdapter);

        }
    }

    public void getMusic() {
        ContentResolver contentResolver = getActivity().getContentResolver();
        Uri songsUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        @SuppressLint("Recycle") Cursor songCursor = contentResolver.query(songsUri, null, null, null, null);

        if(songCursor != null && songCursor.moveToFirst())
        {
            int songTitle = songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int songArtist = songCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int songLength = songCursor.getColumnIndex(MediaStore.Audio.Media.DURATION);

            do {
                String currentTitle = songCursor.getString(songTitle);
                String currentArtist = songCursor.getString(songArtist);
                String currentLength = songCursor.getString(songLength);

                arrayList.add(currentTitle + "\n" + currentArtist + "\n" + currentLength);
            }while (songCursor.moveToNext());

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.toolbar_menu_add:
                Toast.makeText(getContext(), "Add Songs", Toast.LENGTH_SHORT).show();
            case R.id.toolbar_menu_search:
                Toast.makeText(getContext(), "Search Songs", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
