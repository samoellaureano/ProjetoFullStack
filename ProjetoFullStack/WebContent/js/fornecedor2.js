$(document).ready(function () {
    // Activate tooltip
    $('[data-toggle="tooltip"]').tooltip();

    // Select/Deselect checkboxes
    var checkbox = $('table tbody input[type="checkbox"]');
    $("#selectAll").click(function () {
        if (this.checked) {
            checkbox.each(function () {
                this.checked = true;
            });
        } else {
            checkbox.each(function () {
                this.checked = false;
            });
        }
    });
    checkbox.click(function () {
        if (!this.checked) {
            $("#selectAll").prop("checked", false);
        }
    });

    busca();
});

cadFornecedor = function () {
    var fornecedor = new Object();

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
                busca();
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

};

busca = function () {
    var cfg = {
        type: "POST",
        url: "rest/fornecedorRest/buscarFornecedor",
        success: function (listaDeFornecedores) {
            visualizarFornecedores(listaDeFornecedores);
        },
        error: function (errJson) {
            resp = ("Erro ao buscar os dados!");
            exibirMessagem(resp, 2);
        }
    };
    desafioFullStack.ajax.post(cfg);
};

visualizarFornecedores = function (listaDeFornecedores) {

    var tbody = $('#tbFornecedores > tbody');

    if (listaDeFornecedores != undefined) {
        if (listaDeFornecedores.length > 0) {
            for (var i = 0; i < listaDeFornecedores.length; i++) {
                console.log(listaDeFornecedores[i].name);
                tbody.append($('<tr>')
                            .append($('<td>').append("<th><span class='custom-checkbox'><input type='checkbox' id='selectAll'><label for='selectAll'></label></span></th>"))
                            .append($('<td>').append(listaDeFornecedores[i].name))
                            .append($('<td>').append(listaDeFornecedores[i].comment))
                            .append($('<td>').append(listaDeFornecedores[i].email))
                            .append($('<td>').append(listaDeFornecedores[i].cnpj))
                            .append($('<td>').append())
                )
            };
        } else {
            tbody.append($('<tr>')
                        .append("<tr>Nenhum registro encontrado</tr>")
            )
        };

    } else {
        tbody.append($('<tr>')
                        .append("<tr>Nenhum registro encontrado</tr>")
            )
    };
};

