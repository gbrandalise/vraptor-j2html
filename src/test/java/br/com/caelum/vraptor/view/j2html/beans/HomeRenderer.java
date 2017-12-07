package br.com.caelum.vraptor.view.j2html.beans;

import static j2html.TagCreator.*;

import br.com.caelum.vraptor.view.j2html.exception.ViewJ2HtmlException;
import br.com.caelum.vraptor.view.j2html.rederers.ViewRenderer;
import br.com.caelum.vraptor.view.j2html.util.ViewAttributesWrapper;
import j2html.tags.DomContent;

public class HomeRenderer implements ViewRenderer {
	
	private ViewAttributesWrapper attributes;
	
	public HomeRenderer(ViewAttributesWrapper attributes) {
		this.attributes = attributes;
	}

	@Override
	public DomContent[] getElements() throws ViewJ2HtmlException {
		Client client = attributes.getRequestAttribute("client", Client.class);
		return new DomContent[] {text(client.getLogin()), br()};
	}

}
