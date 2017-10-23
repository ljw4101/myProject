package com.airbnb.web.command;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.airbnb.web.constant.Extension;
import com.airbnb.web.constant.Path;


@Lazy
@Component
public class Command {
protected String dir,action, page,pageNumber,search,view,column,startRow,endRow;
	/*id,pw*/
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

	/*dir & pageNumber�� ������ �ִ�*/
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
		/*this.action = (action==null)?"move":action;*/
		System.out.println("COmmand action:::"+action);
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
		System.out.println("������ �̸� :: "+this.page);
	}

	public String getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(String pageNumber) {
		this.pageNumber = (pageNumber==null)?"1":pageNumber;
		System.out.println("command:: ������ ��ȣ::"+this.pageNumber);
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search=(search==null)? "none":search;
		System.out.println("commandDTO��ġ: "+this.search);
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = (column==null)?"none":column;
		System.out.println("�÷�: "+this.column);
	}
	public String getView() {
		return view;
	}

	public void process() {
		/*VIEW�� �����ش�*/
		this.view=(dir.equals("home"))?
				"/WEB-INF/view/common/home.jsp":
			Path.VIEW+dir+Path.SEPARATOR+page+Extension.JSP;
		System.out.println("�̵�������:"+this.view);
	}
	

}
