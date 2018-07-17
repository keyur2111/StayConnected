package vwc.com.stayconnected;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Gallery_Fragment extends android.support.v4.app.Fragment {

    public Gallery_Fragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View gallery_fragment = inflater.inflate(R.layout.gallery_fragment, container, false);
        setHasOptionsMenu(true);

        return gallery_fragment;
    }
}
