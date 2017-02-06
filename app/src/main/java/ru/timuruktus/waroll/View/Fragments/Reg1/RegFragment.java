package ru.timuruktus.waroll.View.Fragments.Reg1;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;

import ru.timuruktus.waroll.Presenter.MainActivity.ViewEvents.EChangeToolbarTitle;
import ru.timuruktus.waroll.Presenter.Reg1.ViewEvents.EOnRegFragClick;
import ru.timuruktus.waroll.R;

public class RegFragment extends Fragment implements View.OnClickListener{

    private View rootView;
    private EditText regEmailEdit, regLoginEdit, regPassEdit;
    private Button regRegBut;
    private TextView regPassText,regEmailText,regLoginText;
    private final int RED = 0xFFAB0E0E;
    private final int BLACK = 0x00000000;
    private ProgressBar regLoadingBar;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView =
                inflater.inflate(R.layout.reg_1_fragment, container, false);

        EventBus.getDefault().post(new EChangeToolbarTitle(this));

        regEmailEdit = (EditText) rootView.findViewById(R.id.regEmailEdit);
        regLoginEdit = (EditText) rootView.findViewById(R.id.regLoginEdit);
        regPassEdit = (EditText) rootView.findViewById(R.id.regPassEdit);
        regRegBut = (Button) rootView.findViewById(R.id.regRegBut);
        regLoadingBar = (ProgressBar) rootView.findViewById(R.id.regLoadingBar);
        regLoadingBar.setVisibility(View.INVISIBLE);
        regRegBut.setOnClickListener(this);

        regPassText = (TextView) rootView.findViewById(R.id.regPassText);
        regEmailText = (TextView) rootView.findViewById(R.id.regEmailText);
        regLoginText = (TextView) rootView.findViewById(R.id.regLoginText);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.regRegBut){
            EventBus.getDefault().post(new EOnRegFragClick(EOnRegFragClick.RegActions.REG,
                    getDataFromFields()));
            regPassText.setTextColor(BLACK);
            regEmailText.setTextColor(BLACK);
            regLoginText.setTextColor(BLACK);
        }
    }

    /**
     * @return login, email and pass from HashMap
     */
    private HashMap<String, String> getDataFromFields(){
        HashMap<String, String> regData = new HashMap<>();
        regData.put("email", regEmailEdit.getText().toString());
        if(regEmailEdit.getText().toString().equals("")) Log.d("tag", "email is space");
        regData.put("login", regLoginEdit.getText().toString());
        if(regLoginEdit.getText().toString().equals("")) Log.d("tag", "login is space");
        regData.put("pass", regPassEdit.getText().toString());
        if(regPassEdit.getText().toString().equals("")) Log.d("tag", "pass is space");
        return regData;
    }

    /**
     * Triggers when some error happens
     * @param regDataError - what kind of error
     *                     Can be seen on waroll.View.Reg1.RegDataError
     */
    @Subscribe
    public void onErrorListener(RegDataError regDataError){
        //TODO Различные Toast'ы для разных ситуаций

        if(regDataError.regWrong == RegDataError.RegWrong.PASS){

            regPassText.setTextColor(RED);
            Toast.makeText(rootView.getContext(),R.string.reg_1_pass_error,Toast.LENGTH_LONG).show();
        }
    }

    @Subscribe
    public void showLoading(RegLoadingEvent regLoadingEvent){
        if(regLoadingEvent.startLoading){
            // TODO Спрятывание всех элементов и открытие progressBar'а
            regLoadingBar.setVisibility(View.VISIBLE);
        }else{
            // TODO НАОБОРОТ
        }
    }

}
