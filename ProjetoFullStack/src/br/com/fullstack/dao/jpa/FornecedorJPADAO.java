package br.com.fullstack.dao.jpa;

import br.com.fullstack.dao.FornecedorDAO;
import br.com.fullstack.entidade.Fornecedor;

public class FornecedorJPADAO extends JPAAbstract<Fornecedor> implements FornecedorDAO{

	@Override
	public String getEntityName() {
		return "Fornecedor";
	}

}
