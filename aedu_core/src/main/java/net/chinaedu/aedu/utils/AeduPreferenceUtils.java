package net.chinaedu.aedu.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.Map;

public class AeduPreferenceUtils {
	private SharedPreferences mPreference;

	public AeduPreferenceUtils(Context context, String applicationName) {
		mPreference = context.getSharedPreferences(applicationName,
				Context.MODE_PRIVATE);
	}

	/**
	 * 保存参数
	 */
	public void save(String key, String value) {
		try {
			Editor editor = mPreference.edit();
			editor.putString(key, value);
			editor.commit();
		} catch (Exception e) {
		}
	}

	public void save(String key, int value) {
		try {
			Editor editor = mPreference.edit();
			editor.putInt(key, value);
			editor.commit();
		} catch (Exception e) {
		}

	}

	public void save(String key, boolean value) {
		try {
			Editor editor = mPreference.edit();
			editor.putBoolean(key, value);
			editor.commit();
		} catch (Exception e) {
		}

	}

	/**
	 * 保存多个参数
	 */
	public void save(Map<String, String> map) {
		try {
			Editor editor = mPreference.edit();
			for (String key : map.keySet()) {
				editor.putString(key, map.get(key));
			}
			editor.commit();
		} catch (Exception e) {
		}

	}

	public void save(String keys[], int values[]) {
		try {
			Editor editor = mPreference.edit();
			int n = keys.length;
			for (int i = 0; i < n; i++) {
				editor.putInt(keys[i], values[i]);
			}
			editor.commit();
		} catch (Exception e) {
		}

	}

	public String getString(String key, String defValue) {
		String value = defValue;
		try {
			value = mPreference.getString(key, defValue);
		} catch (Exception e) {
		}
		return value;
	}

	public int getInt(String key, int defValue) {
		int value = defValue;
		try {
			value = mPreference.getInt(key, defValue);
		} catch (Exception e) {
		}
		return value;

	}

	public Boolean getBoolean(String key, Boolean defValue) {
		Boolean value = defValue;
		try {
			value = mPreference.getBoolean(key, defValue);
		} catch (Exception e) {
		}
		return value;

	}
}
