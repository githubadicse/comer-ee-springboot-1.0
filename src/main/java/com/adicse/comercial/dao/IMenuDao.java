package com.adicse.comercial.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.adicse.comercial.model.Menu;

@Repository
public interface IMenuDao extends CrudRepository<Menu, String> {
		public List<Menu> findByOrderByOrdenAsc();
}
