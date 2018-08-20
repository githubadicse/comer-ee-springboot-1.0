package com.adicse.comercial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adicse.comercial.model.Perfilesdetalle;
import com.adicse.comercial.model.Usuario;
import com.adicse.comercial.service.PerfilesdetalleService;
import com.adicse.comercial.service.UsuarioService;

@RestController
@RequestMapping("/res/perfildetalle")
public class PerfilDetalleController {
	
	@Autowired
	private PerfilesdetalleService perfilesdetalleService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	@RequestMapping(value = "/getPerfilDetalleByIdPerfil", produces=MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	public List<Perfilesdetalle> getPerfilDetalleByIdPerfil(@RequestParam ("idperfil") Integer idperfil){
		
		return perfilesdetalleService.getPerfildetalleByIdPerfil(idperfil);
		
	}

	@RequestMapping(value = "/getPerfilDetalleByLogin", produces=MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	public List<Perfilesdetalle> getPerfilDetalleByIdUsuario(@RequestParam ("login") String login){
		
		Usuario usuario = usuarioService.getAllByLogin(login);
		
		Integer idperfil = usuario.getPerfil().getIdperfil();
		
		return perfilesdetalleService.getPerfildetalleByIdPerfil(idperfil);
		
	}	
}
