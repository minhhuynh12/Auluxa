package net.dirox.auluxa.security.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.SecuritySurvellianceSlideShowActivity;
import net.dirox.auluxa.adapter.SecuritySurvellianceAdapter;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.databinding.FragmentSecuritySurvellianceBinding;
import net.dirox.auluxa.widget.SimpleDividerItemDecoration;

/**
 * Created by an on 6/20/2017.
 */

public class FragmentSecuritySurvelliance extends BaseFragment {

    private FragmentSecuritySurvellianceBinding mBinding;
    private int focusItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        focusItem = 0;
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_security_survelliance, container, false);
        mBinding.setName("SURVELLIANCE");
        mBinding.setCam("Back door cam");

        mBinding.securityTitleButtoneprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        mBinding.securitySurvellianceBtnExtend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SecuritySurvellianceSlideShowActivity.class);
                intent.putExtra("Position", focusItem);
                startActivity(intent);
            }
        });

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mBinding.recyclerView.setLayoutManager(mLayoutManager);

        SecuritySurvellianceAdapter adapter = new SecuritySurvellianceAdapter(getActivity()) {
            @Override
            public void onSelectItem(int position, String name, int resourceId) {
                focusItem = position;
                mBinding.setCam(name);
                mBinding.imageView.setImageResource(resourceId);
            }
        };
        mBinding.recyclerView.setAdapter(adapter);
        mBinding.recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity(), R.drawable.line_divider));

        return mBinding.getRoot();
    }
}
