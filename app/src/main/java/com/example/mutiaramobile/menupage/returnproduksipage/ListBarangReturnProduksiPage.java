package com.example.mutiaramobile.menupage.returnproduksipage;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.mutiaramobile.MainActivity;
import com.example.mutiaramobile.R;
import com.example.mutiaramobile.api.ApiService;
import com.example.mutiaramobile.model.ItemModel;
import com.example.mutiaramobile.model.ReturnProduksi.ListBarangNotaProduksiModel;
import com.example.mutiaramobile.serviceprovider.MutiaraServiceProvider;
import com.example.mutiaramobile.serviceprovider.RetrofitService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListBarangReturnProduksiPage extends AppCompatActivity {

    Toolbar toolbar;
    String nota;
    ArrayList<String> nama_barang;
    ArrayList<String> jumlah_barang;
    ArrayList<Map<String,Object>> dataList;
    ListView listView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_barang_return_produksi_page);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Return Produksi");
        progressDialog.setMessage("Mengambil data...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        listView = (ListView) findViewById(R.id.list_barang_return_produksi);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                myIntent.putExtra("fragment", 3);
                startActivity(myIntent);
                finish();
            }
        });

        nota = getIntent().getStringExtra("nota");

        nama_barang = new ArrayList<>();
        jumlah_barang = new ArrayList<>();
        dataList = new ArrayList<Map<String, Object>>();

        MutiaraServiceProvider mutiaraServiceProvider = new MutiaraServiceProvider();
        ApiService apiService = new RetrofitService().getData().create(ApiService.class);
        Call<ItemModel> dataRespons = apiService.getListBarangNotaProduksi(
                mutiaraServiceProvider.getAuthorization(getApplicationContext()), nota);
        dataRespons.enqueue(new Callback<ItemModel>() {
            @Override
            public void onResponse(Call<ItemModel> call, Response<ItemModel> response) {
                progressDialog.dismiss();

                try {
                    List<ListBarangNotaProduksiModel> listBarang = response.body().getListBarangNotaProduksi();

                    for (ListBarangNotaProduksiModel result : listBarang) {
                        nama_barang.add(result.getIName());
                        jumlah_barang.add(result.getPodQty() + " " + result.getUName());
                    }
                    for (int i = 0; i < nama_barang.size(); i++) {
                        Map<String, Object> listItem = new HashMap<String, Object>();
                        listItem.put("judul", nama_barang.get(i));
                        listItem.put("subjudul", jumlah_barang.get(i));
                        dataList.add(listItem);
                    }

                    SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), dataList,
                            R.layout.list_item, new String[]{"judul", "subjudul"}, new int[]{R.id.judul, R.id.subjudul});

                    listView.setAdapter(simpleAdapter);
                } catch (Exception e){
                    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ItemModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), nama_barang.get(position), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
