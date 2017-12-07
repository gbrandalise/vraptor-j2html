package br.com.caelum.vraptor.view.j2html.rederers.impl;

import static j2html.TagCreator.rawHtml;

import br.com.caelum.vraptor.view.j2html.exception.ViewJ2HtmlException;
import br.com.caelum.vraptor.view.j2html.rederers.ViewRenderer;
import j2html.tags.DomContent;

/**
 * [en_US] The default implementation class for rendering the view element when
 * not specified on @J2HtmlRenderer annotation.
 * 
 * [pt_BR] Classe de implementação padrão para renderizar o elemento de exibição
 * quando não especificado na anotação @J2HtmlRenderer
 * 
 * @author giovanny.brandalise
 *
 */
public class DefaultViewRenderer implements ViewRenderer {

	@Override
	public DomContent[] getElements() throws ViewJ2HtmlException {
		return new DomContent[] { rawHtml("") };
	}

}
