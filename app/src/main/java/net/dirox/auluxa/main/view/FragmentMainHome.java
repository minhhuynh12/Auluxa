package net.dirox.auluxa.main.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.adapter.MainHomeAdapter;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.databinding.FragmentMainHomeBinding;
import net.dirox.auluxa.extras.Enumes;
import net.dirox.auluxa.utils.Constant;
import net.dirox.auluxa.utils.DateTimeUtil;
import net.dirox.auluxa.utils.Prefs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by MINH NGUYEN on 6/9/2017.
 */

public class FragmentMainHome extends BaseFragment {

    private FragmentInteraction mInteraction;
    FragmentMainHomeBinding mBinding;

    public static ArrayList<String> nameScenes;
    MainHomeAdapter adapter;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");

    public static FragmentMainHome newInstance() {

        Bundle args = new Bundle();

        FragmentMainHome fragment = new FragmentMainHome();
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentMainHome() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_home, container, false);

        /**
         * Custom Analog Clock FragmentMainHome
         */
        mBinding.analogClock.setAutoUpdate(true);
        mBinding.analogClock.init(getContext(), R.drawable.scenes_clock, R.drawable.hour_hand,
                R.drawable.minute_hand, R.drawable.second_hand, 0, false, false);
        mBinding.analogClock.setScale(1.5f);

        /**
         * Get Day Of Week
         */
        DateTimeUtil.getDayOfWeek(mBinding.tvDayNow);

        /**
         * Get Current Date
         */
        DateTimeUtil.getCurrentDate(mBinding.tvDateNow);

        String[] tmp = Prefs.loadArrayPrefs(getContext(), Constant.ARRAY_SCENES_NAME);

        if (tmp == null || tmp.length == 0) {
            nameScenes = new ArrayList<String>(Arrays.asList(tmp));
            nameScenes.add("COMFORTABLE");
            nameScenes.add("THEATRE SCENES");
            nameScenes.add("MOVIE SCENES");
            nameScenes.add("PARTY ALL NIGHT");
            nameScenes.add("MAGICAL SCENES");
            Prefs.saveArrayPrefs(getContext(), Constant.ARRAY_SCENES_NAME, nameScenes.toArray(new String[nameScenes.size()]));
        } else {
            nameScenes = new ArrayList<String>(Arrays.asList(tmp));
        }

        /**
         * Listview Scenes Home
         */
        adapter = new MainHomeAdapter(getActivity(), R.layout.layout_item_scenes, nameScenes) {
            @Override
            public void arrowClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putString(Constant.SCENES_NAME, nameScenes.get(position));
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SCENE_CREATE_STEP_2, bundle, Enumes.Direction.RIGHT_IN);

            }
        };

        mBinding.lvScheduledScenes.setAdapter(adapter);

        mBinding.tvCreateScenes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.tvCreateScenes.setTextColor(ContextCompat.getColor(getContext(), R.color.auluxaGreen));
                mBinding.imgAddScene.setImageResource(R.drawable.add_icon);
                Bundle bundle = new Bundle();
                bundle.putString(Constant.CREATE_SCENES_NAME, Constant.CREATE_SCENES_NAME);
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SCENE_CREATE_STEP_2, bundle, Enumes.Direction.RIGHT_IN);
            }
        });

        mBinding.imgAddScene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.tvCreateScenes.setTextColor(ContextCompat.getColor(getContext(), R.color.auluxaGreen));
                mBinding.imgAddScene.setImageResource(R.drawable.add_icon);
                Bundle bundle = new Bundle();
                bundle.putString(Constant.CREATE_SCENES_NAME, Constant.CREATE_SCENES_NAME);
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SCENE_CREATE_STEP_2, bundle, Enumes.Direction.RIGHT_IN);
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
