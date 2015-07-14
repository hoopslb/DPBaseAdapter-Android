package com.base.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.base.adapter.DPListViewAdapter;
import com.base.adapter.R;
import com.base.data.DPFakeData;
import com.base.inter.DPOnItemChildClickListener;
import com.base.inter.DPOnItemChildLongClickListener;
import com.base.model.DPItemModel;
import com.base.utils.ListUtils;

public class ListViewJsonPage extends Activity implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener,DPOnItemChildClickListener,DPOnItemChildLongClickListener{
	
	private static final String TAG = "ListViewDemoPage";
	private ListView mListView;
	private List<DPItemModel> mDataList = new ArrayList<DPItemModel>();
	private DPListViewAdapter mDpListViewAdapter;
	private ProgressBar mProgressBar;
	private Handler mHandler = new Handler(Looper.getMainLooper());
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_data);
		mListView = (ListView) findViewById(R.id.list_view);
		mProgressBar = (ProgressBar)findViewById(R.id.progress_bar);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				initData();
			}
		}).start();
	}
	
	private void initData() {
		sleep(1500);
		mDataList = DPFakeData.get().getDatas();
		if (!ListUtils.isEmpty(mDataList)) {
			mHandler.post(new Runnable() {
				
				@Override
				public void run() {
					initAdapter();
				}
			});
		}else {
			mHandler.post(new Runnable() {
				
				@Override
				public void run() {
					mListView.setVisibility(View.INVISIBLE);
					mProgressBar.setVisibility(View.INVISIBLE);
					Toast.makeText(getBaseContext(), "no data", Toast.LENGTH_SHORT).show();
				}
			});
		}
	}

	private void initAdapter() {
		mDpListViewAdapter = new DPListViewAdapter(this, mDataList, R.layout.listview_item);
		mDpListViewAdapter.setOnItemChildLongClickListener(this);
		mDpListViewAdapter.setOnItemChildClickListener(this);
		mListView.setOnItemClickListener(this);
		mListView.setOnItemLongClickListener(this);
		mListView.setAdapter(mDpListViewAdapter);
		mListView.setVisibility(View.VISIBLE);
		mProgressBar.setVisibility(View.INVISIBLE);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
		Toast.makeText(ListViewJsonPage.this, "点击" + position,Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,int position, long id) {
		Toast.makeText(ListViewJsonPage.this, "长按" + position,Toast.LENGTH_SHORT).show();
		return false;
	}
	
	@Override
	public boolean onItemChildLongClick(View v, int position) {
		if (v.getId() == R.id.listview_delete) {
			Toast.makeText(ListViewJsonPage.this, "长按了删除 " + mDpListViewAdapter.getItem(position).mItemTitle + position,Toast.LENGTH_SHORT).show();
            return true;
        }
		return false;
	}

	@Override
	public boolean onItemChildClick(View v, int position) {
		if (v.getId() == R.id.listview_delete) {
			Toast.makeText(ListViewJsonPage.this, "按了删除 " + mDpListViewAdapter.getItem(position).mItemTitle + position,Toast.LENGTH_SHORT).show();
			mDpListViewAdapter.removeItem(position);
			return true;
        }
		return false;
	}
	
	private void sleep(long time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
