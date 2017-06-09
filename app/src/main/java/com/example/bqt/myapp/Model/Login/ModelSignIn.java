package com.example.bqt.myapp.Model.Login;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by BQT on 6/5/2017.
 */

public class ModelSignIn {
    private AccessToken accessToken;
    private AccessTokenTracker accessTokenTracker;
    private GoogleSignInOptions googleSignInOptions;
    private GoogleApiClient googleApiClient;

    public AccessToken getAccessToken() {
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                accessToken = currentAccessToken;
            }
        };
        accessToken = AccessToken.getCurrentAccessToken();
        return accessToken;
    }

    public GoogleApiClient getGoogleApiClient(Context context, GoogleApiClient.OnConnectionFailedListener failedListener){
        googleSignInOptions=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient=new GoogleApiClient.Builder(context)
                .enableAutoManage((AppCompatActivity)context,failedListener )
                .addApi(Auth.GOOGLE_SIGN_IN_API,googleSignInOptions)
                .build();

        return  googleApiClient;
    }

    public GoogleSignInResult getInfoGoogle(GoogleApiClient googleApiClient){
        OptionalPendingResult<GoogleSignInResult> opr=Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if(opr.isDone()){
            return  opr.get();
        }
        return null;
    }

    public void DestroyAccessToken() {
        accessTokenTracker.stopTracking();
    }
}
