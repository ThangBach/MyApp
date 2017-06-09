package com.example.bqt.myapp.Presenter.Login.SignIn;

import com.facebook.AccessToken;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by BQT on 6/5/2017.
 */

public interface IPresenterSignIn {
    String getUserNameFB();
    AccessToken getTokenFB();
    String destroyToken();
    GoogleSignInResult getResultGoogle(GoogleApiClient googleApiClient);
}
