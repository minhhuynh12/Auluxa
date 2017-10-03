package net.dirox.auluxa.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import net.dirox.auluxa.R;
import net.dirox.auluxa.data.sample.AccessItem;

import java.util.List;

/**
 * Created by trungnq on 14/06/2017.
 */

public class LocalAccessAdapter extends ArrayAdapter<AccessItem> {

    private AccessItem itemAccess;
    private Activity activity;
    private List<AccessItem> list;
    private int row;
    LocalAccessAdapter.ViewHolder viewHolder;

    public LocalAccessAdapter(Activity activity, int resource, List<AccessItem> list) {
        super(activity, resource, list);
        this.activity = activity;
        this.row = resource;
        this.list = list;
    }

    public class ViewHolder {
        TextView tvLocalAccessName;
        EditText etLocalAccessEdit;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(row, null);
            viewHolder = new LocalAccessAdapter.ViewHolder();
            view.setTag(viewHolder);
        } else {
            viewHolder = (LocalAccessAdapter.ViewHolder) view.getTag();
        }

        if ((list == null) || ((position + 1) > list.size()))
            return view;

        itemAccess = list.get(position);

        viewHolder.tvLocalAccessName = (TextView) view.findViewById(R.id.tvLocalAccessItem);
        viewHolder.etLocalAccessEdit = (EditText) view.findViewById(R.id.etLocalAccessEdit);

        viewHolder.tvLocalAccessName.setText(itemAccess.getLocalAccessName());
        viewHolder.etLocalAccessEdit.setHint(itemAccess.getLocalAccessContent());
        viewHolder.etLocalAccessEdit.setText(viewHolder.etLocalAccessEdit.getText());

        return view;
    }
}
