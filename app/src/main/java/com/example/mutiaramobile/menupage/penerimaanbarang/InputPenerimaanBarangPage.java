package com.example.mutiaramobile.menupage.penerimaanbarang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.mutiaramobile.MainActivity;
import com.example.mutiaramobile.R;

public class InputPenerimaanBarangPage extends AppCompatActivity {

    String nota, item, order, satuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_penerimaan_barang_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        nota = getIntent().getStringExtra("Nota");
        item = getIntent().getStringExtra("Item");
        order = getIntent().getStringExtra("Order");
        satuan = getIntent().getStringExtra("Order");


    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), TerimaBarangPage.class);
        myIntent.putExtra("Nota", this.nota);
        setResult(RESULT_OK, myIntent);
        finish();
        return true;
    }
}
