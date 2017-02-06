package ru.timuruktus.waroll.Presenter.MainActivity;


import android.app.FragmentManager;
import android.app.FragmentTransaction;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import ru.timuruktus.waroll.Presenter.MainActivity.ViewEvents.EChangeToolbarTitle;
import ru.timuruktus.waroll.Presenter.MainActivity.ViewEvents.EReplaceFragment;
import ru.timuruktus.waroll.R;
import ru.timuruktus.waroll.View.Fragments.Join.JoinFragment;
import ru.timuruktus.waroll.View.Fragments.Main.MainFragment;
import ru.timuruktus.waroll.View.Fragments.Reg1.RegFragment;
import ru.timuruktus.waroll.View.Fragments.Reg2.Reg2Fragment;
import ru.timuruktus.waroll.View.MainActivity;

public class MainActivityPresenter {

    private MainActivity mainActivity;

    public MainActivityPresenter(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        EventBus.getDefault().register(this);
    }

    /**
     * Replaces the current fragment
     * @param eReplaceFragment- event, which contain new fragment
     */
    @Subscribe
    public void replaceFragment(EReplaceFragment eReplaceFragment){
        FragmentManager fragmentManager = mainActivity.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(eReplaceFragment.addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(R.id.fragmentContainer, eReplaceFragment.fragment);
        fragmentTransaction.commit();
    }

    /**
     * Changes toolbar title
     * @param eChangeToolbarTitle- event, which contains current fragment
     */
    @Subscribe
    public void changeToolbarTitle(EChangeToolbarTitle eChangeToolbarTitle){
        if(eChangeToolbarTitle.fragment instanceof JoinFragment){
            mainActivity.toolbar.setTitle(R.string.join_title);
        }else if(eChangeToolbarTitle.fragment instanceof RegFragment){
            mainActivity.toolbar.setTitle(R.string.reg_1_topic);
        }else if(eChangeToolbarTitle.fragment instanceof MainFragment){
            mainActivity.toolbar.setTitle(R.string.app_name);
        }else if(eChangeToolbarTitle.fragment instanceof Reg2Fragment){
            mainActivity.toolbar.setTitle(R.string.reg_1_topic);
        }
    }


}
