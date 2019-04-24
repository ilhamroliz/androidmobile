package com.example.mutiaramobile.menupage.submenupage;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.mutiaramobile.R;
import com.example.mutiaramobile.api.ApiService;
import com.example.mutiaramobile.model.ItemModel;
import com.example.mutiaramobile.model.ItemTerimaBarangModel;
import com.example.mutiaramobile.model.PenerimaanBarang.PenerimaanBarangModel;
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
import retrofit2.Retrofit;

public class TerimaBarang extends AppCompatActivity {

    ListView listView;
    ArrayList<String> nama, qty, satuan;
    ArrayList<Map<String,Object>> dataBarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String nota= getIntent().getStringExtra("Nota");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terima_barang);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Penerimaan Barang");
        progressDialog.setMessage("Mengambil data...");
        progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
        progressDialog.show();

        nama = new ArrayList<String>();
        qty = new ArrayList<String>();
        satuan = new ArrayList<String>();
        dataBarang = new ArrayList<Map<String,Object>>();

        MutiaraServiceProvider service = new MutiaraServiceProvider();
        ApiService getData = new RetrofitService().getData().create(ApiService.class);
        Call<ItemTerimaBarangModel> data = getData.getDataPenerimaanNota(service.getAuthorization(this), nota);
        data.enqueue(new Callback<ItemTerimaBarangModel>() {
            @Override
            public void onResponse(Call<ItemTerimaBarangModel> call, Response<ItemTerimaBarangModel> response) {

                List<TerimaBarangNotaModel> barang = response.body().getItemTerimaBarang();

                for (TerimaBarangNotaModel data : barang){
                    nama.add(data.getIName());
                    qty.add(String.valueOf(data.getPodQty()));
                    satuan.add(data.getUName());
                }

                int panjang = nama.size();

                for (int i = 0; i < panjang; i++) {
                    Map<String,Object> listItem = new HashMap<String, Object>();
                    listItem.put("judul", nama.get(i));
                    listItem.put("subjudul", qty.get(i).concat(" ").concat(satuan.get(i)));
                    dataBarang.add(listItem);
                }

                SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), dataBarang, R.layout.list_item,
                        new String[]{"judul", "subjudul"}, new int[]{R.id.judul,R.id.subjudul});

                ListView listview =(ListView) findViewById(R.id.ListBarangNota);
                listview.setAdapter(simpleAdapter);

                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<ItemTerimaBarangModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        });
//
//                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        Toast.makeText(getApplicationContext(), "Sukses", Toast.LENGTH_LONG).show();
//                    }
//                });
    }
}
