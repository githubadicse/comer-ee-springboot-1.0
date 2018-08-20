package com.adicse.comercial.service;
import java.util.List;
import java.util.Optional;
import static com.adicse.comercial.specification.SpecificationBuilder.selectFrom;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.adicse.comercial.dao.IGuiaRemision001Dao;
import com.adicse.comercial.dao.IUsuarioDao;
import com.adicse.comercial.especification.UsuarioSpecification;
import com.adicse.comercial.model.GuiaRemision001;
import com.adicse.comercial.model.Usuario;
import com.adicse.comercial.model.Usuarioempleado;
import com.adicse.comercial.model.Vehiculo;
import com.adicse.comercial.shared.CustomFilterSpec;
import com.adicse.comercial.specification.ConvertObjectToFormatJson;
import com.adicse.comercial.specification.Filter;
import com.adicse.comercial.utilitarios.Idunico;

@Service
@Transactional
public class UsuarioService implements IAdicseService<Usuario, Integer> {
	
	@Autowired
	private IUsuarioDao iUsuarioDao;

	@Autowired
	private ConvertObjectToFormatJson convertObjectToFormatJson;
	
	@Override
	public Page<?> paginationParmsExtra(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter, Object paramsExtra) {
		return null;
	}

	@Override
	public Page<Usuario> pagination(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter) {
		
		Sort sort = new Sort(sortdireccion.toUpperCase() == "DESC" ? Direction.DESC : Direction.ASC, sortcolumn);
		Pageable pageable =  PageRequest.of(pagenumber, rows, sort);
		
		Filter f = convertObjectToFormatJson.ConvertObjectToFormatSpecification(filter);

		Page<Usuario> lista = selectFrom(iUsuarioDao).where(f).findPage(pageable);

		return lista;
	}

	@Override
	public List<Usuario> getall() {
		// TODO Auto-generated method stub
		return (List<Usuario>) iUsuarioDao.findAll();
	}

	@Override
	public List<Usuario> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario grabar(Usuario entidad) {
		// TODO Auto-generated method stub
		Integer nexId = 0;
		
		if(entidad.getIdusuario() == 0){
			nexId = iUsuarioDao.getMax()==null?1: iUsuarioDao.getMax() + 1;
			entidad.setIdusuario(nexId);
		}
		
		if(entidad.getUsuarioempleados() != null ){
			for (Usuarioempleado usuarioempleado : entidad.getUsuarioempleados()) {
				usuarioempleado.setIdusuarioempleado(new Idunico().getIdunico());
			}
		}

		return iUsuarioDao.save(entidad);
	}

	@Override
	public void delete(Usuario entidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletebyid(Integer id) {
		// TODO Auto-generated method stub
		iUsuarioDao.deleteById(id);
		
	}

	@Override
	public Boolean exists(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Usuario> findbyid(Integer id) {
		// TODO Auto-generated method stub
		return iUsuarioDao.findById(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Usuario getAllByLogin(String login){
		return iUsuarioDao.findAllByLogin(login);
	}

	@Override
	public Optional<Usuario> EntityForSpecificatios(Usuario entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Usuario> lstUsuario(Filter filter){
		return selectFrom(iUsuarioDao).where(filter).findAll();
	}
	

}
