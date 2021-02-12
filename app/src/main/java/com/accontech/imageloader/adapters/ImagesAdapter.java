package com.accontech.imageloader.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.accontech.imageloader.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class ImagesAdapter extends BaseAdapter {

    private List<String> images;
    private LayoutInflater layoutInflater;

    public ImagesAdapter(Context context) {
        images = new ArrayList<>();
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public String getItem(int position) {
        return images.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.item_list_view, parent, false);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image_view);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ImageLoader.getInstance().displayImage(getItem(position), viewHolder.image);
        return convertView;
    }

    public void updateList(List<String> imagesList){
        images.clear();
        images.addAll(imagesList);
        notifyDataSetChanged();
    }

    private class ViewHolder {
        ImageView image;
    }
}
