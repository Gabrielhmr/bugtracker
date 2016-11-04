package br.com.triadworks.bugtracker.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.triadworks.bugtracker.controller.UsuarioWeb;


@SuppressWarnings("serial")
public class AutorizacaoListener implements PhaseListener{

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext context = event.getFacesContext();
		String paginaAcessada = context.getViewRoot().getViewId();
		if ("/login.xhtml".equals(paginaAcessada)) {
			return;
		}
		
		UsuarioWeb usuarioWeb = context.getApplication().evaluateExpressionGet(context, "#{usuarioWeb}", UsuarioWeb.class);
		
		//veriifca se usuario esta logado
		if (!usuarioWeb.islogado()) {
			NavigationHandler handler = context.getApplication().getNavigationHandler();
			handler.handleNavigation(context, null, "/login?faces-redirect=true");
			
			//Pula pra ultima fase do ciclo de vida
			context.renderResponse();
		}
		
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
