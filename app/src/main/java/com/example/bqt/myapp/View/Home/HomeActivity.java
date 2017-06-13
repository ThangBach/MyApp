package com.example.bqt.myapp.View.Home;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.arlib.floatingsearchview.FloatingSearchView;
import com.example.bqt.myapp.Adapter.ExpandAdapter;
import com.example.bqt.myapp.Model.DienTu.ModelDienTu;
import com.example.bqt.myapp.Model.Login.ModelSignIn;
import com.example.bqt.myapp.Model.ObjectClass.ProductCategory;
import com.example.bqt.myapp.Parse.JSONParser;
import com.example.bqt.myapp.Presenter.DienTu.PresenterDientu;
import com.example.bqt.myapp.Presenter.Home.Menu.PresenterLogicHandleMenu;
import com.example.bqt.myapp.Presenter.Login.SignIn.PresenterSignIn;
import com.example.bqt.myapp.R;
import com.example.bqt.myapp.View.Home.Fragment.DienTuFragment;
import com.example.bqt.myapp.View.Home.Fragment.KhuyenMaiFragment;
import com.example.bqt.myapp.View.Home.Fragment.LamDepFragment;
import com.example.bqt.myapp.View.Home.Fragment.MeBeFragment;
import com.example.bqt.myapp.View.Home.Fragment.NhaCuaDoiSongFragment;
import com.example.bqt.myapp.View.Home.Fragment.TheThaoDuLichFragment;
import com.example.bqt.myapp.View.Home.Fragment.ThoiTrangFragment;
import com.example.bqt.myapp.View.Home.View.IViewHandleMenu;
import com.example.bqt.myapp.View.Home.View.IViewSignIn;
import com.example.bqt.myapp.View.Login.LoginActivity;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.nightonke.boommenu.BoomButtons.BoomButton;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.OnBoomListener;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class HomeActivity extends AppCompatActivity implements OnBoomListener, IViewHandleMenu, IViewSignIn, GoogleApiClient.OnConnectionFailedListener {

    private android.support.v7.widget.Toolbar toolbar;
    private BoomMenuButton bmb;
    private ViewPager viewPager;
    private SmartTabLayout viewPagerTab;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private ExpandableListView expandableListView;
    //    private FloatingSearchView floatingSearchView;
    private PresenterLogicHandleMenu handleMenu;
    private PresenterSignIn presenterSignIn;
    private GoogleApiClient googleApiClient;
    private GoogleSignInResult googleSignInResult;
    private ModelSignIn modelSignIn;
    CollapsingToolbarLayout collapsingToolbarLayout;
    AppBarLayout appBarLayout;
    private String userNameFB = "";
    private String keyLogin="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_home);
        init();
        handleMenu = new PresenterLogicHandleMenu(this);
        modelSignIn=new ModelSignIn();
        presenterSignIn = new PresenterSignIn(this);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add(R.string.fragment_khuyenmai, KhuyenMaiFragment.class)
                .add(R.string.fragment_dientu, DienTuFragment.class)
                .add(R.string.fragment_thoitrang, ThoiTrangFragment.class)
                .add(R.string.fragment_nhacua_doi_song, NhaCuaDoiSongFragment.class)
                .add(R.string.fragment_mebe, MeBeFragment.class)
                .add(R.string.fragment_lamdep, LamDepFragment.class)
                .add(R.string.fragment_thethao_dulich, TheThaoDuLichFragment.class)
                .create());
        viewPager.setAdapter(adapter);
        viewPagerTab.setViewPager(viewPager);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle.syncState();

        handleMenu.getMenuList();
        if (presenterSignIn.getTokenFB() != null) {
            presenterSignIn.getUserNameFB();
        }
        googleApiClient=modelSignIn.getGoogleApiClient(this,this);
        if(googleApiClient!=null){
            googleSignInResult=presenterSignIn.getResultGoogle(googleApiClient);
        }

        initBoomMenu();
        bmb.setOnBoomListener(this);
    }

    private void init() {
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.homeToolbar);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        expandableListView = (ExpandableListView) findViewById(R.id.expMenu);
        bmb = (BoomMenuButton) findViewById(R.id.bmb);

    }

    private void initBoomMenu() {
        assert bmb != null;
        bmb.setButtonEnum(ButtonEnum.Ham);
        for (int i = 0; i < MenuItems().size(); i++) {
            bmb.addBuilder(MenuItems().get(i));
        }
    }
    private List<HamButton.Builder> MenuItems() {
        List<HamButton.Builder> menus = new ArrayList<>();

        if (userNameFB.isEmpty()) {
            HamButton.Builder login = new HamButton.Builder()
                    .normalImageRes(R.drawable.user)
                    .normalTextRes(R.string.item_login)
                    .normalTextColor(Color.BLACK)
                    .textSize(20)
                    .textGravity(Gravity.CENTER)
                    .imagePadding(new Rect(20, 20, 20, 20))
                    .normalColorRes(R.color.it_White);
            menus.add(login);
        } else {
            HamButton.Builder login = new HamButton.Builder()
                    .normalImageRes(R.drawable.user)
                    .normalText("Hi! " + userNameFB)
                    .normalTextColor(Color.BLACK)
                    .textSize(20)
                    .textGravity(Gravity.CENTER)
                    .imagePadding(new Rect(20, 20, 20, 20))
                    .normalColorRes(R.color.it_White);
            menus.add(login);
        }

        HamButton.Builder notify = new HamButton.Builder()
                .normalImageRes(R.drawable.notifi)
                .normalTextRes(R.string.item_notify)
                .normalTextColor(Color.BLACK)
                .textSize(20)
                .textGravity(Gravity.CENTER)
                .imagePadding(new Rect(20, 20, 20, 20))
                .normalColorRes(R.color.it_White);
        menus.add(notify);

        HamButton.Builder like = new HamButton.Builder()
                .normalImageRes(R.drawable.like)
                .normalTextRes(R.string.item_like)
                .normalTextColor(Color.BLACK)
                .textSize(20)
                .textGravity(Gravity.CENTER)
                .imagePadding(new Rect(20, 20, 20, 20))
                .normalColorRes(R.color.it_White);
        menus.add(like);

        HamButton.Builder order = new HamButton.Builder()
                .normalImageRes(R.drawable.order)
                .normalTextRes(R.string.item_order)
                .normalTextColor(Color.BLACK)
                .textSize(20)
                .textGravity(Gravity.CENTER)
                .imagePadding(new Rect(20, 20, 20, 20))
                .normalColorRes(R.color.it_White);
        menus.add(order);

        HamButton.Builder setting = new HamButton.Builder()
                .normalImageRes(R.drawable.setting)
                .normalTextRes(R.string.item_setting)
                .normalTextColor(Color.BLACK)
                .textSize(20)
                .textGravity(Gravity.CENTER)
                .imagePadding(new Rect(20, 20, 20, 20))
                .normalColorRes(R.color.it_White);
        menus.add(setting);

        if (!userNameFB.isEmpty()) {
            HamButton.Builder logout = new HamButton.Builder()
                    .normalImageRes(R.drawable.ic_logout)
                    .normalTextRes(R.string.item_logout)
                    .normalTextColor(Color.BLACK)
                    .textSize(20)
                    .textGravity(Gravity.CENTER)
                    .imagePadding(new Rect(20, 20, 20, 20))
                    .normalColorRes(R.color.it_White);
            menus.add(logout);
        }

        return menus;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutrangchu, menu);
        if(googleApiClient!=null){
            googleSignInResult=presenterSignIn.getResultGoogle(googleApiClient);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClicked(int index, BoomButton boomButton) {
        switch (index) {
            case 0: {
                if (userNameFB.isEmpty()) {
                    Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                break;
            }
            case 5: {
                Toast.makeText(this, "Đăng xuất", Toast.LENGTH_SHORT).show();
                if(presenterSignIn.getTokenFB()!=null){
                    LoginManager.getInstance().logOut();
//                    userNameFB="";
//                    bmb.clearBuilders();
//                    initBoomMenu();
                }
                if(googleSignInResult!=null){
                    Auth.GoogleSignInApi.signOut(googleApiClient);
//                    userNameFB="";
//                    bmb.clearBuilders();
//                    initBoomMenu();
                }
            }
        }
    }

    @Override
    public void onBackgroundClick() {

    }

    @Override
    public void onBoomWillHide() {

    }

    @Override
    public void onBoomDidHide() {

    }

    @Override
    public void onBoomWillShow() {

    }

    @Override
    public void onBoomDidShow() {
    }

    @Override
    public void ShowMenuList(List<ProductCategory> categories) {
        ExpandAdapter expandAdapter = new ExpandAdapter(this, categories);
        expandableListView.setAdapter(expandAdapter);
        expandAdapter.notifyDataSetChanged();
    }

    @Override
    public void showUserNameFB(String key,String data) {
        keyLogin=key;
        switch (key){
            case "FB":{
                userNameFB = data;
                bmb.clearBuilders();
                bmb.setButtonPlaceEnum(ButtonPlaceEnum.HAM_6);
                bmb.setPiecePlaceEnum(PiecePlaceEnum.HAM_6);
                initBoomMenu();
                break;
            }
            case "GG":{

                if(googleSignInResult!=null){
                    userNameFB=googleSignInResult.getSignInAccount().getDisplayName();
                    bmb.clearBuilders();
                    bmb.setButtonPlaceEnum(ButtonPlaceEnum.HAM_6);
                    bmb.setPiecePlaceEnum(PiecePlaceEnum.HAM_6);
                    initBoomMenu();
                }
                break;
            }
            default:{
                bmb.clearBuilders();
                bmb.setButtonPlaceEnum(ButtonPlaceEnum.HAM_5);
                bmb.setPiecePlaceEnum(PiecePlaceEnum.HAM_5);
                initBoomMenu();
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
