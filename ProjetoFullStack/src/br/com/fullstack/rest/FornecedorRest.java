package br.com.fullstack.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import br.com.fullstack.dao.jpa.FornecedorJPADAO;
import br.com.fullstack.entidade.Fornecedor;
import br.com.fullstack.util.UtilRest;

@Path("fornecedorRest")
public class FornecedorRest extends UtilRest{
	
	@POST
	@Path("/addFornecedor")
	@Consumes("application/*")

	public Response salvar(String addEmpresa){

		try {

			Fornecedor empresa = new ObjectMapper().readValue(addEmpresa,Fornecedor.class);
			boolean	retorno = new FornecedorJPADAO().salvar(empresa);

			return this.buildResponse(retorno);

		} catch (Exception e){
			e.printStackTrace();

			return this.buildErrorResponse(e.toString());
		}

	}
	
	@POST
	@Path("/buscarFornecedor")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response buscarFornecedor(){
		try{
			List<Fornecedor> fornecedor = new FornecedorJPADAO().buscar();
			
			return this.buildResponse(fornecedor);
		}catch (Exception e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	
	
	@POST
	@Path("/buscarFornecedorPorId/{idFornecedor}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response buscarFornecedorPorId(@PathParam("idFornecedor") int idFornecedor){
		try{
			Fornecedor fornecedor = new FornecedorJPADAO().buscarPorId(idFornecedor);
			
			return this.buildResponse(fornecedor);
		}catch (Exception e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	
	@POST
	@Path("/editFornecedor")
	@Consumes("application/*")

	public Response editFornecedor(String editFornecedor){

		try {

			Fornecedor fornecedor = new ObjectMapper().readValue(editFornecedor,Fornecedor.class);
			FornecedorJPADAO fornecedorJpadao = new FornecedorJPADAO();
			boolean	retorno = fornecedorJpadao.atualizar(fornecedor);

			return this.buildResponse(retorno);			
			
		} catch (Exception e){
			e.printStackTrace();

			return this.buildErrorResponse(e.toString());
		}

	}
	@POST
	@Path("/excluirFornecedor/{idFornecedor}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response excluirFornecedor(@PathParam("idFornecedor") int idFornecedor){

		try {
			boolean	retorno = false;
			
			retorno = new FornecedorJPADAO().excluirPorId(idFornecedor);
				
			return this.buildResponse(retorno);
		} catch (Exception e){
			e.printStackTrace();

			return this.buildErrorResponse(e.toString());
		}
	}

}
