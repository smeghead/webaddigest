package com.starbug1.android.webaddigest;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.util.Log;

import com.starbug1.android.newsapp.FetchFeedService;
import com.starbug1.android.newsapp.data.NewsListItem;
import com.starbug1.android.newsapp.utils.UrlUtils;

public class AppFetchFeedService extends FetchFeedService {
	private static final String TAG = "AppFetchFeedService";

	private final Pattern imageUrl_ = Pattern.compile("<img.*?src=\"([^\"]*)\"", Pattern.MULTILINE);
	private Pattern adronContent_ = Pattern.compile("div class=\"article_box(.*)div class=\"profile\"", Pattern.DOTALL);
	private Pattern dokodaContent_ = Pattern.compile("div id=\"(?:cmsMark|articleBody|tmplBody|article_body)\"(.*)div class=\"(?:endkwd|endlink|ctrl)\"", Pattern.DOTALL);
	private Pattern gyokaiContent_ = Pattern.compile("div class=\"asset-body\"(.*)div class=\"asset-footer\"", Pattern.DOTALL);
	private Pattern markezineContent_ = Pattern.compile("div id=\"article\"(.*)<!-- /article -->", Pattern.DOTALL);

	@Override
	protected List<Feed> getFeeds() {
		List<Feed> feeds = new ArrayList<Feed>();
		
		feeds.add(new Feed("dokoda", "http://rssc.dokoda.jp/r/fa3b4f315408bacfed8b74f52d1d8cc6/http/www.itmedia.co.jp/keywords/ad.html") {

			@Override
			public String getImageUrl(String content, NewsListItem item) {
				Matcher m = dokodaContent_.matcher(content);
				if (!m.find()) {
					return null;
				}
				String mainPart = m.group(1);
				
				String imageUrl = null;
				while (true) {
					m = imageUrl_.matcher(mainPart);
					if (!m.find()) {
						return null;
					}
					imageUrl = m.group(1);
					if (!imageUrl.startsWith("/tags/")) {
						break;
					}
					// ただのアイコンだった場合は、次を検索する。
					mainPart = mainPart.substring(m.end());
				}
				if (imageUrl.startsWith("/")) {
					imageUrl = UrlUtils.findSchemaDomain(item.getLink()) + imageUrl;
				}
				return imageUrl;
			}
			
		});
		feeds.add(new Feed("markezine", "http://markezine.jp/rss/new/20/index.xml") {

			@Override
			public String getImageUrl(String content, NewsListItem item) {
				Matcher m = markezineContent_.matcher(content);
				if (!m.find()) {
					return null;
				}
				String mainPart = m.group(1);
				
				String imageUrl = null;
				while (true) {
					m = imageUrl_.matcher(mainPart);
					if (!m.find()) {
						return null;
					}
					imageUrl = m.group(1);
					if (!imageUrl.startsWith("/tags/")) {
						break;
					}
					// ただのアイコンだった場合は、次を検索する。
					mainPart = mainPart.substring(m.end());
				}
				if (imageUrl.startsWith("/")) {
					imageUrl = UrlUtils.findSchemaDomain(item.getLink()) + imageUrl;
				}
				return imageUrl;
			}
			
		});
		feeds.add(new Feed("ad-ron", "http://rssc.dokoda.jp/r/d8fa8de87f8346c752c8fef4624ff0fe/http/ad-ron.jp/") {

			@Override
			public String getImageUrl(String content, NewsListItem item) {
				Matcher m = adronContent_.matcher(content);
				if (!m.find()) {
					return null;
				}
				String mainPart = m.group(1);
				
				String imageUrl = null;
				while (true) {
					m = imageUrl_.matcher(mainPart);
					if (!m.find()) {
						return null;
					}
					imageUrl = m.group(1);
					if (!imageUrl.startsWith("/tags/")) {
						break;
					}
					// ただのアイコンだった場合は、次を検索する。
					mainPart = mainPart.substring(m.end());
				}
				if (imageUrl.startsWith("/")) {
					imageUrl = UrlUtils.findSchemaDomain(item.getLink()) + imageUrl;
				}
				return imageUrl;
			}
			
		});
		feeds.add(new Feed("g-yokai", "http://g-yokai.com/rss.xml") {

			@Override
			public String getImageUrl(String content, NewsListItem item) {
				Matcher m = gyokaiContent_.matcher(content);
				if (!m.find()) {
					return null;
				}
				String mainPart = m.group(1);
				
				String imageUrl = null;
				while (true) {
					m = imageUrl_.matcher(mainPart);
					if (!m.find()) {
						return null;
					}
					imageUrl = m.group(1);
					if (!imageUrl.startsWith("/tags/")) {
						break;
					}
					// ただのアイコンだった場合は、次を検索する。
					mainPart = mainPart.substring(m.end());
				}
				if (imageUrl.startsWith("/")) {
					imageUrl = UrlUtils.findSchemaDomain(item.getLink()) + imageUrl;
				}
				return imageUrl;
			}
			
		});
		
		return feeds;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	protected boolean isValidItem(NewsListItem item) {
//		if (item.getSource().equals("famitsu")) {
//			if (item.getLink().toString().indexOf("/news/") != -1) {
//				return true;
//			}
//			return false;
//		}
		return super.isValidItem(item);
	}

	@Override
	protected Class getMainActivity() {
		return AppMainActivity.class;
	}
	
	
}
