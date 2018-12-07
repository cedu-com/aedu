package net.chinaedu.aedu.utils;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.Date;

public class AeduViewUtils {
	
	public static final int SCREEN_SIZE_LARGE = 1;
	public static final int SCREEN_SIZE_MEDIUM = 2;
	public static final int SCREEN_SIZE_SMALL = 3;
	
	public static int screenSize;
	
	public static void setScreenstyle(int width, int height){
		if(width == 480 && height == 800){
			screenSize = SCREEN_SIZE_MEDIUM;
		}
		else if(width > 480 && height > 800){
			screenSize = SCREEN_SIZE_LARGE;
		}
		else if(width < 480 && height < 800){
			screenSize = SCREEN_SIZE_SMALL;
		}
		else{
			screenSize = SCREEN_SIZE_MEDIUM;
		}
	}

	public static byte[] getViewBitmapResourcesToByteArray(View v){
		if(v == null) return null;
		v.setDrawingCacheEnabled(true);
		Bitmap bm = Bitmap.createBitmap(v.getDrawingCache());
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.PNG, 100, bos);
		return bos.toByteArray();
	}

	public static EditText createDateEditText(final View parentView, final Context ct, final int editTextId,
											  String strDate0, final String strDateFormat) {
 		final EditText etDate =  (EditText)parentView.findViewById(editTextId);
 		etDate.setText(strDate0);

    	final DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		    	//EditText etDate =  (EditText)parentView.findViewById(editTextId);
		    	//etDate.setText(String.valueOf(year) + "-" + String.valueOf(monthOfYear+1) + "-" + String.valueOf(dayOfMonth));	
				String strDate = AeduDateUtils.formatDate(year, monthOfYear, dayOfMonth, strDateFormat);
		    	etDate.setText(strDate);		    	
	    	
			}   
    	}; 

    	View.OnClickListener dateOnClickListener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText et = (EditText)v;
		    	Calendar nowCalendar = Calendar.getInstance();
		    	Date dt = AeduDateUtils.String2Date(et.getText().toString(), strDateFormat);
		    	if(dt != null)
		    		nowCalendar.setTime(dt);    	
		    	
				Dialog dateDialog = new DatePickerDialog(ct,
						dateListener, 
						nowCalendar.get(Calendar.YEAR),
						nowCalendar.get(Calendar.MONTH),
						nowCalendar.get(Calendar.DAY_OF_MONTH));
				dateDialog.show();
			}    		
    	};
    	
    	etDate.setOnClickListener(dateOnClickListener);
    	return etDate;
	}

	/**
	 * 适配listview高度
	 * listitem布局根必须为linearlayout
	 * @param listView
	 */
	public static void adapterListViewHight(final ListView listView){
		BaseAdapter adapter = (BaseAdapter)listView.getAdapter();
		int itemHeight = 0;
		int itemCount = adapter.getCount();

		for(int i = 0;i < itemCount;i++){
			View item = adapter.getView(i, null, listView);
			item.measure(0, 0);
			itemHeight = itemHeight + item.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = itemHeight + (listView.getDividerHeight() *
				(itemCount - 1));
		listView.setLayoutParams(params);
	}

	public static void setAdapterViewHight(final AdapterView adapterView){
		BaseAdapter adapter = (BaseAdapter)adapterView.getAdapter();
		int itemHeight = 0;
		int itemCount = adapter.getCount();

		for(int i = 0;i < itemCount;i++){
			View item = adapter.getView(i, null, adapterView);
			item.measure(0, 0);
			itemHeight = itemHeight + item.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = adapterView.getLayoutParams();
		params.height = itemHeight;
		adapterView.setLayoutParams(params);
	}

	/**
	 * 获取listview高度
	 * @param listView
	 * @return
	 */
	public static int getListViewHight(final ListView listView){
		BaseAdapter adapter = (BaseAdapter)listView.getAdapter();
		int itemHeight = 0;
		int itemCount = adapter.getCount();

		for(int i = 0;i < itemCount;i++){
			View item = adapter.getView(i, null, listView);
			item.measure(0, 0);
			itemHeight = itemHeight + item.getMeasuredHeight();
		}
		return itemHeight + (listView.getDividerHeight() * (itemCount - 1));
	}

	/**
	 * 通知listview数据改变
	 * @param listView
	 */
	public static void notifyDataSetChangedListView(final ListView listView){
		BaseAdapter adapter = (BaseAdapter)listView.getAdapter();
		adapter.notifyDataSetChanged();
		adapterListViewHight(listView);
	}
	
	public static TextView setTextViewData(Activity v, int resId, String strData) {
        TextView tv = (TextView)v.findViewById(resId);
		//if(!StringUtil.isEmpty(strData)) 
			tv.setText(strData);
        return tv;
	}
	
	public static TextView setTextViewData(Activity v, int resId, CharSequence strData) {
        TextView tv = (TextView)v.findViewById(resId);
		//if(!StringUtil.isEmpty(strData)) 
			tv.setText(strData);
        return tv;
	}	
	
	public static TextView setTextViewData(Activity v, int resId, int dataId) {
        TextView tv = (TextView)v.findViewById(resId);
        tv.setText(dataId);
        return tv;
	}		
	
	public static TextView setTextViewData(View v, int resId, String strData) {
        TextView tv = (TextView)v.findViewById(resId);
		//if(!StringUtil.isEmpty(strData)) 
			tv.setText(strData);
        return tv;
	}
	
	public static TextView setTextViewData(View v, int resId, CharSequence strData) {
        TextView tv = (TextView)v.findViewById(resId);
		//if(!StringUtil.isEmpty(strData)) 
			tv.setText(strData);
        return tv;
	}	
	
	public static TextView setTextViewData(View v, int resId, int dataId) {
        TextView tv = (TextView)v.findViewById(resId);
        tv.setText(dataId);
        return tv;
	}	
	
	
}


