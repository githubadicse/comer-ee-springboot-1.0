package com.adicse.comercial.clases;

import java.util.ArrayList;
import java.util.List;

import com.adicse.comercial.model.Menu;
import com.google.gson.Gson;

public class TreeMain {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object getMain(List<Menu> lstMenu){
		
		
		TreeRoot treeRoot = new TreeRoot();
		
		
		for(Menu row:lstMenu){
			
			TreeNodo treeNodo = new TreeNodo();
			if(row.getIdmenu().length() > 2){
				treeNodo = treeRoot.getTreeNodoParent(row, row.getIdmenu().length());
				if(treeNodo != null)
					treeRoot.addData(treeNodo, row,1);
			}else{
				treeRoot.addData(treeNodo, row);
			}
			
		}
		
		
		
        Gson gson = new Gson();
        List tn = new ArrayList<>();
        for(Integer i=0; i< treeRoot.itemsRoot.size() ; i++){
        	TreeNodo tr = treeRoot.itemsRoot.get(i);
        	tn.add(tr);
        }
			
		System.out.println(gson.toJson(tn));
		return gson.toJson(tn);
        //return tn;
	}

}
