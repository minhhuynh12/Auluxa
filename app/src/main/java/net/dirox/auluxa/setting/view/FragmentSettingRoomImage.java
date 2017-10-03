package net.dirox.auluxa.setting.view;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.swipe.util.Attributes;
import com.google.gson.reflect.TypeToken;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.adapter.SettingsRoomImageAdapter;
import net.dirox.auluxa.common.activity.BaseActivity;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.sample.RoomImageItem;
import net.dirox.auluxa.databinding.LayoutSettingsRoomImageBinding;
import net.dirox.auluxa.main.view.MainActivity;
import net.dirox.auluxa.utils.Constant;
import net.dirox.auluxa.utils.Prefs;
import net.dirox.auluxa.utils.room_image.FileUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class FragmentSettingRoomImage extends BaseFragment {

    private static final String TAG = FragmentSettingRoomImage.class.getSimpleName();

    private LayoutSettingsRoomImageBinding mBinding;

    private FragmentInteraction mInteraction;

    SettingsRoomImageAdapter adapter;

    public static ArrayList<RoomImageItem> roomItems;
    int pos, id;

    private int GALLERY = 1, CAMERA = 2;

    public static FragmentSettingRoomImage newInstance() {

        Bundle args = new Bundle();

        FragmentSettingRoomImage fragment = new FragmentSettingRoomImage();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_settings_room_image, container, false);

        FileUtils.copyFileOrDir(getContext(), "Images");

        mBinding.btnBackSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });


        Type type = new TypeToken<ArrayList<RoomImageItem>>() {
        }.getType();

        roomItems = (ArrayList<RoomImageItem>) Prefs.loadObjectPrefs(getContext(), Constant.ROOM_IMAGE, type);

        if (roomItems == null) {
            roomItems = new ArrayList<>();
            roomItems.add(new RoomImageItem(0, "LIVING ROOM", "/data/data/"
                    + getContext().getApplicationContext().getPackageName()
                    + "/Images/back_livingroom.png"));
            roomItems.add(new RoomImageItem(1, "GAMING ROOM", "/data/data/"
                    + getContext().getApplicationContext().getPackageName()
                    + "/Images/back_livingroom.png"));
            roomItems.add(new RoomImageItem(2, "BED ROOM", "/data/data/"
                    + getContext().getApplicationContext().getPackageName()
                    + "/Images/back_bedroom.png"));
            roomItems.add(new RoomImageItem(3, "DINING ROOM", "/data/data/"
                    + getContext().getApplicationContext().getPackageName()
                    + "/Images/back_diningroom.png"));
            Prefs.applyObjectPrefs(getContext(), Constant.ROOM_IMAGE, roomItems);

        }

        id = roomItems.size();
        adapter = new SettingsRoomImageAdapter(getContext(), R.layout.layout_item_room_image, roomItems) {
            @Override
            public void onItemSelected(int position) {
                pos = position;
                showPictureDialog();
            }
        };

        adapter.setItemActionCallback(new SettingsRoomImageAdapter.ItemActionCallback() {
            @Override
            public void onItemEditName(int position, RoomImageItem data) {
                Prefs.applyObjectPrefs(getActivity().getApplicationContext(), Constant.ROOM_IMAGE, adapter.getDataList());
                updateDataNavigate();
            }

            @Override
            public void onItemDeleted(int position, RoomImageItem data) {
                adapter.removeDataAt(position);
                Prefs.applyObjectPrefs(getActivity().getApplicationContext(), Constant.ROOM_IMAGE, adapter.getDataList());
                updateDataNavigate();
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mBinding.recyclerRoomImage.setHasFixedSize(true);
        mBinding.recyclerRoomImage.setNestedScrollingEnabled(false);
        mBinding.recyclerRoomImage.setLayoutManager(layoutManager);
        mBinding.recyclerRoomImage.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    adapter.setLockEdittingText(false);
                    adapter.mItemManger.closeAllItems();
                } else {
                    adapter.setLockEdittingText(true);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        adapter.setMode(Attributes.Mode.Single);
        mBinding.recyclerRoomImage.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        mBinding.btnAddRoomImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setEnabled(false);
                adapter.atDataAt(0, new RoomImageItem(id++, "DEFAULT", "/data/data/"
                        + getContext().getApplicationContext().getPackageName()
                        + "/Images/back_livingroom.png"));
                Prefs.applyObjectPrefs(getContext(), Constant.ROOM_IMAGE, adapter.getDataList());
                mBinding.recyclerRoomImage.smoothScrollToPosition(0);
                adapter.mItemManger.closeAllItems();
                updateDataNavigate();
                v.setEnabled(true);
            }
        });

        mBinding.settingsmainSettingsBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity) getActivity()).hideKeyboard();
            }
        });

        return mBinding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInteraction = (FragmentInteraction) context;
    }

    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(getContext());
        pictureDialog.setTitle("What to Open?");
        String[] pictureDialogItems = {
                "Take Photo",
                "Choose from Phone Library",
                "Cancel"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                takePhotoFromCamera();
                                break;
                            case 1:
                                choosePhotoFromGallary();
                                break;
                            case 2:
                                dialog.dismiss();
                                break;
                        }
                    }

                });
        pictureDialog.show();
    }

    private void takePhotoFromCamera() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CAMERA},
                    CAMERA);
        } else {
            Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAMERA);
        }
    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == getActivity().RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), contentURI);
                    String path = FileUtils.saveToInternalStorage(getContext(), bitmap);
                    adapter.getDataList().get(pos).roomImagePath = path;
                    Prefs.applyObjectPrefs(getContext(), Constant.ROOM_IMAGE, adapter.getDataList());
                    adapter.notifyDataSetChanged();
                    updateDataNavigate();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            String path = FileUtils.saveToInternalStorage(getContext(), bitmap);
            adapter.getDataList().get(pos).roomImagePath = path;
            Prefs.applyObjectPrefs(getContext(), Constant.ROOM_IMAGE, adapter.getDataList());
            adapter.notifyDataSetChanged();
            updateDataNavigate();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void updateDataNavigate() {
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).updateNavigationDrawerData();
        }
    }

}
