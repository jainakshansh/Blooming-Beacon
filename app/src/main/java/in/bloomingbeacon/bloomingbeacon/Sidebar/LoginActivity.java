package in.bloomingbeacon.bloomingbeacon.Sidebar;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import in.bloomingbeacon.bloomingbeacon.LandingActivity;
import in.bloomingbeacon.bloomingbeacon.R;

public class LoginActivity extends AppCompatActivity {

    private EditText login_email, login_password;
    private Button login, forgotPassword;
    private TextView register;

    private String username, password;
    private FirebaseAuth firebaseAuth;

    private Typeface regular, bold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initialising the firebase authentication
        firebaseAuth = FirebaseAuth.getInstance();

        bold = Typeface.createFromAsset(getAssets(), "fonts/poppinsb.ttf");
        regular = Typeface.createFromAsset(getAssets(), "fonts/poppinsr.ttf");
        initViews();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = login_email.getText().toString();
                password = login_password.getText().toString();
                signIn(username, password);
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!login_email.getText().toString().trim().isEmpty()) {
                    firebaseAuth.sendPasswordResetEmail(login_email.getText().toString().trim())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(LoginActivity.this, "We have sent you the instructions to reset your password!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(LoginActivity.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(LoginActivity.this, "Input registered email!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initViews() {
        login_email = (EditText) findViewById(R.id.email_login_screen);
        login_email.setTypeface(regular);
        login_password = (EditText) findViewById(R.id.password_login_screen);
        login_password.setTypeface(regular);
        login = (Button) findViewById(R.id.login_login_screen);
        login.setTypeface(bold);
        forgotPassword = (Button) findViewById(R.id.forgotpassword_login_screen);
        forgotPassword.setTypeface(regular);
        register = (TextView) findViewById(R.id.register_login_screen);
        register.setTypeface(bold);
    }

    private void signIn(String email, String password) {
        if (!validateForm()) {
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), LandingActivity.class));
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "Login failed!\nIncorrect username and password pair.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = login_email.getText().toString();
        if (TextUtils.isEmpty(email)) {
            login_email.setError("Required.");
            valid = false;
        } else {
            login_email.setError(null);
        }

        String password = login_password.getText().toString();
        if (TextUtils.isEmpty(password)) {
            login_password.setError("Required.");
            valid = false;
        } else {
            login_password.setError(null);
        }

        return valid;
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null) {
            Toast.makeText(this, "You are already logged in!", Toast.LENGTH_SHORT).show();
            login.setVisibility(View.GONE);
            startActivity(new Intent(getApplicationContext(), LandingActivity.class));
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
