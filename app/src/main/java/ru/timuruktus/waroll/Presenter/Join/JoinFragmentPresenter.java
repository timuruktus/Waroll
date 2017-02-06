package ru.timuruktus.waroll.Presenter.Join;


import android.app.FragmentManager;
import android.app.FragmentTransaction;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import ru.timuruktus.waroll.Presenter.Join.ViewEvents.EOnJoinFragClick;
import ru.timuruktus.waroll.R;
import ru.timuruktus.waroll.View.Fragments.Reg1.RegFragment;
import ru.timuruktus.waroll.View.MainActivity;

public class JoinFragmentPresenter {

    private MainActivity mainActivity;

    public JoinFragmentPresenter(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void onJoinFragClick(EOnJoinFragClick eOnJoinFragClick){
        FragmentManager fragmentManager = mainActivity.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(eOnJoinFragClick.getAction() == EOnJoinFragClick.JoinActions.REG){
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.replace(R.id.fragmentContainer, new RegFragment());
        }else if(eOnJoinFragClick.getAction() == EOnJoinFragClick.JoinActions.JOIN){

        }else if(eOnJoinFragClick.getAction() == EOnJoinFragClick.JoinActions.PASS) {

        }
        fragmentTransaction.commit();
    }


}
