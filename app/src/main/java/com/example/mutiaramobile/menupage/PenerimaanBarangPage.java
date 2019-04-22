package com.example.mutiaramobile.menupage;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mutiaramobile.R;
import com.example.mutiaramobile.api.ApiService;
import com.example.mutiaramobile.model.ItemModel;
import com.example.mutiaramobile.model.PenerimaanBarangModel;
import com.example.mutiaramobile.serviceprovider.MutiaraServiceProvider;
import com.example.mutiaramobile.serviceprovider.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PenerimaanBarangPage extends Fragment {


    public PenerimaanBarangPage() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
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
                progressDialog.dismiss();
                try {
                    Toast.makeText(getActivity(), "Sukses", Toast.LENGTH_LONG).show();
                } catch (Exception e){
                    Toast.makeText(getActivity(), "error try", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ItemModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "error", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_penerimaan_barang_page, container, false);
    }

}
