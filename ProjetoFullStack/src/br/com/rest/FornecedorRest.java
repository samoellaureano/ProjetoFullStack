package br.com.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import br.com.dao.jpa.FornecedorJPADAO;
import br.com.entidade.Fornecedor;
import br.com.util.UtilRest;

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

			return this.buildErrorResponse("Erro ao cadastrar");
		}

	}
	
	@POST
	@Path("/buscaEmpresasPorDesc/{desc}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response buscaEmpresasPorDesc(@PathParam("desc") String desc){
		try{
			List<Fornecedor> listaEmpresas = new FornecedorJPADAO().buscarPorDescricao(desc);
			
			return this.buildResponse(listaEmpresas);
		}catch (Exception e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	
	@POST
	@Path("/buscarEmpresaPorId/{idEmpresa}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response buscarEmpresaPorId(@PathParam("idEmpresa") int idEmpresa){
		try{
			Fornecedor empresa = new FornecedorJPADAO().buscarPorId(idEmpresa);
			
			return this.buildResponse(empresa);
		}catch (Exception e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	
	@POST
	@Path("/editEmpresa")
	@Consumes("application/*")

	public Response editEmpresa(String editEmpresa){

		try {

			Fornecedor empresa = new ObjectMapper().readValue(editEmpresa,Fornecedor.class);
			FornecedorJPADAO empresaJpadao = new FornecedorJPADAO();
			boolean	retorno = empresaJpadao.atualizar(empresa);

			if(retorno){
				// true = Cadastrado com sucesso.
				return this.buildResponse("1");				

			}else if(retorno==false){
				// false = ja existe
				return this.buildErrorResponse("2");

			}else {
				// null = Erro ao cadastrar
				return this.buildErrorResponse("0");			
			}

		} catch (Exception e){
			e.printStackTrace();

			return this.buildErrorResponse("Erro ao cadastrar");
		}

	}
	@POST
	@Path("/excluirEmpresa/{idEmpresa}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response inativaFuncionario(@PathParam("idEmpresa") int idEmpresa){

		try {
			boolean	retorno = false;
			
			retorno = new FornecedorJPADAO().excluirPorId(idEmpresa);
			
			if(retorno){
				
				return this.buildResponse(retorno);				
			}else{
				
				return this.buildResponse(retorno);
			}
			

		} catch (Exception e){
			e.printStackTrace();

			return this.buildErrorResponse("Erro ao atualizar Funcionario");
		}
	}

}
