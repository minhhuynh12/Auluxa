package net.dirox.auluxa.video;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import net.dirox.auluxa.R;
import net.dirox.auluxa.adapter.MediaServerAdapter;
import net.dirox.auluxa.common.fragment.BaseFragment;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class VideoMediaServerPageOneFragment extends BaseFragment {

    @InjectView(R.id.rvMediaServer)
    RecyclerView rvMediaServer;
    @InjectView(R.id.etSeach)
    EditText etSeach;

    @InjectView(R.id.tvTiteMenu)
    TextView tvTiteMenu;


    ArrayList<String> list;
    public static String titleMenu;
    MediaServerAdapter adapter;

    public static VideoMediaServerPageOneFragment newInstance(int page, String title, boolean visibleTopMenu) {
        VideoMediaServerPageOneFragment fragmentFirst = new VideoMediaServerPageOneFragment();
        Bundle args = new Bundle();
        args.putBoolean("VisibleTopMenu", visibleTopMenu);
        fragmentFirst.setArguments(args);
        titleMenu = title;
        return fragmentFirst;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_room_centre_video_media_server_page_one, container, false);
        ButterKnife.inject(this, view);
        tvTiteMenu.setText(titleMenu);

        boolean visibleTopMenu = getArguments().getBoolean("VisibleTopMenu", true);
        if (visibleTopMenu == false) {
            view.findViewById(R.id.layoutTopMenu).setVisibility(View.GONE);
        }

        list = new ArrayList<>();
        list.add("Transformers");
        list.add("Wonder Woman");
        list.add("Harry Potter");
        list.add("Kingsman");
        list.add("Spider-Man");
        list.add("Resident Evil");
        list.add("Car 3");
        list.add("Edge of Innocence");
        list.add("Alien 2017");
        list.add("Fast & Furious 8");
        list.add("Power Rangers");
        list.add("Em Chưa 18");
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvMediaServer.setLayoutManager(layoutManager);
        adapter = new MediaServerAdapter(list);
        rvMediaServer.setAdapter(adapter);
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

    @OnClick(R.id.rvMediaServer)
    public void hideKeyBoard() {
        hideKeyboardIfNeed(getContext());
    }

}