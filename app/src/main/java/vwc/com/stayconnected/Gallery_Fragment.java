package vwc.com.stayconnected;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class Gallery_Fragment extends android.support.v4.app.Fragment {


    public Gallery_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View gallery_fragment = inflater.inflate(R.layout.gallery_fragment, container, false);
        setHasOptionsMenu(true);


        return gallery_fragment;
    }

}
