package student.if319006.projectkelompok;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText edUsername, edPassword;
    TextView noAccount;

    final String url_login = "https://proyekpasti.000webhostapp.com/login_user.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);
        edUsername = findViewById(R.id.et_email);
        edPassword = findViewById(R.id.et_password);
        noAccount = findViewById(R.id.tvCreateAccount);

        noAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edUsername.getText().toString();
                String password = edPassword.getText().toString();

                new LoginUser().execute(username, password);
            }
        });
    }

    public class LoginUser extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String username = strings[0];
            String password = strings[1];

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody formBody = new FormBody.Builder()
                    .add("user_name", username)
                    .add("user_password", password)
                    .build();

            Request request = new Request.Builder()
                    .url(url_login)
                    .post(formBody)
                    .build();

            Response response = null;
            try{
                response = okHttpClient.newCall(request).execute();
                if(response.isSuccessful()){
                    String result = response.body().string();
                    if(result.equalsIgnoreCase("login")){
                        Intent i = new Intent(LoginActivity.this,
                                MainActivity.class);
                        startActivity(i);
                        finish();
                    }else{
                        showToast("Email or Password mismatched!");
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
                Toast.makeText(LoginActivity.this,Text , Toast.LENGTH_LONG).show();
            }
        });
    }
}