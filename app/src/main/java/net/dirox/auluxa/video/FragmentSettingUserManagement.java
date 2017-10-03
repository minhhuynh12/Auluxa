package net.dirox.auluxa.video;

import android.content.DialogInterface;
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
import net.dirox.auluxa.common.activity.BaseActivity;
import net.dirox.auluxa.common.fragment.BaseFragment;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class FragmentSettingUserManagement extends BaseFragment {


    @InjectView(R.id.cbLivingRoom)
    CheckBox cbLivingRoom;
    @InjectView(R.id.cbDiningRoom)
    CheckBox cbDiningRoom;
    @InjectView(R.id.cbGuestBedroom)
    CheckBox cbGuestBedroom;
    @InjectView(R.id.llEdit)
    LinearLayout llEdit;
    @InjectView(R.id.tvDelete)
    TextView tvDelete;

    @InjectView(R.id.llName)
    RelativeLayout llName;
    @InjectView(R.id.tvName)
    TextView tvName;
    @InjectView(R.id.tvNameContent)
    TextView tvNameContent;
    @InjectView(R.id.etName)
    EditText etName;
    @InjectView(R.id.rlAccount)
    RelativeLayout rlAccount;
    @InjectView(R.id.tvAccount)
    TextView tvAccount;
    @InjectView(R.id.ivArrow1)
    ImageView ivArrow1;
    @InjectView(R.id.ivArrow2)
    ImageView ivArrow2;
    @InjectView(R.id.tvAccountContent)
    TextView tvAccountContent;

    @InjectView(R.id.rlEmail)
    RelativeLayout rlEmail;
    @InjectView(R.id.tvEmail)
    TextView tvEmail;
    @InjectView(R.id.tvEmailContent)
    TextView tvEmailContent;
    @InjectView(R.id.etEmail)
    EditText etEmail;
    @InjectView(R.id.rlPasswordConfirm)
    RelativeLayout rlPasswordConfirm;
    @InjectView(R.id.tvPasswordConfirm)
    TextView tvPasswordConfirm;
    @InjectView(R.id.tvPasswordConfirmContent)
    TextView tvPasswordConfirmContent;
    @InjectView(R.id.etPasswordConfirm)
    EditText etPasswordConfirm;
    @InjectView(R.id.etPassword)
    EditText etPassword;
    @InjectView(R.id.tvPasswordContent)
    TextView tvPasswordContent;
    @InjectView(R.id.viLivingRoom)
    View viLivingRoom;
    @InjectView(R.id.tvLivingRoom)
    TextView tvLivingRoom;
    @InjectView(R.id.tvDiningRoom)
    TextView tvDiningRoom;
    @InjectView(R.id.viDiningRoom)
    View viDiningRoom;
    @InjectView(R.id.tvGuestBedroom)
    TextView tvGuestBedroom;
    @InjectView(R.id.viGuestBedroom)
    View viGuestBedroom;
    @InjectView(R.id.tvEdit)
    TextView tvEdit;
    @InjectView(R.id.llDelete)
    RelativeLayout llDelete;
    @InjectView(R.id.tvTitle)
    TextView tvTitle;

    boolean isAdmin = false;
    boolean isEdit = false;
    int pos = -1;
    String name = "";
    String userStr;
    User user;
    static Bundle args = new Bundle();


    public static FragmentSettingUserManagement newInstance(Bundle bundle) {
        FragmentSettingUserManagement fragmentFirst = new FragmentSettingUserManagement();
        args = bundle;
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting_user_management, container, false);
        ButterKnife.inject(this, view);
        isEdit = args.getBoolean("isEdit");
        pos = args.getInt("position");
        name = args.getString("name");
        isAdmin = args.getBoolean("isAdmin");
        isAdmin = args.getBoolean("isAdmin");
        userStr = args.getString("Object");

        user = new Gson().fromJson(userStr, User.class);

        tvTitle.setText(name);

        if (isAdmin == true) {
            tvAccountContent.setText("ADMIN");
        } else tvAccountContent.setText("USER");
        if (user != null) {
            tvEmailContent.setText(user.email);
            tvPasswordContent.setText(user.tvPasswordContent);
            tvPasswordConfirmContent.setText(user.tvPasswordContent);
            if (user.llLivingRoom)
                cbLivingRoom.setChecked(true);
            else cbLivingRoom.setChecked(false);
            if (user.llGuestBedroom)
                cbGuestBedroom.setChecked(true);
            else cbGuestBedroom.setChecked(false);
            if (user.llDiningRoom)
                cbDiningRoom.setChecked(true);
            else cbDiningRoom.setChecked(false);
        }
        if (!isEdit)
            setDefault();
        else setEdit();


        return view;
    }


    @OnClick(R.id.llEdit)
    public void onClickEdit() {
        if (!isEdit) {
            setEdit();
        } else {
            if (!etPassword.getText().toString().trim().isEmpty() &&
                    etPassword.getText().toString().equals(etPasswordConfirm.getText().toString()) &&
                    !etName.getText().toString().isEmpty()) {
                if (pos == FragmentSettingListUserManagement.userAdapter.getItemCount() - 1) {
                    FragmentSettingListUserManagement.userAdapter.addUser(etName.getText().toString(), isAdmin = tvAccountContent.getText().toString().equals("ADMIN") ? true : false, etEmail.getText().toString(), etPassword.getText().toString(), cbLivingRoom.isChecked() ? true : false, cbDiningRoom.isChecked() ? true : false, cbGuestBedroom.isChecked() ? true : false);
                } else {
                    FragmentSettingListUserManagement.userAdapter.updateUser(etName.getText().toString(), pos, isAdmin = tvAccountContent.getText().toString().equals("ADMIN") ? true : false, etEmail.getText().toString(), etPassword.getText().toString(), cbLivingRoom.isChecked() ? true : false, cbDiningRoom.isChecked() ? true : false, cbGuestBedroom.isChecked() ? true : false);
                }
                getActivity().onBackPressed();
            } else {
                ((BaseActivity) getActivity()).showAlert("", "Invalid");
            }
        }
    }

    public void setDefault() {
        isEdit = false;
        tvNameContent.setText(name);
        rlPasswordConfirm.setVisibility(View.GONE);
        llName.setBackgroundColor(Color.TRANSPARENT);
        llDelete.setVisibility(View.GONE);
//        tvEmailContent.setText(etEmail.getText().toString());
//        tvPasswordContent.setText(etPassword.getText().toString());
//        tvPasswordConfirmContent.setText(etPasswordConfirm.getText().toString());

        cbDiningRoom.setVisibility(View.GONE);
        cbGuestBedroom.setVisibility(View.GONE);
        cbLivingRoom.setVisibility(View.GONE);
        viDiningRoom.setVisibility(View.VISIBLE);
        viGuestBedroom.setVisibility(View.VISIBLE);
        viLivingRoom.setVisibility(View.VISIBLE);

        ivArrow2.setVisibility(View.GONE);
        ivArrow1.setVisibility(View.GONE);

        etName.setVisibility(View.GONE);
        etPasswordConfirm.setVisibility(View.GONE);
        etEmail.setVisibility(View.GONE);
        etPassword.setVisibility(View.GONE);

        tvNameContent.setVisibility(View.VISIBLE);
        tvName.setVisibility(View.VISIBLE);
        tvPasswordConfirmContent.setVisibility(View.VISIBLE);
        tvEmailContent.setVisibility(View.VISIBLE);
        tvPasswordContent.setVisibility(View.VISIBLE);

        tvEdit.setText("EDIT");

        tvAccountContent.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarker));
        if (cbLivingRoom.isChecked())
            viLivingRoom.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.shape_background_audio_checkbox_enable));
        else
            viLivingRoom.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.shape_background_audio_checkbox_disable));
        if (cbGuestBedroom.isChecked())
            viGuestBedroom.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.shape_background_audio_checkbox_enable));
        else
            viGuestBedroom.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.shape_background_audio_checkbox_disable));
        if (cbDiningRoom.isChecked())
            viDiningRoom.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.shape_background_audio_checkbox_enable));
        else
            viDiningRoom.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.shape_background_audio_checkbox_disable));
    }

    public void setEdit() {
        isEdit = true;
        llName.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.shape_background_audio_edit));
        etName.setText(tvNameContent.getText().toString());
        etEmail.setText(tvEmailContent.getText().toString());
        etPassword.setText(tvPasswordContent.getText().toString());
        etPasswordConfirm.setText(tvPasswordConfirmContent.getText().toString());

        cbDiningRoom.setVisibility(View.VISIBLE);
        cbGuestBedroom.setVisibility(View.VISIBLE);
        cbLivingRoom.setVisibility(View.VISIBLE);
        viDiningRoom.setVisibility(View.GONE);
        viGuestBedroom.setVisibility(View.GONE);
        viLivingRoom.setVisibility(View.GONE);

        ivArrow2.setVisibility(View.VISIBLE);
        ivArrow1.setVisibility(View.VISIBLE);

        etName.setVisibility(View.VISIBLE);
        etPasswordConfirm.setVisibility(View.VISIBLE);
        etEmail.setVisibility(View.VISIBLE);
        etPassword.setVisibility(View.VISIBLE);


        tvNameContent.setVisibility(View.GONE);
        tvName.setVisibility(View.GONE);
        tvEmailContent.setVisibility(View.GONE);
        tvPasswordContent.setVisibility(View.GONE);
        tvPasswordConfirmContent.setVisibility(View.GONE);
        llDelete.setVisibility(View.VISIBLE);
        tvAccountContent.setTextColor(ContextCompat.getColor(getContext(), R.color.auluxaGreen));
        rlPasswordConfirm.setVisibility(View.VISIBLE);
        if (pos == FragmentSettingListUserManagement.userAdapter.getItemCount() - 1)
            llDelete.setVisibility(View.GONE);
        tvEdit.setText("SAVE");
    }

    @OnClick(R.id.ivArrow1)
    public void onClickAccountContent() {
        hideKeyboard();
        if (tvAccountContent.getText().equals("USER")) {
            tvAccountContent.setText("ADMIN");
        } else
            tvAccountContent.setText("USER");
    }

    @OnClick(R.id.ivArrow2)
    public void onClickAccountContent2() {
        onClickAccountContent();
    }

    @OnClick(R.id.tvAccountContent)
    public void onClickAccountContent3() {
        if (isEdit)
            onClickAccountContent();
    }

    @OnClick(R.id.ivBack)
    public void onBack() {
        hideKeyboard();
        getActivity().onBackPressed();
    }

    @OnClick(R.id.cbDiningRoom)
    public void hideKeyboard() {
        hideKeyboardIfNeed(getContext());
    }

    @OnClick(R.id.cbGuestBedroom)
    public void hideKeyboard2() {
        hideKeyboard();
    }

    @OnClick(R.id.cbLivingRoom)
    public void hideKeyboard3() {
        hideKeyboard();
    }

    @OnClick(R.id.tvDelete)
    public void onDeleteUser() {
        DialogInterface.OnClickListener onClickListener1 = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FragmentSettingListUserManagement.userAdapter.removeItem(pos);
                getActivity().onBackPressed();
            }
        };

        DialogInterface.OnClickListener onClickListener2 = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };
        ((BaseActivity) getActivity()).showAlertConfirmTwoButton("AULUXA!", "Are you sure delete this user?", getActivity(), "CANCEL", "DELETE", onClickListener2, onClickListener1);

    }

}