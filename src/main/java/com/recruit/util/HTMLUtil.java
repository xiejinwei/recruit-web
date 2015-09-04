package com.recruit.util;


public class HTMLUtil {

	/**
	 * 封装分页HTML代码
	 * 
	 * @param page
	 * @param url
	 *            通过StringUtil.getRequestGetUrl()方式取得的url地址
	 * @return
	 */
	public static String getNumberPageHTML(Page page, String url) {
		if (url.indexOf("?") == -1) {
			url += "?";
		}
		int len = url.substring(url.indexOf("?") + 1, url.length()).length();
		if (len > 0) {
			url += "&pageNo=";
		} else {
			url += "pageNo=";
		}
		String html = "<ul class='pagination pagination-centered'>";
		// 首页
		html += "<li title='首页'><a href='" + url + "1" + "&pageSize=" + page.getPageSize()
				+ "'>&laquo;</a></li>";

		//页码
		int length = 0;
		int startpage = 0;
		if(page.getPage()<=3)
			startpage = 1;
		else if(page.getPage()>page.getPageCount()-3)
			startpage = page.getPageCount()-4;
		else
			startpage = page.getPage()-2;
		for (int i=startpage; i <= page.getPageCount(); i++) {
			if(length==5)
				break;
			if (i == page.getPage()) {
				html += "<li class='active'><a>" + i + "</a></li>";
			} else {
				html += "<li><a href='" + url + i + "&pageSize="
						+ page.getPageSize() + "'>" + i + "</a></li>";
			}
			length++;
		}

		// 尾页
		html += "<li title='末页'><a href='" + url + page.getPageCount() + "&pageSize="
				+ page.getPageSize() + "'>&raquo;</a></li>";
		html += "</ul>";
		return html;
	}

	public static String getPreNextPageHTML(Page page, String url) {
		return null;
	}

}
