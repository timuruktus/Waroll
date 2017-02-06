package ru.timuruktus.waroll.Model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import ru.timuruktus.waroll.Model.Events.EPrepareRegister;
import ru.timuruktus.waroll.Model.Events.EStartRegister;

public class Auth {

    private String login,pass,email;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    public Auth(){
        EventBus.getDefault().register(this);
    }

    /**
     * initialise login, pass and email
     * @param ePrepareRegister
     */
    @Subscribe
    public void prepareRegister(EPrepareRegister ePrepareRegister){
        Log.d("tag", "getting data");
        this.login = ePrepareRegister.regData.get("login");
        this.pass = ePrepareRegister.regData.get("pass");
        this.email = ePrepareRegister.regData.get("email");
    }

    @Subscribe
    public void register(EStartRegister estartRegister){
        // TODO доделать
        mAuth = FirebaseAuth.getInstance();
        createAccount();
    }

    /**
     * Creates account and sends email verification
     */
    private void createAccount() {

        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("tag", "createUserWithEmail:onComplete:" + task.isSuccessful());

                        if (!task.isSuccessful()) {
                            Log.e("tag","Auth.createAccount!task.isSuccessful()");
                            return;
                        }

                        FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Log.d("tag", "Email sent.");
                                        }
                                    }
                                });
                        writeNewUser();
                    }
                });
    }

    /**
     * Writes all data to Firebase DB
     */
    private void writeNewUser() {
        UserAccount user = new UserAccount();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getEmail()).setValue(user);
        mDatabase.child("Logins").child(FirebaseAuth.getInstance().getCurrentUser().getEmail()).setValue(login);

    }



}
