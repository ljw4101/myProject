package com.airbnb.web.command;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.airbnb.web.constant.Extension;
import com.airbnb.web.constant.Path;

import lombok.Data;

@Lazy
@Component
@Data
public class Command {
	protected String dir, action, page, pageNumber, search, view, column, startRow, endRow;

	public String getStartRow() {
		return startRow;
	}

	public void setStartRow(String startRow){
		this.startRow = startRow;
	}

	public String getEndRow(){
		return endRow;
	}

	public void setEndRow(String endRow) {
		this.endRow = endRow;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		if(action==null){
			this.action="move";
		}else {
			this.action=action;
		}
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(String pageNumber) {
		this.pageNumber = (pageNumber==null)?"1":pageNumber;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search=(search==null)? "none":search;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = (column==null)?"none":column;
	}
	
	public String getView() {
		return view;
	}

	public void process() {
		this.view = (dir.equals("home"))?"/WEB-INF/view/common/home.jsp":Path.VIEW+dir+Path.SEPARATOR+page+Extension.JSP;
		System.out.println("Command/이동페이지: "+view);
	}
	
	public String toString() {
		return String.format("dir: %s / action: %s / page: %s / pageNumber: %s / search: %s / view: %s / column: %s / startRow: %s / endRow: %s", dir, action, page, pageNumber, search, view, column, startRow, endRow);
	}
	
}
