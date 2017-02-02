package ru.timuruktus.waroll.View.Fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import ru.timuruktus.waroll.R;

public class JoinFragment extends Fragment implements View.OnClickListener{

    private View rootView;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView =
                inflater.inflate(R.layout.join_fragment, container, false);

        EventBus.getDefault().register(this);
        return rootView;
    }


    @Override
    public void onClick(View v) {

    }
}

