package com.starbug1.android.webaddigest;

import android.app.Activity;
import android.util.Log;
import android.view.MenuItem;

import com.motionbeat.labs.adstirbaas.sdk.ASB;
import com.starbug1.android.newsapp.ActivityProcessAditional;
import com.starbug1.android.newsapp.R;

public class AppActivityProcessAditional implements ActivityProcessAditional {
	private static final String TAG = "AppActivityProcessAditional";

	private ASB asb;
	
	public AppActivityProcessAditional() {
	}

	public void onCreateAditional(Activity activity) {
		Log.d(TAG, "coCreateAditional");
		asb = new ASB(activity);
	}
	
	public boolean onOptionsItemSelectedAdditional(Activity activity, MenuItem item) {
		if (item.getItemId() == R.id.menu_other) {
			return true;
		} else if (item.getItemId() == R.id.menu_notify_all) {
			asb.shareString(activity.getResources().getString(R.string.shareDescription) + " #" + activity.getResources().getString(R.string.app_name), "紹介");
			return true;
		} else if (item.getItemId() == R.id.menu_review) {
			asb.gotoMarket();
			return true;
		} else if (item.getItemId() == R.id.menu_support) {
			asb.startSupportActivity();
			return true;
		}
		return false;
	}
}
