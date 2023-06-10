package com.example.hotelmanagementsystem.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hotelmanagementsystem.Api.APIInterface;
import com.example.hotelmanagementsystem.Api.RetrofitApi;
import com.example.hotelmanagementsystem.Model.RegistrationModel;
import com.example.hotelmanagementsystem.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.myhexaville.smartimagepicker.ImagePicker;
import com.myhexaville.smartimagepicker.OnImagePickedListener;
import com.squareup.picasso.Picasso;
import com.tuyenmonkey.mkloader.MKLoader;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {

    EditText et_name, et_phone_no, et_email_id, et_password, et_confirm_password;
    TextView tv_login;
    AppCompatButton btn_register;
    CircleImageView ci_profile_image;

    ImagePicker imagePicker;

    MKLoader sharingan,fishspinner;

    int PERMISSION_ALL = 10;

    String user_id;
    int image;

    StorageReference storageReference;
    FirebaseAuth auth;

    SharedPreferences sharedPrefrences;
    SharedPreferences.Editor editor;

    APIInterface api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        sharingan = findViewById(R.id.sharingan);
        fishspinner = findViewById(R.id.fishspinner);

        ci_profile_image = findViewById(R.id.ci_profile_image);
        et_name = findViewById(R.id.et_name);
        et_phone_no = findViewById(R.id.et_phone_no);
        et_email_id = findViewById(R.id.et_email_id);
        et_password = findViewById(R.id.et_password);
        et_confirm_password = findViewById(R.id.et_confirm_password);
        btn_register = findViewById(R.id.btn_register);
        tv_login = findViewById(R.id.tv_login);

        sharedPrefrences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPrefrences.edit();

        String[] PERMISSIONS = new String[]{

                Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.CAMERA
        };

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(RegistrationActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        ci_profile_image.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!hasPermissions(RegistrationActivity.this, PERMISSIONS)) {

                    ActivityCompat.requestPermissions(RegistrationActivity.this, PERMISSIONS, PERMISSION_ALL);

                }else{

                    imagePicker.choosePicture(true);

                }

            }
        });

        imagePicker = new ImagePicker( this, null, new OnImagePickedListener() {
            @Override
            public void onImagePicked(Uri imageUri) {

                UCrop.of(imageUri, getTempUri())
                        .withAspectRatio(1, 1)
                        .start(RegistrationActivity.this);
            }
        });


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = et_name.getText().toString();
                String phone_no = et_phone_no.getText().toString();
                String email_id = et_email_id.getText().toString();
                String password = et_password.getText().toString();
                String confirm_password = et_confirm_password.getText().toString();

               if(name.equals("")){

                    Toast.makeText(RegistrationActivity.this, "Please Enter Your Name", Toast.LENGTH_SHORT).show();

                }else if(phone_no.equals("")){

                    Toast.makeText(RegistrationActivity.this, "Please Enter Your Phone Number", Toast.LENGTH_SHORT).show();

                }else if(email_id.equals("")){

                    Toast.makeText(RegistrationActivity.this, "Please Enter Your Email Id", Toast.LENGTH_SHORT).show();

                } else if(!email_id.contains(".com")|| !email_id.contains("@")) {

                    Toast.makeText(RegistrationActivity.this, "Please Enter Valid Email Id", Toast.LENGTH_SHORT).show();

                } else if(password.equals("")){

                    Toast.makeText(RegistrationActivity.this, "Please Enter Your Password", Toast.LENGTH_SHORT).show();

                }else if(confirm_password.equals("")){

                    Toast.makeText(RegistrationActivity.this, "Please Enter Your Confirm Password", Toast.LENGTH_SHORT).show();

                }else if(!password.equals(confirm_password)){

                    Toast.makeText(RegistrationActivity.this, "Password Do Not Match", Toast.LENGTH_SHORT).show();

                }else {

                    sharingan.setVisibility(View.VISIBLE);

                    registration_data(name,phone_no,email_id,password);
                }

            }
        });

    }

    private Uri getTempUri(){

        String dir = Environment.getExternalStorageDirectory() + File.separator + "Temp";
        File dirFile = new File(dir);
        dirFile.mkdir();

        String file = dir + File.separator + "temp.jpg";
        File tempFile = new File(file);
        try {

            tempFile.createNewFile();

        } catch (Exception e) {

           System.out.println(e);
        }

        return Uri.fromFile(tempFile);
    }

    public static boolean hasPermissions(Context context, String... permissions){

        if(context != null && permissions != null){

            for(String permission : permissions){

                if(ActivityCompat.checkSelfPermission(context,permission) != PackageManager.PERMISSION_GRANTED){
                    return false;
                }
            }
        }

        return true;
    }

    public void onRequestPermissionsResult(int requestCode, String permissionsList[], int[] grantResults) {

        super.onRequestPermissionsResult( requestCode, permissionsList, grantResults );
        imagePicker.handlePermission(requestCode,grantResults);

        switch (requestCode) {

            case 1:

                if (grantResults.length > 0) {
                    boolean flag = true;

                    for (int i = 0; i < permissionsList.length; i++) {

                        if (grantResults[i] == PackageManager.PERMISSION_DENIED) {

                            flag = false;
                        }
                    }
                    if(flag){

                        imagePicker.choosePicture(true);
                    }

                return;
            }
        }
    }

    protected void onActivityResult(int requestCode,int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        imagePicker.handleActivityResult(resultCode,requestCode,data);

        if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            final Uri resultUri = UCrop.getOutput(data);

            upload(resultUri);

        } else if (resultCode == UCrop.RESULT_ERROR) {
            final Throwable cropError = UCrop.getError(data);
        }
    }

    void upload(Uri uri){

        fishspinner.setVisibility(View.VISIBLE);

        StorageReference riversRef = FirebaseStorage.getInstance().getReference()
                .child("Temp" + System.currentTimeMillis() + ".jpg");

        riversRef.putFile(uri).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

                Toast.makeText( RegistrationActivity.this, "Failed"+exception.getMessage(), Toast.LENGTH_SHORT ).show();

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                riversRef.getDownloadUrl()
                        .addOnSuccessListener( new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        fishspinner.setVisibility( View.GONE );

                        ci_profile_image.setImageURI( null );
                        Picasso.get().load( uri ).into( ci_profile_image );

                    }
                });
            }
        });
    }



    private void registration_data(String name, String phone, String email, String password) {

        api = RetrofitApi.getClient(this).create(APIInterface.class);
        Call<RegistrationModel> registrationModel = api.GetRegistration(""+name,""+phone,""+email,""+password);
        registrationModel.enqueue(new Callback<RegistrationModel>() {
            @Override
            public void onResponse(Call<RegistrationModel> call, Response<RegistrationModel> response) {

                if (response.body().getHotelRegistrationModel().get(0).getSuccess().equals("1")) {

                    sharingan.setVisibility(View.GONE);

                    user_id = response.body().getHotelRegistrationModel().get(0).getUserId();
                    editor.putString("user_id",user_id);

                    String name = et_name.getText().toString();
                    editor.putString("name",name);

                    String phone_no = et_phone_no.getText().toString();
                    editor.putString("phone",phone_no);

                    String email_id = et_email_id.getText().toString();
                    editor.putString("email",email_id);

                    editor.commit();

                    Toast.makeText(RegistrationActivity.this, "Registration SuccessFully", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(RegistrationActivity.this, NavigationActivity.class);
                    startActivity(intent);
                    finish();

                } else {

                    sharingan.setVisibility(View.GONE);

                    Toast.makeText(RegistrationActivity.this, "Email Id Already Used", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegistrationModel> call, Throwable t) {

                Toast.makeText(RegistrationActivity.this, "Please Enter Valid Data", Toast.LENGTH_SHORT).show();
            }
        });

    }
}