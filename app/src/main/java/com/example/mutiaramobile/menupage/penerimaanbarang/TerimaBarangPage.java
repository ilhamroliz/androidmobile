package com.example.mutiaramobile.menupage.penerimaanbarang;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.mutiaramobile.MainActivity;
import com.example.mutiaramobile.R;
import com.example.mutiaramobile.api.ApiService;
import com.example.mutiaramobile.model.ItemModel;
import com.example.mutiaramobile.model.PenerimaanBarang.TerimaBarangNotaModel;
import com.example.mutiaramobile.serviceprovider.MutiaraServiceProvider;
import com.example.mutiaramobile.serviceprovider.RetrofitService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TerimaBarangPage extends AppCompatActivity {

    ArrayList<String> nama, qty, satuan, notaPo;
    ArrayList<Integer> idItem;
    ArrayList<Map<String, Object>> dataBarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String nota = getIntent().getStringExtra("Nota");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terima_barang);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Penerimaan Barang");
        progressDialog.setMessage("Mengambil data...");
        progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
        progressDialog.show();

        nama = new ArrayList<String>();
        qty = new ArrayList<String>();
        satuan = new ArrayList<String>();
        notaPo = new ArrayList<String>();
        idItem = new ArrayList<Integer>();
        dataBarang = new ArrayList<Map<String, Object>>();

        MutiaraServiceProvider service = new MutiaraServiceProvider();
        ApiService getData = new RetrofitService().getData().create(ApiService.class);
        Call<ItemModel> data = getData.getDataPenerimaanNota(service.getAuthorization(this), nota);
        data.enqueue(new Callback<ItemModel>() {
            @Override
            public void onResponse(Call<ItemModel> call, Response<ItemModel> response) {

                List<TerimaBarangNotaModel> barang = response.body().getItemTerimaBarang();

                for (TerimaBarangNotaModel data : barang) {
                    nama.add(data.getIName());
                    qty.add(String.valueOf(data.getPodQty()));
                    satuan.add(data.getUName());
                    notaPo.add(data.getPoNota());
                    idItem.add(data.getI_id());
                }

                int panjang = nama.size();

                for (int i = 0; i < panjang; i++) {
                    Map<String, Object> listItem = new HashMap<String, Object>();
                    listItem.put("judul", nama.get(i));
                    listItem.put("subjudul", qty.get(i).concat(" ").concat(satuan.get(i)));
                    dataBarang.add(listItem);
                }

                SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), dataBarang, R.layout.list_item,
                        new String[]{"judul", "subjudul"}, new int[]{R.id.judul, R.id.subjudul});

                ListView listview = (ListView) findViewById(R.id.ListBarangNota);
                listview.setAdapter(simpleAdapter);

                progressDialog.dismiss();

                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getBaseContext(), InputPenerimaanBarangPage.class);
                        intent.putExtra("Nota", notaPo.get(position));
                        intent.putExtra("Item", idItem.get(position));
                        intent.putExtra("Order", qty.get(position));
                        intent.putExtra("Satuan", satuan.get(position));
                        intent.putExtra("Nama", nama.get(position));
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onFailure(Call<ItemModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        myIntent.putExtra("fragment", "Penerimaan Barang");
        setResult(RESULT_OK, myIntent);
        finish();
        return true;
    }
}
