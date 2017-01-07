package com.newbie.amien.projectbesar2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.newbie.amien.projectbesar2.Home.fragment.MapFragment;
import com.newbie.amien.projectbesar2.Home.fragment.TabFragment;

public class MainActivity extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    TextView namapemilik, emailpemilik;
    private View navHeader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        namapemilik = (TextView) findViewById(R.id.nav_namapemilik);
        emailpemilik = (TextView) findViewById(R.id.nav_emailpemilik);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff);

//        di gae DrawerLayout sama NavigationView /////
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);


//        inflate yang pertama di fragment
//        sing pertama tabfragment
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView, new TabFragment()).commit();
        mNavigationView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View v) {
                ngaturmenu();
            }
        });
//        iki gae ketika menu di navigasi di klik
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){


            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();
                if(menuItem.getItemId()==R.id.nav_item_login){
//                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
//                    fragmentTransaction.replace(R.id.containerView, new LoginActivity()).commit();
                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                }

                if (menuItem.getItemId()==R.id.nav_item_home){
                    FragmentTransaction xframentTransaction = mFragmentManager.beginTransaction();
                    xframentTransaction.replace(R.id.containerView, new TabFragment()).commit();
                }
                if (menuItem.getItemId()==R.id.nav_profil){
                    Intent i = new Intent(getApplicationContext(), Register.class);
                    startActivity(i);
                }
                if (menuItem.getItemId()==R.id.nav_logout){
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("Id", "0");
                    editor.commit();
                    ngaturmenu();

                }
                return false;
            }
        });


//        Drawer TOgle of toolbar
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar,R.string.app_name, R.string.app_name);

//        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onResume() {
        super.onResume();
       ngaturmenu();

    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);
        ngaturmenu();
        return super.onCreateOptionsMenu(menu);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        ngaturmenu();
        return super.onMenuOpened(featureId, menu);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void ngaturmenu(){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        Menu menuNav = mNavigationView.getMenu();
        navHeader = mNavigationView.getHeaderView(0);
        TextView txtName = (TextView) navHeader.findViewById(R.id.nav_namapemilik);
        TextView txtEmail = (TextView) navHeader.findViewById(R.id.nav_emailpemilik);
        if(!pref.getString("Id",null).toString().equals("0")){
//            namapemilik.setText(pref.getString("Nama", null));
//            emailpemilik.setText("Email", null);
            menuNav.findItem(R.id.nav_item_login).setVisible(false);

            txtName.setText(pref.getString("Nama", null));
            txtEmail.setText(pref.getString("Email", null));
            menuNav.findItem(R.id.nav_item_history).setVisible(true);
            menuNav.findItem(R.id.nav_profil).setVisible(true);
            menuNav.findItem(R.id.nav_logout).setVisible(true);
            txtName.setGravity(Gravity.LEFT);

        }else{
            txtName.setText("Welcome to the Kost Application");
            txtEmail.setText("");
            menuNav.findItem(R.id.nav_item_login).setVisible(true);
            menuNav.findItem(R.id.nav_item_history).setVisible(false);
            menuNav.findItem(R.id.nav_profil).setVisible(false);
            menuNav.findItem(R.id.nav_logout).setVisible(false);


        }
    }

}
