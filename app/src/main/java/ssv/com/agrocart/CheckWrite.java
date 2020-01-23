package ssv.com.agrocart;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class CheckWrite extends AppCompatActivity {

    DatabaseReference rootRef,demoRef;
    Button post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_write);
        post =findViewById(R.id.checkBtnPost);
        rootRef = FirebaseDatabase.getInstance().getReference();

//database reference pointing to demo node
        demoRef = rootRef.child("demo");

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//push creates a unique id in database
            demoRef.push().setValue("ssv");
            }
        });
    }
}
