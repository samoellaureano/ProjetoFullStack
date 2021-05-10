package br.com.dao.jpa;

import br.com.dao.FornecedorDAO;
import br.com.entidade.Fornecedor;

public class FornecedorJPADAO extends JPAAbstract<Fornecedor> implements FornecedorDAO{

	@Override
	public String getEntityName() {
		return "Empresa";
	}

}
