package com.example.mutiaramobile;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mutiaramobile.menupage.DashboardPage;
import com.example.mutiaramobile.menupage.pembayaranpage.PembayaranPage;
import com.example.mutiaramobile.menupage.penerimaanbarang.PenerimaanBarangPage;
import com.example.mutiaramobile.serviceprovider.StoreSession;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    StoreSession store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        store = new StoreSession(MainActivity.this.getApplicationContext());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            String page = getIntent().getStringExtra("fragment");
            System.out.println(page);
            if (page != null){
                movePage(page);
            } else {
                DashboardPage fragment = new DashboardPage();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, fragment);
                fragmentTransaction.commit();
            }
        } catch (Exception e){
            DashboardPage fragment = new DashboardPage();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout, fragment);
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View menu = navigationView.getHeaderView(0);
        TextView namauser = (TextView) menu.findViewById(R.id.namauser);
        TextView notlp = (TextView) menu.findViewById(R.id.notlp);
        TextView area = (TextView) menu.findViewById(R.id.area);
        if (store.getDataString("username") == null){
            Intent intent = new Intent(this, LoginPage.class);
            startActivity(intent);
        } else {
            namauser.setText(store.getDataString("nama"));
            area.setText(store.getDataString("area"));
            notlp.setText(" (" + store.getDataString("tlp") + ")");
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.menudashboard) {
            DashboardPage fragment = new DashboardPage();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout, fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.menupenerimaanbarang){
            PenerimaanBarangPage fragment = new PenerimaanBarangPage();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.menupembayaran){
            PembayaranPage fragment = new PembayaranPage();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout, fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.menureturnproduksi) {

        } else if (id == R.id.menupengelolaanbarangmasuk) {

        } else if (id == R.id.menupengelolaanbarangkeluar) {

        } else if (id == R.id.menupengelolaandistribusibarang) {

        } else if (id == R.id.menupengelolaanmanajemenstock) {

        } else if (id == R.id.menumanajemenmarketing) {

        } else if (id == R.id.menumanajemenpenjualanpusat) {

        } else if (id == R.id.menumanajemenkonsinyasipusat) {

        } else if (id == R.id.menumanajemenmarketingarea) {

        } else if (id == R.id.menumanajemenagen) {

        } else if (id == R.id.menukinerjasdm) {

        } else if (id == R.id.menukelolaabsensisdm) {

        } else if (id == R.id.logout) {
            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
            alert.setTitle("Logout")
                    .setMessage("Apakah anda yakin ingin keluar dari aplikasi?")
                    .setCancelable(false)
                    .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            Toast.makeText(getApplicationContext(), "Batal Logout", Toast.LENGTH_LONG).show();
                        }
                    })
                    .setPositiveButton("Lanjutkan", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                            progressDialog.setTitle("Logout");
                            progressDialog.setMessage("Menghapus data...");
                            progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
                            progressDialog.show();
                            store.RemoveSession();
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Sukses", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MainActivity.this, LoginPage.class);
                            startActivity(intent);
                        }
                    });
            AlertDialog muncul = alert.create();
            muncul.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void movePage(String page){
        if (page == "Penerimaan Barang"){
            PenerimaanBarangPage fragment = new PenerimaanBarangPage();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout, fragment);
            fragmentTransaction.commit();
        }
    }
}