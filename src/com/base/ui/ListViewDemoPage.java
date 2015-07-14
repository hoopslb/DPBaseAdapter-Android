package com.base.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.base.adapter.DPListViewAdapter;
import com.base.adapter.R;
import com.base.inter.DPOnItemChildClickListener;
import com.base.inter.DPOnItemChildLongClickListener;
import com.base.model.DPItemModel;

public class ListViewDemoPage extends Activity implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener,DPOnItemChildClickListener,DPOnItemChildLongClickListener{
	
	private static final String TAG = "ListViewDemoPage";
	private ListView mListView;
	private List<DPItemModel> mDataList = new ArrayList<DPItemModel>();
	private static final String IMAGEURL = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superplus/img/logo_white_ee663702.png";
	private DPListViewAdapter mDpListViewAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview);
		initData();
		initView();
	}

	private void initData() {
		for (int i = 0; i < 30; i++) {
			DPItemModel model = new DPItemModel();
			model.setItemResId(R.drawable.ic_launcher);
			model.setItemTitle("title" + i);
			model.setItemContent("content" + i);
			model.setItemIconUrl(IMAGEURL);
			mDataList.add(model);
		}
	}

	private void initView() {
		mListView = (ListView) findViewById(R.id.list_view);
		mDpListViewAdapter = new DPListViewAdapter(this, mDataList, R.layout.listview_item);
		mDpListViewAdapter.setOnItemChildLongClickListener(this);
		mDpListViewAdapter.setOnItemChildClickListener(this);
		mListView.setOnItemClickListener(this);
		mListView.setOnItemLongClickListener(this);
		mListView.setAdapter(mDpListViewAdapter);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
		Toast.makeText(ListViewDemoPage.this, "点击" + position,Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,int position, long id) {
		Toast.makeText(ListViewDemoPage.this, "长按" + position,Toast.LENGTH_SHORT).show();
		return false;
	}
	
	@Override
	public boolean onItemChildLongClick(View v, int position) {
		if (v.getId() == R.id.listview_delete) {
			Toast.makeText(ListViewDemoPage.this, "长按了删除 " + mDpListViewAdapter.getItem(position).mItemTitle + position,Toast.LENGTH_SHORT).show();
            return true;
        }
		return false;
	}

	@Override
	public boolean onItemChildClick(View v, int position) {
		if (v.getId() == R.id.listview_delete) {
			Toast.makeText(ListViewDemoPage.this, "按了删除 " + mDpListViewAdapter.getItem(position).mItemTitle + position,Toast.LENGTH_SHORT).show();
			mDpListViewAdapter.removeItem(position);
			return true;
        }
		return false;
	}
}
