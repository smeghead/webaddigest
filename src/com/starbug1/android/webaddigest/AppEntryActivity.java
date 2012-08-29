package com.starbug1.android.webaddigest;

import android.view.MenuItem;
import android.webkit.WebView;

import com.motionbeat.labs.adstirbaas.sdk.ASB;
import com.starbug1.android.newsapp.EntryActivity;
import com.starbug1.android.newsapp.R;

public class AppEntryActivity extends EntryActivity {
	private ASB asb_ = null;
	
	@Override
	protected void initAdditional() {
		asb_ = new ASB(this);
		super.initAdditional();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.menu_reload) {
			WebView entryView = (WebView) this
			.findViewById(R.id.entryView);
			startLoading();
			entryView.reload();
		} else if (item.getItemId() == R.id.menu_share) {
			share();
		} else if (item.getItemId() == R.id.menu_notify_all) {
			shareAll();
		} else if (item.getItemId() == R.id.menu_review) {
			asb_.gotoMarket();
		} else if (item.getItemId() == R.id.menu_favorite) {
			favorite();
		} else if (item.getItemId() == R.id.menu_support) {
			asb_.startSupportActivity();
		}
		return super.onOptionsItemSelected(item);
	}

}
