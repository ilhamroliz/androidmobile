package com.example.mutiaramobile.menupage.returnproduksipage;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mutiaramobile.R;
import com.example.mutiaramobile.api.ApiService;
import com.example.mutiaramobile.model.ItemModel;
import com.example.mutiaramobile.model.ReturnProduksi.ListNotaProduksiModel;
import com.example.mutiaramobile.model.ReturnProduksi.SupplierProduksiModel;
import com.example.mutiaramobile.serviceprovider.MutiaraServiceProvider;
import com.example.mutiaramobile.serviceprovider.RetrofitService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReturnProduksiPage extends Fragment {

    Spinner spinner_supplier;
    EditText startdate, enddate;
    final Calendar datestartpicker = Calendar.getInstance();
    final Calendar dateendpicker = Calendar.getInstance();
    Button btn_cari;
    ListView list_nota;
    ProgressDialog progressDialog;
    ArrayList<String> supplier;
    ArrayList<Integer> id_supplier;
    ArrayList<String> listNota;
    ArrayList<String> listTanggal;
    ArrayList<String> listJumlah;
    ArrayList<Map<String, Object>> dataList;

    public ReturnProduksiPage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_return_produksi_page, container, false);

        spinner_supplier = (Spinner) fragmentView.findViewById(R.id.return_produksi_select_supplier);
        startdate = (EditText) fragmentView.findViewById(R.id.return_produksi_start_date);
        enddate = (EditText) fragmentView.findViewById(R.id.return_produksi_end_date);
        btn_cari = (Button) fragmentView.findViewById(R.id.return_produksi_btn_cari);
        list_nota = (ListView) fragmentView.findViewById(R.id.return_produksi_list_nota_produksi);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Return Produksi");
        progressDialog.setMessage("Mengambil data...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startdate.setShowSoftInputOnFocus(false);
            enddate.setShowSoftInputOnFocus(false);
        }

        final DatePickerDialog.OnDateSetListener tglmulai = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // TODO Auto-generated method stub
                datestartpicker.set(Calendar.YEAR, year);
                datestartpicker.set(Calendar.MONTH, month);
                datestartpicker.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                setStartDate();
            }
        };

        startdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        tglmulai, datestartpicker.get(Calendar.YEAR), datestartpicker.get(Calendar.MONTH),
                        datestartpicker.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        final DatePickerDialog.OnDateSetListener tglakhir = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dateendpicker.set(Calendar.YEAR, year);
                dateendpicker.set(Calendar.MONTH, month);
                dateendpicker.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                setEndDate();
            }
        };

        enddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        tglakhir, dateendpicker.get(Calendar.YEAR), dateendpicker.get(Calendar.MONTH),
                        dateendpicker.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        supplier = new ArrayList<String>();
        id_supplier = new ArrayList<Integer>();
        final MutiaraServiceProvider mutiaraServiceProvider = new MutiaraServiceProvider();
        final ApiService apiService = new RetrofitService().getData().create(ApiService.class);
        Call<ItemModel> data = apiService.getSupplierProduksi(mutiaraServiceProvider.getAuthorization(getActivity()));
        data.enqueue(new Callback<ItemModel>() {
            @Override
            public void onResponse(Call<ItemModel> call, Response<ItemModel> response) {
                progressDialog.dismiss();
                try {
                    List<SupplierProduksiModel> result = response.body().getSupplierProduksi();
                    for (SupplierProduksiModel supplierProduksiModel : result){
                        supplier.add(supplierProduksiModel.getSCompany());
                        id_supplier.add(supplierProduksiModel.getSId());
                    }
                    ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getActivity(), R.layout.custom_spinner, supplier);
                    spinner_supplier.setAdapter(myAdapter);
                } catch (Exception e){
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ItemModel> call, Throwable t) {
                progressDialog.dismiss();
            }
        });

        btn_cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                String awal = startdate.getText().toString().trim();
                String akhir = enddate.getText().toString().trim();
                int position = spinner_supplier.getSelectedItemPosition();
                int idSupplier = id_supplier.get(position);
                if (awal.isEmpty() || awal.length() == 0 || awal.equals("") || awal == null ||
                        akhir.isEmpty() || akhir.length() == 0 || akhir.equals("") || akhir == null){
                    Toast.makeText(getActivity(), "Tanggal tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else {
                    MutiaraServiceProvider mutiaraServiceProvider1 = new MutiaraServiceProvider();
                    ApiService apiService1 = new RetrofitService().getData().create(ApiService.class);
                    Call<ItemModel> dataRespons = apiService1.getListNotaProduksi(
                            mutiaraServiceProvider1.getAuthorization(getActivity()), idSupplier, awal, akhir);
                    dataRespons.enqueue(new Callback<ItemModel>() {
                        @Override
                        public void onResponse(Call<ItemModel> call, Response<ItemModel> response) {
                            listNota = new ArrayList<>();
                            listTanggal = new ArrayList<>();
                            listJumlah = new ArrayList<>();
                            dataList = new ArrayList<Map<String, Object>>();
                            try {
                                List<ListNotaProduksiModel> listNotaProduksiModels = response.body().getNotaProduksi();
                                for (ListNotaProduksiModel result : listNotaProduksiModels){
                                    listNota.add(result.getPoNota());
                                    listTanggal.add(result.getPoDate());
                                    listJumlah.add(result.getJumlah());
                                }
                                for (int i = 0; i < listNota.size(); i++) {
                                    Map<String, Object> listItem = new HashMap<String, Object>();
                                    listItem.put("judul", listTanggal.get(i) + " (" + listNota.get(i) + ")");
                                    listItem.put("subjudul", listJumlah.get(i));
                                    dataList.add(listItem);
                                }
                                SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), dataList,
                                        R.layout.list_item, new String[]{"judul", "subjudul"}, new int[]{R.id.judul, R.id.subjudul});
                                list_nota.setAdapter(simpleAdapter);
                            } catch (Exception e){
                                Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                            }
                            progressDialog.dismiss();
                        }

                        @Override
                        public void onFailure(Call<ItemModel> call, Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        list_nota.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ListBarangReturnProduksiPage.class);
                intent.putExtra("nota", listNota.get(position));
                startActivity(intent);
            }
        });

        return fragmentView;
    }

    private void setStartDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        startdate.setText(sdf.format(datestartpicker.getTime()));
    }

    private void setEndDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        enddate.setText(sdf.format(dateendpicker.getTime()));
    }
}
