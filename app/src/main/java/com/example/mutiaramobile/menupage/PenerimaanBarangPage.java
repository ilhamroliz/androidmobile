package com.example.mutiaramobile.menupage;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.mutiaramobile.R;
import com.example.mutiaramobile.api.ApiService;
import com.example.mutiaramobile.menupage.submenupage.TerimaBarang;
import com.example.mutiaramobile.model.ItemModel;
import com.example.mutiaramobile.model.PenerimaanBarang.PenerimaanBarangModel;
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
public class PenerimaanBarangPage extends Fragment {

    ListView listView;
    ArrayList<String> nota, supplier;

    public PenerimaanBarangPage() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_penerimaan_barang_page, container, false);

        listView = (ListView) fragmentView.findViewById(R.id.ListPenerimaanBarang);

        super.onCreate(savedInstanceState);
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Penerimaan Barang");
        progressDialog.setMessage("Mengambil data...");
        progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
        progressDialog.show();

        MutiaraServiceProvider service = new MutiaraServiceProvider();
        ApiService getData = new RetrofitService().getData().create(ApiService.class);
        Call<ItemModel> data = getData.getDataPenerimaan(service.getAuthorization(getActivity()));
        data.enqueue(new Callback<ItemModel>() {
            @Override
            public void onResponse(Call<ItemModel> call, Response<ItemModel> response) {
                try {
                    List<PenerimaanBarangModel> barang = response.body().getItemPenerimaan();

                    nota = new ArrayList<String>();
                    supplier = new ArrayList<String>();

                    ArrayList<Map<String, Object>> itemDataList;
                    itemDataList = new ArrayList<Map<String, Object>>();

                    for (PenerimaanBarangModel data : barang){
                        nota.add(data.getPoNota());
                        supplier.add(data.getSCompany());
                    }

                    int panjang = nota.size();

                    for (int i = 0; i < panjang; i++) {
                        Map<String,Object> listItem = new HashMap<String,Object>();
                        listItem.put("judul", supplier.get(i));
                        listItem.put("subjudul", nota.get(i));
                        itemDataList.add(listItem);
                    }

                    SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), itemDataList, R.layout.list_item,
                            new String[]{"judul", "subjudul"}, new int[]{R.id.judul,R.id.subjudul});

                    listView.setAdapter(simpleAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(getActivity(), TerimaBarang.class);
                            intent.putExtra("Nota", nota.get(position));
                            startActivity(intent);
                        }
                    });

                } catch (Exception e){
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ItemModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Cobalah beberapa saat lagi", Toast.LENGTH_LONG).show();
            }
        });

        return fragmentView;
    }
}
