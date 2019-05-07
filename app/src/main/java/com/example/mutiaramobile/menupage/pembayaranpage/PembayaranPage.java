package com.example.mutiaramobile.menupage.pembayaranpage;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mutiaramobile.R;
import com.example.mutiaramobile.api.ApiService;
import com.example.mutiaramobile.menupage.penerimaanbarang.TerimaBarangPage;
import com.example.mutiaramobile.model.ItemModel;
import com.example.mutiaramobile.model.PembayaranNota.NotaOrderProduksiModel;
import com.example.mutiaramobile.serviceprovider.MutiaraServiceProvider;
import com.example.mutiaramobile.serviceprovider.RetrofitService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PembayaranPage extends Fragment implements AdapterView.OnItemSelectedListener {

    ListView listView;
    ProgressDialog progressDialog;
    ArrayList<String> tanggal, supplier, nilai, nota;

    public PembayaranPage() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_pembayaran_page, container, false);

        listView = (ListView) fragmentView.findViewById(R.id.list_nota_pembayaran);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Pembayaran Nota");
        progressDialog.setMessage("Mengambil data...");
        progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
        progressDialog.show();

        MutiaraServiceProvider mutiaraServiceProvider = new MutiaraServiceProvider();
        ApiService apiService = new RetrofitService().getData().create(ApiService.class);
        final Call<ItemModel> data = apiService.getNotaPembayaran(mutiaraServiceProvider.getAuthorization(getActivity()));
        data.enqueue(new Callback<ItemModel>() {
            @Override
            public void onResponse(Call<ItemModel> call, Response<ItemModel> response) {
                progressDialog.dismiss();
                try {
                    List<NotaOrderProduksiModel> nota_order = response.body().getNotaOrderProduksi();
                    tanggal = new ArrayList<String>();
                    nilai = new ArrayList<String>();
                    supplier = new ArrayList<String>();
                    nota = new ArrayList<String>();
                    ArrayList<Map<String, Object>> itemDataList;
                    itemDataList = new ArrayList<Map<String, Object>>();

                    for (NotaOrderProduksiModel data: nota_order){
                        tanggal.add(data.getPopDate());
                        nilai.add(data.getPopValue());
                        supplier.add(data.getSName());
                        nota.add(data.getPoNota());
                    }

                    for (int i = 0; i < supplier.size(); i++) {
                        Map<String,Object> listItem = new HashMap<String,Object>();
                        listItem.put("judul", supplier.get(i) + " (" + nota.get(i) + ")");
                        listItem.put("subjudul", "Termin: " + tanggal.get(i) + "  Rp" + nilai.get(i));
                        itemDataList.add(listItem);
                    }

                    SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), itemDataList, R.layout.list_item,
                            new String[]{"judul", "subjudul"}, new int[]{R.id.judul,R.id.subjudul});

                    listView.setAdapter(simpleAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(getActivity(), ListTerminPembayaranPage.class);
                            intent.putExtra("Nota", nota.get(position));
                            startActivity(intent);
                        }
                    });
                } catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<ItemModel> call, Throwable t) {

            }
        });
        return fragmentView;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
