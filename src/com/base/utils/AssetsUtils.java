package com.base.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.util.EncodingUtils;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;

public class AssetsUtils {
	private static final String TAG = "AssetsUtils";
	public static final String ENCODING = "UTF-8";
	
	/**
	 * 从assets 文件夹中获取文件并读取数据
	 * @param context
	 * @param fileName 文件路径
	 * @return
	 */
	public static String getDataFromAssets(Context context, String fileName) {
		if (context == null || StringUtils.isEmpty(fileName)) {
			return null;
		}
		String result = "";
		try {
			InputStream in = context.getResources().getAssets().open(fileName);
			int lenght = in.available();
			byte[] buffer = new byte[lenght];
			in.read(buffer);
			result = EncodingUtils.getString(buffer, ENCODING);
		} catch (Exception e) {
			  Log.e(TAG,"getDataFromAssets() -> exception:" + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	/**
     * get an asset using ACCESS_STREAMING mode. This provides access to files that have been bundled with an
     * application as assets -- that is, files placed in to the "assets" directory.
     * 
     * @param context
     * @param fileName The name of the asset to open. This name can be hierarchical.
     * @return
     */
	public static String geFileFromAssets(Context context, String fileName) {
        if (context == null || StringUtils.isEmpty(fileName)) {
            return null;
        }

        StringBuilder s = new StringBuilder("");
        try {
            InputStreamReader in = new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader br = new BufferedReader(in);
            String line;
            while ((line = br.readLine()) != null) {
                s.append(line);
            }
            return s.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
	
	/**
	 * json文件转Model
	 * @param context 
	 * @param jsonPath josn文件路径
	 * @param clazz model类
	 * @return model 异常则返回null
	 */
    public static <T> T getModelFromAssets(Context context, String jsonPath, Class<T> clazz) {
        try {
            String dataStr = getDataFromAssets(context, jsonPath);
            List<T> jsonList = (ArrayList<T>) JSON.parseArray(dataStr, clazz);
            return jsonList.get(0);
        } catch (Exception e) {
            Log.e(TAG,"getModelFromAssets() -> exception:" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
	
    /**
     * 从resources中的raw 文件夹中获取文件并读取数据
     * @param context
     * @param id raw文件资源id
     * @return
     */
    public String getFromRaw(Context context,int id){  
        String result = "";  
            try {  
                InputStream in = context.getResources().openRawResource(id);  
                int lenght = in.available();
                byte[]  buffer = new byte[lenght];  
                in.read(buffer);
                result = EncodingUtils.getString(buffer, ENCODING);  
            } catch (Exception e) {
            	Log.e(TAG,"getFromRaw() -> exception:" + e.getMessage());
                e.printStackTrace();  
            }  
            return result;  
    }  
    
    /**
     * get content from a raw resource. This can only be used with resources whose value is the name of an asset files
     * -- that is, it can be used to open drawable, sound, and raw resources; it will fail on string and color
     * resources.
     * 
     * @param context
     * @param resId The resource identifier to open, as generated by the appt tool.
     * @return
     */
    public static String geFileFromRaw(Context context, int resId) {
        if (context == null) {
            return null;
        }

        StringBuilder s = new StringBuilder();
        try {
            InputStreamReader in = new InputStreamReader(context.getResources().openRawResource(resId));
            BufferedReader br = new BufferedReader(in);
            String line;
            while ((line = br.readLine()) != null) {
                s.append(line);
            }
            return s.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}