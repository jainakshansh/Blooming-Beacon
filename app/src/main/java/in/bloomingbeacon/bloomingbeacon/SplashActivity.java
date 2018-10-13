package in.bloomingbeacon.bloomingbeacon;

import android.content.Intent;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Setting up the activity for fullscreen mode.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha_splash);
        animation.reset();
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.constraint_splash);
        layout.clearAnimation();
        layout.startAnimation(animation);

        animation = AnimationUtils.loadAnimation(this, R.anim.translate_splash);
        animation.reset();
        ImageView imageView = (ImageView) findViewById(R.id.image_splash);
        imageView.clearAnimation();
        imageView.startAnimation(animation);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), LandingActivity.class));
                finish();
            }
        }, 2000);
    }
}
