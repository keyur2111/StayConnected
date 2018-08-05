package vwc.com.stayconnected;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ToolbarWidgetWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_SHORT;

public class Phone_Fragment extends android.support.v4.app.Fragment {

    FloatingActionButton floatingActionButton_Phone;
    BottomNavigationView bottomNavigationView;
    FragmentManager fragmentManager;
    Context context;

    public Phone_Fragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View phone_fragment_view = inflater.inflate(R.layout.phone_fragment, container,false);

        context = phone_fragment_view.getContext();
        floatingActionButton_Phone = (FloatingActionButton) phone_fragment_view.findViewById(R.id.add_floating_button_round);
        bottomNavigationView = (BottomNavigationView) phone_fragment_view.findViewById(R.id.bottom_navigation_bar);
        fragmentManager = getActivity().getSupportFragmentManager();

        return phone_fragment_view;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();

        if(ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
        {
            Toast.makeText(context, "Storage Permission is disabled", Toast.LENGTH_LONG).show();
        }
        else
        {
            floatingActionButton_Phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Toast.makeText(context, "Dialer", Toast.LENGTH_SHORT).show();
                }
            });

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            if (save_tab_state.getState() == 0)
            {
                fragmentTransaction.replace(R.id.tab_phone_fragment_layout,new Recent_List_Fragment()).commit();
                //bottomNavigationView.setBackgroundResource(R.color.colorPrimaryDark);
                bottomNavigationView.setSelectedItemId(R.id.recent_items);
                //Toast.makeText(getContext(),"Recent Calls is PreSelected", Toast.LENGTH_SHORT).show();
            }
            else if(save_tab_state.getState() == 1)
            {
                fragmentTransaction.replace(R.id.tab_phone_fragment_layout,new History_List_Fragment()).commit();
                //bottomNavigationView.setBackgroundResource(R.color.colorAccent);
                bottomNavigationView.setSelectedItemId(R.id.history_list);
                //Toast.makeText(getContext(),"History is PreSelected", Toast.LENGTH_SHORT).show();
            }
            else if(save_tab_state.getState() == 2)
            {
                fragmentTransaction.replace(R.id.tab_phone_fragment_layout,new Contact_List_Fragment()).commit();
                //bottomNavigationView.setBackgroundResource(R.color.orangeDark);
                bottomNavigationView.setSelectedItemId(R.id.contact_list);
                //Toast.makeText(getContext(),"Contacts is PreSelected", Toast.LENGTH_SHORT).show();
            }

            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    switch (item.getItemId())
                    {
                        case R.id.recent_items:
                            if(save_tab_state.getState() != 0)
                            {
                                //bottomNavigationView.setBackgroundResource(R.color.colorPrimaryDark);
                                fragmentTransaction.replace(R.id.tab_phone_fragment_layout,new Recent_List_Fragment()).commit();
                                //Toast.makeText(getContext(),"Recent Calls", Toast.LENGTH_SHORT).show();
                                save_tab_state.setTabState(0);
                            }
                            break;
                        case R.id.history_list:
                            if(save_tab_state.getState() != 1)
                            {
                                //bottomNavigationView.setBackgroundResource(R.color.colorAccent);
                                fragmentTransaction.replace(R.id.tab_phone_fragment_layout, new History_List_Fragment()).commit();
                                //Toast.makeText(getContext(),"Call History", Toast.LENGTH_SHORT).show();
                                save_tab_state.setTabState(1);

                            }
                            break;
                        case R.id.contact_list:
                            if(save_tab_state.getState() != 2)
                            {
                                //bottomNavigationView.setBackgroundResource(R.color.orangeDark);
                                fragmentTransaction.replace(R.id.tab_phone_fragment_layout, new Contact_List_Fragment()).commit();
                                //Toast.makeText(getContext(),"Contacts", Toast.LENGTH_SHORT).show();
                                save_tab_state.setTabState(2);
                            }
                            break;
                    }
                    return true;
                }
            });


        }

    }
}
