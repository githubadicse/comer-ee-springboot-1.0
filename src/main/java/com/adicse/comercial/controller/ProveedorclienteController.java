package com.adicse.comercial.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adicse.comercial.model.Lineacredito;
import com.adicse.comercial.model.Proveedorcliente;
import com.adicse.comercial.model.Proveedorclientedireccion;
import com.adicse.comercial.service.ProveedorclienteService;
import com.adicse.comercial.utilitarios.Idunico;

@RestController
@RequestMapping("/res/proveedorcliente")
public class ProveedorclienteController {
	
	@Autowired
	private ProveedorclienteService proveedorclienteService;
	
	
	@RequestMapping("/filterGlobal")
	@ResponseBody
	public List<Proveedorcliente> filtroBusqueda(@RequestParam("dato") String dato){
		
		List<Proveedorcliente> lst = proveedorclienteService.filterGlobal(dato);
		
		for(Proveedorcliente pc:lst) {
			
			for(Proveedorclientedireccion pcd:pc.getProveedorclientedireccions()) {
				pcd.setProveedorcliente(null);
			}
		}
		
		return lst ;
	}
	
	@RequestMapping("/pagination")
	@ResponseBody
	public Map<String, Object> pagination(@RequestParam("pagenumber") Integer pagenumber,
			@RequestParam("rows") Integer rows, @RequestParam("sortdireccion") String sortdireccion,
			@RequestParam("sortcolumn") String sortcolumn, @RequestParam("filters") Object filter) {

		System.out.println("pagenumber :" + pagenumber);
		System.out.println("rows :" + rows);
		Page<Proveedorcliente> page = proveedorclienteService.pagination(pagenumber, rows, sortdireccion, sortcolumn, filter);

		List<Proveedorcliente> lst = page.getContent();

		Map<String, Object> response = new HashMap<String, Object>();

		response.put("data", lst);
		response.put("totalCount", page.getTotalElements());
		response.put("success", true);
		return response;
	}	
	
	@RequestMapping("/findbyid")
	@ResponseBody
	public Map<String, Object> getbyid(@RequestParam("id") Integer idproveedorcliente){
		Map<String, Object> response = new HashMap<>();
		
		Optional<Proveedorcliente> proveedorcliente = proveedorclienteService.findbyid(idproveedorcliente);
		
		for(Proveedorclientedireccion pcd:proveedorcliente.get().getProveedorclientedireccions()){
			pcd.setProveedorcliente(null);
		}
		
		for(Lineacredito lc:proveedorcliente.get().getLineacreditos()){
			lc.setProveedorcliente(null);
		}
		
		response.put("data", proveedorcliente);
		return response;
	}
	@RequestMapping("/edit")
	@ResponseBody
	public Proveedorcliente getEdit(@RequestParam("id") Integer id) {
		
		Proveedorcliente proveedorcliente = proveedorclienteService.findbyid(id).get();
		for(Proveedorclientedireccion row:proveedorcliente.getProveedorclientedireccions()) {
			row.setProveedorcliente(null);
		}
		return proveedorcliente;
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public Proveedorcliente postCreate(@RequestBody Proveedorcliente proveedorcliente) {
		proveedorcliente.setIdproveedorcliente(0);
		return proveedorclienteService.grabar(proveedorcliente);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Proveedorcliente putUdate(@RequestBody Proveedorcliente proveedorcliente) {
		
		//eliminamos el detalle es decir las direcciones.
		proveedorclienteService.deleteDireccionByIdProveedorCliente(proveedorcliente.getIdproveedorcliente());
		
		
		Proveedorcliente proveedorclienteUpdate = proveedorclienteService.findbyid(proveedorcliente.getIdproveedorcliente() ).get();
		
		BeanUtils.copyProperties(proveedorcliente, proveedorclienteUpdate);
		
		for(Proveedorclientedireccion row: proveedorclienteUpdate.getProveedorclientedireccions()) {
			row.setProveedorcliente(proveedorclienteUpdate);
			row.setIdproveedorclientedireccion(new Idunico().getIdunico());
		}
		
		proveedorclienteUpdate =proveedorclienteService.grabar(proveedorclienteUpdate);
		
		for(Proveedorclientedireccion row2:proveedorclienteUpdate.getProveedorclientedireccions()) {
			row2.setProveedorcliente(null);
		}
		return proveedorclienteUpdate;
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable Integer id) {	
		
		proveedorclienteService.deletebyid(id);
	}

	@RequestMapping("/findAllByRazonsocialContainsIgnoreCaseOrderByRazonsocial")
	@ResponseBody
	public Map<String, Object> findAllByRazonsocialContainsIgnoreCaseOrderByRazonsocial(String razonsocial){
		 Map<String, Object> response = new HashMap<>();
		 
		 List<Proveedorcliente> lst = proveedorclienteService.findAllByRazonsocialContainsIgnoreCaseOrderByRazonsocial(razonsocial);
		 response.put("data", lst);
		 
		 return response;
	}
	
	@RequestMapping("/getall")
	@ResponseBody
	public List<Proveedorcliente> getall(){
		return proveedorclienteService.getall();
	}
	

}
