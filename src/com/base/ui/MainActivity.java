package com.base.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.base.adapter.R;

public class MainActivity extends Activity implements OnClickListener{
	
	private Button mButtonListView,mButtonGridView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	private void initView() {
		mButtonListView = (Button)findViewById(R.id.listview_btn);
		mButtonGridView = (Button)findViewById(R.id.gridview_btn);
		mButtonListView.setOnClickListener(this);
		mButtonGridView.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.listview_btn:
			start(ListViewDemoPage.class);
			break;
		case R.id.gridview_btn:
			start(GridViewDemoPage.class);
			break;

		}
	}

	private void start(Class<?> class1) {
		Intent intent = new Intent(MainActivity.this, class1);
		startActivity(intent);
	}
	
}