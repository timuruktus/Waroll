package ru.timuruktus.waroll.View.Fragments.Reg;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import ru.timuruktus.waroll.Events.ChangeToolbarTitle;
import ru.timuruktus.waroll.Events.OnRegFragClick;
import ru.timuruktus.waroll.R;

public class RegFragment extends Fragment implements View.OnClickListener{

    private View rootView;
    private EditText regEmailEdit, regLoginEdit, regPassEdit;
    private Button regRegBut;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView =
                inflater.inflate(R.layout.reg_1_fragment, container, false);

        EventBus.getDefault().post(new ChangeToolbarTitle(this));

        regEmailEdit = (EditText) rootView.findViewById(R.id.regEmailEdit);
        regLoginEdit = (EditText) rootView.findViewById(R.id.regLoginEdit);
        regPassEdit = (EditText) rootView.findViewById(R.id.regPassEdit);
        regRegBut = (Button) rootView.findViewById(R.id.regRegBut);
        regRegBut.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.regRegBut){
            EventBus.getDefault().post(new OnRegFragClick(OnRegFragClick.RegActions.REG,
                    getDataFromFields()));
        }
    }

    private HashMap<String, String> getDataFromFields(){
        HashMap<String, String> regData = new HashMap<>();
        regData.put("Email", regEmailEdit.getText().toString());
        regData.put("Login", regLoginEdit.getText().toString());
        regData.put("Pass", regPassEdit.getText().toString());
        return regData;
    }

    public void onErrorListener(RegDataError regDataError){

    }

}
