package net.dirox.auluxa.video;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.adapter.UserAdapter;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.extras.Enumes;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class FragmentSettingListUserManagement extends BaseFragment {

    @InjectView(R.id.rvUser)
    RecyclerView rvUser;

    public ArrayList<User> list;
    public static UserAdapter userAdapter;
    private FragmentInteraction mInteraction;
    LinearLayoutManager layoutManager;


    public static FragmentSettingListUserManagement newInstance() {
        FragmentSettingListUserManagement fragmentFirst = new FragmentSettingListUserManagement();
        Bundle args = new Bundle();
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = new ArrayList<>();
        list.add(new User("ADMIN 1", true, "", "1234", false, false, false));
        list.add(new User("USER 1", false, "", "1234", false, false, false));
        list.add(new User("USER 2", false, "", "1234", false, false, false));
        list.add(new User("USER 3", false, "", "1234", false, false, false));
        list.add(new User("USER 4", false, "", "1234", false, false, false));
        list.add(new User("USER 5", false, "", "1234", false, false, false));
        userAdapter = new UserAdapter(list, new UserAdapter.OnClickItemUserListener() {
            @Override
            public void inClick(int pos) {
                Bundle bundle = new Bundle();
                boolean isEdit = false;
                bundle.putBoolean("isEdit", isEdit);
                bundle.putInt("position", pos);
                bundle.putString("name", list.get(pos).name);
                bundle.putBoolean("isAdmin", list.get(pos).isAdmin);
                bundle.putString("Object", new Gson().toJson(list.get(pos)));
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_USER_MANAGEMENT, bundle, Enumes.Direction.RIGHT_IN);
            }

            @Override
            public void inClickAddItem(int pos) {
                Bundle bundle = new Bundle();
                boolean isEdit = true;
                bundle.putBoolean("isEdit", isEdit);
                bundle.putInt("position", pos);
                bundle.putString("name", "ADD ACCOUNT");
                bundle.putBoolean("isAdmin", list.get(pos).isAdmin);
                bundle.putString("Object", new Gson().toJson(list.get(pos)));
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SETTING_USER_MANAGEMENT, bundle, Enumes.Direction.RIGHT_IN);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting_list_user_management, container, false);
        ButterKnife.inject(this, view);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvUser.setLayoutManager(layoutManager);
        rvUser.setAdapter(userAdapter);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInteraction = (FragmentInteraction) context;
    }

    @OnClick(R.id.ivBack)
    public void onBack() {
        hideKeyboardIfNeed(getContext());
        getActivity().onBackPressed();
    }
}