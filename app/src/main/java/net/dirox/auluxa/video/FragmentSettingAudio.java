package net.dirox.auluxa.video;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import net.dirox.auluxa.R;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.sample.AudioDeviceItem;
import net.dirox.auluxa.data.sample.AudioGroupDeviceItem;
import net.dirox.auluxa.data.sample.AudioGroupDeviceItemData;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class FragmentSettingAudio extends BaseFragment {

    @InjectView(R.id.cbCheckHighDynamic)
    CheckBox cbCheckHighDynamic;
    @InjectView(R.id.cbCheckPlayback)
    CheckBox cbCheckPlayback;
    @InjectView(R.id.llEdit)
    LinearLayout llEdit;
    @InjectView(R.id.viPlay)
    View viPlay;
    @InjectView(R.id.viHigh)
    View viHigh;
    @InjectView(R.id.ivLanguage)
    ImageView ivLanguage;

    @InjectView(R.id.llName)
    RelativeLayout llName;
    @InjectView(R.id.llGuest)
    RelativeLayout llGuest;
    @InjectView(R.id.llWifi)
    RelativeLayout llWifi;
    @InjectView(R.id.llTimeZone)
    RelativeLayout llTimeZone;
    @InjectView(R.id.llTimeFormat)
    RelativeLayout llTimeFormat;
    @InjectView(R.id.llLanguage)
    RelativeLayout llLanguage;
    @InjectView(R.id.llPlayback)
    RelativeLayout llPlayback;
    @InjectView(R.id.llHighDynamic)
    RelativeLayout llHighDynamic;
    @InjectView(R.id.ivArrow1)
    ImageView ivArrow1;
    @InjectView(R.id.ivArrow2)
    ImageView ivArrow2;
    @InjectView(R.id.ivTimeZone)
    ImageView ivTimeZone;
    @InjectView(R.id.etName)
    EditText etName;
    @InjectView(R.id.etGuest)
    EditText etGuest;
    @InjectView(R.id.tvTimeZoneContent)
    TextView tvTimeZoneContent;
    @InjectView(R.id.tvTimeFormatContent)
    TextView tvTimeFormatContent;
    @InjectView(R.id.tvLanguageContent)
    TextView tvLanguageContent;
    @InjectView(R.id.tvNameContent)
    TextView tvNameContent;
    @InjectView(R.id.tvGuestContent)
    TextView tvGuestContent;
    @InjectView(R.id.tvEdit)
    TextView tvEdit;

    @InjectView(R.id.tvTimeZone)
    TextView tvTimeZone;
    @InjectView(R.id.tvLanguage)
    TextView tvLanguage;
    @InjectView(R.id.tvName)
    TextView tvName;
    @InjectView(R.id.tvGuest)
    TextView tvGuest;
    @InjectView(R.id.tvHongKong)
    TextView tvHongKong;
    @InjectView(R.id.tvVietnam)
    TextView tvVietnam;
    @InjectView(R.id.tvSingapore)
    TextView tvSingapore;
    @InjectView(R.id.lnMenuTime)
    LinearLayout lnMenuTime;
    @InjectView(R.id.lnMenuLanguage)
    LinearLayout lnMenuLanguage;
    @InjectView(R.id.tvFrance)
    TextView tvFrance;
    @InjectView(R.id.tvLanguageVietnam)
    TextView tvLanguageVietnam;
    @InjectView(R.id.tvEngLish)
    TextView tvEngLish;

    ArrayList<String> list;
    boolean isEdit = false;
    static Bundle args = new Bundle();
    String strAudioGroupDeviceItem;


    public static FragmentSettingAudio newInstance(Bundle bundle) {
        FragmentSettingAudio fragmentFirst = new FragmentSettingAudio();
        args = bundle;
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_audio_setting, container, false);
        ButterKnife.inject(this, view);
        strAudioGroupDeviceItem = args.getString("Object");

        AudioGroupDeviceItemData audioGroupDeviceItem = new Gson().fromJson(strAudioGroupDeviceItem, AudioGroupDeviceItemData.class);

        if (audioGroupDeviceItem != null) {
            etName.setText(audioGroupDeviceItem.items.get(0).name);
            etGuest.setText(audioGroupDeviceItem.items.get(0).pin);
        }
        setDefault();
        return view;
    }


    @OnClick(R.id.llEdit)
    public void onClickEdit() {
        if (!isEdit) {
            setEdit();
        } else {
            onBackAndHideKeyBoard();
        }
    }

    public void setDefault() {
        isEdit = false;
        if (tvTimeFormatContent.getText().toString().equals("12 HRS"))
            tvTimeFormatContent.setText("12 HOURS");
        else tvTimeFormatContent.setText("24 HOURS");
        tvNameContent.setText(etName.getText().toString());
        tvGuestContent.setText(etGuest.getText().toString());
        tvTimeZone.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarker));
        tvLanguage.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarker));
        tvGuest.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarker));
        tvName.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarker));
        tvTimeZone.setPadding(0, 0, 0, 0);
        tvLanguage.setPadding(0, 0, 0, 0);
        tvGuest.setPadding(0, 0, 0, 0);
        tvName.setPadding(0, 0, 0, 0);
        tvEdit.setText("EDIT");
        ivLanguage.setVisibility(View.GONE);
        cbCheckPlayback.setVisibility(View.GONE);
        viPlay.setVisibility(View.VISIBLE);
        viHigh.setVisibility(View.VISIBLE);
        cbCheckHighDynamic.setVisibility(View.GONE);
        ivArrow2.setVisibility(View.GONE);
        ivArrow1.setVisibility(View.GONE);
        ivTimeZone.setVisibility(View.GONE);
        etName.setVisibility(View.GONE);
        etGuest.setVisibility(View.GONE);
        tvNameContent.setVisibility(View.VISIBLE);
        tvGuestContent.setVisibility(View.VISIBLE);
        llName.setBackgroundColor(Color.TRANSPARENT);
        llGuest.setBackgroundColor(Color.TRANSPARENT);
        llTimeZone.setBackgroundColor(Color.TRANSPARENT);
        llLanguage.setBackgroundColor(Color.TRANSPARENT);
        tvTimeZoneContent.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarker));
        tvTimeFormatContent.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarker));
        tvLanguageContent.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarker));
        if (cbCheckPlayback.isChecked())
            viPlay.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.shape_background_audio_checkbox_enable));
        else
            viPlay.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.shape_background_audio_checkbox_disable));
        if (cbCheckHighDynamic.isChecked())
            viHigh.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.shape_background_audio_checkbox_enable));
        else
            viHigh.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.shape_background_audio_checkbox_disable));
        hideMenu();
    }

    public void setEdit() {
        isEdit = true;
        tvTimeZone.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
        tvLanguage.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
        tvGuest.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
        tvName.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarkGray));
        tvTimeZone.setPadding(20, 0, 0, 0);
        tvLanguage.setPadding(20, 0, 0, 0);
        tvGuest.setPadding(20, 0, 0, 0);
        tvName.setPadding(20, 0, 0, 0);
        etName.setText(tvNameContent.getText().toString());
        etGuest.setText(tvGuestContent.getText().toString());

        llName.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.shape_background_audio_edit));
        llGuest.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.shape_background_audio_edit));
        llTimeZone.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.shape_background_audio_edit));
        llLanguage.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.shape_background_audio_edit));
        cbCheckPlayback.setVisibility(View.VISIBLE);
        viPlay.setVisibility(View.GONE);
        viHigh.setVisibility(View.GONE);
        cbCheckHighDynamic.setVisibility(View.VISIBLE);
        ivArrow2.setVisibility(View.VISIBLE);
        ivArrow1.setVisibility(View.VISIBLE);
        ivLanguage.setVisibility(View.VISIBLE);
        ivTimeZone.setVisibility(View.VISIBLE);
        etName.setVisibility(View.VISIBLE);
        etGuest.setVisibility(View.VISIBLE);
        tvNameContent.setVisibility(View.GONE);
        tvGuestContent.setVisibility(View.GONE);
        tvEdit.setText("SAVE");
        tvTimeZoneContent.setTextColor(ContextCompat.getColor(getContext(), R.color.auluxaGreen));
        tvTimeFormatContent.setTextColor(ContextCompat.getColor(getContext(), R.color.auluxaGreen));
        tvLanguageContent.setTextColor(ContextCompat.getColor(getContext(), R.color.auluxaGreen));

        if (tvTimeFormatContent.getText().toString().equals("12 HOURS"))
            tvTimeFormatContent.setText("12 HRS");
        else tvTimeFormatContent.setText("24 HRS");
    }

    @OnClick(R.id.ivArrow1)
    public void onClickTimeFormat() {
        hideMenu();
        if (tvTimeFormatContent.getText().equals("24 HRS")) {
            tvTimeFormatContent.setText("12 HRS");
        } else
            tvTimeFormatContent.setText("24 HRS");
    }

    @OnClick(R.id.ivArrow2)
    public void onClickTimeFormat2() {
        hideMenu();
        onClickTimeFormat();
    }

    @OnClick(R.id.tvTimeFormatContent)
    public void onClickTimeFormat3() {
        if (isEdit) {
            hideMenu();
            onClickTimeFormat();
        }
    }

    @OnClick(R.id.llTimeZone)
    public void onMenuTimeZone() {
        if (isEdit) {
            hideKeyboardIfNeed(getContext());
            lnMenuLanguage.setVisibility(View.GONE);
            if (lnMenuTime.getVisibility() == View.VISIBLE)
                lnMenuTime.setVisibility(View.GONE);
            else lnMenuTime.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.llLanguage)
    public void onMenuLanguage() {
        if (isEdit) {
            hideKeyboardIfNeed(getContext());
            lnMenuTime.setVisibility(View.GONE);
            if (lnMenuLanguage.getVisibility() == View.VISIBLE)
                lnMenuLanguage.setVisibility(View.GONE);
            else lnMenuLanguage.setVisibility(View.VISIBLE);
        }
    }


    @OnClick(R.id.tvHongKong)
    public void onClickHongKong() {
        tvTimeZoneContent.setText(tvHongKong.getText().toString());
        setChangeColorItemMenuSelect(tvHongKong);
        lnMenuTime.setVisibility(View.GONE);
    }

    @OnClick(R.id.tvSingapore)
    public void onClickSingapore() {
        tvTimeZoneContent.setText(tvSingapore.getText().toString());
        setChangeColorItemMenuSelect(tvSingapore);
        lnMenuTime.setVisibility(View.GONE);
    }

    @OnClick(R.id.tvVietnam)
    public void onClickVietNam() {
        tvTimeZoneContent.setText(tvVietnam.getText().toString());
        setChangeColorItemMenuSelect(tvVietnam);
        lnMenuTime.setVisibility(View.GONE);
    }

    @OnClick(R.id.tvEngLish)
    public void onClickEnglish() {
        tvLanguageContent.setText(tvEngLish.getText().toString());
        setChangeColorItemMenuLanguageSelect(tvEngLish);
        lnMenuLanguage.setVisibility(View.GONE);
    }

    @OnClick(R.id.tvLanguageVietnam)
    public void onClickLanguageVietNam() {
        tvLanguageContent.setText(tvLanguageVietnam.getText().toString());
        setChangeColorItemMenuLanguageSelect(tvLanguageVietnam);
        lnMenuLanguage.setVisibility(View.GONE);
    }

    @OnClick(R.id.tvFrance)
    public void onClickFrance() {
        tvLanguageContent.setText(tvFrance.getText().toString());
        setChangeColorItemMenuLanguageSelect(tvFrance);
        lnMenuLanguage.setVisibility(View.GONE);
    }

    public void setChangeColorItemMenuSelect(TextView tvMenuSelect) {
        tvHongKong.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        tvSingapore.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        tvVietnam.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        tvMenuSelect.setTextColor(ContextCompat.getColor(getContext(), R.color.auluxaGreen));
    }

    public void setChangeColorItemMenuLanguageSelect(TextView tvMenuSelect) {
        tvEngLish.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        tvLanguageVietnam.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        tvFrance.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        tvMenuSelect.setTextColor(ContextCompat.getColor(getContext(), R.color.auluxaGreen));
    }

    @OnClick(R.id.etGuest)
    public void hideMenu() {
        lnMenuLanguage.setVisibility(View.GONE);
        lnMenuTime.setVisibility(View.GONE);
    }

    @OnClick(R.id.etName)
    public void hideMenu2() {
        hideMenu();
    }

    @OnClick(R.id.ivBack)
    public void onBack() {
        getActivity().onBackPressed();
    }
}