package vwc.com.stayconnected;


import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Internal_Storage_Fragment extends android.support.v4.app.Fragment {

    private List<String> fileList = new ArrayList<String>();

    public Internal_Storage_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View third_fragment_view = inflater.inflate(R.layout.internal_storage_fragment, container, false);
        setHasOptionsMenu(true);

        ListView lview = (ListView) third_fragment_view.findViewById(R.id.explorer_listview);
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        File f = new File(path);//converted string object to file
        String[] values = f.list();//getting the list of files in string array
        //now presenting the data into screen
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, values);
        lview.setAdapter(adapter);//setting the adapter

        return third_fragment_view;
    }
}
