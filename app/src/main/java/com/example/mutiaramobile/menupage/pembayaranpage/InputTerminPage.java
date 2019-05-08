package com.example.mutiaramobile.menupage.pembayaranpage;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mutiaramobile.R;
import com.example.mutiaramobile.api.ApiService;
import com.example.mutiaramobile.model.StatusModel;
import com.example.mutiaramobile.serviceprovider.MutiaraServiceProvider;
import com.example.mutiaramobile.serviceprovider.RetrofitService;

import java.text.ParseException;
import java.util.Locale;

import faranjit.currency.edittext.CurrencyEditText;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InputTerminPage extends AppCompatActivity {

    String nota, termin, bayar, bayarrp, tanggal, tagihan, tagihanrp;
    TextView text_nota, text_termin, text_tagihan, text_sisa, text_tanggal;
    CurrencyEditText text_bayar;
    EditText text_edit;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_termin_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        text_nota = (TextView) findViewById(R.id.input_pembayaran_nota);
        text_termin = (TextView) findViewById(R.id.input_pembayaran_termin);
        text_tagihan = (TextView) findViewById(R.id.input_pembayaran_tagihan);
        text_sisa = (TextView) findViewById(R.id.input_pembayaran_sisatagihan);
        text_bayar = (CurrencyEditText) findViewById(R.id.input_pembayaran_bayar);
        text_tanggal = (TextView) findViewById(R.id.input_pembayaran_tanggal);

        nota = getIntent().getStringExtra("nota");
        termin = getIntent().getStringExtra("termin");
        bayar = getIntent().getStringExtra("bayar");
        bayarrp = getIntent().getStringExtra("bayarrp");
        tanggal = getIntent().getStringExtra("tanggal");
        tagihan = getIntent().getStringExtra("tagihan");
        tagihanrp = getIntent().getStringExtra("tagihanrp");

        text_nota.setText(nota);
        text_termin.setText(termin);
        text_tagihan.setText("Rp" + tagihanrp);
        text_tanggal.setText(tanggal);
        text_sisa.setText("Rp" + bayarrp);


        text_bayar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @SuppressLint("ResourceAsColor")
            @Override
            public void afterTextChanged(Editable s) {
                String ganti = String.valueOf(s);
                ganti = ganti.replace(".", "");
                int bayarint = Integer.parseInt(bayar);
                int input = 0;
                try {
                    input = Integer.parseInt(ganti);
                } catch (Exception e){
                    input = 0;
                }
                if (input > bayarint) {
                    Toast.makeText(getApplicationContext(), "tidak boleh melebihi total tagihan", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void UpdatePembayaran(View view) {
        String input = "0";
        int masukan = 0;
        int bayarint = Integer.parseInt(bayar);
        try {
            input = text_bayar.getCurrencyText();
            input = input.replace(".", "");
            masukan = Integer.parseInt(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (masukan == 0){
            Toast.makeText(getApplicationContext(), "Tidak boleh 0", Toast.LENGTH_SHORT).show();
        }
        if (masukan > bayarint && masukan != 0){
            Toast.makeText(getApplicationContext(), "Jumlah melebihi sisa tagihan", Toast.LENGTH_SHORT).show();
        } else if (masukan <= bayarint && masukan != 0){
            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Pembayaran Termin");
            progressDialog.setMessage("Mengirim data...");
            progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
            progressDialog.show();

            MutiaraServiceProvider mutiaraServiceProvider = new MutiaraServiceProvider();
            ApiService apiService = new RetrofitService().getData().create(ApiService.class);
            Call<StatusModel> data = apiService.simpanDataTermin(mutiaraServiceProvider.getAuthorization(this), nota, termin, masukan);
            data.enqueue(new Callback<StatusModel>() {
                @Override
                public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
                    progressDialog.dismiss();
                    try {
                        Toast.makeText(getApplicationContext(), response.body().getStatus(), Toast.LENGTH_SHORT).show();
                        finish();
                        Intent intent = new Intent(getApplicationContext(), ListTerminPembayaranPage.class);
                        intent.putExtra("Nota", nota);
                        startActivity(intent);
                    } catch (Exception e){
                        Toast.makeText(getApplicationContext(), "Error, segera hubungi admin", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<StatusModel> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Error, server gagal merespon", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), ListTerminPembayaranPage.class);
        setResult(RESULT_OK, myIntent);
        finish();
        return true;
    }
}
