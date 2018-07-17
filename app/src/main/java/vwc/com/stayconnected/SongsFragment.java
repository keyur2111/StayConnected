package vwc.com.stayconnected;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SongsFragment extends Fragment {

    public SongsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View songs_fragment = inflater.inflate(R.layout.songs_fragment, container, false);

        return songs_fragment;
    }
}
