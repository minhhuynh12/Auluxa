package net.dirox.auluxa.security.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dirox.auluxa.R;
import net.dirox.auluxa.adapter.SecurityAlarmAdapter;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.databinding.FragmentSecurityBinding;
import net.dirox.auluxa.widget.SimpleDividerItemDecoration;

/**
 * Created by an on 6/20/2017.
 */

public class FragmentSecurityAlarm extends BaseFragment {

    private FragmentSecurityBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_security, container, false);
        mBinding.setName("ALARM");

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mBinding.recyclerView.setLayoutManager(mLayoutManager);

        SecurityAlarmAdapter adapter = new SecurityAlarmAdapter(getActivity());
        mBinding.recyclerView.setAdapter(adapter);
        mBinding.recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity(), R.drawable.line_divider));
        mBinding.securityTitleButtoneprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        return mBinding.getRoot();
    }
}
