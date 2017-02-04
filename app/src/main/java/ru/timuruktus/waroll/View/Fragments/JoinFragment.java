package ru.timuruktus.waroll.View.Fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import ru.timuruktus.waroll.Events.ChangeToolbarTitle;
import ru.timuruktus.waroll.Events.OnFragmentReplace;
import ru.timuruktus.waroll.Events.OnJoinFragClick;
import ru.timuruktus.waroll.Model.ExtendedSliderLayout;
import ru.timuruktus.waroll.R;

public class JoinFragment extends Fragment implements View.OnClickListener{

    private View rootView;
    private ExtendedSliderLayout imageSlider;
    private Button join,reg;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView =
                inflater.inflate(R.layout.join_fragment, container, false);
        initImageSlider();

        reg = (Button) rootView.findViewById(R.id.reg);
        reg.setOnClickListener(this);
        EventBus.getDefault().post(new ChangeToolbarTitle(this));

        return rootView;
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.join){
            EventBus.getDefault().post(new OnJoinFragClick(OnJoinFragClick.Actions.JOIN));
        }else if(id == R.id.reg){
            EventBus.getDefault().post(new OnJoinFragClick(OnJoinFragClick.Actions.REG));
        }else if(id == R.id.forgottenPass){
            EventBus.getDefault().post(new OnJoinFragClick(OnJoinFragClick.Actions.PASS));
        }
    }

    private void initImageSlider(){
        imageSlider = (ExtendedSliderLayout) rootView.findViewById(R.id.slider);

        ArrayList<Integer> files = new ArrayList<>();
        files.add(R.drawable.img1);
        files.add(R.drawable.img2);
        files.add(R.drawable.img3);
        files.add(R.drawable.img4);

        for(int i = 0; i < files.size(); i++){
            DefaultSliderView sliderView = new DefaultSliderView(getActivity());
            // initialize a SliderLayout
            sliderView
                    .image(files.get(i))
                    .setScaleType(BaseSliderView.ScaleType.Fit);

            imageSlider.addSlider(sliderView);
        }
        imageSlider.setOnDragListener(null);
        imageSlider.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Invisible);
        imageSlider.setDuration(4000);
        imageSlider.getPagerIndicator().setVisibility(View.INVISIBLE);
    }


    @Override
    public void onStart() {
        //EventBus.getDefault().register(this);
        super.onStart();

    }

    @Override
    public void onStop(){
        //EventBus.getDefault().unregister(this);
        super.onStop();
    }


}

