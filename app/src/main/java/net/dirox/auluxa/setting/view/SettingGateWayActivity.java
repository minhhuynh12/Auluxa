package net.dirox.auluxa.setting.view;

import android.support.v4.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import net.dirox.auluxa.R;
import net.dirox.auluxa.common.activity.BaseActivity;

/**
 * Created by trungnq on 14/06/2017.
 */

public class SettingGateWayActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_gateway);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new FragmentGatewaySetting())
                .commit();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
