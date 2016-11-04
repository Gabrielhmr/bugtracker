package br.com.triadworks.bugtracker.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.triadworks.bugtracker.dao.BugDao;
import br.com.triadworks.bugtracker.dao.UsuarioDao;
import br.com.triadworks.bugtracker.modelo.Bug;
import br.com.triadworks.bugtracker.modelo.Status;
import br.com.triadworks.bugtracker.modelo.Usuario;
import br.com.triadworks.bugtracker.util.FacesUtils;

// fazer imports

@Controller
@Scope("request")
public class BugBean {

	private Bug bug = new Bug();
	
	@Autowired
	private BugDao dao;
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	FacesUtils facesUtils;
	
	private List<Bug> bugs = new ArrayList<Bug>();
	
	private List<Usuario> usuarios;
	
	
	@PostConstruct
	public void init() {
		this.bug.setResponsavel(new Usuario());
	}
	
	public void lista() {
		this.bugs = dao.lista();
	}
	
	public void altera() {
		dao.atualiza(bug);
		facesUtils.adicionaMensagemDeSucesso("Bug atualizado com sucesso!");
	}
	
	public void remove(Bug bug) {
		dao.remove(bug);
		this.bugs = dao.lista();
		facesUtils.adicionaMensagemDeSucesso("Bug removido com sucesso!");
	}
	
	public void adiciona() {
		dao.salva(bug);
		this.bug = new Bug();
		facesUtils.adicionaMensagemDeSucesso("Bug adicionado com sucesso!");
	}
	
	public List<Status> getTodosOsStatus() {
		return Arrays.asList(Status.values());
	}

	public Bug getBug() {
		return this.bug;
	}

	public List<Bug> getBugs() {
		return bugs;
	}

	public void setBugs(List<Bug> bugs) {
		this.bugs = bugs;
	}

	public void setBug(Bug bug) {
		this.bug = bug;
	}

	public List<Usuario> getUsuarios() {
		if (usuarios == null) {
			this.usuarios = usuarioDao.lista();
		}
		return usuarios;
	}

	
	
	
}