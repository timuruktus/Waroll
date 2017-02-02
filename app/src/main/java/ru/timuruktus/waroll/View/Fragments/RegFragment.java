package ru.timuruktus.waroll.View.Fragments;


import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import ru.timuruktus.waroll.R;

public class RegFragment extends Fragment implements View.OnClickListener{

    private View rootView;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView =
                inflater.inflate(R.layout.reg_fragment, container, false);

        EventBus.getDefault().register(this);
        return rootView;
    }


    @Override
    public void onClick(View v) {

    }
}

