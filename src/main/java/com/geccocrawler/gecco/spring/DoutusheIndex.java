package com.geccocrawler.gecco.spring;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.Href;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Request;
import com.geccocrawler.gecco.annotation.RequestParameter;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;

import java.util.List;

//凡是交由爬取队列的url满足这个条件的都在这里处理（交给后面这个pipelines处理）
@Gecco(matchUrl="https://doutushe.com/portal/index/index/p/{page}", pipelines="doutusheIndexPipeline")
public class DoutusheIndex implements HtmlBean {

	private static final long serialVersionUID = -7127412585200687225L;
	
	@Request
	private HttpRequest request;
	
	@RequestParameter("user")
	private String page;
	
	
	@HtmlField(cssPath="a.link-2")
	private List<String> nameList;
	
	@Href(value = "href")
	@HtmlField(cssPath="a.link-2")
	private List<String> idList;
	
	@Href(value = "href")
	@HtmlField(cssPath = "ul.pagination li")
	private List<IndexPageEntity> pageList;
	
	public HttpRequest getRequest() {
		return request;
	}
	
	public void setRequest(HttpRequest request) {
		this.request = request;
	}
	
	public String getPage() {
		return page;
	}
	
	public void setPage(String page) {
		this.page = page;
	}
	
	public List<String> getNameList() {
		return nameList;
	}
	
	public void setNameList(List<String> nameList) {
		this.nameList = nameList;
	}
	
	public List<String> getIdList() {
		return idList;
	}
	
	public void setIdList(List<String> idList) {
		this.idList = idList;
	}
	
	public List<IndexPageEntity> getPageList() {
		return pageList;
	}
	
	public void setPageList(List<IndexPageEntity> pageList) {
		this.pageList = pageList;
	}
}
