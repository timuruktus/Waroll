package ru.timuruktus.waroll.Presenter;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import ru.timuruktus.waroll.Events.ChangeToolbarTitle;
import ru.timuruktus.waroll.Events.OnFragmentReplace;
import ru.timuruktus.waroll.R;
import ru.timuruktus.waroll.View.Fragments.JoinFragment;
import ru.timuruktus.waroll.View.Fragments.MainFragment;
import ru.timuruktus.waroll.View.Fragments.RegFragment;
import ru.timuruktus.waroll.View.MainActivity;

public class MainActivityPresenter {

    private MainActivity mainActivity;

    public MainActivityPresenter(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void onFragmentReplace(OnFragmentReplace onFragmentReplace){
        FragmentManager fragmentManager = mainActivity.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(onFragmentReplace.addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(R.id.fragmentContainer, onFragmentReplace.fragment);
        fragmentTransaction.commit();
    }

    @Subscribe
    public void changeToolbarTitle(ChangeToolbarTitle changeToolbarTitle){
        if(changeToolbarTitle.fragment instanceof JoinFragment){
            mainActivity.toolbar.setTitle(R.string.join_title);
        }else if(changeToolbarTitle.fragment instanceof RegFragment){
            mainActivity.toolbar.setTitle(R.string.reg_1_topic);
        }else if(changeToolbarTitle.fragment instanceof MainFragment){
            mainActivity.toolbar.setTitle(R.string.app_name);
        }
    }


}
