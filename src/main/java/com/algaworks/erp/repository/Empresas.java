package com.algaworks.erp.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.algaworks.erp.model.Empresa;

public class Empresas implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	public Empresas() {

	}

	public Empresas(EntityManager manager) {
		this.manager = manager;
	}

	public Empresa porId(Long id) {
		return manager.find(Empresa.class, id);
	}

	public List<Empresa> pesquisar(String nome) {
		
		TypedQuery<Empresa> query = manager.createQuery("from Empresa where nome_fantasia like :nome_fantasia", Empresa.class);
		query.setParameter("nome_fantasia", nome + "%"); /*ESSA LINHA É RELACIONADO AO PARAMETRO QUE VAI SER UTILIZADO NA CONSULTA ACIMA O PERCENTUAL É O LIKE*/
		
		return query.getResultList() ;
	}

	public Empresa guardar(Empresa empresa) {
		return manager.merge(empresa);/*SE PASSAR UMA EMPRESA QUE NÃO EXISTE ELE DÁ UM INSERT, SE EXISTIR, ELE DÁ UM UPDATE NO REGISTRO*/
	}

	public void remover(Empresa empresa) {
		empresa = porId(empresa.getId());
		manager.remove(empresa);
	}
}