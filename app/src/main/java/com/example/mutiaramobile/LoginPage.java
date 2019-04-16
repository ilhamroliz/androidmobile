package com.example.mutiaramobile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mutiaramobile.api.ApiService;
import com.example.mutiaramobile.model.TokenModel;
import com.example.mutiaramobile.serviceprovider.RetrofitService;
import com.example.mutiaramobile.serviceprovider.MutiaraServiceProvider;
import com.example.mutiaramobile.serviceprovider.StoreSession;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginPage extends AppCompatActivity {

    EditText username, password;
    StoreSession store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        final Button button = findViewById(R.id.btn_login);
        username = (EditText) findViewById(R.id.input_username);
        password = (EditText) findViewById(R.id.input_password);

        store = new StoreSession(LoginPage.this.getApplicationContext());

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Login");
        progressDialog.setMessage("Menunggu respon dari server...");
        progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                MutiaraServiceProvider service = new MutiaraServiceProvider();
                String name = username.getText().toString();
                String pass = password.getText().toString();
                String client = service.getGlobalClient();
                String secret = service.getGlobalSecret();
                String grant = service.getGlobalGrant();

                int valid = 1;

                if (name == null || name == ""){
                    valid = 0;
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Username tidak boleh kosong!!", Toast.LENGTH_LONG).show();
                }

                if (pass == null || pass == ""){
                    valid = 0;
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Password tidak boleh kosong!!", Toast.LENGTH_LONG).show();
                }

                if (valid == 1){
                    ApiService login = new RetrofitService().getToken().create(ApiService.class);
                    Call<TokenModel> sendToken = login.getToken(name, pass, grant, secret, client);
                    sendToken.enqueue(new Callback<TokenModel>() {
                        @Override
                        public void onResponse(Call<TokenModel> call, Response<TokenModel> response) {
                            try {
                                if (response.body().getAccessToken() != null){
                                    store.setDataString("access_token", response.body().getAccessToken());
                                    store.setDataInteger("expires_in", response.body().getExpiresIn());
                                    store.setDataString("token_type", response.body().getTokenType());
                                    store.setDataString("nama", response.body().getNama());
                                    store.setDataString("area", response.body().getArea());
                                    store.setDataString("tlp", response.body().getTlp());
                                    store.setDataString("tipe", response.body().getTipe());
                                    store.setDataString("username", response.body().getUsername());
                                    store.setDataString("refresh_token", response.body().getRefreshToken());
                                    progressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(), "Berhasil Login", Toast.LENGTH_SHORT).show();
                                    Sukses();
                                }
                            } catch (Exception e){
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Username / Password salah", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<TokenModel> call, Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Coba beberapa saat lagi", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    public void Sukses(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
