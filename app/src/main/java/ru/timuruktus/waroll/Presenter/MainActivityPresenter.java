package ru.timuruktus.waroll.Presenter;


import android.app.FragmentManager;
import android.app.FragmentTransaction;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import ru.timuruktus.waroll.Events.OnLeftMenuClick;
import ru.timuruktus.waroll.R;
import ru.timuruktus.waroll.View.Fragments.JoinFragment;
import ru.timuruktus.waroll.View.MainActivity;

public class MainActivityPresenter {

    private MainActivity mainActivity;

    public MainActivityPresenter(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void onLeftMenuClickListener(OnLeftMenuClick onLeftMenuClick){
        FragmentManager fragmentManager = mainActivity.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(onLeftMenuClick.menuClick == OnLeftMenuClick.MenuClick.JOIN){
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.replace(R.id.fragmentContainer, new JoinFragment());
        }
        fragmentTransaction.commit();
    }


}
