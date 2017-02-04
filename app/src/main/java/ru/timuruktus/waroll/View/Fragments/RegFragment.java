package ru.timuruktus.waroll.View.Fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import ru.timuruktus.waroll.Events.ChangeToolbarTitle;
import ru.timuruktus.waroll.R;

public class RegFragment extends Fragment {

    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView =
                inflater.inflate(R.layout.reg_1_fragment, container, false);

        EventBus.getDefault().post(new ChangeToolbarTitle(this));
        return rootView;
    }

}
