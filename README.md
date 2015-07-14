# DPBaseAdapter-Android
DPBaseAdapter-Android demo中学习BaseAdapter的使用,应用到ListView和GridView的Adapter适配器，并且添加item的点击和长按点击时间监听，和item中控件的监听并回调

**【感谢慕课网的 [Android必学-BaseAdapter的使用与优化](http://www.imooc.com/learn/365) 视频教程】**<br/>
**【[bingoogolapple-BGAAdapter-Android](https://github.com/bingoogolapple/BGAAdapter-Android)】**<br/>
    可以查看之前的[Android-BaseAdapter-study01](https://github.com/whiskeyfei/Android-BaseAdapter-study01/blob/Android-BaseAdapter-study02/README.md)使用,介绍了adapter的基本使用
    
#### 7.14更新内容

* 单数获取数据单例类：DPFakeData，DPItemModel实现序列化
* 添加自定义Application，初始化base context(在程序的任何地方都可以获得)
* 添加工具类：AssetsUtils、ListUtils、StringUtils

#### 详细说明

1、AssetsUtils：从assets或者raw文件夹中获取文件并读取数据，并转化model<br/>
2、ListUtils：目前只有isEmpty和getCount方法，以后丰富<br/>
3、StringUtils：目前只有isEmpty和isMailAddress，以后丰富<br/>


#### 效果图
 <img src="https://github.com/whiskeyfei/DPBaseAdapter-Android/blob/master/res/drawable/device_gridview.png" width = "380" height = "676" alt="图片名称" align=center />
  <img src="https://github.com/whiskeyfei/DPBaseAdapter-Android/blob/master/res/drawable/device_listview.png" width = "380" height = "676" alt="图片名称" align=center />
  
### 使用方法
  1、目前在Activity当中使用，实现DPOnItemChildClickListener和DPOnItemChildLongClickListener接口<br/>
  ```
  implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener,DPOnItemChildClickListener,DPOnItem   ChildLongClickListener
  ```
  2、注册各种监听
  
  ```
  ...
    mDpListViewAdapter = new DPListViewAdapter(this, mDataList, R.layout.xxx);
		mDpListViewAdapter.setOnItemChildLongClickListener(this);
		mDpListViewAdapter.setOnItemChildClickListener(this);
		mListView.setOnItemClickListener(this);
		mListView.setOnItemLongClickListener(this);
		mListView.setAdapter(mDpListViewAdapter);
	...
  ```
  3、在各自监听事件回调中处理
  
  ```java 
  @Override
	public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
	//相应处理
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,int position, long id) {
	//相应处理
		return false;
	}
	
	@Override
	public boolean onItemChildLongClick(View v, int position) {
		    if (v.getId() == R.id.xxx) {
		        //相应处理
            return true;
        }
	    	return false;
	}

	@Override
	public boolean onItemChildClick(View v, int position) {
		if (v.getId() == R.id.xxx) {
		//相应处理
			return true;
        }
		return false;
	}
  ```
  在原来的ViewHolder基础之上扩展事件监听
  
  ```java
  public void setOnItemChildLongClickListener(DPOnItemChildLongClickListener l){
    	mOnItemChildLongClickListener = l;
    }
    
    public void setOnItemChildClickListener(DPOnItemChildClickListener l){
    	mOnItemChildClickListener = l;
    }
    
    public DPAdapterViewHolder setItemChildClickListener(int viewId){
    	View view = getViewByid(viewId);
    	view.setOnClickListener(this);
    	return this;
    }
    
    public DPAdapterViewHolder setOnTouchListener(int viewId, View.OnTouchListener listener){
    	View view = getViewByid(viewId);
    	view.setOnTouchListener(listener);
    	return this;
    }
    
    public DPAdapterViewHolder setItemChildLongClickListener(int viewId){
    	View view = getViewByid(viewId);
    	view.setOnLongClickListener(this);
    	return this;
    }
  ```

## 下一步任务
* 本地json数据（7.14完成）
* 使用线上的数据，异步任务访问，添加loadding圈
* 使用RecyclerView来实现ListView和GridView
* 使用Fragment替换activity

  
## License

    Copyright 2015 bingoogolapple

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
