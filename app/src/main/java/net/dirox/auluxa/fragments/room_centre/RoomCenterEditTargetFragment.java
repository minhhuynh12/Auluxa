package net.dirox.auluxa.fragments.room_centre;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.bumptech.glide.util.ExceptionCatchingInputStream;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.activity.main.view.MainActivity;
import net.dirox.auluxa.common.activity.BaseActivity;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.databinding.LayoutEditTargetFragmentBinding;
import net.dirox.auluxa.extras.Enumes;


/**
 * Created by an on 6/15/2017.
 */

public class RoomCenterEditTargetFragment extends BaseFragment  {

    private FragmentInteraction mInteraction;
    private View rootView;
    private LayoutEditTargetFragmentBinding mBinding;

    public RoomCenterEditTargetFragment() {
    }

    /*@Inject
    ClimateControlContract.Presenter mPresenter;*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_edit_target_fragment, container, false);
        mBinding.btnCancelEditTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_ENERGY, null, Enumes.Direction.FLIP_IN);
            }
        });


        mBinding.checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ((BaseActivity) getActivity()).hideKeyboard();
                mBinding.textView.setCursorVisible(false);
                mBinding.textView.setBackground(null);
                if (isChecked) {
                    if(! mBinding.textView.getText().toString().contains("kW"))
                        mBinding.textView.setText("kW " + mBinding.textView.getText());
                } else {
                    String textValue = mBinding.textView.getText().toString();
                    if(textValue.contains("kW "))
                        textValue = textValue.replace("kW ", "");
                    //mBinding.textView.setBackground(getResources().getDrawable(R.drawable.edittext_border));
                }
            }
        });

        mBinding.fragmentEditTargetTxtview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((BaseActivity) getActivity()).hideKeyboard();
                mBinding.textView.setCursorVisible(false);
                mBinding.textView.setBackground(null);
                if(! mBinding.textView.getText().toString().contains("kW"))
                    mBinding.textView.setText("kW " + mBinding.textView.getText());
            }
        });


        mBinding.btnBackEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((BaseActivity) getActivity()).hideKeyboard();
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_ENERGY, null, Enumes.Direction.RIGHT_IN);

            }
        });

        mBinding.btnSaveEditTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_ENERGY, null, Enumes.Direction.FLIP_OUT);
            }
        });

        mBinding.btnCancelEditTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_ENERGY, null, Enumes.Direction.FLIP_OUT);

            }
        });

        mBinding.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBinding.textView.setCursorVisible(true);
                String textValue = mBinding.textView.getText().toString();

                if(textValue.contains("kW "))
                    textValue = textValue.replace("kW ", "");

                mBinding.textView.setText(textValue);
                mBinding.textView.setBackground(getResources().getDrawable(R.drawable.edittext_border));
            }
        });


        return mBinding.getRoot();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInteraction = (FragmentInteraction) context;
    }


}
