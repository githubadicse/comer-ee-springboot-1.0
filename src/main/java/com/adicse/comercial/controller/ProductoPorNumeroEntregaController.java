package com.adicse.comercial.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adicse.comercial.model.CatalogoMarca;
import com.adicse.comercial.model.CatalogoProductoQaliwarma;
import com.adicse.comercial.model.ProductoPorNumeroEntrega;
import com.adicse.comercial.model.ProductoPresentacion;
import com.adicse.comercial.service.CatalogoMarcaService;
import com.adicse.comercial.service.CatalogoProductoQaliwarmaService;
import com.adicse.comercial.service.ProductoPorNumeroEntregaService;
import com.adicse.comercial.service.ProductoPresentacionService;

@RestController
@RequestMapping(value = "/res/productoPorNumeroEntrega")
public class ProductoPorNumeroEntregaController {

	@Autowired
	private ProductoPorNumeroEntregaService productoPorNumeroEntregaService;

	@Autowired
	private CatalogoProductoQaliwarmaService catalogoProductoQaliwarmaService;

	@Autowired
	private ProductoPresentacionService productoPresentacionService;

	@Autowired
	private CatalogoMarcaService catalogoMarcaService;

	@RequestMapping("/getbyanno")
	public List<ProductoPorNumeroEntrega> getProductoPorNumeroEntregaAnno(@RequestParam("anno") Integer anno) {

		List<ProductoPorNumeroEntrega> lst = productoPorNumeroEntregaService.getProductoPorNumeroEntregaAnno(anno);

		for (ProductoPorNumeroEntrega row : lst) {

			CatalogoProductoQaliwarma catalogoProductoQaliwarma = row.getCatalogoProductoQaliwarma();

			for (ProductoPresentacion productoPresentacion : catalogoProductoQaliwarma.getProductoPresentacions()) {
				productoPresentacion.setCatalogoProductoQaliwarma(null);
			}

		}

		return lst;
	}

	@RequestMapping("/getProductoPorNumeroEntregaAndIdproducto")
	public List<ProductoPorNumeroEntrega> getProductoPorNumeroEntregaAndIdproducto(@RequestParam("anno") Integer anno,
			@RequestParam("numeroEntrega") Integer numeroEntrega,
			@RequestParam("idCatalogoProducto") String idCatalogoProducto) {

		List<ProductoPorNumeroEntrega> lst = productoPorNumeroEntregaService
				.getProductoPorNumeroEntregaPorNumeroEntregaAndAnno(numeroEntrega, anno, idCatalogoProducto);

		return lst;

	}

	@RequestMapping("/getProductoPorNumeroEntregaPorNumeroEntregaAndAnnoAndItem")
	public List<ProductoPorNumeroEntrega> getProductoPorNumeroEntregaPorNumeroEntregaAndAnnoAndItem(
			@RequestParam("anno") Integer anno, @RequestParam("numeroEntrega") Integer numeroEntrega,
			@RequestParam("item") String item) {

		List<ProductoPorNumeroEntrega> lst = productoPorNumeroEntregaService
				.getProductoPorNumeroEntregaPorNumeroEntregaAndAnnoAndItem(numeroEntrega, anno, item);

		lst.sort((a, b) -> a.getCatalogoProductoQaliwarma().getDscCatalogoProductoQaliwarma()
				.compareTo(b.getCatalogoProductoQaliwarma().getDscCatalogoProductoQaliwarma()));
		return lst;

	}

	@RequestMapping("/save")
	public ProductoPorNumeroEntrega save(@RequestBody ProductoPorNumeroEntrega productoPorNumeroEntrega) {

		String idProductoPorNumeroEntrega = productoPorNumeroEntrega.getIdProductoPorNumeroEntrega();

		ProductoPorNumeroEntrega productoPorNumeroEntregaAux = productoPorNumeroEntregaService
				.findbyid(idProductoPorNumeroEntrega).isPresent()
						? productoPorNumeroEntregaService.findbyid(idProductoPorNumeroEntrega).get()
						: new ProductoPorNumeroEntrega();

		productoPorNumeroEntregaAux.setDscComplementoProducto(productoPorNumeroEntrega.getDscComplementoProducto());

		productoPorNumeroEntregaAux = productoPorNumeroEntregaService.grabar(productoPorNumeroEntregaAux);

		return productoPorNumeroEntregaAux;
	}

	@RequestMapping("/update")
	public ProductoPorNumeroEntrega update(@RequestBody ProductoPorNumeroEntrega productoPorNumeroEntrega) {

		String idProductoPorNumeroEntrega = productoPorNumeroEntrega.getIdProductoPorNumeroEntrega();
		Integer numeroEntrega = productoPorNumeroEntrega.getEntregaPorItem().getNumeroEntrega().getNumeroEntregaValor();
		Integer anno = productoPorNumeroEntrega.getEntregaPorItem().getItemEntrega().getAnno();

		ProductoPorNumeroEntrega productoPorNumeroEntregaAux = productoPorNumeroEntregaService
				.findbyid(idProductoPorNumeroEntrega).isPresent()
						? productoPorNumeroEntregaService.findbyid(idProductoPorNumeroEntrega).get()
						: new ProductoPorNumeroEntrega();

		BeanUtils.copyProperties(productoPorNumeroEntrega, productoPorNumeroEntregaAux);

		// Elimino todos los catalogos marcas relacionados.....
		List<CatalogoMarca> lstCatalogoMarca = catalogoMarcaService
				.getCatalogoMarcaByIdProductoPorNumeroEntrega(idProductoPorNumeroEntrega, anno, numeroEntrega);

		for (CatalogoMarca row : lstCatalogoMarca) {
			catalogoMarcaService.deletebyid(row.getIdCatalogoMarca());
		}

		String idCatalogoProductoQaliwarma = productoPorNumeroEntrega.getCatalogoProductoQaliwarma()
				.getIdCatalogoProductoQaliwarma();

		// llamar al producto presentacion por el anno y el numero de entrega.
		List<ProductoPresentacion> lstProductoPresentacion = productoPresentacionService
				.getProductoPresentacionByIdProductoAnnoNumeroEntrega(idCatalogoProductoQaliwarma, anno, numeroEntrega);

		for (ProductoPresentacion presentacion : lstProductoPresentacion) {

			CatalogoMarca catalogoMarca = new CatalogoMarca();
			catalogoMarca
					.setIdCatalogoMarca(idProductoPorNumeroEntrega + "-" + presentacion.getIdProductoPresentacion());
			catalogoMarca.setProductoPorNumeroEntrega(productoPorNumeroEntrega);
			catalogoMarca.setProductoPresentacion(presentacion);
			catalogoMarcaService.grabar(catalogoMarca);
		}

		productoPorNumeroEntregaAux = productoPorNumeroEntregaService.grabar(productoPorNumeroEntregaAux);

		return productoPorNumeroEntregaAux;
	}

}
