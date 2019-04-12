package com.example.mutiaramobile;

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
import android.widget.Toast;

import com.example.mutiaramobile.menupage.DashboardPage;
import com.example.mutiaramobile.menupage.PenerimaanBarangPage;
import com.example.mutiaramobile.serviceprovider.StoreSession;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    StoreSession store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        store = new StoreSession(MainActivity.this.getApplicationContext());

        Toast.makeText(getApplicationContext(), store.getDataString("username"), Toast.LENGTH_LONG).show();
        if (store.getDataString("username") == null){
            Intent intent = new Intent(this, LoginPage.class);
            startActivity(intent);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DashboardPage fragment = new DashboardPage();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
            store.RemoveSession();
            Toast.makeText(getApplicationContext(), "Sukses", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, LoginPage.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}