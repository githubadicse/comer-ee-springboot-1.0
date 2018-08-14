package com.adicse.comercial.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adicse.comercial.model.Usuarioempleado;
import com.adicse.comercial.service.UsuarioEmpleadoService;

@RestController
@RequestMapping("/res/usuarioempleado")
public class UsuarioempleadoController {
	
	@Autowired
	private UsuarioEmpleadoService usuarioEmpleadoService;
	
	@ResponseBody
	@RequestMapping("/getUsuarioEmpleadoByIdUsuario")
	public Map<String,Object> getUsuarioEmpleadoByIdUsuario(Integer idusuario){
		Map<String,Object> response = new HashMap<>();
		
		Usuarioempleado usuarioempleado = usuarioEmpleadoService.getUsuarioEmpleadoByIdUsuario(idusuario);
		
		
		response.put("data", usuarioempleado);

		
		return response;
	}

}
