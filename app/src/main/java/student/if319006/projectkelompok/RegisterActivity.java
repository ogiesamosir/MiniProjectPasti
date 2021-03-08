package student.if319006.projectkelompok;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText etUsername, etNik, etPassword;
    Button btnRegister;


    final String url_Register = "https://proyekpasti.000webhostapp.com/register_user.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = (EditText) findViewById(R.id.et_email);
        etNik = (EditText) findViewById(R.id.et_nik);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnRegister = (Button) findViewById(R.id.btnRegis);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String nik = etNik.getText().toString();
                String password = etPassword.getText().toString();

                new RegisterUser().execute(username , nik , password);
            }
        });
    }

    public class RegisterUser extends AsyncTask<String , Void , String>{

        @Override
        protected String doInBackground(String... strings) {
            String username = strings[0];
            String nik = strings[1];
            String password = strings[2];

            String finalURL = url_Register + "?user_name=" + username +
                    "&user_nik=" + nik +
                    "&user_password=" + password;

            try {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(finalURL)
                        .get()
                        .build();
                Response response = null;

                try {
                    response = okHttpClient.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String result = response.body().string();

                        if (result.equalsIgnoreCase("User registered successfully")) {
                            showToast("Register successful");
                            Intent i = new Intent(RegisterActivity.this,
                                    LoginActivity.class);
                            startActivity(i);
                            finish();
                        } else if (result.equalsIgnoreCase("User already exists")) {
                            showToast("User already exists");
                        } else {
                            showToast("oop! please try again");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }

    public void showToast(final String Text){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(RegisterActivity.this,
                        Text, Toast.LENGTH_LONG).show();
            }
        });
    }





}