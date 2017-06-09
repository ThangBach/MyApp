package com.example.bqt.myapp.View.Login;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.bqt.myapp.R;
import com.example.bqt.myapp.View.Home.Fragment.DienTuFragment;
import com.example.bqt.myapp.View.Home.Fragment.KhuyenMaiFragment;
import com.example.bqt.myapp.View.Home.Fragment.LamDepFragment;
import com.example.bqt.myapp.View.Home.Fragment.MeBeFragment;
import com.example.bqt.myapp.View.Home.Fragment.NhaCuaDoiSongFragment;
import com.example.bqt.myapp.View.Home.Fragment.TheThaoDuLichFragment;
import com.example.bqt.myapp.View.Home.Fragment.ThoiTrangFragment;
import com.example.bqt.myapp.View.Home.HomeActivity;
import com.example.bqt.myapp.View.Login.Fragment.SignInFragment;
import com.example.bqt.myapp.View.Login.Fragment.SignUpFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager viewPager;
    private SmartTabLayout viewPagerTab;
    private Toolbar toolbar;
    private ImageView img_btn_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        init();

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add(R.string.fragment_dangnhap, SignInFragment.class)
                .add(R.string.fragment_dangky, SignUpFragment.class)
                .create());

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        viewPager.setAdapter(adapter);
        viewPagerTab.setViewPager(viewPager);

        img_btn_close.setOnClickListener(this);
    }

    private void init() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        toolbar = (Toolbar) findViewById(R.id.toolBarDangNhap);
        img_btn_close = (ImageView) findViewById(R.id.login_img_btn_close);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_img_btn_close: {
                navigateToHome();
                break;
            }
        }
    }

    private void navigateToHome() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}
