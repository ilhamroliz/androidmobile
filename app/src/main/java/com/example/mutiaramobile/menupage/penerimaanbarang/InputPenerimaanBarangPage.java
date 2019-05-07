package com.example.mutiaramobile.menupage.penerimaanbarang;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mutiaramobile.MainActivity;
import com.example.mutiaramobile.R;
import com.example.mutiaramobile.api.ApiService;
import com.example.mutiaramobile.model.PenerimaanBarang.TerimaQtyBarangModel;
import com.example.mutiaramobile.model.StatusModel;
import com.example.mutiaramobile.serviceprovider.MutiaraServiceProvider;
import com.example.mutiaramobile.serviceprovider.RetrofitService;
import com.example.mutiaramobile.serviceprovider.StoreSession;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InputPenerimaanBarangPage extends AppCompatActivity {

    String nota, order, satuan, nama;
    int item, terima, satuan_order;
    EditText qty, notado;
    TextView nama_item, jumlah_order, satuan_item, sisa;
    Button simpan;
    MutiaraServiceProvider serviceProvider;
    StoreSession store;
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_penerimaan_barang_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Penerimaan Barang");
        progressDialog.setMessage("Mengambil data...");
        progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
        progressDialog.show();

        nota = getIntent().getStringExtra("Nota");
        item = getIntent().getIntExtra("Item", 0);
        order = getIntent().getStringExtra("Order");
        satuan = getIntent().getStringExtra("Satuan");
        nama = getIntent().getStringExtra("Nama");

        nama_item = (TextView) findViewById(R.id.input_terima_barang_nama_barang);
        jumlah_order = (TextView) findViewById(R.id.input_terima_barang_jumlah_order);
        satuan_item = (TextView) findViewById(R.id.input_terima_barang_label_satuan);
        sisa = (TextView) findViewById(R.id.input_terima_barang_sisa);

        qty = (EditText) findViewById(R.id.input_terima_barang_form_terima);
        notado = (EditText) findViewById(R.id.input_terima_barang_form_notado);

        simpan = (Button) findViewById(R.id.btn_terima_barang_submit);

        nama_item.setText(nama);
        jumlah_order.setText(order.concat(" ").concat(satuan));
        satuan_item.setText(satuan);

        serviceProvider = new MutiaraServiceProvider();
        apiService = new RetrofitService().getData().create(ApiService.class);
        Call<TerimaQtyBarangModel> data = apiService.getQtyBarang(serviceProvider.getAuthorization(this), item, nota);
        data.enqueue(new Callback<TerimaQtyBarangModel>() {
            @Override
            public void onResponse(Call<TerimaQtyBarangModel> call, Response<TerimaQtyBarangModel> response) {
                progressDialog.dismiss();
                terima = response.body().getQtyTerimaBarang();
                satuan_order = response.body().getSatuanTerimaBarang();
                terima = Integer.parseInt(order) - terima;
                String str_terima = String.valueOf(terima);
                sisa.setText(str_terima.concat(" ").concat(satuan));
            }

            @Override
            public void onFailure(Call<TerimaQtyBarangModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), TerimaBarangPage.class);
        myIntent.putExtra("Nota", this.nota);
        setResult(RESULT_OK, myIntent);
        finish();
        return true;
    }

    public void UpdatePenerimaan(View view) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Penerimaan Barang");
        progressDialog.setMessage("Mengirim data...");
        progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
        progressDialog.show();
        String str_kuantitas = qty.getText().toString();
        if (str_kuantitas.isEmpty()){
            progressDialog.dismiss();
            Toast.makeText(getApplicationContext(), "Kuantitas tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else {
            String nota_do = notado.getText().toString();
            if (!nota_do.isEmpty()){
                int kuantitas = Integer.parseInt(str_kuantitas);
                if (kuantitas > terima){
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Kuantitas melebihi sisa barang", Toast.LENGTH_SHORT).show();
                } else if (kuantitas == 0){
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Kuantitas tidak boleh 0", Toast.LENGTH_SHORT).show();
                } else {
                    store = new StoreSession(getApplicationContext());
                    apiService = new RetrofitService().getData().create(ApiService.class);
                    Call<StatusModel> status = apiService.simpanTerimaBarang(serviceProvider.getAuthorization(getApplicationContext()),
                            item, satuan_order, kuantitas, store.getDataString("username"), nota_do, nota);
                    status.enqueue(new Callback<StatusModel>() {
                        @Override
                        public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), response.body().getStatus(), Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(getIntent());
                        }

                        @Override
                        public void onFailure(Call<StatusModel> call, Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "gagal", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            } else {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Nota DO harus diisi", Toast.LENGTH_SHORT).show();
            }
        }
    }
}