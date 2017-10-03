package net.dirox.auluxa.video;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.dirox.auluxa.R;
import net.dirox.auluxa.adapter.InputAdapter;
import net.dirox.auluxa.common.fragment.BaseVideoRemoteFragment;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class VideoMatrixPageOneFragment extends BaseVideoRemoteFragment {

    @InjectView(R.id.rvInput)
    RecyclerView rvInput;
    @InjectView(R.id.rvOutput)
    RecyclerView rvOutput;
    @InjectView(R.id.tvTiteMenu)
    TextView tvTiteMenu;


    ArrayList<String> list;
    public static String titleMenu;

    public static VideoMatrixPageOneFragment newInstance(int page, String title, boolean visibleTopMenu) {
        VideoMatrixPageOneFragment fragmentFirst = new VideoMatrixPageOneFragment();
        Bundle args = new Bundle();
        args.putBoolean("VisibleTopMenu", visibleTopMenu);
        fragmentFirst.setArguments(args);
        titleMenu = title;
        return fragmentFirst;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_room_centre_video_matrix_page_one, container, false);
        ButterKnife.inject(this, view);
        tvTiteMenu.setText(titleMenu);
        list = new ArrayList<>();
        list.add("INPUT 7");
        list.add("INPUT 6");
        list.add("INPUT 5");
        list.add("INPUT 4");
        list.add("INPUT 3");
        list.add("INPUT 2");
        list.add("INPUT 1");
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvInput.setLayoutManager(layoutManager);
        adapter1 = new InputAdapter(list, new InputAdapter.OnClickItemListener() {
            @Override
            public void inClick() {
                resetViewDefault(view);
                adapter2.setSelect(-1);
            }
        });
        rvInput.setAdapter(adapter1);

        list = new ArrayList<>();
        list.add("OUTPUT 7");
        list.add("OUTPUT 6");
        list.add("OUTPUT 5");
        list.add("OUTPUT 4");
        list.add("OUTPUT 3");
        list.add("OUTPUT 2");
        list.add("OUTPUT 1");
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity());
        layoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
        rvOutput.setLayoutManager(layoutManager2);
        adapter2 = new InputAdapter(list, new InputAdapter.OnClickItemListener() {
            @Override
            public void inClick() {
                resetViewDefault(view);
                adapter1.setSelect(-1);
            }
        });
        rvOutput.setAdapter(adapter2);

        boolean visibleTopMenu = getArguments().getBoolean("VisibleTopMenu", true);
        if (visibleTopMenu == false) {
            view.findViewById(R.id.layoutTopMenu).setVisibility(View.GONE);
        }

        changeActionButtonImage(view, R.id.llMute, true, -1, -1, R.color.BgDarker);
        changeActionButtonImage(view, R.id.llHome, true, R.drawable.home_video, R.drawable.home_video, -1);
        changeActionButtonImage(view, R.id.llPower, true, R.drawable.power, R.drawable.power_white, -1);
        return view;
    }

}