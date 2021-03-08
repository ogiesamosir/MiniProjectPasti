package student.if319006.projectkelompok;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity{

    ImageView imgAccount;
    CardView cardViewInfo,cardViewNote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgAccount = findViewById(R.id.imgLogo);
        cardViewNote = findViewById(R.id.cvNotes);



        imgAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , ProfileActivity.class));
            }
        });

        cardViewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProductActivity.class));
            }
        });




    }
}