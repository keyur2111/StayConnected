package vwc.com.stayconnected;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class Messages_Fragment extends android.support.v4.app.Fragment {


    public Messages_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View messages_fragment = inflater.inflate(R.layout.messages_fragment, container, false);
        setHasOptionsMenu(true);

        return messages_fragment;
    }

}
