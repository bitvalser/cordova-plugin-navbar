package com.bitvalser.cordova.plugin.navbar;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.os.Handler;
import java.lang.reflect.Method;

public class NavBar extends CordovaPlugin {
	private static final String LOG_TAG = "NavBar";

    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		super.initialize(cordova, webView);
	
    }
	
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		if (action.equals("getNavBarHeight")) {			
			cordova.getActivity().runOnUiThread(new Runnable(){
				@Override
				public void run() {						
					callbackContext.success(getNavBarHeight())			
				}
			});				
			return true;
		}				
		return false;
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB) 
	private int getNavBarHeight() {
		Resources resources = context.getResources();
		int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
		if (resourceId > 0) {
			return resources.getDimensionPixelSize(resourceId);
		}
		return 0;
	}

	public static View getView(CordovaWebView webView) {	
		if(View.class.isAssignableFrom(CordovaWebView.class)) {
			return (View) webView;
		}
		
		try {
			Method getViewMethod = CordovaWebView.class.getMethod("getView", (Class<?>[]) null);
			if(getViewMethod != null) {
				Object[] args = {};
				return (View) getViewMethod.invoke(webView, args);
			}
		} 
		catch (Exception e) {
		}
		
		return null;
	}
}