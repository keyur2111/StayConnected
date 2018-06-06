package vwc.com.stayconnected;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class History_List_Fragment extends android.support.v4.app.Fragment {

    public History_List_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View recent_list_view =  inflater.inflate(R.layout.history_list_fragment, container, false);
        setHasOptionsMenu(true);

        return recent_list_view;
    }

}
