package com.adicse.comercial.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.adicse.comercial.model.GuiaRemision001;
import com.adicse.comercial.model.GuiaRemision002;
import com.adicse.comercial.service.GuiaRemision001Service;
import com.adicse.comercial.viewResolver.PdfGuiaRemision;

@RestController
@RequestMapping("/res/guiaremision")
public class GuiaRemision001Controller {
	
	@Autowired
	private GuiaRemision001Service guiaRemision001Service; 
	
	
	@RequestMapping("/pagination")
	@ResponseBody
	public Map<String, Object> pagination(@RequestParam("pagenumber") Integer pagenumber,
			@RequestParam("rows") Integer rows, @RequestParam("sortdireccion") String sortdireccion,
			@RequestParam("sortcolumn") String sortcolumn, @RequestParam("filters") Object filter) {

		System.out.println("pagenumber :" + pagenumber);
		System.out.println("rows :" + rows);
		Page<GuiaRemision001> page = guiaRemision001Service.pagination(pagenumber, rows, sortdireccion, sortcolumn, filter);

		List<GuiaRemision001> lst = page.getContent();
		


		Map<String, Object> response = new HashMap<String, Object>();

		response.put("data", lst);
		response.put("totalCount", page.getTotalElements());
		response.put("success", true);
		return response;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public GuiaRemision001 updateGuiaRemision(@RequestBody GuiaRemision001 guiaRemision001) {
		
		GuiaRemision001 guiaRemisionUpdate = guiaRemision001Service.findbyid(guiaRemision001.getIdGuiaRemision001() ).get() ;
		
		BeanUtils.copyProperties(guiaRemision001, guiaRemisionUpdate);
		
		return guiaRemision001Service.grabar(guiaRemisionUpdate);	
		
	}
	
	@RequestMapping("/guiaRemisionPorSerieNumero")
	public ModelAndView getGuiaRemision(
			@RequestParam("serie") Integer serie,
			@RequestParam("numero") Integer numero) {
		
		GuiaRemision001 guiaRemision001 = guiaRemision001Service.getGuiaRemisionPorSerieNumero(serie, numero);
		List<GuiaRemision001> lstGuiaRemision001 = new ArrayList<GuiaRemision001>();
		
		for(GuiaRemision002 guiaRemision002: guiaRemision001.getGuiaRemision002s()) {
			guiaRemision002.setGuiaRemision001(null);
		}
		
		lstGuiaRemision001.add(guiaRemision001);
		
		Map<String,Object> model = new HashMap<>();
		
		model.put("data", lstGuiaRemision001);
		
		ModelAndView mv = new ModelAndView(new PdfGuiaRemision(),model);
		
		return mv;	
		
	}

	@RequestMapping("/guiaRemisionPorCodigoModular")
	public ModelAndView getGuiaRemisionPorCodigoModular(
			@RequestParam("anno") Integer anno,
			@RequestParam("numeroEntrega") Integer numeroEntrega,
			@RequestParam("codigoModular") String codigoModular
			) {
		
		codigoModular = codigoModular.trim();
		GuiaRemision001 guiaRemision001 = guiaRemision001Service.getGuiaRemisionPorCodigoModular(anno, numeroEntrega, codigoModular) ;
		List<GuiaRemision001> lstGuiaRemision001 = new ArrayList<GuiaRemision001>();
		
		for(GuiaRemision002 guiaRemision002: guiaRemision001.getGuiaRemision002s()) {
			guiaRemision002.setGuiaRemision001(null);
		}
		
		lstGuiaRemision001.add(guiaRemision001);
		
		Map<String,Object> model = new HashMap<>();
		
		model.put("data", lstGuiaRemision001);
		
		ModelAndView mv = new ModelAndView(new PdfGuiaRemision(),model);
		
		return mv;	
		
	}
	
	@RequestMapping("/guiaRemisionPorCodigoModularJson")
	public GuiaRemision001 getGuiaRemisionPorCodigoModularJson(
			@RequestParam("anno") Integer anno,
			@RequestParam("numeroEntrega") Integer numeroEntrega,
			@RequestParam("codigoModular") String codigoModular
			) {
		
		codigoModular = codigoModular.trim();
		GuiaRemision001 guiaRemision001 = guiaRemision001Service.getGuiaRemisionPorCodigoModular(anno, numeroEntrega, codigoModular) ;
		
		return guiaRemision001;
	}
	
	@RequestMapping("/guiasRemisionPorCodigoModular")
	public List<GuiaRemision001> getGuiaRemisionPorCodigoModular(
			@RequestParam("codigoModular") String CodigoModular){
		
		
		return null;
	}
	
	@RequestMapping("/guiasRemisionPorItem")
	@ResponseBody
	public ModelAndView getGuiaRemisionPorItem(
			@RequestParam("idItem") String idItem,
			@RequestParam("anno") Integer anno,
			@RequestParam("numeroEntrega") Integer numeroEntrega
			){
		
		List<GuiaRemision001> lstGuiaRemision001 = guiaRemision001Service.getGuiaRemisionPorItemAndAnnoNumeroEntrega(idItem, anno, numeroEntrega);
		
		for(GuiaRemision001 guiaRemision001: lstGuiaRemision001) {
			for(GuiaRemision002 guiaRemision002: guiaRemision001.getGuiaRemision002s()) {
				guiaRemision002.setGuiaRemision001(null);
			}
		}
		
		Map<String,Object> model = new HashMap<>();
		
		model.put("data", lstGuiaRemision001);
		
		ModelAndView mv = new ModelAndView(new PdfGuiaRemision(),model);
		
		return mv;	
		
		
		
		
	}
	
//	@RequestMapping("/update")
//	@ResponseBody
//	public GuiaRemision001 putUdate(@RequestBody GuiaRemision001 guiaRemision001) {
//		
//		GuiaRemision001 guiaRemisionUpdate = guiaRemision001Service.findbyid(guiaRemision001.getIdGuiaRemision001()).get();
//		
//		BeanUtils.copyProperties(guiaRemision001, guiaRemisionUpdate);
//		
//		return guiaRemision001Service.grabar(guiaRemisionUpdate);
//	}
}
