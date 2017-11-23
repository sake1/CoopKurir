package com.coopmart.kurir.coopkurir.Activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.coopmart.kurir.coopkurir.Method.Navigator;
import com.coopmart.kurir.coopkurir.R;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.io.FileNotFoundException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileEditActivity extends AppCompatActivity {

    public static final String TAG = "Edit Profil";

    @BindView(R.id.ape_output_profile_picture) ImageView profilePic;
    @BindView(R.id.ape_input_name) EditText name;
    @BindView(R.id.ape_input_phone) EditText phone;
    @BindView(R.id.ape_trigger_submit) Button submit;

    @OnClick(R.id.ape_trigger_upload_picture)
    public void updatePicture() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, Navigator.CODE_REQUEST_EXTERNAL_STORAGE);
                return;
            }
        }
        pickImageFromGallery();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == Navigator.CODE_REQUEST_EXTERNAL_STORAGE) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                pickImageFromGallery();
            }
        }
    }

    private void pickImageFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, 1212);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Navigator.FROM_GALLERY) {
            if(resultCode == RESULT_OK) {
                try {
                    Uri imageUri = data.getData();
                    InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    Bitmap image = BitmapFactory.decodeStream(imageStream);
                    Log.d(getClass().toString(), "width: " + image.getWidth() + ", height: " + image.getHeight());
                    profilePic.setImageBitmap(image);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(ProfileEditActivity.this, "Fail to retrieve image data", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @OnClick(R.id.ape_trigger_submit)
    public void submit() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSubmitFailed();
            return;
        }

        submit.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(ProfileEditActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Mohon Tunggu");
        progressDialog.show();

        String fullname = name.getText().toString();
        String phone = this.phone.getText().toString();

        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSubmitFailed
                        // depending on success
                        onSignupSuccess();
                        // onSubmitFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        getSupportActionBar().setTitle(TAG);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void onSignupSuccess() {
        submit.setEnabled(true);

        Intent data = new Intent();
        data.putExtra("fullname", name.getText().toString());
        data.putExtra("phone", phone.getText().toString());

        setResult(RESULT_OK, data);
        finish();
    }

    public void onSubmitFailed() {
        Toast.makeText(getBaseContext(), "Gagal merubah profil", Toast.LENGTH_LONG).show();

        submit.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String fullname = name.getText().toString();
        String phone = this.phone.getText().toString();

        if (fullname.isEmpty() || fullname.length() < 3) {
            name.setError("at least 3 characters");
            valid = false;
        } else {
            name.setError(null);
        }

        if (phone.isEmpty() || phone.length() != 10) {
            this.phone.setError("Enter Valid Mobile Number");
            valid = false;
        } else {
            this.phone.setError(null);
        }

        return valid;
    }
}
