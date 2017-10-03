package net.dirox.auluxa.fragments.room_centre;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.adapter.ApplianceGridAdapter;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.sample.RoomImageItem;
import net.dirox.auluxa.databinding.FragmentRoomCentreBinding;
import net.dirox.auluxa.extras.Enumes;
import net.dirox.auluxa.utils.Constant;
import net.dirox.auluxa.utils.ScreenUtil;

/**
 * Created by an on 6/12/2017.
 */

public class RoomCentreFragment extends BaseFragment {

    private FragmentInteraction mInteraction;
    private FragmentRoomCentreBinding mBinding;
    private RoomImageItem mRoomItem;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRoomItem = getArguments().getParcelable(Constant.ROOM_IMAGE_ITEM);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_room_centre, container, false);
        mBinding.setRoomName(mRoomItem.roomName);

        GridView gridView = (GridView) mBinding.getRoot().findViewById(R.id.room_category_grid);

        TypedValue tv = new TypedValue();
        getContext().getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true);
        int actionBarHeight = getResources().getDimensionPixelSize(tv.resourceId);

        int gridHeight = ScreenUtil.getScreenHeight(getContext())
                - ScreenUtil.getStatusBarHeight(getContext())
                - actionBarHeight
                - ScreenUtil.dpToPx(getContext(), 45) // header
                - ScreenUtil.dpToPx(getContext(), 10);//10dp margin

        ViewGroup.LayoutParams layoutParams = gridView.getLayoutParams();
        layoutParams.height = gridHeight;
        gridView.setLayoutParams(layoutParams);

        int rowHeight = (gridHeight - 2 * ScreenUtil.dpToPx(getContext(), 5)) / 3;

        ApplianceGridAdapter adapter = new ApplianceGridAdapter(RoomCentreFragment.this, rowHeight) {
            @Override
            public void onItemClicked(int position) {
                Enumes.FragmentType fragmentType = Enumes.FragmentType.FRAGMENT_DEFAULT_NULL;
                Bundle bundle = new Bundle();
                bundle.putParcelable(Constant.ROOM_IMAGE_ITEM, mRoomItem);

                switch (position) {
                    case 0:
                        fragmentType = Enumes.FragmentType.FRAGMENT_ROOM_CENTRE_CLIMATE;
                        break;
                    case 1:
                        fragmentType = Enumes.FragmentType.FRAGMENT_ROOM_CENTRE_LIGHTS;
                        break;
                    case 2:
                        fragmentType = Enumes.FragmentType.FRAGMENT_ROOM_CENTRE_AUDIO;
                        break;
                    case 3:
                        fragmentType = Enumes.FragmentType.FRAGMENT_ROOM_CENTRE_VIDEO;
                        break;
                    case 4:
                        fragmentType = Enumes.FragmentType.FRAGMENT_ROOM_CENTRE_SHADES;
                        break;
                    case 5:
                        fragmentType = Enumes.FragmentType.FRAGMENT_ROOM_CENTRE_OTHERS;
                        break;
                }

                if (mInteraction != null) {
                    mInteraction.openFragment(fragmentType, bundle, Enumes.Direction.RIGHT_IN);
                }
            }
        };

        gridView.setAdapter(adapter);

        return mBinding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInteraction = (FragmentInteraction) context;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
