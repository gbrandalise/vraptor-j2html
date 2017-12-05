package br.com.caelum.vraptor.view.j2html.rederers;

import br.com.caelum.vraptor.view.j2html.exception.ViewJ2HtmlException;
import j2html.tags.DomContent;

public interface ViewRenderer {
	
	DomContent[] getElements() throws ViewJ2HtmlException;
	
}
