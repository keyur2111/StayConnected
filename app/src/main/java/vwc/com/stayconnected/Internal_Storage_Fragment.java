package vwc.com.stayconnected;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Internal_Storage_Fragment extends android.support.v4.app.Fragment {

    private List<String> fileList = new ArrayList<>();
    ListView lview;
    Context context;

    public Internal_Storage_Fragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View third_fragment_view = inflater.inflate(R.layout.internal_storage_fragment, container, false);
        setHasOptionsMenu(true);

        context = third_fragment_view.getContext();
        lview = (ListView) third_fragment_view.findViewById(R.id.explorer_listview);

        return third_fragment_view;
    }

    @Override
    public void onStart() {
        super.onStart();

        if(ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
        {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath();
            File f = new File(path);//converted string object to file
            String[] values = f.list();//getting the list of files in string array
            //now presenting the data into screen
            ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, values);
            lview.setAdapter(adapter);//setting the adapter
        }
    }
}
