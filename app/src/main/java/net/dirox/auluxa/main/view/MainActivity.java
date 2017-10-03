package net.dirox.auluxa.main.view;

import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.common.activity.BaseActivity;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.sample.ScheduleItem;
import net.dirox.auluxa.databinding.ActivityMainBinding;
import net.dirox.auluxa.energy.view.FragmentEnergyManager;
import net.dirox.auluxa.extras.Enumes;
import net.dirox.auluxa.fragments.room_centre.ClimateSchedulingDetailFragment;
import net.dirox.auluxa.fragments.room_centre.ClimateSchedulingFragment;
import net.dirox.auluxa.fragments.room_centre.ClimateSleepTimerFragment;
import net.dirox.auluxa.fragments.room_centre.RoomCenterEditTargetFragment;
import net.dirox.auluxa.fragments.room_centre.RoomCentreAudioCreateGroupFragment;
import net.dirox.auluxa.fragments.room_centre.RoomCentreAudioDevicesFragment;
import net.dirox.auluxa.fragments.room_centre.RoomCentreAudioFragment;
import net.dirox.auluxa.fragments.room_centre.RoomCentreClimateControlFragment;
import net.dirox.auluxa.fragments.room_centre.RoomCentreClimateFragment;
import net.dirox.auluxa.fragments.room_centre.RoomCentreClimateScheduleFragment;
import net.dirox.auluxa.fragments.room_centre.RoomCentreFragment;
import net.dirox.auluxa.fragments.room_centre.RoomCentreLightsFragment;
import net.dirox.auluxa.fragments.room_centre.RoomCentreOthersFragment;
import net.dirox.auluxa.fragments.room_centre.RoomCentreShadesFragment;
import net.dirox.auluxa.fragments.room_centre.RoomCentreVideoFragment;
import net.dirox.auluxa.fragments.scenes.CreateScenesLightFragment;
import net.dirox.auluxa.fragments.scenes.CreateScenesRenameFragment;
import net.dirox.auluxa.fragments.scenes.CreateScenesRoomClimateDetailFragment;
import net.dirox.auluxa.fragments.scenes.CreateScenesRoomDetailFragment;
import net.dirox.auluxa.fragments.scenes.CreateScenesRoomFragment;
import net.dirox.auluxa.fragments.scenes.CreateScenesRoomSecurityDetailFragment;
import net.dirox.auluxa.fragments.scenes.CreateScenesRoomVideoAddSequenceFragment;
import net.dirox.auluxa.fragments.scenes.CreateScenesRoomVideoListFragment;
import net.dirox.auluxa.fragments.scenes.CreateScenesRoomVideoListSequenceFragment;
import net.dirox.auluxa.fragments.scenes.CreateScenesScheduleFragment;
import net.dirox.auluxa.fragments.scenes.CreateScenesSequenceFragment;
import net.dirox.auluxa.fragments.scenes.CreateScenesShadesFragment;
import net.dirox.auluxa.fragments.scenes.CreateScenesStep1Fragment;
import net.dirox.auluxa.fragments.scenes.CreateScenesStep2Fragment;
import net.dirox.auluxa.fragments.schedules.SchedulingFragment;
import net.dirox.auluxa.login.view.LoginActivity;
import net.dirox.auluxa.main.MainActivityContract;
import net.dirox.auluxa.main.dagger.DaggerMainActivityComponent;
import net.dirox.auluxa.main.dagger.MainActivityComponent;
import net.dirox.auluxa.main.dagger.MainActivityModule;
import net.dirox.auluxa.notification.view.FragmentNotification;
import net.dirox.auluxa.security.view.FragemntSecurityAirbnb;
import net.dirox.auluxa.security.view.FragmentConnectToIGLooHome;
import net.dirox.auluxa.security.view.FragmentSecuirityActivityLog;
import net.dirox.auluxa.security.view.FragmentSecurity;
import net.dirox.auluxa.security.view.FragmentSecurityAssignedLockPin;
import net.dirox.auluxa.security.view.FragmentSecurityAssignedPin;
import net.dirox.auluxa.security.view.FragmentSecurityCreateDurationPin;
import net.dirox.auluxa.security.view.FragmentSecurityCreateLockPin;
import net.dirox.auluxa.security.view.FragmentSecurityCreateOneTimeOrPermanentPin;
import net.dirox.auluxa.security.view.FragmentSecurityDoorLock;
import net.dirox.auluxa.security.view.FragmentSecurityDurationPin;
import net.dirox.auluxa.security.view.FragmentSecurityLock;
import net.dirox.auluxa.security.view.FragmentSecurityLockEditName;
import net.dirox.auluxa.security.view.FragmentSecuritySurvelliance;
import net.dirox.auluxa.security.view.FragmentSecurityVideoPhone;
import net.dirox.auluxa.security.view.FragmentSecurityAddBluetooth;
import net.dirox.auluxa.security.view.FragmentSecurityPendingBluetoothKey;
import net.dirox.auluxa.setting.view.FragmentGatewaySetting;
import net.dirox.auluxa.setting.view.FragmentSetting;
import net.dirox.auluxa.setting.view.FragmentSettingClimate;
import net.dirox.auluxa.setting.view.FragmentSettingDateTime;
import net.dirox.auluxa.setting.view.FragmentSettingLanguage;
import net.dirox.auluxa.setting.view.FragmentSettingNotifications;
import net.dirox.auluxa.setting.view.FragmentSettingNotificationsEnergy;
import net.dirox.auluxa.setting.view.FragmentSettingNotificationsRooms;
import net.dirox.auluxa.setting.view.FragmentSettingRecentlyAdded;
import net.dirox.auluxa.setting.view.FragmentSettingRoomImage;
import net.dirox.auluxa.setting.view.FragmentSettingTimeZone;
import net.dirox.auluxa.setting.view.device_list.FragmentSettingAddDevices;
import net.dirox.auluxa.setting.view.device_list.FragmentSettingDeviceList;
import net.dirox.auluxa.setting.view.device_list.FragmentSettingThirdParty;
import net.dirox.auluxa.setting.view.device_list.audio.FragmentAudioDeviceConnect;
import net.dirox.auluxa.setting.view.device_list.audio.FragmentAudioDeviceGeneral;
import net.dirox.auluxa.setting.view.device_list.audio.FragmentAudioDeviceHdmi;
import net.dirox.auluxa.setting.view.device_list.audio.FragmentAudioDeviceList;
import net.dirox.auluxa.setting.view.device_list.audio.FragmentAudioDeviceMapping;
import net.dirox.auluxa.setting.view.device_list.audio.FragmentAudioDeviceSettings;
import net.dirox.auluxa.setting.view.device_list.climate.FragmentClimateDeviceGeneral;
import net.dirox.auluxa.setting.view.device_list.climate.FragmentClimateDeviceList;
import net.dirox.auluxa.setting.view.device_list.climate.FragmentClimateDeviceRoomsGeneral;
import net.dirox.auluxa.setting.view.device_list.climate.FragmentClimateDeviceThermostatPanel;
import net.dirox.auluxa.setting.view.device_list.climate.FragmentClimateSelectDeviceHumidity;
import net.dirox.auluxa.setting.view.device_list.climate.FragmentClimateSelectDeviceIrNode;
import net.dirox.auluxa.setting.view.device_list.climate.FragmentClimateSelectDeviceIrNodeCategory;
import net.dirox.auluxa.setting.view.device_list.climate.FragmentClimateSelectDeviceIrNodeManufacture;
import net.dirox.auluxa.setting.view.device_list.climate.FragmentClimateSelectDeviceTempHumiditySensor;
import net.dirox.auluxa.setting.view.device_list.climate.FragmentClimateSelectDeviceTempSensor;
import net.dirox.auluxa.setting.view.device_list.lights.FragmentDimmingNode;
import net.dirox.auluxa.setting.view.device_list.lights.FragmentFourButtonOnOff;
import net.dirox.auluxa.setting.view.device_list.lights.FragmentFourButtonPanel;
import net.dirox.auluxa.setting.view.device_list.lights.FragmentOnOffNode;
import net.dirox.auluxa.setting.view.device_list.lights.FragmentOneButtonDimming;
import net.dirox.auluxa.setting.view.device_list.lights.FragmentOneButtonDimmingPanel;
import net.dirox.auluxa.setting.view.device_list.lights.FragmentOneButtonOnOff;
import net.dirox.auluxa.setting.view.device_list.lights.FragmentOneButtonPanel;
import net.dirox.auluxa.setting.view.device_list.lights.FragmentSettingDeviceLights;
import net.dirox.auluxa.setting.view.device_list.lights.FragmentTwoButtonDimming;
import net.dirox.auluxa.setting.view.device_list.lights.FragmentTwoButtonDimmingPanel;
import net.dirox.auluxa.setting.view.device_list.lights.FragmentTwoButtonOnOff;
import net.dirox.auluxa.setting.view.device_list.lights.FragmentTwoButtonPanel;
import net.dirox.auluxa.setting.view.device_list.security.FragmentAlarm;
import net.dirox.auluxa.setting.view.device_list.security.FragmentAuluxaDoorLock;
import net.dirox.auluxa.setting.view.device_list.security.FragmentDoorBell;
import net.dirox.auluxa.setting.view.device_list.security.FragmentDoorLock;
import net.dirox.auluxa.setting.view.device_list.security.FragmentSensor;
import net.dirox.auluxa.setting.view.device_list.security.FragmentSettingDeviceSecurity;
import net.dirox.auluxa.setting.view.device_list.security.FragmentVideoDoorPhone;
import net.dirox.auluxa.setting.view.device_list.shades.FragmentOneButtonShades;
import net.dirox.auluxa.setting.view.device_list.shades.FragmentOneButtonShadesPanel;
import net.dirox.auluxa.setting.view.device_list.shades.FragmentSettingDeviceShades;
import net.dirox.auluxa.setting.view.device_list.shades.FragmentShadesNode;
import net.dirox.auluxa.setting.view.device_list.shades.FragmentTwoButtonShadesPanel;
import net.dirox.auluxa.setting.view.device_list.video.FragmentSettingDeviceCode;
import net.dirox.auluxa.setting.view.device_list.video.FragmentSettingDeviceVideo;
import net.dirox.auluxa.setting.view.device_list.video.FragmentVideoDeviceGeneral;
import net.dirox.auluxa.setting.view.device_list.video.FragmentVideoDeviceRoomsGeneral;
import net.dirox.auluxa.setting.view.device_list.video.FragmentVideoSelectCategory;
import net.dirox.auluxa.setting.view.device_list.video.FragmentVideoSelectDevice;
import net.dirox.auluxa.setting.view.device_list.video.FragmentVideoSelectDeviceManufacture;
import net.dirox.auluxa.utils.Constant;
import net.dirox.auluxa.utils.ScreenUtil;
import net.dirox.auluxa.utils.room_image.FileUtils;
import net.dirox.auluxa.video.FragmentSettingAudio;
import net.dirox.auluxa.video.FragmentSettingListUserManagement;
import net.dirox.auluxa.video.FragmentSettingUserManagement;

import javax.inject.Inject;


public class MainActivity extends BaseActivity implements MainActivityContract.View, NavigationView.OnNavigationItemSelectedListener,
        FragmentInteraction, FragmentManager.OnBackStackChangedListener {

    private ActivityMainBinding mBinding;
    private ActionBarDrawerToggle mToggle;
    private MainActivityComponent mComponent;
    AssetManager assetManager;

    private MainActivityComponent component() {
        if (mComponent == null) {
            mComponent = DaggerMainActivityComponent.builder()
                    .activityComponent(abstractActivityComponent())
                    .mainActivityModule(new MainActivityModule(this))
                    .build();
        }
        return mComponent;
    }

    private AlertDialog mLogOutAlert;

    @Inject
    MainActivityContract.Presenter presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //
        component().inject(this);
        mBinding.appBarMain.setPresenter(presenter);
        //setup toolbar
        setupToolBar();
        //setup navigation drawer
        setupNavigationDrawer();
        //
        mBinding.appBarMain.mainMenuButtonHome.setPressed(true);
        getSupportFragmentManager().beginTransaction()
                .replace(mBinding.appBarMain.activityFragmentContainer.getId(), FragmentMainHome.newInstance())
                .commit();

        getSupportFragmentManager().addOnBackStackChangedListener(this);
        //openFragment(Enumes.FragmentType.FRAGMENT_ROOM_MY_LIVING, null);

        // Copy room images
        FileUtils.copyFileOrDir(this, "Images");

    }

    private void setupToolBar() {
        setSupportActionBar(mBinding.appBarMain.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowCustomEnabled(false);
            Drawable menuDrawable = ContextCompat.getDrawable(this, R.drawable.ic_menu_selectors);
            getSupportActionBar().setHomeAsUpIndicator(menuDrawable);
        }
    }

    private void setupNavigationDrawer() {
        final TypedArray styledAttributes = getTheme().obtainStyledAttributes(
                new int[]{android.R.attr.actionBarSize});
        float tempSize = (int) styledAttributes.getDimension(0, 0);
        if (tempSize == 0) {
            tempSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 56, getResources().getDisplayMetrics());
        }
        float mActionBarSize = tempSize;
        mBinding.drawerLayout.post(new Runnable() {
            @Override
            public void run() {
                float width = ScreenUtil.getScreenWidth(MainActivity.this) - mActionBarSize;
                DrawerLayout.LayoutParams params = (DrawerLayout.LayoutParams) mBinding.navView.getLayoutParams();
                params.width = (int) (width);
                mBinding.navView.setLayoutParams(params);
            }
        });

        mBinding.drawerLayout.setScrimColor(Color.TRANSPARENT);
        mToggle = new ActionBarDrawerToggle(
                this, mBinding.drawerLayout, mBinding.appBarMain.toolbar, R.string.navigation_drawer_open, R
                .string.navigation_drawer_close) {

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                float transitionX = slideOffset * drawerView.getWidth();
                mBinding.appBarMain.appBarMainView.setTranslationX(transitionX);
                Log.d("DrawerLayout", "transitionX " + transitionX);
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                if (newState == DrawerLayout.STATE_SETTLING) {
                    if (mBinding.drawerLayout.isDrawerOpen(Gravity.LEFT) != true) {
                        // starts opening
                        mBinding.appBarMain.mainMenuButtonToggle.setSelected(true);
                    } else {
                        // closing drawer
                        mBinding.appBarMain.mainMenuButtonToggle.setSelected(false);
                    }
                    invalidateOptionsMenu();
                }
            }

            @Override
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                mBinding.appBarMain.mainMenuButtonToggle.setSelected(false);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                mBinding.appBarMain.mainMenuButtonToggle.setSelected(true);
            }
        };

        mBinding.drawerLayout.addDrawerListener(mToggle);
        NavigationView navigationView = mBinding.navView;
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.start();
    }

    @Override
    protected void onStop() {
        presenter.stop();
        super.onStop();
        if (mLogOutAlert != null && mLogOutAlert.isShowing()) {
            mLogOutAlert.dismiss();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onBackPressed() {

        if (mBinding.drawerLayout.isDrawerOpen(Gravity.LEFT) == true) {
            mBinding.drawerLayout.closeDrawers();
            return;
        }

        try {

            FragmentManager manager = getSupportFragmentManager();
            int countFragment = manager.getBackStackEntryCount();
            if (countFragment >= 1) {
                manager.popBackStackImmediate();
            } else {
                super.onBackPressed();
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                finish();
            }
        } catch (Exception e) {
            finish();
        }
    }

    @Override
    public void openFragment(Enumes.FragmentType fragmentType, Bundle bundle, Enumes.Direction directionIn) {
        hideKeyboard();
        if (mBinding.drawerLayout.isDrawerOpen(Gravity.LEFT) == true) {
            mBinding.drawerLayout.closeDrawers();
        }

        BaseFragment fragment = null;

        switch (fragmentType) {
            case FRAGMENT_ROOM_CENTRE:
                fragment = new RoomCentreFragment();
                break;

            case FRAGMENT_ROOM_CENTRE_CLIMATE:
                fragment = new RoomCentreClimateFragment();
                break;

            case FRAGMENT_ROOM_CENTRE_CLIMATE_CONTROL:
                fragment = new RoomCentreClimateControlFragment();
                break;

            case FRAGMENT_ROOM_CENTRE_CLIMATE_SCHEDULE:
                fragment = new RoomCentreClimateScheduleFragment();
                break;

            case FRAGMENT_CLIMATE_SLEEP_TIMER:
                fragment = new ClimateSleepTimerFragment();
                break;

            case FRAGMENT_CLIMATE_SCHEDULING:
                fragment = new ClimateSchedulingFragment();
                break;

            case FRAGMENT_CLIMATE_SCHEDULING_DETAIL:
                fragment = new ClimateSchedulingDetailFragment();
                break;

            case FRAGMENT_ROOM_CENTRE_LIGHTS:
                fragment = new RoomCentreLightsFragment();
                break;

            case FRAGMENT_ROOM_CENTRE_AUDIO:
                fragment = new RoomCentreAudioFragment();
                break;

            case FRAGMENT_ROOM_CENTRE_AUDIO_SETTINGS:
                fragment = FragmentSettingAudio.newInstance(bundle);
                break;

            case FRAGMENT_ROOM_CENTRE_AUDIO_DEVICES:
                fragment = new RoomCentreAudioDevicesFragment();
                break;

            case FRAGMENT_ROOM_CENTRE_AUDIO_CREATE_GROUP:
                fragment = RoomCentreAudioCreateGroupFragment.newInstance(bundle);
                break;

            case FRAGMENT_ROOM_CENTRE_VIDEO:
                fragment = RoomCentreVideoFragment.newInstance(RoomCentreVideoFragment.CurrentFragment.APLIFIER);
                break;

            case FRAGMENT_ROOM_CENTRE_VIDEO_APLIFIER:
                fragment = RoomCentreVideoFragment.newInstance(RoomCentreVideoFragment.CurrentFragment.APLIFIER, "AUDIO");
                break;

            case FRAGMENT_ROOM_CENTRE_VIDEO_APPLE_TV:
                fragment = RoomCentreVideoFragment.newInstance(RoomCentreVideoFragment.CurrentFragment.APPLE_TV, "AUDIO");
                break;

            case FRAGMENT_ROOM_CENTRE_VIDEO_CABLE_TV:
                fragment = RoomCentreVideoFragment.newInstance(RoomCentreVideoFragment.CurrentFragment.CABLE_TV, "AUDIO");
                break;

            case FRAGMENT_ROOM_CENTRE_VIDEO_MATRIX:
                fragment = RoomCentreVideoFragment.newInstance(RoomCentreVideoFragment.CurrentFragment.MATRIX, "AUDIO");
                break;

            case FRAGMENT_ROOM_CENTRE_VIDEO_TV_REMOTE:
                fragment = RoomCentreVideoFragment.newInstance(RoomCentreVideoFragment.CurrentFragment.TV_REMOTE, "AUDIO");
                break;

            case FRAGMENT_ROOM_CENTRE_VIDEO_DVD_REMOTE:
                fragment = RoomCentreVideoFragment.newInstance(RoomCentreVideoFragment.CurrentFragment.DVD_REMOTE, "AUDIO");
                break;

            case FRAGMENT_ROOM_CENTRE_VIDEO_MEDIA_SERVER:
                fragment = RoomCentreVideoFragment.newInstance(RoomCentreVideoFragment.CurrentFragment.MEDIA_SERVER, "AUDIO");
                break;

            case FRAGMENT_SETTING_LIST_USER_MANAGEMENT:
                fragment = FragmentSettingListUserManagement.newInstance();
                break;

            case FRAGMENT_SETTING_USER_MANAGEMENT:
                fragment = FragmentSettingUserManagement.newInstance(bundle);
                break;

            case FRAGMENT_ROOM_CENTRE_SHADES:
                fragment = new RoomCentreShadesFragment();
                break;

            case FRAGMENT_ROOM_CENTRE_OTHERS:
                fragment = new RoomCentreOthersFragment();
                break;

            case FRAGMENT_SCHEDULING:
                fragment = SchedulingFragment.newInstance(new ScheduleItem("Schedule", "8:05"));
                break;

            case FRAGMENT_MAIN_HOME:
                fragment = new FragmentMainHome();
                break;

            case FRAGMENT_EDIT_TARGET:
                fragment = new RoomCenterEditTargetFragment();
                break;

            case FRAGMENT_SETTING_MAIN:
                fragment = new FragmentSetting();
                break;

            case FRAGMENT_SETTING_LANGUAGE:
                fragment = new FragmentSettingLanguage();
                break;

            case FRAGMENT_SETTING_CLIMATE:
                fragment = new FragmentSettingClimate();
                break;

            case FRAGMENT_SETTING_DATE_TIME:
                fragment = new FragmentSettingDateTime();
                break;

            case FRAGMENT_SETTING_TIMEZONE:
                fragment = new FragmentSettingTimeZone();
                break;

            case FRAGMENT_SETTING_NOTIFICATIONS:
                fragment = new FragmentSettingNotifications();
                break;

            case FRAGMENT_SETTING_NOTIFICATIONS_ENERGY:
                fragment = new FragmentSettingNotificationsEnergy();
                break;

            case FRAGMENT_SETTING_NOTIFICATIONS_ROOMS:
                fragment = new FragmentSettingNotificationsRooms();
                break;

            case FRAGMENT_SETTING_ROOM_IMAGE:
                fragment = new FragmentSettingRoomImage();
                break;

            case FRAGMENT_SETTING_DEVICE_LIST:
                fragment = new FragmentSettingDeviceList();
                break;

            case FRAGMENT_SETTING_RECENTLY_ADDED:
                fragment = new FragmentSettingRecentlyAdded();
                break;

            case FRAGMENT_SETTING_ADD_DEVICES:
                fragment = new FragmentSettingAddDevices();
                break;

            case FRAGMENT_SETTING_THIRD_PARTY:
                fragment = new FragmentSettingThirdParty();
                break;

            case FRAGMENT_SETTING_DEVICE_CLIMATE:
                fragment = new FragmentClimateDeviceList();
                break;

            case FRAGMENT_SETTING_DEVICE_LIGHTS:
                fragment = new FragmentSettingDeviceLights();
                break;

            case FRAGMENT_SETTING_DEVICE_AUDIO:
                fragment = new FragmentAudioDeviceList();
                break;

            case FRAGMENT_SETTING_DEVICE_VIDEO:
                fragment = new FragmentSettingDeviceVideo();
                break;

            case FRAGMENT_SETTING_DEVICE_SECURITY:
                fragment = new FragmentSettingDeviceSecurity();
                break;

            case FRAGMENT_SETTING_DEVICE_SHADES:
                fragment = new FragmentSettingDeviceShades();
                break;

            case FRAGMENT_SETTING_CLIMATE_DEVICE_THERMOSTAT_PANEL:
                fragment = new FragmentClimateDeviceThermostatPanel();
                break;

            case FRAGMENT_SETTING_CLIMATE_DEVICE_GENERAL:
                fragment = new FragmentClimateDeviceGeneral();
                break;

            case FRAGMENT_SETTING_CLIMATE_DEVICE_ROOMS_GENERAL:
                fragment = new FragmentClimateDeviceRoomsGeneral();
                break;

            case FRAGMENT_SETTING_CLIMATE_SELECT_DEVICE_IR_NODE:
                fragment = new FragmentClimateSelectDeviceIrNode();
                break;

            case FRAGMENT_SETTING_CLIMATE_SELECT_DEVICE_IR_NODE_CATEGORY:
                fragment = new FragmentClimateSelectDeviceIrNodeCategory();
                break;

            case FRAGMENT_SETTING_CLIMATE_SELECT_DEVICE_IR_NODE_MANUFACTURER:
                fragment = new FragmentClimateSelectDeviceIrNodeManufacture();
                break;

            case FRAGMENT_SETTING_CLIMATE_SELECT_DEVICE_HUMIDITY:
                fragment = new FragmentClimateSelectDeviceHumidity();
                break;

            case FRAGMENT_SETTING_CLIMATE_SELECT_DEVICE_TEMP_SENSOR:
                fragment = new FragmentClimateSelectDeviceTempSensor();
                break;

            case FRAGMENT_SETTING_CLIMATE_SELECT_DEVICE_TEMP_HUMIDITY_SENSOR:
                fragment = new FragmentClimateSelectDeviceTempHumiditySensor();
                break;

            case FRAGMENT_SETTING_AUDIO_DEVICE_GENERAL:
                fragment = new FragmentAudioDeviceGeneral();
                break;


            case FRAGMENT_SETTING_AUDIO_DEVICE_CONNECT:
                fragment = new FragmentAudioDeviceConnect();
                break;

            case FRAGMENT_SETTING_AUDIO_DEVICE_SETTINGS:
                fragment = new FragmentAudioDeviceSettings();
                break;

            case FRAGMENT_SETTING_AUDIO_DEVICE_MAPPING:
                fragment = new FragmentAudioDeviceMapping();
                break;

            case FRAGMENT_SETTING_AUDIO_DEVICE_HDMI:
                fragment = new FragmentAudioDeviceHdmi();
                break;

            case FRAGMENT_SETTING_VIDEO_DEVICE_GENERAL:
                fragment = FragmentVideoDeviceGeneral.newInstance(bundle.getString(Constant.CLIMATE_DEVICE_NAME));
                break;

            case FRAGMENT_SETTING_VIDEO_SELECT_DEVICE:
                fragment = new FragmentVideoSelectDevice();
                break;

            case FRAGMENT_SETTING_VIDEO_SELECT_CATEGORY:
                fragment = new FragmentVideoSelectCategory();
                break;

            case FRAGMENT_SETTING_VIDEO_SELECT_MANUFACTURER:
                fragment = new FragmentVideoSelectDeviceManufacture();
                break;

            case FRAGMENT_SETTING_VIDEO_ROOMS:
                fragment = FragmentVideoDeviceRoomsGeneral.newInstance(bundle.getInt("position"));
                break;

            case FRAGMENT_SETTING_VIDEO_CODE:
                fragment = FragmentSettingDeviceCode.newInstance(bundle.getString("title"), bundle.getBoolean("visible"));
                break;

            case FRAGMENT_SECURITY:
                fragment = FragmentSecurity.newInstance();
                break;

//            case FRAGMENT_SECURITY_ALARM:
//                fragment = new FragmentSecurityAlarm();
//                break;

            case FRAGMENT_SECURITY_DOOR_LOCK:
                fragment = new FragmentSecurityDoorLock();
                break;

            case FRAGMENT_SECURITY_SURVELLIANCE:
                fragment = new FragmentSecuritySurvelliance();
                break;

            case FRAGMENT_SECURITY_VIDEO_PHONE:
                fragment = new FragmentSecurityVideoPhone();
                break;
            case FRAGMENT_ENERGY:
                fragment = new FragmentEnergyManager();
                break;

            case FRAGMENT_SCENE_CREATE_STEP_1:
                fragment = new CreateScenesStep1Fragment();
                break;

            case FRAGMENT_SCENE_CREATE_STEP_2:
                fragment = new CreateScenesStep2Fragment();
                break;

            case FRAGMENT_SCENE_CREATE_RENAME:
                fragment = new CreateScenesRenameFragment();
                break;

            case FRAGMENT_SCENE_CREATE_ROOMS:
                fragment = new CreateScenesRoomFragment();
                break;

            case FRAGMENT_SCENE_CREATE_ROOM_DETAIL:
                fragment = new CreateScenesRoomDetailFragment();
                break;

            case FRAGMENT_SCENE_CREATE_ROOM_VIDEO_LIST:
                fragment = new CreateScenesRoomVideoListFragment();
                break;

            case FRAGMENT_SCENE_CREATE_ROOM_VIDEO_LIST_SEQUENCE:
                fragment = new CreateScenesRoomVideoListSequenceFragment();
                break;

            case FRAGMENT_SCENE_CREATE_ROOM_VIDEO_ADD_SEQUENCE:
                fragment = new CreateScenesRoomVideoAddSequenceFragment();
                break;

            case FRAGMENT_SCENE_CREATE_ROOM_SECURITY:
                fragment = new CreateScenesRoomSecurityDetailFragment();
                break;

            case FRAGMENT_SCENE_CREATE_ROOM_CLIMATE:
                fragment = new CreateScenesRoomClimateDetailFragment();
                break;

            case FRAGMENT_SCENE_CREATE_SCHEDULE:
                fragment = new CreateScenesScheduleFragment();
                break;

            case FRAGMENT_SCENE_CREATE_SEQUENCE:
                fragment = new CreateScenesSequenceFragment();
                break;

            case FRAGMENT_SCENE_CREATE_LIGHT:
                fragment = new CreateScenesLightFragment();
                break;

            case FRAGMENT_SCENE_CREATE_SHADE:
                fragment = new CreateScenesShadesFragment();
                break;

            // Setting Lights
            case FRAGMENT_ONE_BUTTON_ON_OFF:
                fragment = new FragmentOneButtonOnOff();
                break;
            case FRAGMENT_TWO_BUTTON_ON_OFF:
                fragment = new FragmentTwoButtonOnOff();
                break;
            case FRAGMENT_FOUR_BUTTON_ON_OFF:
                fragment = new FragmentFourButtonOnOff();
                break;

            case FRAGMENT_ON_OFF_NODE:
                fragment = new FragmentOnOffNode();
                break;

            case FRAGMENT_ONE_BUTTON_DIMMING:
                fragment = new FragmentOneButtonDimming();
                break;

            case FRAGMENT_TWO_BUTTON_DIMMING:
                fragment = new FragmentTwoButtonDimming();
                break;

            case FRAGMENT_DIMMING_NODE:
                fragment = new FragmentDimmingNode();
                break;

            case FRAGMENT_ONE_BUTTON_PANEL:
                fragment = new FragmentOneButtonPanel();
                break;

            case FRAGMENT_TWO_BUTTON_PANEL:
                fragment = new FragmentTwoButtonPanel();
                break;

            case FRAGMENT_FOUR_BUTTON_PANEL:
                fragment = new FragmentFourButtonPanel();
                break;

            case FRAGMENT_ONE_BUTTON_DIMMING_PANEL:
                fragment = new FragmentOneButtonDimmingPanel();
                break;
            case FRAGMENT_TWO_BUTTON_DIMMING_PANEL:
                fragment = new FragmentTwoButtonDimmingPanel();
                break;

            // Setting Shades
            case FRAGMENT_ONE_BUTTON_SHADES:
                fragment = new FragmentOneButtonShades();
                break;

            case FRAGMENT_TWO_BUTTON_SHADES:
                fragment = new FragmentTwoButtonPanel();
                break;

            case FRAGMENT_ONE_BUTTON_SHADES_PANEL:
                fragment = new FragmentOneButtonShadesPanel();
                break;

            case FRAGMENT_TWO_BUTTON_SHADES_PANEL:
                fragment = new FragmentTwoButtonShadesPanel();
                break;

            case FRAGMENT_SHADES_NODE:
                fragment = new FragmentShadesNode();
                break;

            // Settings Security
            case FRAGMENT_AULUXA_DOOR_LOCK:
                fragment = new FragmentAuluxaDoorLock();
                break;

            case FRAGMENT_DOOR_LOCK:
                fragment = new FragmentDoorLock();
                break;

            case FRAGMENT_ALARM:
                fragment = new FragmentAlarm();
                break;

            case FRAGMENT_SENSOR:
                fragment = new FragmentSensor();
                break;

            case FRAGMENT_DOOR_BELL:
                fragment = new FragmentDoorBell();
                break;

            case FRAGMENT_VIDEO_DOOR_PHONE:
                fragment = new FragmentVideoDoorPhone();
                break;
            case FRAGMENT_ACTIVITY_LOG:
                fragment = new FragmentSecuirityActivityLog();
                break;
            case FRAGMENT_SECURITY_AIRBNB:
                fragment = new FragemntSecurityAirbnb();
                break;
            case FRAGMENT_SECURITY_ASSIGNED_LOCK_PIN:
                fragment = new FragmentSecurityAssignedLockPin();
                break;
            case FRAGMENT_CONNECT_TO_IGLOOHOME:
                fragment = new FragmentConnectToIGLooHome();
                break;
            case FRAGMENT_SECURITY_LOCK:
                fragment = FragmentSecurityLock.newInstance();
                break;
            case FRAGMENT_SECURITY_LOCK_EDITNAME:
                fragment = new FragmentSecurityLockEditName();
                break;
            case FRAGMENT_SECURITY_ASSIGNED_PIN:
                fragment = new FragmentSecurityAssignedPin();
                break;
            case FRAGMENT_SECURITY_DURATION_PIN:
                fragment = new FragmentSecurityDurationPin();
                break;
            case FRAGMENT_SECURITY_CREATE_LOCK_PIN:
                fragment = new FragmentSecurityCreateLockPin();
                break;
            case FRAGMNET_ADD_BLUETOOTH_KEY:
                fragment = new FragmentSecurityAddBluetooth();
                break;
            case FRAGMENT_SECURITY_CREATE_DURATION_PIN:
               fragment = new FragmentSecurityCreateDurationPin();
                break;
            case FRAGMENT_SECURITY_PENDING_BLUETOOTH_KEY:
                fragment = new FragmentSecurityPendingBluetoothKey();
                break;
            case FRAGMENT_SECURITY_CREATE_ONETIME_OR_PERMANENT_PIN:
                fragment = new FragmentSecurityCreateOneTimeOrPermanentPin();
                break;
        }

        if (fragment == null) {
            return;
        }

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        switch (directionIn) {
            case LEFT_IN:
                break;
            case RIGHT_IN:
                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
                break;
            case RIGHT_IN_FLIP_OUT:
                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.from_middle, R.anim.to_middle);
                break;
            case TOP_IN:
                transaction.setCustomAnimations(R.anim.enter_from_top, R.anim.exit_to_bottom);
                break;
            case BOTTOM_IN:
                break;
            case FLIP_IN:
                transaction.setCustomAnimations(R.anim.from_middle, R.anim.to_middle);
                break;

            case FLIP_OUT:
                transaction.setCustomAnimations(R.anim.from_middle, R.anim.to_middle);
                break;


        }

        if (bundle != null && bundle.size() > 0) {
            fragment.setArguments(bundle);
        }

        String tagName = fragmentType.toString();
        transaction.replace(R.id.activity_fragment_container, fragment, tagName);

        int backStackCount = manager.getBackStackEntryCount();
        Log.d("Fragment", "backStackCount " + backStackCount);
        if (backStackCount > 0) {
            transaction.addToBackStack(tagName);
        } else {
            transaction.addToBackStack(tagName);
        }

        transaction.commit();
    }

    @Override
    public void onFragmentVisible(boolean isVisible) {

    }

    @Override
    public void onBackStackChanged() {
        FragmentManager manager = getSupportFragmentManager();
        if (manager != null) {
            BaseFragment baseFragment = (BaseFragment) manager.findFragmentById(R.id.activity_fragment_container);
            if (baseFragment != null) {
                if (baseFragment instanceof RoomCentreClimateControlFragment) {
                    if (manager.getBackStackEntryCount() >= 1) {
                        String topOnStack = manager.getBackStackEntryAt(manager.getBackStackEntryCount() - 1).getName();
                        if (topOnStack != null && topOnStack.length() > 0) {
                            if (topOnStack.equalsIgnoreCase(Enumes.FragmentType.FRAGMENT_ROOM_CENTRE_CLIMATE_CONTROL.toString()) == true) {
                                ((RoomCentreClimateControlFragment) baseFragment).onResumeFromBackStack();
                            }
                        }
                    }
                }

                if (baseFragment instanceof RoomCentreAudioDevicesFragment) {
                    if (manager.getBackStackEntryCount() >= 1) {
                        String topOnStack = manager.getBackStackEntryAt(manager.getBackStackEntryCount() - 1).getName();
                        if (topOnStack != null && topOnStack.length() > 0) {
                            if (topOnStack.equalsIgnoreCase(Enumes.FragmentType.FRAGMENT_ROOM_CENTRE_AUDIO_DEVICES.toString()) == true) {
                                ((RoomCentreAudioDevicesFragment) baseFragment).onResumeFromBackStack();
                            }
                        }
                    }
                }

            }
        }
    }

    @Override
    public void toggleNavigationViewClick() {
        if (mBinding.appBarMain.mainMenuButtonToggle.isSelected() == true) {
            mBinding.appBarMain.mainMenuButtonToggle.setSelected(false);
        } else {
            mBinding.appBarMain.mainMenuButtonToggle.setSelected(true);
        }

        if (mBinding.drawerLayout.isDrawerOpen(Gravity.LEFT) == true) {
            mBinding.drawerLayout.closeDrawers();
        } else {
            mBinding.drawerLayout.openDrawer(Gravity.LEFT);
        }
    }

    @Override
    public void showHomeToolbarMenuClick() {
        startActivity(new Intent(this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        overridePendingTransition(R.anim.enter_from_top, R.anim.translate_nothing);
        if (mBinding.appBarMain.mainMenuSettingDropdownHolder.getVisibility() == View.VISIBLE) {
            toggleMenuDropDownClicked();
        }
    }

    @Override
    public void showDropDownToolbarMenuClick() {
        toggleMenuDropDownClicked();
        toggleMenuClicked(R.id.main_menu_button_setting);

    }

    @Override
    public void showSecurityToolbarMenuClick() {
        openFragment(Enumes.FragmentType.FRAGMENT_SECURITY, null, Enumes.Direction.TOP_IN);
        if (mBinding.appBarMain.mainMenuSettingDropdownHolder.getVisibility() == View.VISIBLE) {
            toggleMenuDropDownClicked();
        }
        toggleMenuClicked(R.id.main_menu_button_security);
    }

    @Override
    public void showNotifyFragment() {
        mBinding.appBarMain.mainMenuSettingDropdownNotifyItem.setBackgroundColor(ContextCompat.getColor(this, R.color.auluxaGreen));
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_from_top, R.anim.exit_to_bottom)
                .replace(mBinding.appBarMain.activityFragmentContainer.getId(), FragmentNotification.newInstance())
                .addToBackStack(null)
                .commit();
        toggleMenuDropDownClicked();
    }

    @Override
    public void showLogOutAlert() {
        if (mLogOutAlert == null) {
            mLogOutAlert = new AlertDialog.Builder(this)
                    .setTitle("Alert!")
                    .setMessage("Sign out")
                    .setPositiveButton("Ok", (dialog, i) -> {
                        presenter.userLogOut();
                        dialog.dismiss();
                    })
                    .setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss())
                    .create();
        }
        if (mLogOutAlert != null && !mLogOutAlert.isShowing()) {
            mLogOutAlert.show();
        }
        toggleMenuDropDownClicked();
    }

    @Override
    public void showGateWayFragment() {
        mBinding.appBarMain.mainMenuSettingDropdownGateWayItem.setBackgroundColor(ContextCompat.getColor(this, R.color.auluxaGreen));
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_from_top, R.anim.exit_to_bottom)
                .replace(mBinding.appBarMain.activityFragmentContainer.getId(), FragmentGatewaySetting.newInstance())
                .addToBackStack(null)
                .commit();
        toggleMenuDropDownClicked();
    }

    @Override
    public void showEnergySystemFragment() {
        mBinding.appBarMain.mainMenuSettingDropdownEnergyItem.setBackgroundColor(ContextCompat.getColor(this, R.color.auluxaGreen));
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_from_top, R.anim.exit_to_bottom)
                .replace(mBinding.appBarMain.activityFragmentContainer.getId(), FragmentEnergyManager.newInstance())
                .addToBackStack(null)
                .commit();
        toggleMenuDropDownClicked();
    }

    @Override
    public void showLogInActivity() {
        startActivity(new Intent(MainActivity.this, LoginActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    @Override
    public void showSettingFragment() {
        mBinding.appBarMain.mainMenuSettingDropdownSettingItem.setBackgroundColor(ContextCompat.getColor(this, R.color.auluxaGreen));
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_from_top, R.anim.exit_to_bottom)
                .replace(mBinding.appBarMain.activityFragmentContainer.getId(), FragmentSetting.newInstance())
                .addToBackStack(null)
                .commit();
        toggleMenuDropDownClicked();
    }

    private void toggleMenuClicked(int id) {
        switch (id) {
            case R.id.main_menu_button_home:
                mBinding.appBarMain.mainMenuButtonSecurity.setBackgroundColor(Color.TRANSPARENT);
                mBinding.appBarMain.mainMenuButtonSecurity.setPressed(false);
                mBinding.appBarMain.mainMenuButtonSetting.setPressed(false);
                mBinding.appBarMain.mainMenuButtonHome.setPressed(true);
                break;
            case R.id.main_menu_button_setting:
                mBinding.appBarMain.mainMenuButtonSecurity.setBackgroundColor(Color.TRANSPARENT);
                mBinding.appBarMain.mainMenuButtonSecurity.setPressed(false);
                mBinding.appBarMain.mainMenuButtonSetting.setPressed(true);
                mBinding.appBarMain.mainMenuButtonHome.setPressed(false);
                break;
            case R.id.main_menu_button_security:
                mBinding.appBarMain.mainMenuButtonSecurity.setPressed(true);
                mBinding.appBarMain.mainMenuButtonSecurity.setBackgroundColor(ContextCompat.getColor(this, R.color.auluxaGreen));
                mBinding.appBarMain.mainMenuButtonSetting.setPressed(false);
                mBinding.appBarMain.mainMenuButtonHome.setPressed(false);
                break;
        }
    }

    private void toggleMenuDropDownClicked() {
        if (mBinding.appBarMain.mainMenuSettingDropdownHolder.getVisibility() == View.GONE) {
            mBinding.appBarMain.mainMenuButtonSetting.setImageDrawable(ContextCompat.getDrawable(this, R.drawable
                    .scenes_more_button_rollover));
            Fragment fragment = getSupportFragmentManager().findFragmentById(mBinding.appBarMain.activityFragmentContainer.getId());
            if (fragment != null) {
                if (!(fragment instanceof FragmentEnergyManager)) {
                    mBinding.appBarMain.mainMenuSettingDropdownEnergyItem.setBackgroundColor(Color.TRANSPARENT);
                }
                if (!(fragment instanceof FragmentNotification)) {
                    mBinding.appBarMain.mainMenuSettingDropdownNotifyItem.setBackgroundColor(Color.TRANSPARENT);
                }
                if (!(fragment instanceof FragmentGatewaySetting)) {
                    mBinding.appBarMain.mainMenuSettingDropdownGateWayItem.setBackgroundColor(Color.TRANSPARENT);
                }
                if (!(fragment instanceof FragmentSetting)) {
                    mBinding.appBarMain.mainMenuSettingDropdownSettingItem.setBackgroundColor(Color.TRANSPARENT);
                }
            }
            Animation slideDown = AnimationUtils.loadAnimation(this, R.anim.slide_down);
            slideDown.setInterpolator(new LinearInterpolator());
            mBinding.appBarMain.mainMenuSettingDropdownHolder.startAnimation(slideDown);
            mBinding.appBarMain.mainMenuSettingDropdownHolder.setVisibility(View.VISIBLE);
        } else {
            mBinding.appBarMain.mainMenuButtonSetting.setImageDrawable(ContextCompat.getDrawable(this, R.drawable
                    .scenes_more_button));
            Animation slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up);
            slideUp.setInterpolator(new LinearInterpolator());
            mBinding.appBarMain.mainMenuSettingDropdownHolder.startAnimation(slideUp);
            mBinding.appBarMain.mainMenuSettingDropdownHolder.setVisibility(View.GONE);
        }
    }

    public void updateNavigationDrawerData() {
        Fragment navigationFragment = getSupportFragmentManager().findFragmentByTag(getString(R.string.nav_fragment_tag));
        if (navigationFragment != null && navigationFragment instanceof NavigationDrawerFragment) {
            ((NavigationDrawerFragment) navigationFragment).updateDataListChange();
            ((NavigationDrawerFragment) navigationFragment).initializeViews();
        }
    }


}
