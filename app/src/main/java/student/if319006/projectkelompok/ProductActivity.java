package student.if319006.projectkelompok;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductActivity extends AppCompatActivity {

    public static final String URL = "https://proyekpasti.000webhostapp.com/";
    private ProgressDialog progress;

    @BindView(R.id.editTextNama) EditText editTextNama;
    @BindView(R.id.editTextHarga) EditText editTextHarga;


    @OnClick(R.id.buttonDaftar) void daftar() {
        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setMessage("Loading ...");
        progress.show();

        String nama = editTextNama.getText().toString();
        String harga = editTextHarga.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

//        RegisterAPI api = retrofit.create(RegisterAPI.class);
        RegisterAPI api = retrofit.create(RegisterAPI.class);
        Call<Value> call = api.daftar(nama,harga);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();
                String message = response.body().getMessage();
                progress.dismiss();
                if (value.equals("1")) {
                    Toast.makeText(ProductActivity.this, message, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ProductActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(ProductActivity.this, "Jaringan Error!", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @OnClick(R.id.buttonLihat) void lihat() {
        startActivity(new Intent(ProductActivity.this, ViewActivity.class));
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        ButterKnife.bind(this);
    }
}