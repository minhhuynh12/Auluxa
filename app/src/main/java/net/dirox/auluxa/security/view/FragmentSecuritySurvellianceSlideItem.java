package net.dirox.auluxa.security.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dirox.auluxa.R;
import net.dirox.auluxa.data.sample.SecurityItem;
import net.dirox.auluxa.databinding.LayoutItemSecuritySurvellianceSlideBinding;

/**
 * Created by an on 6/22/2017.
 */

public class FragmentSecuritySurvellianceSlideItem extends Fragment {

    public static Fragment newInstance(SecurityItem.CameraItem cameraItem) {
        Fragment fragment = new FragmentSecuritySurvellianceSlideItem();
        Bundle bundle = new Bundle();
        bundle.putString("Name", cameraItem.name);
        bundle.putInt("ResourceId", cameraItem.resourceId);
        fragment.setArguments(bundle);
        return fragment;
    }


    private LayoutItemSecuritySurvellianceSlideBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_item_security_survelliance_slide, container, false);

        int resourceId = getArguments().getInt("ResourceId");
        mBinding.imageView.setImageResource(resourceId);
        return mBinding.getRoot();
    }


}
