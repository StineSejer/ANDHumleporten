package com.example.humleporten.Schedule;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import com.example.humleporten.MainScreen.BeerActivity;
import com.example.humleporten.BeerLibrary.PlayBtnActivity;
import com.example.humleporten.MainScreen.MainActivity;
import com.example.humleporten.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DayActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private static final String TAG = "DayActivity";

    private ListView LvDay;
    private ArrayList<Days> brewList;
    private ArrayList<String> listTitle;
    private Adapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_days);

        //navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_bottom);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {
                switch (menuitem.getItemId()) {
                    case R.id.beerLibrarybt:
                        startActivity(new Intent(getApplicationContext()
                                , BeerActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.home:
                        return true;
                    case R.id.beerBrewing:
                        startActivity(new Intent(getApplicationContext()
                                , PlayBtnActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;

            }
        });

        Log.d(TAG,"onCreate: Started");

        LvDay = findViewById(R.id.LvDay);

        brewList = DataHelp.loadDays(this);
        listTitle = new ArrayList<>();
        for (int i = 0; i < brewList.size(); i++) {
            String str = brewList.get(i).getTitle();
            listTitle.add(str);
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item,listTitle);
        LvDay.setAdapter((ListAdapter) adapter);
        LvDay.setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, detailsDaysActivity.class);
        String title = brewList.get(position).getTitle();
        String humleporten = brewList.get(position).getHumleporten();

        intent.putExtra("EXTRA_TITLE",title);
        intent.putExtra("EXTRA_HUMLEPORTEN", humleporten);

        startActivity(intent);
    }
}
