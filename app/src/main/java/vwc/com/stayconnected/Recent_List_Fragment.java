package vwc.com.stayconnected;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Recent_List_Fragment extends android.support.v4.app.Fragment {

    public Recent_List_Fragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View recent_list_fragment = inflater.inflate(R.layout.recent_list_fragment, container, false);
        setHasOptionsMenu(true);

        return recent_list_fragment;
    }
}
