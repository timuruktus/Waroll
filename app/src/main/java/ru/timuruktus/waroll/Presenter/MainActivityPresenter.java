package ru.timuruktus.waroll.Presenter;


import android.app.FragmentManager;
import android.app.FragmentTransaction;

import org.greenrobot.eventbus.Subscribe;

import ru.timuruktus.waroll.Events.OnLeftMenuClick;
import ru.timuruktus.waroll.R;
import ru.timuruktus.waroll.View.MainActivity;

public class MainActivityPresenter {

    private MainActivity mainActivity;

    public MainActivityPresenter(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    @Subscribe
    public void onLeftMenuClickListener(OnLeftMenuClick onLeftMenuClick){
        FragmentManager fragmentManager = mainActivity.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(onLeftMenuClick.menuClick == OnLeftMenuClick.MenuClick.REGISTRATION){
            fragmentTransaction.replace(R.id.fragmentContainer, event.fragment);

        }
        fragmentTransaction.commit();
    }
}
