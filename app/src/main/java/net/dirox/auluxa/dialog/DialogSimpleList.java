package net.dirox.auluxa.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import net.dirox.auluxa.R;

import java.util.List;

/**
 * Created by an on 6/16/2017.
 */

public class DialogSimpleList extends Dialog {

    public interface ListenerBasicListDialog {
        void onSelectItem(int selectedPosition, String stringSelected);
    }

    private Context mContext;
    private ListenerBasicListDialog mCallback;

    private LinearLayout rootLayout;
    private TextView txtTitle;
    private ListView listView;
    private List<String> stringList;


    public DialogSimpleList(@NonNull Context context) {
        super(context, R.style.DialogTheme);
        mContext = context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_layout_simple_list);
        rootLayout = (LinearLayout) findViewById(R.id.diaglog_simple_list_rootLayout);
        txtTitle = (TextView) findViewById(R.id.diaglog_simple_list_txtTitle);
        listView = (ListView) findViewById(R.id.diaglog_simple_list_listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mCallback != null) {
                    mCallback.onSelectItem(position, stringList.get(position));
                }
                dismiss();
            }
        });
    }


    public void showDialog(String title, List<String> stringList, ListenerBasicListDialog listener) {
        this.stringList = stringList;
        mCallback = listener;

        txtTitle.setText(title);
        txtTitle.setVisibility(View.GONE);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                android.R.layout.simple_list_item_1, android.R.id.text1, this.stringList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);
                textView.setTextColor(Color.WHITE);
                return textView;
            }
        };
        listView.setAdapter(adapter);

        show();
    }

}
