package com.base.ui;

import java.util.ArrayList;
import java.util.List;

import com.base.adapter.DPGridViewAdapter;
import com.base.adapter.R;
import com.base.inter.DPOnItemChildClickListener;
import com.base.inter.DPOnItemChildLongClickListener;
import com.base.model.DPItemModel;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class GridViewDemoPage extends Activity implements OnItemClickListener,OnItemLongClickListener,DPOnItemChildClickListener,DPOnItemChildLongClickListener{
	private GridView mGridView;
	private List<DPItemModel> mDataList = new ArrayList<DPItemModel>();
	DPGridViewAdapter mDPGridViewAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridview);
		initData();
		initView();
	}

	private void initData() {
		for (int i = 0; i < 30; i++) {
			mDataList.add(new DPItemModel(R.drawable.ic_launcher, "title" + i,"content" + i));
		}
	}
	private void initView() {
		mGridView = (GridView)findViewById(R.id.gridview);
		mGridView.setNumColumns(3);
		mDPGridViewAdapter = new DPGridViewAdapter(this, mDataList, R.layout.gridview_item);
		mGridView.setOnItemClickListener(this);
		mGridView.setOnItemLongClickListener(this);
		mDPGridViewAdapter.setOnItemChildLongClickListener(this);
		mDPGridViewAdapter.setOnItemChildClickListener(this);
		mGridView.setAdapter(mDPGridViewAdapter);
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
		Toast.makeText(GridViewDemoPage.this, "点击" + position,Toast.LENGTH_SHORT).show();
	}
	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,int position, long id) {
		Toast.makeText(GridViewDemoPage.this, "长按" + position,Toast.LENGTH_SHORT).show();
		return false;
	}

	@Override
	public boolean onItemChildLongClick(View v, int position) {
		if (v.getId() == R.id.gridview_delete) {
			Toast.makeText(GridViewDemoPage.this, "长按了删除 " + mDPGridViewAdapter.getItem(position).mItemTitle + position,Toast.LENGTH_SHORT).show();
            return true;
        }
		return false;
	}

	@Override
	public boolean onItemChildClick(View v, int position) {
		if (v.getId() == R.id.gridview_delete) {
			Toast.makeText(GridViewDemoPage.this, "按了删除 " + mDPGridViewAdapter.getItem(position).mItemTitle + position,Toast.LENGTH_SHORT).show();
			mDPGridViewAdapter.removeItem(position);
			return true;
        }
		return false;
	}

}
