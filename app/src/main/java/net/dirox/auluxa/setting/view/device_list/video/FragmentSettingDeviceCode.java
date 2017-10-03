package net.dirox.auluxa.setting.view.device_list.video;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.dirox.auluxa.R;
import net.dirox.auluxa.adapter.CodeAdapter;
import net.dirox.auluxa.adapter.MediaServerAdapter;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.utils.Prefs;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class FragmentSettingDeviceCode extends BaseFragment {

    @InjectView(R.id.rvCode)
    RecyclerView rvCode;
    @InjectView(R.id.etSeach)
    EditText etSeach;

    @InjectView(R.id.viTop)
    View viTop;
    @InjectView(R.id.tvTitle)
    TextView tvTitle;
    @InjectView(R.id.viBot)
    View viBot;
    @InjectView(R.id.rlSelect)
    RelativeLayout rlSelect;
    @InjectView(R.id.lnMenu)
    LinearLayout lnMenu;

    @InjectView(R.id.tvMenu1)
    TextView tvMenu1;
    @InjectView(R.id.tvSelect)
    TextView tvSelect;
    @InjectView(R.id.tvMenu2)
    TextView tvMenu2;
    @InjectView(R.id.tvMenu3)
    TextView tvMenu3;
    @InjectView(R.id.tvMenu4)
    TextView tvMenu4;
    @InjectView(R.id.rlLoading)
    RelativeLayout rlLoading;
    @InjectView(R.id.rlContent)
    RelativeLayout rlContent;
    @InjectView(R.id.tvCountDown)
    TextView tvCountDown;
    @InjectView(R.id.llSuccess)
    LinearLayout llSuccess;
    @InjectView(R.id.llLoading)
    LinearLayout llLoading;

    ArrayList<String> list;
    ArrayList<String> list2;
    public static String title1;
    CodeAdapter adapter;
    int isSelect = 0;

    static boolean visibleTopMenu;

    public static FragmentSettingDeviceCode newInstance(String title, boolean _visibleTopMenu) {
        FragmentSettingDeviceCode fragmentFirst = new FragmentSettingDeviceCode();
        Bundle args = new Bundle();
        fragmentFirst.setArguments(args);
        title1 = title;
        visibleTopMenu = _visibleTopMenu;
        return fragmentFirst;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting_device_list_video_select_manufacturer, container, false);
        ButterKnife.inject(this, view);
        tvTitle.setText(title1);


        if (!visibleTopMenu) {
            viBot.setVisibility(View.GONE);
            viTop.setVisibility(View.GONE);
            rlSelect.setVisibility(View.GONE);
        }

        list = new ArrayList<String>();
        if (title1.equals("SELECT MANUFACTURER")) {
            list.add("ABEX");
            list.add("ACCESSHD");
            list.add("ACCOR");
            list.add("ACER");
        } else {
            list.add("Y512F2");
            list.add("EL512F2");
            list.add("UI7393");
            list.add("QL512F2");
            list.add("REMOTE209333");
            list.add("REMOTE 81");
            list.add("KJ213213");
            list.add("VN12323");
            list.add("AL123211");
            list.add("MN20907");
            list.add("PR23188");
            list.add("EC87811");
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvCode.setLayoutManager(layoutManager);
        adapter = new CodeAdapter(list, visibleTopMenu, new CodeAdapter.OnClickItem() {
            @Override
            public void onClick() {
                lnMenu.setVisibility(View.GONE);
            }
        });
        rvCode.setAdapter(adapter);

        rvCode.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                hideKeyboardIfNeed(getContext());
                lnMenu.setVisibility(View.GONE);
            }
        });

        etSeach.setOnKeyListener(onKeyListener);
        etSeach.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (etSeach.getText().toString() == null || etSeach.getText().toString().trim().isEmpty())
                    adapter.reloadList(list);
                else
                    adapter.filter(etSeach.getText().toString(), list);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return view;
    }

    @OnClick(R.id.llSave)
    public void onSave() {
        if (visibleTopMenu)
            Prefs.setIntPrefs(getContext(), "CODE", (adapter.getSelect() != -1)?292:0);
        lnMenu.setVisibility(View.GONE);
        onBackAndHideKeyBoard();
    }

    @OnClick(R.id.llCancel)
    public void onCancel() {
        lnMenu.setVisibility(View.GONE);
        onBackAndHideKeyBoard();
    }

    @OnClick(R.id.ivBack)
    public void onBack() {
        onBackAndHideKeyBoard();
    }

    @OnClick(R.id.rlSelect)
    public void onMenu() {
        if (lnMenu.getVisibility() == View.VISIBLE) {
            lnMenu.setVisibility(View.GONE);
        } else {
            lnMenu.setVisibility(View.VISIBLE);
        }
        hideKeyboardIfNeed(getContext());
    }

    @OnClick(R.id.tvMenu1)
    public void onClickTVRemote() {
        if (isSelect != 1) {
            isSelect = 1;
            tvSelect.setText(tvMenu1.getText());
            setChangeColorItemMenuSelect(tvMenu1);

        }
        lnMenu.setVisibility(View.GONE);
    }

    @OnClick(R.id.tvMenu2)
    public void onClickTVRemote2() {
        if (isSelect != 2) {
            isSelect = 2;
            tvSelect.setText(tvMenu2.getText());
            setChangeColorItemMenuSelect(tvMenu2);

        }
        lnMenu.setVisibility(View.GONE);
    }

    @OnClick(R.id.tvMenu3)
    public void onClickTVRemote3() {
        if (isSelect != 3) {
            isSelect = 3;
            tvSelect.setText(tvMenu3.getText());
            setChangeColorItemMenuSelect(tvMenu3);

        }
        lnMenu.setVisibility(View.GONE);
    }

    @OnClick(R.id.llSuccess)
    public void onSuccess() {
        onBackAndHideKeyBoard();
    }

    @OnClick(R.id.tvMenu4)
    public void onClickTVRemote4() {
        if (isSelect != 4) {
            tvTitle.setText(tvMenu4.getText());
            isSelect = 4;
            rlLoading.setVisibility(View.VISIBLE);
            rlContent.setVisibility(View.GONE);
            llSuccess.setVisibility(View.GONE);
            llLoading.setVisibility(View.VISIBLE);
            new CountDownTimer(5000, 1000) {
                public void onTick(long millisUntilFinished) {
                    tvCountDown.setText("" + millisUntilFinished / 1000);
                }

                public void onFinish() {
                    llSuccess.setVisibility(View.VISIBLE);
                    llLoading.setVisibility(View.GONE);
                }

            }.start();
        }
        lnMenu.setVisibility(View.GONE);
    }


    public void setChangeColorItemMenuSelect(TextView tvMenuSelect) {
        tvMenu1.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        tvMenu2.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        tvMenu3.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        tvMenu4.setTextColor(ContextCompat.getColor(getContext(), R.color.white));

        tvMenuSelect.setTextColor(ContextCompat.getColor(getContext(), R.color.auluxaGreen));

    }


}