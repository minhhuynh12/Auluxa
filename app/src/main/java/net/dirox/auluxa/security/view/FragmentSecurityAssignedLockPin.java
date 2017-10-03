package net.dirox.auluxa.security.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.google.gson.reflect.TypeToken;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.adapter.AssignedLockPinAdapter;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.sample.AssignedLockPinItem;
import net.dirox.auluxa.databinding.FragmentSecurityAssignedLockPinBinding;
import net.dirox.auluxa.extras.Enumes;
import net.dirox.auluxa.utils.Constant;
import net.dirox.auluxa.utils.Prefs;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by DoanThiPhuongHuyen on 27/07/2017.
 */

public class FragmentSecurityAssignedLockPin extends BaseFragment {

    private FragmentSecurityAssignedLockPinBinding mBinding;
    private Parcelable recyclerViewState;
    private FragmentInteraction mInteraction;
    private AssignedLockPinAdapter assignedLockPinAdapter;
    ImageView imageViewBackPin;
    Button btnShowPasswordPin;
    public  boolean showPin = true;
     public static ArrayList<AssignedLockPinItem> arrayList;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
      mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_security_assigned_lock_pin, container, false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        Bundle bundler  = getArguments();
        Type typeArray = new TypeToken<ArrayList<AssignedLockPinItem>>() {
        }.getType();
       //Prefs.clearPrefs(getContext());
        arrayList = (ArrayList<AssignedLockPinItem>) Prefs.loadObjectPrefs(getContext(), Constant.ARRAY_PIN, typeArray);

        if(arrayList == null) {
            arrayList = new ArrayList<>();
            arrayList.add(new AssignedLockPinItem("JOHN DOE", "09240520", R.drawable.arrow, "PERMANENT", "TO BE ACTIVATED",0));
            arrayList.add(new AssignedLockPinItem("AMAR DHILLON", "05200924", R.drawable.arrow, "PERMANENT", "TO BE ACTIVATED",0));
            arrayList.add(new AssignedLockPinItem("SUSAN MCMILLION", "90891996", R.drawable.arrow, "PERMANENT", "TO BE ACTIVATED",0));
            arrayList.add(new AssignedLockPinItem("KLEO HIGGINS", "66571726", R.drawable.arrow, "PERMANENT", "USED & EXPIRED",0));
            arrayList.add(new AssignedLockPinItem("CREATE LOCK PIN", "", R.drawable.plus_assgined_lock_pin));
            Prefs.applyObjectPrefs(getContext(), Constant.ARRAY_PIN, arrayList);
        }
        imageViewBackPin = (ImageView) mBinding.getRoot().findViewById(R.id.btnBackPin);
        btnShowPasswordPin = (Button) mBinding.getRoot().findViewById(R.id.btnShowPasswordPin);
        imageViewBackPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("Pin", 1);
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SECURITY_LOCK, bundle, Enumes.Direction.RIGHT_IN);
            }
        });

        assignedLockPinAdapter = new AssignedLockPinAdapter(arrayList, getActivity().getApplicationContext(), false) {
            @Override
            public void onItemSelected(int position) {
                int type =0;
                if (position != (arrayList.size() - 1)) {
                    // Detail of Pin
                    Bundle bundle = new Bundle();
                    type = arrayList.get(position).getType();
                    bundle.putSerializable("Object",arrayList.get(position));
                    bundle.putInt("Position", position);
                    if (type == 3) {

                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SECURITY_DURATION_PIN, bundle, Enumes.Direction.RIGHT_IN);
                    }
                    else{

                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SECURITY_ASSIGNED_PIN, bundle, Enumes.Direction.RIGHT_IN);
                    }
                }
                else
                {
                    // Create lock pin
                    mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SECURITY_CREATE_LOCK_PIN, null, Enumes.Direction.RIGHT_IN);
                }
            }
        };
        btnShowPasswordPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerViewState = mBinding.AssignedLockPinShowPassword.getLayoutManager().onSaveInstanceState();

                if(showPin ==true){
                    assignedLockPinAdapter.setFlag(true);
                    btnShowPasswordPin.setBackgroundResource(R.drawable.hidepin);
                    assignedLockPinAdapter.notifyDataSetChanged();
                    mBinding.AssignedLockPinShowPassword.getLayoutManager().onRestoreInstanceState(recyclerViewState);
                    mBinding.AssignedLockPinShowPassword.setAdapter(assignedLockPinAdapter);
                    showPin = false;
                } else {

                    btnShowPasswordPin.setBackgroundResource(R.drawable.showpin);
                    showPin = true;
                    assignedLockPinAdapter.setFlag(false);
                    assignedLockPinAdapter.notifyDataSetChanged();
                    mBinding.AssignedLockPinShowPassword.getLayoutManager().onRestoreInstanceState(recyclerViewState);
                    mBinding.AssignedLockPinShowPassword.setAdapter(assignedLockPinAdapter);

                }
            }
        });
        mBinding.AssignedLockPinShowPassword.setLayoutManager(layoutManager);
     //   mBinding.AssignedLockPinShowPassword.addItemDecoration(new RecyclerDividerItemDecoration(getActivity(), 39, 38));
        mBinding.AssignedLockPinShowPassword.setAdapter(assignedLockPinAdapter);

        return mBinding.getRoot();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInteraction = (FragmentInteraction) context;
    }
}
