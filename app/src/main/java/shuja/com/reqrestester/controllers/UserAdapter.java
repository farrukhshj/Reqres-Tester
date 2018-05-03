package shuja.com.reqrestester.controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import shuja.com.reqrestester.R;
import shuja.com.reqrestester.model.Datum;

public class UserAdapter extends BaseAdapter {

    private Context mContext;
    private List<Datum> mUserList;
    private LayoutInflater mLayoutInflater;


    public UserAdapter(Context context, List<Datum> userList){
        mContext = context;
        mUserList = userList;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mUserList.size();
    }

    @Override
    public Object getItem(int i) {
        return mUserList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView = view;
        ViewHolder viewHolder = null;

        if (rowView == null) {
            rowView= mLayoutInflater.inflate(R.layout.user_row_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.tvFirstName = rowView.findViewById(R.id.tv_first_name);
            viewHolder.tvLastName = rowView.findViewById(R.id.tv_last_name);
            viewHolder.ivAvatar = rowView.findViewById(R.id.iv_avatar);

            rowView.setTag(viewHolder);
            bindDataToViews(i, viewHolder);
        }
        return rowView;
    }

    private void bindDataToViews(int position, ViewHolder viewHolder) {
        viewHolder.tvFirstName.setText(mUserList.get(position).getFirstName());
        viewHolder.tvLastName.setText(mUserList.get(position).getLastName());
        Picasso.get().load(mUserList.get(position).getAvatar()).into(viewHolder.ivAvatar);
    }

    private static class ViewHolder{
        TextView tvFirstName;
        TextView tvLastName;
        ImageView ivAvatar;
    }
}

