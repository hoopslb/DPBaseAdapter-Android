package com.base.adapter;

import java.util.List;

import android.content.Context;

import com.base.model.DPItemModel;

public class DPListViewAdapter extends DPBaseAdapter<DPItemModel>{
	
	public DPListViewAdapter(Context context, List<DPItemModel> list,int layoutId) {
		super(context, list, layoutId);
	}

	@Override
	public void convert(DPAdapterViewHolder holder, DPItemModel model) {
		//绑定监听
		holder.setItemChildClickListener(R.id.listview_delete);
		holder.setItemChildLongClickListener(R.id.listview_delete);
		
		//绑定数据
		holder.setText(R.id.listview_title, model.mItemTitle);
		holder.setText(R.id.listview_content, model.mItemContent);
		holder.setImageResource(R.id.listview_imageView, model.mItemResId);
//		holder.setImageUrl(R.id.imageView, model.mItemIconUrl);
	}

}
