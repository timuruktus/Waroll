package ru.timuruktus.waroll.View.Fragments.Reg2;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import ru.timuruktus.waroll.Presenter.MainActivity.ViewEvents.EChangeToolbarTitle;
import ru.timuruktus.waroll.R;

public class Reg2Fragment extends Fragment implements View.OnClickListener {

    private View rootView;
    private Bitmap localImg = null;
    private String urlToImage;
    private static final int REQUEST = 1;
    private ImageView userImage = null;
    private Button reg2ButChoose, reg2ButConfirm;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView =
                inflater.inflate(R.layout.reg_2_fragment, container, false);

        userImage = (ImageView) rootView.findViewById(R.id.userImage);
        reg2ButChoose = (Button) rootView.findViewById(R.id.reg2ButChoose);
        reg2ButConfirm = (Button) rootView.findViewById(R.id.reg2ButConfirm);
        reg2ButConfirm.setOnClickListener(this);
        reg2ButChoose.setOnClickListener(this);

        EventBus.getDefault().post(new EChangeToolbarTitle(this));

        return rootView;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.reg2ButChoose){
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(R.string.reg_2_message_text)
                    .setTitle(R.string.reg_2_message_title);
            builder.setPositiveButton(R.string.reg_2_message_link, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    localImg = null;
                    createURLLinkDialog();
                }
            });
            builder.setNegativeButton(R.string.reg_2_message_upload, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    urlToImage = null;
                    chooseIMGFromLocal();
                }
            });
            builder.create();
            builder.show();
        } else if(id == R.id.reg2ButConfirm){
            if((urlToImage == null || urlToImage.equals("")) && localImg == null){
                Toast.makeText(this.getActivity(), R.string.reg_2_image_error, Toast.LENGTH_SHORT).show();
            }else if((urlToImage == null || urlToImage.equals("")) && localImg != null){
                showLoading(true);
                loadImageToServer();
            }else if((localImg == null || localImg.equals("")) && urlToImage != null){
                // TODO Здесь отправляется ивент на создание аккаунте в Auth
            }
        }
    }

    /**
     * Creates dialog with choosing img from URL
     */
    public void createURLLinkDialog(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = getActivity().getLayoutInflater();

        final View view = inflater.inflate(R.layout.dialog_url, null);
        builder.setView(view)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditText et = (EditText) view.findViewById(R.id.push_url_link);
                        urlToImage = et.getText().toString();
                        if(urlToImage.equals("")) return;
                        Picasso.with(rootView.getContext()).load(urlToImage).into(userImage);
                    }
                });
        builder.create();
        builder.show();
    }

    /**
     * Create dialog with choosing image from
     * Local Device
     */
    public void chooseIMGFromLocal(){
        Intent i = new Intent(Intent.ACTION_PICK);
        i.setType("image/*");
        startActivityForResult(i, REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        localImg = null;
        if(resultCode != -1){
            Toast.makeText(rootView.getContext(),R.string.reg_2_dialog_didnt_choose_img,Toast.LENGTH_SHORT).show();
        }
        if (requestCode == REQUEST && resultCode == -1) {
            Uri selectedImage = data.getData();
            try {
                localImg = MediaStore.Images.Media.getBitmap(rootView.getContext().getContentResolver(), selectedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
            userImage.setImageBitmap(localImg);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * Show loading progress bar
     * @param start -start or not
     */
    public void showLoading(boolean start){
        if(start){
            // TODO Спрятывание всех элементов и открытие progressBar'а
        }
    }

    /**
     * Upload image from Bitmap localImage to Firebase server
     */
    public void loadImageToServer(){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        localImg.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] dataBAOS = baos.toByteArray();

        /***************** UPLOADS THE PIC TO FIREBASE*****************/
        StorageReference storageRef = FirebaseStorage.getInstance().
                getReferenceFromUrl("gs://waroll-48897.appspot.com/userImages/");
        StorageReference imagesRef = storageRef.child("1");

        UploadTask uploadTask = imagesRef.putBytes(dataBAOS);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(rootView.getContext(),R.string.error,Toast.LENGTH_SHORT).show();
                showLoading(false);
            }

        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                urlToImage = taskSnapshot.getDownloadUrl().toString();
                // TODO Здесь отправляется ивент на создание аккаунте в Auth
                showLoading(false);
            }
        });
    }

    @Override
    public void onStop(){
        //EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void onStart() {
        //EventBus.getDefault().register(this);
        super.onStart();

    }


}
