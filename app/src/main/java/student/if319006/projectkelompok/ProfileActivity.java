package student.if319006.projectkelompok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ProfileActivity extends AppCompatActivity {
    EditText editTextRecent,editTextNew;
    Button btnChangePass, btnDeleteAccount,btnLogOut;

    final String url_changePass = "https://logdone.000webhostapp.com/change_pass.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnLogOut = findViewById(R.id.btn_logOut);


        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    private class ChangePass extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
                String pass = strings[0];
                String newPass = strings[1];

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody formBody = new FormBody.Builder()
                    .add("recent_password",pass)
                    .add("new_password", newPass)
                    .build();

            Request request = new Request.Builder()
                    .url(url_changePass)
                    .post(formBody)
                    .build();
            Response response = null;
            try{
                response = okHttpClient.newCall(request).execute();
                if(response.isSuccessful()){
                    String result = response.body().string();
                    if(result.equalsIgnoreCase("login")){
                        showToast("Password berhasil diganti!");
                    }else{
                        showToast("Password gagal diganti!");
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }

    public void showToast(final  String Text){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ProfileActivity.this,Text , Toast.LENGTH_LONG).show();
            }
        });
    }
}
