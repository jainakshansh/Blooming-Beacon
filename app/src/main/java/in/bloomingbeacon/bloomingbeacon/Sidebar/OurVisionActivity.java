package in.bloomingbeacon.bloomingbeacon.Sidebar;

import android.graphics.Typeface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.widget.TextView;

import in.bloomingbeacon.bloomingbeacon.R;

public class OurVisionActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private TextView title1, title2, title3;
    private TextView text1, text2, text3;
    private Typeface poppins_bold, poppins_regular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_vision);

        toolbar = (Toolbar) findViewById(R.id.toolbar_our_vision);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Our Vision");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        title1 = (TextView) findViewById(R.id.womemp_title);
        title2 = (TextView) findViewById(R.id.youaff_title);
        title3 = (TextView) findViewById(R.id.socaff_title);
        text1 = (TextView) findViewById(R.id.womemp_text);
        text2 = (TextView) findViewById(R.id.youaff_text);
        text3 = (TextView) findViewById(R.id.socaff_text);

        poppins_bold = Typeface.createFromAsset(getAssets(), "fonts/poppinsb.ttf");
        poppins_regular = Typeface.createFromAsset(getAssets(), "fonts/poppinsr.ttf");

        title1.setTypeface(poppins_bold);
        title2.setTypeface(poppins_bold);
        title3.setTypeface(poppins_bold);
        text1.setTypeface(poppins_regular);
        text2.setTypeface(poppins_regular);
        text3.setTypeface(poppins_regular);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
