package br.com.caelum.vraptor.view.j2html.rederers.impl;

import static j2html.TagCreator.head;
import static j2html.TagCreator.rawHtml;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.slf4j.Logger;

import br.com.caelum.vraptor.view.j2html.exception.ViewJ2HtmlException;
import br.com.caelum.vraptor.view.j2html.rederers.HeadRenderer;
import j2html.tags.ContainerTag;
import j2html.tags.DomContent;

/**
 * [en_US Default implementation class of the rendering of the html head
 * element. This class can be overwritten using the CDI @Specializes annotation.
 * 
 * [pt_BR] Classe default de implementação da renderização do elemento head do
 * html. Essa classe pode ser sobrescrita usando a anotação @Specializes do CDI.
 * 
 * @author giovanny.brandalise
 *
 */
@RequestScoped
public class DefaultHeadRenderer implements HeadRenderer {

	private Logger log;

	/**
	 * @deprecated CDI eyes onlye
	 */
	@Deprecated
	protected DefaultHeadRenderer() {
		this(null);
	}

	@Inject
	public DefaultHeadRenderer(Logger log) {
		this.log = log;
	}

	@Override
	public ContainerTag getContainer(DomContent... content) throws ViewJ2HtmlException {
		log.debug("DefaultHeadRenderer accessed");
		return head(content);
	}

	@Override
	public ContainerTag getContainer(String content) throws ViewJ2HtmlException {
		log.debug("DefaultHeadRenderer accessed");
		return head(rawHtml(content));
	}

}
