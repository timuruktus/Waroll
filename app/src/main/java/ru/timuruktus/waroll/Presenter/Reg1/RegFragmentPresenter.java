package ru.timuruktus.waroll.Presenter.Reg1;


import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;

import ru.timuruktus.waroll.Model.Events.EPrepareRegister;
import ru.timuruktus.waroll.Model.Events.EStartRegister;
import ru.timuruktus.waroll.Presenter.MainActivity.ViewEvents.EReplaceFragment;
import ru.timuruktus.waroll.Presenter.Reg1.ViewEvents.EOnRegFragClick;
import ru.timuruktus.waroll.View.Fragments.Reg1.RegDataError;
import ru.timuruktus.waroll.View.Fragments.Reg1.RegLoadingEvent;
import ru.timuruktus.waroll.View.Fragments.Reg2.Reg2Fragment;
import ru.timuruktus.waroll.View.MainActivity;

public class RegFragmentPresenter {


    private MainActivity mainActivity;
    private DatabaseReference mDatabase;
    public HashMap<String, String> regData;

    public RegFragmentPresenter(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        EventBus.getDefault().register(this);
    }

    /**
     * Triggers if some button was clicked in RegFragment
     * @param eOnRegFragClick
     */
    @Subscribe
    public void onRegFragClick(EOnRegFragClick eOnRegFragClick){
        if(eOnRegFragClick.action == EOnRegFragClick.RegActions.REG){
            EventBus.getDefault().post(new RegLoadingEvent(true));
            regData = eOnRegFragClick.regData;
            if(validate()) {
                validateLogin(eOnRegFragClick.regData.get("login"));
            }
        }
    }

    /**
     * Check the regData validation
     * @return- false if:
     * 1)some empty fields in regData
     * 2)email doesn't contains "@" and "."
     * 3)pass has less than 6 characters
     */
    private boolean validate(){
        if(regData.containsValue("") || regData.containsValue(null)){
            postAnError(RegDataError.RegWrong.EMPTY_FIELD);
            return false;
        }
        if(!regData.get("email").contains("@")){
            postAnError(RegDataError.RegWrong.EMAIL);
            return false;
        }
        if(!regData.get("email").contains(".")){
            postAnError(RegDataError.RegWrong.EMAIL);
            return false;
        }
        if(regData.get("pass").length() < 6){
            postAnError(RegDataError.RegWrong.PASS);
            return false;
        }
        return true;
    }

    /**
     * Posts an error if:
     * 1) Login is empty
     * 2) Login already exists
     * If there's no errors:
     * 1) Prepares register (send Registration data "regData" to Auth)
     * 2) Disabling loading bar in RegFragment
     * 3) Replacing fragment to next step of registration
     * @param login- user login
     */
    private void validateLogin(String login){
        final String _login = login;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Query checkLoginExists = mDatabase.child("Accounts").equalTo(_login, "Username");
        checkLoginExists.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists()) {
                        EventBus.getDefault().post(new EPrepareRegister(regData));
                        EventBus.getDefault().post(new RegLoadingEvent(false));
                        EventBus.getDefault().post(new EReplaceFragment(new Reg2Fragment(), true));
                }else{
                    postAnError(RegDataError.RegWrong.LOGIN_EXISTS);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("RegFragmentPresenter", "loadPost:onCancelled", databaseError.toException());
                postAnError(RegDataError.RegWrong.OTHER);
            }
        });
    }

    /**
     * Send an error event to RegFragment
     * Disabling loading bar in RegFragment
     * @param error
     */
    private void postAnError(RegDataError.RegWrong error){
        EventBus.getDefault().post(new RegDataError(error));
        EventBus.getDefault().post(new RegLoadingEvent(false));
    }

}
