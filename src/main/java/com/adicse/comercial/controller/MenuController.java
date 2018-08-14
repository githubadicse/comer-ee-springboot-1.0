package com.adicse.comercial.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adicse.comercial.clases.TreeMain;

import com.adicse.comercial.model.Menu;
import com.adicse.comercial.model.Perfil;
import com.adicse.comercial.service.MenuService;

@RestController
@RequestMapping(value = "/res/menu")
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	@RequestMapping(value = "/getall")
	@ResponseBody
	public Map<String,Object> getAll(){
		
		Map<String,Object> response = new HashMap<>();
		List<Menu> lstMenu = menuService.getall();
		
		TreeMain treeMain = new TreeMain();
		Object object = treeMain.getMain(lstMenu);
		//sMenu = sMenu.replace("\"", "'");
		//System.out.println(sMenu);
		response.put("data", object);
		
		
		return response;
		
	}
	
}
