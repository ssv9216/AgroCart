package ssv.com.agrocart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnRegister= findViewById(R.id.btnRegister);
        Button btnLogin = findViewById(R.id.btnLogin);


        final Intent i = new Intent(this,RegisterUser.class);
        final Intent j = new Intent(this, loginpage.class);
        final Intent k = new Intent(this,HomePage.class);
        final Intent l = new Intent(this,CheckWrite.class);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean IsLogin = prefs.getBoolean("isLogin",false);
        if(IsLogin){
            startActivity(k);
            finish();
        }

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(i);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(j);
            }
        });



    }
}
