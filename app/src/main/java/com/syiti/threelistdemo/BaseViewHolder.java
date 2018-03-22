package com.syiti.threelistdemo;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * description: <一句话功能简述>
 *
 * @author Chenz
 * @date 2018/3/22 0022
 */
public class BaseViewHolder {

    private SparseArray<View> mViews;
    private View              mConvertView;

    public BaseViewHolder(Context context, int layoutId, ViewGroup parent) {
        this.mViews = new SparseArray<>();
        this.mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        this.mConvertView.setTag(this);
    }

    public static BaseViewHolder getHolder(Context context, int layoutId, View convertView,
                                           ViewGroup parent) {
        if (convertView == null) {
            return new BaseViewHolder(context, layoutId, parent);
        } else {
            return (BaseViewHolder) convertView.getTag();
        }
    }

    public <T extends View> T getItemView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    //获得一个convertView
    public View getConvertView() {
        return mConvertView;
    }

    public BaseViewHolder setTextView(int viewId, String text) {
        TextView view = getItemView(viewId);
        view.setText(text);
        return this;
    }

}
