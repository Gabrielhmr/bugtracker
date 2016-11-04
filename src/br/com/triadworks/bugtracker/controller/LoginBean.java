package br.com.triadworks.bugtracker.controller;



import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.triadworks.bugtracker.modelo.Usuario;
import br.com.triadworks.bugtracker.service.Autenticador;
import br.com.triadworks.bugtracker.service.AutenticadorImpl;
import br.com.triadworks.bugtracker.util.FacesUtils;

@Controller
@Scope("request")
public class LoginBean {

	private String login;
	private String senha;
	
	@Autowired
	private UsuarioWeb usuarioWeb;
	
	@Autowired
	private Autenticador autenticador;
	
	@Autowired
	FacesUtils facesUtils;

	public String logar() {
		Usuario usuario = autenticador.autentica(login, senha);
		if (usuario != null) {
			usuarioWeb.loga(usuario); //preencha usuario na sessao
			return "/pages/usuario/lista?faces-redirect=true";
		}
		facesUtils.adicionaMensagemDeErro("Login ou senha invalidos");
		return null;
	}
	
	public String sair() {
		usuarioWeb.desloga();
		return "/login.xhtml?faces-redirect=true";

	}
	
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	
	
}
