package com.starbug1.android.webaddigest;

import android.content.Intent;
import android.view.MenuItem;

import com.motionbeat.labs.adstirbaas.sdk.ASB;
import com.starbug1.android.newsapp.FavoriteListActivity;
import com.starbug1.android.newsapp.MainActivity;
import com.starbug1.android.newsapp.R;

public class AppMainActivity extends MainActivity {
	private ASB asb_ = null;
	
	@Override
	protected void initAdditional() {
		asb_ = new ASB(this);
		super.initAdditional();
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.menu_update_feeds) {
			fetchFeeds(false);
		} else if (item.getItemId() == R.id.menu_settings) {
			settings();
		} else if (item.getItemId() == R.id.menu_favorites) {
			Intent intent = new Intent(this, AppFavoriteListActivity.class);
			this.startActivity(intent);
		} else if (item.getItemId() == R.id.menu_notify_all) {
			asb_.shareString(getResources().getString(R.string.shareDescription) + " #" + getResources().getString(R.string.app_name), "紹介");
			return true;
		} else if (item.getItemId() == R.id.menu_review) {
			asb_.gotoMarket();
			return true;
		} else if (item.getItemId() == R.id.menu_support) {
			asb_.startSupportActivity();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected Class getEntryActivityClass() {
		return AppEntryActivity.class;
	}
	
}
