package net.chinaedu.aedu.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class AeduNetworkUtils {

    private static String TAG = "NetWorkUtils";

	/**
	 * 检查网络情况
	 *
	 * @param context
	 */
	public static boolean checkNetworkStatus(Context context) {
		if (!AeduNetworkUtils.checkNetWork(context) && !AeduNetworkUtils.checkWifiNetWork(context)) {
			return false;
		}
		return true;
	}

	/**
	 * 检查是否有无线网络
	 * @param context
	 * @return
	 */
	public static boolean checkNetWork(Context context) {
		try {
			ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
			NetworkInfo mobNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			if (activeNetInfo == null || !activeNetInfo.isAvailable() || !activeNetInfo.isConnected()) {
				if (mobNetInfo == null || !mobNetInfo.isAvailable() || !mobNetInfo.isConnected()) {
					return false;
				} else {
					return true;
				}
			} else {
				return true;
			}
		} catch (Exception e) {
			Log.e(TAG, "检查无线网络异常", e);
			return false;
		}
	}

	/**
	 * 检查是否有wifi网络
	 * @param context
	 * @return
	 */
	public static boolean checkWifiNetWork(Context context) {
		try {
			/*ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
			NetworkInfo mobNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			if (activeNetInfo == null || !activeNetInfo.isAvailable() || !activeNetInfo.isConnected()) {
				if (mobNetInfo == null || !mobNetInfo.isAvailable() || !mobNetInfo.isConnected()) {
					return false;
				} else {
					return true;
				}
			} else {
				return true;
			}*/

			ConnectivityManager connectivityManager=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo.State state = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
			if (NetworkInfo.State.CONNECTED == state) {
				return true;
			}
			else{
				return false;
			}
		} catch (Exception e) {
		    Log.e(TAG, "检查wifi网络异常", e);
			return false;
		}
	}
}
