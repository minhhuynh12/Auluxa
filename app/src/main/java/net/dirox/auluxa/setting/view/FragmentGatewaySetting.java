package net.dirox.auluxa.setting.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.adapter.LocalAccessAdapter;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.sample.AccessItem;
import net.dirox.auluxa.databinding.FragmentGatewaySettingBinding;
import net.dirox.auluxa.extras.Enumes;
import net.dirox.auluxa.login.view.LoginActivity;
import net.dirox.auluxa.setting.FragmentGatewaySettingContract;
import net.dirox.auluxa.utils.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MINH NGUYEN on 6/13/2017.
 */

public class FragmentGatewaySetting extends BaseFragment implements FragmentGatewaySettingContract.View {

    private FragmentGatewaySettingBinding mBinding;

    List<AccessItem> listLocalAccess;
    List<AccessItem> listremoteAccess;
    LocalAccessAdapter adapter;

    public static FragmentGatewaySetting newInstance() {

        Bundle args = new Bundle();

        FragmentGatewaySetting fragment = new FragmentGatewaySetting();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_gateway_setting, container, false);

        listLocalAccess = new ArrayList<AccessItem>();

        listLocalAccess.add(new AccessItem("Connection", "Enter Connection Name"));
        listLocalAccess.add(new AccessItem("Gateway", "12345"));
        listLocalAccess.add(new AccessItem("MAC Address", "Enter Connection Name"));
        listLocalAccess.add(new AccessItem("Local IP", "192.167.1.1"));

        adapter = new LocalAccessAdapter(getActivity(), R.layout.item_gateway_settings, listLocalAccess);
        mBinding.lvGatewaySettings.setAdapter(adapter);

        mBinding.tvLocalAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hideKeyboardIfNeed(getContext());

                mBinding.tvLocalAccess.setTextColor(getResources().getColor(R.color.auluxaGreen));
                mBinding.tvRemoteAccess.setTextColor(getResources().getColor(R.color.headerTitleTextColor));

                listLocalAccess = new ArrayList<AccessItem>();

                listLocalAccess.add(new AccessItem("Connection", "Enter Connection Name"));
                listLocalAccess.add(new AccessItem("Gateway", "12345"));
                listLocalAccess.add(new AccessItem("MAC Address", "Enter Connection Name"));
                listLocalAccess.add(new AccessItem("Local IP", "192.167.1.1"));

                adapter = new LocalAccessAdapter(getActivity(), R.layout.item_gateway_settings, listLocalAccess);
                mBinding.lvGatewaySettings.setAdapter(adapter);
            }
        });

        mBinding.tvRemoteAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hideKeyboardIfNeed(getContext());

                mBinding.tvRemoteAccess.setTextColor(getResources().getColor(R.color.auluxaGreen));
                mBinding.tvLocalAccess.setTextColor(getResources().getColor(R.color.headerTitleTextColor));

                listremoteAccess = new ArrayList<AccessItem>();

                listremoteAccess.add(new AccessItem("Host IP", "gmail.org"));
                listremoteAccess.add(new AccessItem("Port", "12345"));

                adapter = new LocalAccessAdapter(getActivity(), R.layout.item_gateway_settings, listremoteAccess);
                mBinding.lvGatewaySettings.setAdapter(adapter);


            }
        });

        mBinding.btnSaveGateway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.putExtra(Constant.SETTING_GATEWAY_KEY, Constant.SETTING_GATEWAY_VALUE);
                startActivity(intent);
            }
        });

        mBinding.btnCancelGateway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.putExtra(Constant.SETTING_GATEWAY_KEY, Constant.SETTING_GATEWAY_VALUE);
                startActivity(intent);
            }
        });

        mBinding.imgGatewaySetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.putExtra(Constant.SETTING_GATEWAY_KEY, Constant.SETTING_GATEWAY_VALUE);
                startActivity(intent);
            }
        });

        return mBinding.getRoot();
    }

    /*@Override
    public void showLocalAccess() {
        mBinding.tvLocalAccess.setTextColor(getResources().getColor(R.color.MainTeal));
        mBinding.tvRemoteAccess.setTextColor(getResources().getColor(R.color.GatewayTitle));
    }

    @Override
    public void showRemoteAccess() {
        mBinding.tvLocalAccess.setTextColor(getResources().getColor(R.color.MainTeal));
        mBinding.tvRemoteAccess.setTextColor(getResources().getColor(R.color.GatewayTitle));
    }

    @Override
    public void saveGatewaySetting() {
        getActivity().finish();
    }

    @Override
    public void cancelGatewaySetting() {
        getActivity().finish();
    }*/


}
