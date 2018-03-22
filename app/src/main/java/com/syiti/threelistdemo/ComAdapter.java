package com.syiti.threelistdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * description: <一句话功能简述>
 *
 * @author Chenz
 * @date 2018/3/22 0022
 */
public abstract class ComAdapter<T> extends BaseAdapter {

    protected List<T>        mData;
    protected Context        mContext;
    protected LayoutInflater mLayoutInflater;
    protected int            mItemLayoutId;

    public ComAdapter(Context mContext, List<T> mData, int mItemLayoutId) {
        mLayoutInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.mData = mData;
        this.mItemLayoutId = mItemLayoutId;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseViewHolder viewHolder = getViewHolder(position, convertView, parent);
        convert(viewHolder, getItem(position));
        return viewHolder.getConvertView();
    }

    private BaseViewHolder getViewHolder(int position, View convertView, ViewGroup parent) {
        return BaseViewHolder.getHolder(mContext, mItemLayoutId, convertView, parent);
    }

    public abstract void convert(BaseViewHolder holder, T item);
}
