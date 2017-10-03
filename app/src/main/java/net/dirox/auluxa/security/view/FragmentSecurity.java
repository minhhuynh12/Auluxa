package net.dirox.auluxa.security.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.adapter.SecurityMainAdapter;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.databinding.FragmentSecurityBinding;
import net.dirox.auluxa.extras.Enumes;
import net.dirox.auluxa.security.SecurityFragmentContract;
import net.dirox.auluxa.widget.SimpleDividerItemDecoration;

/**
 * Created by MINH NGUYEN on 6/12/2017.
 */

public class FragmentSecurity extends BaseFragment implements SecurityFragmentContract.View {

    private FragmentSecurityBinding mBinding;
    private FragmentInteraction mInteraction;

    public static FragmentSecurity newInstance() {

        Bundle args = new Bundle();

        FragmentSecurity fragment = new FragmentSecurity();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_security, container, false);
        mBinding.setName("SECURITY");



        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mBinding.recyclerView.setLayoutManager(mLayoutManager);
        mBinding.recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity(), R.drawable.line_divider));

        SecurityMainAdapter adapter = new SecurityMainAdapter(getActivity()) {
            @Override
            public void onItemSelected(int position, String stringValue) {
                switch (position) {
                    case 0:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_CONNECT_TO_IGLOOHOME, null, Enumes.Direction.RIGHT_IN);
                        break;

                    case 1:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SECURITY_SURVELLIANCE, null, Enumes.Direction.RIGHT_IN);
                        break;

                    case 2:
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SECURITY_VIDEO_PHONE, null, Enumes.Direction.RIGHT_IN);
                        break;

                    case 3:
                       break;
                }
            }
        };
        mBinding.recyclerView.setAdapter(adapter);
        mBinding.securityTitleButtoneprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_MAIN_HOME, null, Enumes.Direction.RIGHT_IN);
            }
        });

        return mBinding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInteraction = (FragmentInteraction) context;
    }

}
