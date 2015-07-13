package com.base.adapter;

import java.util.List;

import android.content.Context;

import com.base.model.DPItemModel;

public class DPGridViewAdapter extends DPBaseAdapter<DPItemModel>{
	
	
	public DPGridViewAdapter(Context context, List<DPItemModel> list,int layoutId) {
		super(context, list, layoutId);
	}

	@Override
	public void convert(DPAdapterViewHolder holder, DPItemModel model) {
		//绑定监听
		holder.setItemChildClickListener(R.id.gridview_delete);
		holder.setItemChildLongClickListener(R.id.gridview_delete);
		
		//绑定数据
		holder.setText(R.id.gridview_title, model.mItemTitle);
		holder.setImageResource(R.id.gridview_imageView, model.mItemResId);
	}

}
