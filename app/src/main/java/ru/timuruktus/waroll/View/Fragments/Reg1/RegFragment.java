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

import java.util.ArrayList;
import java.util.HashMap;

import ru.timuruktus.waroll.Presenter.MainActivity.ViewEvents.EChangeToolbarTitle;
import ru.timuruktus.waroll.Presenter.Reg1.ViewEvents.EOnRegFragClick;
import ru.timuruktus.waroll.R;

public class RegFragment extends Fragment implements View.OnClickListener{

    // TODO Исправить баг с исчезновением надписей LOGIN, EMAIL и PASS

    private View rootView;
    private EditText regEmailEdit, regLoginEdit, regPassEdit;
    private Button regRegBut;
    private TextView regPassText,regEmailText,regLoginText, regPassHint, regEmailHint, regLoginHint
            ,regTopicHint;
    private final int RED = 0xFFAB0E0E;
    private final int BLACK = 0xFF000000;
    private ProgressBar regLoadingBar;
    private ArrayList<View> views;

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
        regPassHint = (TextView) rootView.findViewById(R.id.regPassHint);
        regEmailHint = (TextView) rootView.findViewById(R.id.regEmailHint);
        regLoginHint = (TextView) rootView.findViewById(R.id.regLoginHint);
        regTopicHint = (TextView) rootView.findViewById(R.id.regTopicHint);

        regLoadingBar.setVisibility(View.INVISIBLE);
        regRegBut.setOnClickListener(this);

        regPassText = (TextView) rootView.findViewById(R.id.regPassText);
        regEmailText = (TextView) rootView.findViewById(R.id.regEmailText);
        regLoginText = (TextView) rootView.findViewById(R.id.regLoginText);

        loadAllViewsToArrayList();
        return rootView;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.regRegBut){
            EventBus.getDefault().post(new EOnRegFragClick(EOnRegFragClick.RegActions.REG,
                    getDataFromFields()));

        }
    }

    /**
     * @return login, email and pass from HashMap
     */
    private HashMap<String, String> getDataFromFields(){
        HashMap<String, String> regData = new HashMap<>();
        regData.put("email", regEmailEdit.getText().toString());
        regData.put("login", regLoginEdit.getText().toString());
        regData.put("pass", regPassEdit.getText().toString());
        return regData;
    }

    /**
     * Triggers when some error happens
     * @param regDataError - what kind of error
     *                     Can be seen on waroll.View.Reg1.RegDataError
     */
    @Subscribe
    public void onErrorListener(RegDataError regDataError){

        regPassText.setTextColor(BLACK);
        regEmailText.setTextColor(BLACK);
        regLoginText.setTextColor(BLACK);

        if(regDataError.regWrong == RegDataError.RegWrong.PASS){
            regPassText.setTextColor(RED);
            Toast.makeText(rootView.getContext(),R.string.reg_1_pass_error,Toast.LENGTH_LONG).show();
        }
        else if(regDataError.regWrong == RegDataError.RegWrong.EMPTY_FIELD){
            Log.d("tag", "activated");
            if(regEmailEdit.getText().toString().equals("")){
                regEmailText.setTextColor(RED);
            }
            if(regLoginEdit.getText().toString().equals("")) {
                regLoginText.setTextColor(RED);
            }
            if(regPassEdit.getText().toString().equals("")){
                regPassText.setTextColor(RED);
            }
            Toast.makeText(rootView.getContext(),R.string.reg_1_empty_error,Toast.LENGTH_LONG).show();

        } else if(regDataError.regWrong == RegDataError.RegWrong.LOGIN_EXISTS) {
            regLoginText.setTextColor(RED);
            Toast.makeText(rootView.getContext(), R.string.reg_1_login_exists_error, Toast.LENGTH_LONG).show();
        } else if(regDataError.regWrong == RegDataError.RegWrong.OTHER){
            Toast.makeText(rootView.getContext(), R.string.error, Toast.LENGTH_LONG).show();
        } else if(regDataError.regWrong == RegDataError.RegWrong.EMAIL){
            regEmailText.setTextColor(RED);
            Toast.makeText(rootView.getContext(), R.string.reg_1_email_error, Toast.LENGTH_LONG).show();
        }
    }

    @Subscribe
    public void showLoading(RegLoadingEvent regLoadingEvent){
        int changeVisibilityTo;
        boolean enableViews = !regLoadingEvent.startLoading;
        if(regLoadingEvent.startLoading){
            changeVisibilityTo = View.INVISIBLE;
            for(View view : views){
                view.setVisibility(changeVisibilityTo);
                view.setEnabled(enableViews);
            }
            regLoadingBar.setVisibility(View.VISIBLE);
        }
        else{
            changeVisibilityTo = View.VISIBLE;
            for(View view : views){
                view.setVisibility(changeVisibilityTo);
                view.setEnabled(enableViews);
            }
            regLoadingBar.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Load all view to @var ArrayList<View> views;
     */
    private void loadAllViewsToArrayList(){
        views = new ArrayList<View>();
        views.add(regEmailEdit);
        views.add(regLoginEdit);
        views.add(regPassEdit);
        views.add(regRegBut);
        views.add(regPassText);
        views.add(regEmailText);
        views.add(regLoginText);
        views.add(regLoginHint);
        views.add(regPassHint);
        views.add(regEmailHint);
        views.add(regTopicHint);
    }

    @Override
    public void onStop(){
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void onStart() {
        EventBus.getDefault().register(this);
        super.onStart();

    }


}
