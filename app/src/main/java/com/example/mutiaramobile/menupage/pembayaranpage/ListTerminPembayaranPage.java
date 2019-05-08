package com.example.mutiaramobile.menupage.pembayaranpage;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
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
import com.example.mutiaramobile.model.PembayaranNota.TerminNotaProduksiModel;
import com.example.mutiaramobile.serviceprovider.MutiaraServiceProvider;
import com.example.mutiaramobile.serviceprovider.RetrofitService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListTerminPembayaranPage extends AppCompatActivity {

    ProgressDialog progressDialog;
    String nota;
    ListView listView;
    ArrayList<String> datetop, bayarrp, bayar, termin, tagihan, tagihanrp;
    ArrayList<Map<String, Object>> dataList;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_termin_pembayaran_page);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        listView = (ListView) findViewById(R.id.list_termin_pembayaran);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Pembayaran Nota");
        progressDialog.setMessage("Mengambil data...");
        progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
        progressDialog.show();

        nota = getIntent().getStringExtra("Nota");

        datetop = new ArrayList<String>();
        bayarrp = new ArrayList<String>();
        bayar = new ArrayList<String>();
        termin = new ArrayList<String>();
        tagihan = new ArrayList<String>();
        tagihanrp = new ArrayList<String>();

        dataList = new ArrayList<Map<String, Object>>();

        MutiaraServiceProvider mutiaraServiceProvider = new MutiaraServiceProvider();
        ApiService apiService = new RetrofitService().getData().create(ApiService.class);
        Call<ItemModel> data = apiService.getTerminProduksi(mutiaraServiceProvider.getAuthorization(this), nota);
        data.enqueue(new Callback<ItemModel>() {
            @Override
            public void onResponse(Call<ItemModel> call, Response<ItemModel> response) {
                try {
                    List<TerminNotaProduksiModel> list_termin = response.body().getListTerminNota();

                    for (TerminNotaProduksiModel info : list_termin) {
                        datetop.add(info.getPopDatetop());
                        bayarrp.add(info.getPopValue());
                        bayar.add(info.getPopValueint());
                        termin.add(info.getPopTermin());
                        tagihan.add(info.getTagihan());
                        tagihanrp.add(info.getTagihanrp());
                    }

                    for (int i = 0; i < list_termin.size(); i++) {
                        Map<String, Object> listItem = new HashMap<String, Object>();
                        listItem.put("judul", "Termin ke-" + termin.get(i) + " Rp" + bayarrp.get(i));
                        listItem.put("subjudul", "Jatuh Tempo " + datetop.get(i));
                        dataList.add(listItem);
                    }

                    SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), dataList, R.layout.list_item,
                            new String[]{"judul", "subjudul"}, new int[]{R.id.judul, R.id.subjudul});
                    listView.setAdapter(simpleAdapter);
                    progressDialog.dismiss();

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(getApplicationContext(), InputTerminPage.class);
                            intent.putExtra("nota", nota);
                            intent.putExtra("termin", termin.get(position));
                            intent.putExtra("bayar", bayar.get(position));
                            intent.putExtra("bayarrp", bayarrp.get(position));
                            intent.putExtra("tagihan", tagihan.get(position));
                            intent.putExtra("tagihanrp", tagihanrp.get(position));
                            intent.putExtra("tanggal", datetop.get(position));
                            startActivity(intent);
                        }
                    });

                } catch (Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Kosong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ItemModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "gagal", Toast.LENGTH_SHORT).show();
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                myIntent.putExtra("fragment", 2);
                startActivity(myIntent);
                finish();
            }
        });

    }
}
