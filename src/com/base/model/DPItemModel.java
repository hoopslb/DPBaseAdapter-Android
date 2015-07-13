package com.base.model;

/**
 * Created by whiskeyfei on 15-7-9.
 */
public class DPItemModel {
    public int mItemResId;
    public String mItemTitle;
    public String mItemContent;
    public String mItemIconUrl;

    public DPItemModel(int resId, String title, String content) {
        mItemResId = resId;
        mItemTitle = title;
        mItemContent = content;
    }
    
    public DPItemModel(int resId, String title, String content,String url) {
    	mItemResId = resId;
    	mItemTitle = title;
    	mItemContent = content;
    	mItemIconUrl = url;
    }
    
}
