package com.example.humleporten.Schedule;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.humleporten.R;

public class detailsDaysActivity extends AppCompatActivity {

    private TextView title;
    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_days);

        title = findViewById(R.id.tvDaysTitle);
        description = findViewById(R.id.tv_description_days);

        Bundle extra = getIntent().getExtras();
        if (extra != null){
            String tit = extra.getString("EXTRA_TITLE");
            String humleporten = extra.getString("EXTRA_HUMLEPORTEN");
            title.setText(tit);
            description.setText(humleporten);
        }
    }
}
