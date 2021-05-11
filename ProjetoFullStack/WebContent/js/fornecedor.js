$(document).ready(function () {
	var fornecedor = new Object();

	$("#cadCliente").click(function (e) {

		fornecedor.name = $("#name").val();
		fornecedor.comment = $("#comment").val();
		fornecedor.cnpj = $("#cnpj").val();
		fornecedor.cnpj = fornecedor.cnpj.replace(/\./g, "");
		fornecedor.cnpj = fornecedor.cnpj.replace(/\//g, "");
		fornecedor.cnpj = fornecedor.cnpj.replace(/\-/g, "");
		fornecedor.email = $("#email").val();

		var cfg = {
			url: "rest/fornecedorRest/addFornecedor",
			data: JSON.stringify(fornecedor),
			success: function (succJson) {
				if (succJson) {
					resp = ("Fornecedor cadastrado com sucesso!");
					exibirMessagem(resp, 1);
				} else {
					resp = ("Erro ao cadastrar fornecedor!");
					exibirMessagem(resp, 2);
				}

			},
			error: function (errJson) {
				resp = ("Erro ao cadastrar fornecedor!");
				exibirMessagem(resp, 2);
			}
		};
		desafioFullStack.ajax.post(cfg);
	});

});
