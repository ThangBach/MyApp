package com.example.bqt.myapp.Presenter.Login.SignIn;

import android.os.Bundle;

import com.example.bqt.myapp.Model.Login.ModelSignIn;
import com.example.bqt.myapp.View.Home.View.IViewSignIn;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by BQT on 6/5/2017.
 */

public class PresenterSignIn implements IPresenterSignIn {

    private IViewSignIn myViewSignIn;
    private String dataJson = "";
    private GraphRequest graphRequest;
    private AccessToken accessToken;
    private ModelSignIn modelSignIn;

    public PresenterSignIn(IViewSignIn myViewSignIn) {
        this.myViewSignIn = myViewSignIn;
    }

    @Override
    public String getUserNameFB() {
        modelSignIn = new ModelSignIn();
        accessToken = modelSignIn.getAccessToken();
        graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    dataJson = object.getString("name");
                    myViewSignIn.showUserNameFB("FB",dataJson);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putString("fields", "name");
                    graphRequest.setParameters(bundle);
                    graphRequest.executeAsync();
                    Thread.sleep(1000);
                } catch (Exception e) {

                } finally {
                }
            }
        });
        thread.start();

        return dataJson;
    }

    @Override
    public AccessToken getTokenFB() {
        modelSignIn = new ModelSignIn();
        return modelSignIn.getAccessToken();
    }

    @Override
    public String destroyToken() {
        modelSignIn.DestroyAccessToken();
        return "";
    }

    @Override
    public GoogleSignInResult getResultGoogle(GoogleApiClient googleApiClient) {
        modelSignIn=new ModelSignIn();
        myViewSignIn.showUserNameFB("GG","");
        return modelSignIn.getInfoGoogle(googleApiClient);
    }
}
