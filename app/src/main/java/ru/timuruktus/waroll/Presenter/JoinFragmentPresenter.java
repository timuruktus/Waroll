package ru.timuruktus.waroll.Presenter;


import android.app.FragmentManager;
import android.app.FragmentTransaction;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import ru.timuruktus.waroll.Events.OnJoinFragClick;
import ru.timuruktus.waroll.R;
import ru.timuruktus.waroll.View.Fragments.RegFragment;
import ru.timuruktus.waroll.View.MainActivity;

public class JoinFragmentPresenter {

    private MainActivity mainActivity;

    public JoinFragmentPresenter(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void onJoinFragClick(OnJoinFragClick onJoinFragClick){
        FragmentManager fragmentManager = mainActivity.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(onJoinFragClick.getAction() == OnJoinFragClick.Actions.REG){
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.replace(R.id.fragmentContainer, new RegFragment());
        }else if(onJoinFragClick.getAction() == OnJoinFragClick.Actions.JOIN){

        }else if(onJoinFragClick.getAction() == OnJoinFragClick.Actions.PASS) {

        }
        fragmentTransaction.commit();
    }


}
