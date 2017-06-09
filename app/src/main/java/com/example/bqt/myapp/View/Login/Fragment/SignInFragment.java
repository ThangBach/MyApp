package com.example.bqt.myapp.View.Login.Fragment;


import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.bqt.myapp.Model.Login.ModelSignIn;
import com.example.bqt.myapp.R;
import com.example.bqt.myapp.View.Home.HomeActivity;
import com.example.bqt.myapp.View.Login.LoginActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends Fragment implements View.OnClickListener,GoogleApiClient.OnConnectionFailedListener {

    Button btnLoginFB,btnLoginGG;
    CallbackManager callbackManager;
    GoogleApiClient googleApiClient;
    ModelSignIn modelSignIn;

    private static final int RC_SIGN_IN = 9001;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        FacebookSdk.sdkInitialize(getContext().getApplicationContext());
        modelSignIn=new ModelSignIn();
        googleApiClient=modelSignIn.getGoogleApiClient(getContext(),this);


        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                navigateToHome();
                Toast.makeText(getActivity(), "onSuccess", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getActivity(), "onCancel", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getActivity(), "onError", Toast.LENGTH_SHORT).show();
            }
        });

        init(view);
        btnLoginFB.setOnClickListener(this);
        btnLoginGG.setOnClickListener(this);
        return view;
    }

    private void init(View view){
        btnLoginFB = (Button) view.findViewById(R.id.btnDangNhapFacebook);
        btnLoginGG= (Button) view.findViewById(R.id.btnDangNhapGoogle);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnDangNhapFacebook:{
                LoginManager.getInstance().logInWithReadPermissions(SignInFragment.this, Arrays.asList("public_profile"));
                break;
            }
            case R.id.btnDangNhapGoogle:{
                Intent intent=Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent,RC_SIGN_IN);
                break;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            Log.d("GG",result.getSignInAccount().getDisplayName());
            if(result.isSuccess()){
                navigateToHome();
            }
        }
    }

    private void navigateToHome() {
        Intent intent = new Intent(getActivity(), HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
