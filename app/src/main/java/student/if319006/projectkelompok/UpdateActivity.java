package student.if319006.projectkelompok;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

public class UpdateActivity extends AppCompatActivity {

    public static final String URL = "https://proyekpasti.000webhostapp.com/";
    private ProgressDialog progress;

    @BindView(R.id.editTextId) EditText editTextId;
    @BindView(R.id.editTextNama) EditText editTextNama;
    @BindView(R.id.editTextHarga) EditText editTextHarga;

    @OnClick(R.id.buttonHapus) void hapus(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Peringatan");
        alertDialogBuilder
                .setMessage("Apakah Anda yakin ingin mengapus data ini?")
                .setCancelable(false)
                .setPositiveButton("Hapus",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {

                        String idx = editTextId.getText().toString();
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        RegisterAPI api = retrofit.create(RegisterAPI.class);
                        Call<Value> call = api.hapus(idx);
                        call.enqueue(new Callback<Value>() {
                            @Override
                            public void onResponse(Call<Value> call, Response<Value> response) {
                                String value = response.body().getValue();
                                String message = response.body().getMessage();
                                if (value.equals("1")) {
                                    Toast.makeText(UpdateActivity.this, message, Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    Toast.makeText(UpdateActivity.this, message, Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Value> call, Throwable t) {
                                t.printStackTrace();
                                Toast.makeText(UpdateActivity.this, "Jaringan Error!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                })
                .setNegativeButton("Batal",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    @OnClick(R.id.buttonUbah) void ubah() {
        //membuat progress dialog
        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setMessage("Loading ...");
        progress.show();

        //mengambil data dari edittext
        String id = editTextId.getText().toString();
        String nama = editTextNama.getText().toString();
        String harga = editTextHarga.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RegisterAPI api = retrofit.create(RegisterAPI.class);
        Call<Value> call = api.ubah(id,nama,harga);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();
                String message = response.body().getMessage();
                progress.dismiss();
                if (value.equals("1")) {
                    Toast.makeText(UpdateActivity.this, message, Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(UpdateActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                t.printStackTrace();
                progress.dismiss();
                Toast.makeText(UpdateActivity.this, "Jaringan Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String nama = intent.getStringExtra("nama");
        String harga = intent.getStringExtra("harga");

        editTextId.setText(id);
        editTextNama.setText(nama);
        editTextHarga.setText(harga);

    }




}