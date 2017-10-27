package com.airbnb.web.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.airbnb.web.command.Command;
import com.airbnb.web.domain.Board;
import com.airbnb.web.mapper.JWMapper;
import com.airbnb.web.service.IDeleteService;
import com.airbnb.web.service.IGetService;
import com.airbnb.web.service.IListService;
import com.airbnb.web.service.TransactionService;

@RestController
public class JWController {
	private static final Logger logger = LoggerFactory.getLogger(JWController.class);
	@Autowired JWMapper mapper;
	@Autowired Command cmd;
	@Autowired TransactionService tx;
	
	@RequestMapping(value="/jw/post/{cate}", method=RequestMethod.POST, consumes="application/json")
	public @ResponseBody Map<?, ?> post(@RequestBody Board board, @PathVariable String cate){
		logger.info("board JWController post {}","진입");
		Map<String, Object> map = new HashMap<>();
		String bo_seq = "bo"+String.valueOf((int)((Math.random()*999999999)+100000000));
		String cate_seq = String.valueOf((int)((Math.random()*99999)+10000));
			
		//board
		cmd.setDir(cate);					//table
		cmd.setPageNumber(bo_seq);			//bo_seq
		cmd.setPage(board.getTitle()); 		//title
		cmd.setView(board.getContents()); 	//content
		tx.add(cmd);
		System.out.println("board: "+cmd.getView()+"/"+cmd.getPage()+"/"+cmd.getDir()+"/"+cmd.getPageNumber());
		
		//boardcate
		cmd.setDir("boardcate");			//table
		cmd.setSearch(cate_seq); 			//cate_seq
		cmd.setView(board.getMemberId()); 	//cate_val
		cmd.setPage(board.getRegdate()); 	//cate_level
		tx.add(cmd);
		System.out.println("boardcate: "+cmd.getView()+"/"+cmd.getPage()+"/"+cmd.getDir()+"/"+cmd.getPageNumber());
		
		map.put("result", "success");
		return map;
	}
	
	@RequestMapping(value="/jw/list/{cate}", method=RequestMethod.POST, consumes="application/json")
	public @ResponseBody Map<?, ?> list(@RequestBody Board board, @PathVariable String cate){
		logger.info("JWController 진입: list : "+cate);
		Map<String, Object> map = new HashMap<>();
		
		switch(cate) {
			case "board":
			case "residence":		
				String search = (board.getContents()==null)?"%%":"%"+board.getContents()+"%";
				
				cmd.setDir(cate);
				cmd.setSearch(search);
				System.out.println(cmd.getDir()+"/"+board.getContents()+"/"+cmd.getSearch());
				
				map.put("list", new IListService() {
					@Override
					public List<?> execute(Object o) {
						return mapper.selectList(cmd);
					}
				}.execute(cmd));
				
				System.out.println(map.get("list"));
				break;
			case "combo":				
				map.put("combobox", new IListService() {
					@Override
					public List<?> execute(Object o) {
						return mapper.comboList(cmd);
					}
				}.execute(cmd));
				
				System.out.println(map.get("combobox"));
				break;
		}
		
		map.put("result", "success");
		return map;
	}
	
	@RequestMapping(value="/jw/get/{cate}", method=RequestMethod.POST, consumes="application/json")
	public @ResponseBody Map<?, ?> get(@RequestBody Board board, @PathVariable String cate){
		logger.info("JWController 진입: getDetail : "+cate);
		Map<String, Object> map = new HashMap<>();
		
		cmd.setColumn(board.getTitle());
		cmd.setSearch(board.getBoardSeq());
		cmd.setDir(cate);
		System.out.println(cmd.getColumn()+"/"+cmd.getSearch()+"/"+cmd.getDir());
		
		
		map.put("detail", new IGetService() {
			@Override
			public Object execute(Object o) {
				return mapper.selectOne(cmd);
			}
		}.execute(cmd));
		map.put("result", "success");
		System.out.println(map.get("detail"));
		
		return map;
	}
	
	
	@RequestMapping(value="/jw/put/{cate}", method=RequestMethod.POST, consumes="application/json")
	public @ResponseBody Map<?, ?> put(@RequestBody Board board, @PathVariable String cate){
		logger.info("JWController 진입: put : "+cate);
		Map<String, Object> map = new HashMap<>();
		
		//board
		cmd.setDir(cate);					//table
		cmd.setPageNumber(board.getBoardSeq());	  //bo_seq
		cmd.setPage(board.getTitle()); 		//title
		cmd.setView(board.getContents()); 	//content
		tx.modify(cmd);
		System.out.println("board: "+cmd.getView()+"/"+cmd.getPage()+"/"+cmd.getDir()+"/"+cmd.getPageNumber());
		
		//boardcate
		cmd.setDir("boardcate");			//table
		cmd.setView(board.getMemberId()); 	//cate_val
		cmd.setPage(board.getRegdate()); 	//cate_level
		tx.modify(cmd);
		System.out.println("boardcate: "+cmd.getView()+"/"+cmd.getPage()+"/"+cmd.getDir()+"/"+cmd.getPageNumber());
		map.put("result", "success");
		
		return map;
	}
	
	
	@RequestMapping(value="/jw/delete/{cate}", method=RequestMethod.POST, consumes="application/json")
	public @ResponseBody Map<?, ?> delete(@RequestBody Board board, @PathVariable String cate){
		logger.info("JWController 진입: delete : "+cate);
		Map<String, Object> map = new HashMap<>();
		
		//board
		cmd.setDir(cate);	//table
		cmd.setSearch(board.getBoardSeq());	//board_seq
		tx.remove(cmd);
		System.out.println("board: "+cmd.getSearch()+"/"+cmd.getDir());
		
		//boardcate
		cmd.setDir("boardcate");//table
		tx.remove(cmd);
		System.out.println("boardcate"+cmd.getSearch()+"/"+cmd.getDir());

		map.put("result", "success");

		return map;
	}
}
