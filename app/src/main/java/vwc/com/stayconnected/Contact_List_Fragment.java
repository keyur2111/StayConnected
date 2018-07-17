package vwc.com.stayconnected;

import android.Manifest;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.ToolbarWidgetWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class Contact_List_Fragment extends android.support.v4.app.Fragment {

    RecyclerView recyclerView;
    ArrayList<String> contact_names = new ArrayList<>(), mobile_numbers = new ArrayList<>();

    public Contact_List_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View contact_fragment =  inflater.inflate(R.layout.contact_list_fragment, container, false);
        setHasOptionsMenu(true);

        if(ActivityCompat.checkSelfPermission(contact_fragment.getContext(), Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED)
        {
            String[] PROJECTION_MAIN = new String[]{
                    ContactsContract.Contacts._ID,
                    ContactsContract.CommonDataKinds.Phone.NUMBER,
                    ContactsContract.Contacts.DISPLAY_NAME
            };

            Cursor cursor = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    PROJECTION_MAIN, null, null, null);

            while (cursor != null && cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                String phone = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                contact_names.add(name);
                mobile_numbers.add(phone);
            }

            recyclerView = (RecyclerView) contact_fragment.findViewById(R.id.recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(new RecyclerAdapter(contact_names, mobile_numbers));

        }

        return contact_fragment;
    }

}
