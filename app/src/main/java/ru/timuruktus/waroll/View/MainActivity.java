package ru.timuruktus.waroll.View;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.greenrobot.eventbus.EventBus;

import ru.timuruktus.waroll.Model.Auth;
import ru.timuruktus.waroll.Presenter.MainActivity.ViewEvents.EReplaceFragment;
import ru.timuruktus.waroll.Presenter.Join.JoinFragmentPresenter;
import ru.timuruktus.waroll.Presenter.MainActivity.MainActivityPresenter;
import ru.timuruktus.waroll.Presenter.Reg1.RegFragmentPresenter;
import ru.timuruktus.waroll.R;
import ru.timuruktus.waroll.View.Fragments.Join.JoinFragment;
import ru.timuruktus.waroll.View.Fragments.Main.MainFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public  Toolbar toolbar;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initAllListeners();
        loadFragment();
        //EventBus.getDefault().register(this);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.action_settings) {
            drawer.openDrawer(GravityCompat.START,true);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.left_reg) {
            EventBus.getDefault().post(new EReplaceFragment(new JoinFragment(), true));
        } else if (id == R.id.nav_manage) {

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onDestroy(){
        //EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    /**
     * Initialise all EventBus's listeners in application
     */
    private void initAllListeners(){
        MainActivityPresenter mainActivityPresenter = new MainActivityPresenter(this);
        JoinFragmentPresenter joinFragmentPresenter = new JoinFragmentPresenter(this);
        RegFragmentPresenter regFragmentPresenter = new RegFragmentPresenter(this);
        Auth auth = new Auth();
    }

    /**
     * Load first fragment
     */
    private void loadFragment(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MainFragment mainFragment = new MainFragment();
        fragmentTransaction.replace(R.id.fragmentContainer, mainFragment);
        fragmentTransaction.commit();
    }



}
