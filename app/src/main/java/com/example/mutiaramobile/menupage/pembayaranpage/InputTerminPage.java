package com.example.mutiaramobile.menupage.pembayaranpage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mutiaramobile.MainActivity;
import com.example.mutiaramobile.R;

public class InputTerminPage extends AppCompatActivity {
    String nota, termin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_termin_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        nota = getIntent().getStringExtra("nota");
        termin = getIntent().getStringExtra("termin");
        Toast.makeText(getApplicationContext(), nota, Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), termin, Toast.LENGTH_SHORT).show();

    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), ListTerminPembayaranPage.class);
        setResult(RESULT_OK, myIntent);
        finish();
        return true;
    }
}
