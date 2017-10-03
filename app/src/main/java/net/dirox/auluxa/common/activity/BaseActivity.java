package net.dirox.auluxa.common.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import net.dirox.auluxa.common.App;
import net.dirox.auluxa.common.dagger.ActivityComponent;
import net.dirox.auluxa.common.dagger.ActivityModule;
import net.dirox.auluxa.common.dagger.DaggerActivityComponent;
import net.dirox.auluxa.dialog.DialogSimpleList;

import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public abstract class BaseActivity extends AppCompatActivity {
    private ActivityComponent activityComponent;

    public ActivityComponent abstractActivityComponent() {
        buildActivityComponent();
        return activityComponent;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistableBundle) {
        super.onCreate(savedInstanceState, persistableBundle);
        buildActivityComponent();
    }

    private void buildActivityComponent() {
        if (activityComponent == null) {
            activityComponent = DaggerActivityComponent.builder()
                    .appComponent(((App) getApplication()).getComponent())
                    .activityModule(new ActivityModule(this))
                    .build();
        }
    }

    public void showDialogBasicList(String title, List<String> stringList, DialogSimpleList.ListenerBasicListDialog listener) {
        DialogSimpleList dialogSimpleList = new DialogSimpleList(this);
        dialogSimpleList.showDialog(title, stringList, listener);

    }

    public void hideKeyboard() {
        // Hide keyboard
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void showAlert(String title, String message) {
        AlertDialog mLogOutAlert = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", (dialog, i) -> {
                    dialog.dismiss();
                })
                .create();
        mLogOutAlert.show();
    }

    public void showAlertConfirm(String title, String message, Activity activity) {
        AlertDialog mLogOutAlert = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", (dialog, i) -> {
                    activity.onBackPressed();
                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                .create();
        mLogOutAlert.show();
    }

    public void showAlertConfirmTwoButton(String title, String message, Activity activity, String tv1, String tv2, DialogInterface.OnClickListener onClickListener1, DialogInterface.OnClickListener onClickListener2) {
        AlertDialog mLogOutAlert = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(tv1, onClickListener1)
                .setNegativeButton(tv2, onClickListener2)
                .create();
        mLogOutAlert.show();
    }


}
