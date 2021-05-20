package com.example.humleporten.MainScreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.humleporten.BeerLibrary.PlayBtnActivity;
import com.example.humleporten.LoginRegScreen.LoginActivity;
import com.example.humleporten.ROOMNoteCreating.ActivityNote;
import com.example.humleporten.Schedule.DayActivity;
import com.example.humleporten.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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

        Button beerScheudule = (Button) findViewById(R.id.beerSch);
        beerScheudule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openDetailActivity(); }
        });

        Button createOwnBeerSchdule = (Button) findViewById(R.id.OwnBrewSch);
        createOwnBeerSchdule.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){ openCreateOwnBeerSchedule();}
        });

        Button beerLibrary = (Button) findViewById(R.id.beerLibrarybt);
        beerLibrary.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){openBeerLibraryActivity();}
        });



    }

    public void openDetailActivity(){
        Intent intent = new Intent(this, DayActivity.class);
        startActivity(intent);
    }

    public void openCreateOwnBeerSchedule(){
        Intent intent = new Intent(this, ActivityNote.class);
        startActivity(intent);
    }

    public void openBeerLibraryActivity(){
        Intent intent = new Intent(this, PlayBtnActivity.class);
        startActivity(intent);
    }

    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }
}
