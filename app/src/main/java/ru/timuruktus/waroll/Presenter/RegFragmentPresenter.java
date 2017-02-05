package ru.timuruktus.waroll.Presenter;


import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;

import ru.timuruktus.waroll.Events.OnRegFragClick;
import ru.timuruktus.waroll.View.Fragments.Reg.RegDataError;
import ru.timuruktus.waroll.View.Fragments.Reg.RegLoadingEvent;
import ru.timuruktus.waroll.View.MainActivity;

public class RegFragmentPresenter {


    private MainActivity mainActivity;
    private DatabaseReference mDatabase;
    public HashMap<String, String> regData;

    public RegFragmentPresenter(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void onRegFragClick(OnRegFragClick onRegFragClick){
        if(onRegFragClick.action == OnRegFragClick.RegActions.REG){
            EventBus.getDefault().post(new RegLoadingEvent(true));
            regData = onRegFragClick.regData;
            validateLogin(onRegFragClick.regData.get("Login"));
        }
    }

    private boolean validate(){
        if(regData.containsValue("") || regData.containsValue(null)){
            postAnError(RegDataError.RegWrong.EMPTY_FIELD);
            return false;
        }
        if(!regData.get("Email").contains("@")){
            postAnError(RegDataError.RegWrong.EMAIL);
            return false;
        }
        if(!regData.get("Email").contains(".")){
            postAnError(RegDataError.RegWrong.EMAIL);
            return false;
        }
        if(regData.get("Pass").length() < 6){
            postAnError(RegDataError.RegWrong.PASS);
            return false;
        }
        return true;
    }

    private void validateLogin(String login){
        if(login == "" || login == null){
            postAnError(RegDataError.RegWrong.LOGIN);
            return;
        }
        final String _login = login;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Usernames").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<ArrayList<String>> temp = new GenericTypeIndicator<ArrayList<String>>(){};
                ArrayList<String> usernames = dataSnapshot.getValue(temp);
                for(String currentUsername : usernames){
                    Log.d("tag", "RegFragmentPresenter: onDataChange. Username = " + currentUsername);
                    if (_login.equals(currentUsername)) {
                        postAnError(RegDataError.RegWrong.LOGIN_EXISTS);
                        return;
                    }
                }
                if(validate()){
                    register();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("RegFragmentPresenter", "loadPost:onCancelled", databaseError.toException());
                postAnError(RegDataError.RegWrong.OTHER);
                return;
            }

        });
    }

    private void register(){

    }

    private void postAnError(RegDataError.RegWrong error){
        EventBus.getDefault().post(new RegDataError(error));
        EventBus.getDefault().post(new RegLoadingEvent(false));
    }

}
