package com.adicse.comercial.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.adicse.comercial.model.Cierremensual;
import com.adicse.comercial.model.Kardex;
import com.adicse.comercial.model.Periodoalmacen;
import com.adicse.comercial.model.Producto;
import com.adicse.comercial.service.CierremensualService;
import com.adicse.comercial.service.KardexService;
import com.adicse.comercial.service.PeriodoalmacenService;
import com.adicse.comercial.service.ProductoService;
import com.adicse.comercial.viewResolver.PdfKardexProducto;

@RestController
@RequestMapping("/res/kardex")
public class KardexController {

	@Autowired
	private KardexService kardexService;
	
	@Autowired
	private PeriodoalmacenService periodoalmacenService; 
	
	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private CierremensualService cierremensualService;
	

	
	@RequestMapping("/pdfkardexproducto")
	public ModelAndView reportePdfMarca(HttpServletResponse res,HttpServletRequest req,
			Integer mes,Integer anno,Integer idalmacen,Integer idproducto
			){
		
		Periodoalmacen periodoalmacen = periodoalmacenService.findAllByMesEqualsAndAnnoEqualsAndAlmacenIdalmacenEquals(mes, anno, idalmacen);
		Optional<Producto> producto = productoService.findbyid(idproducto);
		
		List<Kardex> lst = kardexService.generaKardex(periodoalmacen, producto.get());		
		
		Map<String,Object> model = new HashMap<>();
		
		Cierremensual cierremensual = cierremensualService.findAllByPeriodoalmacen(periodoalmacen, producto.get());
		
		model.put("data", lst);
		model.put("cierremensual", cierremensual);
	
	
		ModelAndView mv = new ModelAndView(new PdfKardexProducto(),model);
		
		return mv;
		
	}	
}
