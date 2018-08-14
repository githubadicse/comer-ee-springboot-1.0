package com.adicse.comercial.clases;

import java.util.ArrayList;
import java.util.List;

import com.adicse.comercial.model.Menu;



public class TreeRoot  {
	
	//List<TreeNodo> lstTreeNodo = new ArrayList<>();
	List<TreeNodo> itemsRoot = new ArrayList<>();
    public void addData(TreeNodo parent, Menu row)
    {
        // if we're at the end of the path - create a node
        //String data = row.getIdmenu();
        parent.setIdmenu(row.getIdmenu());
        parent.setLabel(row.getLabel());
        parent.setIcon(row.getIcon());
        parent.setLeaf(row.getLeaf());
//        if(row.getLeaf() == 1){
//        	String a[] = new String[1];
//        	a[0] = row.getRouterlink();
//        	parent.setRouterlink(a[0]);
//        }
        
        
//        List<TreeNodo> items = new ArrayList<>();
//        TreeNodo tn = new TreeNodo();
//        items.add(tn);
//        parent.setLstTreeNodo(items);
        itemsRoot.add(parent);
 
    }  
    
    public void addChild(TreeNodo parent,Integer leaf){
      List<TreeNodo> items = new ArrayList<>();
      TreeNodo tn = new TreeNodo();
      
      if(leaf == 0)
    	  tn.setLstTreeNodo(null);
      
      items.add(tn);
      //si en parent size = 0 se add sino se hace el set
      Integer size = parent.getLstTreeNodo() == null?0:parent.getLstTreeNodo().size();
      if( size == 0){
    	  parent.setLstTreeNodo(items);
      }else{
    	  parent.getLstTreeNodo().add(tn) ;  
      }
      
      
    }
    
    public void addData(TreeNodo parent, Menu row, Integer flagChildren)
    {
        // if we're at the end of the path - create a node
 //   	if(parent.getLeaf() == 0){
    		//if(parent.getLeaf() == 0)
    			this.addChild(parent,row.getLeaf());
        	
        	List<TreeNodo> lstChildTreeNodo = parent.getLstTreeNodo();
        	TreeNodo treNodoChild = lstChildTreeNodo.get(0);
        	if(treNodoChild.getIdmenu() != null ){
        		//TreeNodo nTn = new TreeNodo();
        		//lstChildTreeNodo.add(nTn);
        		treNodoChild = lstChildTreeNodo.get(lstChildTreeNodo.size()-1);
        	}
            //String data = row.getIdmenu();
            treNodoChild.setIdmenu(row.getIdmenu());
            treNodoChild.setLabel(row.getLabel());
            treNodoChild.setIcon(row.getIcon());
            treNodoChild.setLeaf(row.getLeaf());
            if(row.getLeaf() == 1){
            	String a[] = new String[1];
            	a[0] = row.getRouterlink();
            	treNodoChild.setRouterlink(a[0]);
            }            
     
            
            //verificamos si list del treenodo su id es null
 //           List<TreeNodo> subLista = treNodoChild.getLstTreeNodo();
//            if(subLista != null){
//                if(subLista.size() == 1 && subLista.get(0).idmenu == null){
//                    List<TreeNodo> items = new ArrayList<>();
//                    TreeNodo tn = new TreeNodo();
//                    items.add(tn);                	
//                	
//                	treNodoChild.setLstTreeNodo(items);
//                }else{
//                    TreeNodo tn = new TreeNodo();
//                    subLista.add(tn);                	
//                }        	
//            }else{
//                List<TreeNodo> items = new ArrayList<>();
//                TreeNodo tn = new TreeNodo();
//                items.add(tn);                	
//            	
//            	treNodoChild.setLstTreeNodo(items);        	
            	
 //           }    		
//    	}



 
    }      
    
    //longitud del idmenu siempres y cuando sea mayor a dos.
    public Integer getLength(Menu row){
    	return row.getIdmenu().length();
    }
    
    public TreeNodo getTreeNodoParent(Menu row, Integer len){
    	String idMenuFind = row.getIdmenu().substring(0,row.getIdmenu().trim().length() - 2);
    	/* Buscamos en la listra tree el parent*/
    	TreeNodo treeNodo = this.getIterationListMenu(idMenuFind);
    	return treeNodo;
    }
    
    public TreeNodo getIterationListMenu(String idmenuFind){
    	
    
    	List<TreeNodo> lstTree = itemsRoot;
    	List<TreeNodo> lstSubTreeNodo = null;
    	TreeNodo treeNodo = null;
    	for(Integer i=0; i < lstTree.size()  ;i++){
    		treeNodo = lstTree.get(i);
    		if(treeNodo.getIdmenu().trim().equals(idmenuFind)){
    			return treeNodo;
    		}
    		
        	lstSubTreeNodo = treeNodo.getLstTreeNodo();
        	while(lstSubTreeNodo != null){
        		
            	for(TreeNodo row : lstSubTreeNodo){
            		if(row.getIdmenu() != null ){
                		if(row.getIdmenu().trim().equals(idmenuFind)){
                			return row;
                		}        			
            		}

            		//break;
            	}
            	
            	lstSubTreeNodo = lstSubTreeNodo.get(0).getLstTreeNodo();
            	
        	}    		
    	}


    	return null;
    }
  
    
}
