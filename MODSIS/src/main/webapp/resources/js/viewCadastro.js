/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


    var state = "incluir";
    var btnIncluir = document.getElementById("cadastro:btnIncluir");
    var btnAlterar = document.getElementById("cadastro:btnAlterar");
    var btnExcluir = document.getElementById("cadastro:btnExcluir");
    function excluir()
    {
        if (confirm('Confirma a exclusão do produto ?'))
            if (validacao())
                return true;
        return false;
    }
    function validacao()
    {
        if (!cadastro.checkValidity())
        {
            alert("Preencha todos os campos!");
            return false;
        }
        else
            return true;
    }
    function showWaiting(data)
    {
        var a = document.getElementById("message");
        var status = data.status;
        switch (status) 
        {
            case "begin": // Before the ajax request is sent.
                a.innerText = "Loading... please wait"; ;
            break;
            case "complete": // After the ajax response is arrived.
                a.innerText = status;
            break;
            case "success": // After update of HTML DOM based on ajax response..
                //a.innerText = document.getElementById("message").innerText;
            break;
        }
        if (status == "begin")
            return true;
    }
    onload = function()
    {
    	
    }