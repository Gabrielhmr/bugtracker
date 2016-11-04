package br.com.triadworks.bugtracker.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.triadworks.bugtracker.dao.UsuarioDao;
import br.com.triadworks.bugtracker.modelo.Usuario;
import br.com.triadworks.bugtracker.util.FacesUtils;

@Controller
@Scope("request")
public class UsuarioBean {
	
	@Autowired
	private UsuarioDao dao;
	
	@Autowired
	FacesUtils facesUtils;
	
	private Usuario usuario = new Usuario();
	
	private List<Usuario> usuarios;
	
	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}
	
	public void lista() {
		//UsuarioDao dao = new UsuarioDao();
		this.usuarios = dao.lista();
	}
	
	
	public Usuario getUsuario() {
		return this.usuario;
	}
	
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void adiciona() {
		//UsuarioDao dao = new UsuarioDao();
		dao.adiciona(this.usuario);
		this.usuario = new Usuario(); //limpa campos
		facesUtils.adicionaMensagemDeSucesso("Usuario adicionado com sucesso");
	}
	
	public void remove(Usuario usuario) {
		//UsuarioDao dao = new UsuarioDao();
		dao.remove(usuario);
		this.usuarios = dao.lista();//recarregar usuario
		facesUtils.adicionaMensagemDeSucesso("Usuario removido com sucesso");
	}
	
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
		
	}

	public void altera() {
		//UsuarioDao dao = new UsuarioDao();
		dao.atualiza(usuario);
		facesUtils.adicionaMensagemDeSucesso("Usuario atualizado com sucesso");
	}

}
