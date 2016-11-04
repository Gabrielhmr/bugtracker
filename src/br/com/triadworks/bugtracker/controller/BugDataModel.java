package br.com.triadworks.bugtracker.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.triadworks.bugtracker.dao.BugDao;
import br.com.triadworks.bugtracker.modelo.Bug;

@ViewScoped
@ManagedBean
public class BugDataModel extends LazyDataModel<Bug> {
	
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{bugDao}")
	private BugDao dao;
	
	@PostConstruct
	public void init() {
		this.setRowCount(dao.contaTodos());
	}
	
	@Override
	public List<Bug> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {
		return dao.listaPaginada(first, pageSize);
	}
	
	public void setDao(BugDao dao) {
		this.dao = dao;
	}
}