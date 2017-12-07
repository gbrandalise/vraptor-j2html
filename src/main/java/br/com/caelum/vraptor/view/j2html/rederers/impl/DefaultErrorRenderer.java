package br.com.caelum.vraptor.view.j2html.rederers.impl;

import static j2html.TagCreator.*;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.google.common.collect.Lists;

import br.com.caelum.vraptor.view.j2html.exception.ViewJ2HtmlException;
import br.com.caelum.vraptor.view.j2html.rederers.ErrorRenderer;
import j2html.tags.ContainerTag;
import j2html.tags.DomContent;

/**
 * [en_US] The default implementation class of the rendering of the error page.
 * That class can be overwritten using the CDI @Specializes annotation.
 * 
 * [pt_BR] Classe default de implementação da renderização da página de erro.
 * Essa classe pode ser sobrescrita usando a anotação @Specializes do CDI.
 * 
 * @author giovanny.brandalise
 *
 */
@RequestScoped
public class DefaultErrorRenderer implements ErrorRenderer {

	private Logger log;

	/**
	 * @deprecated CDI eyes only
	 */
	@Deprecated
	protected DefaultErrorRenderer() {
		this(null);
	}

	@Inject
	public DefaultErrorRenderer(Logger log) {
		this.log = log;
	}

	@Override
	public ContainerTag getContainer(ViewJ2HtmlException ex, DomContent... content) {
		log.debug("DefaultErrorRenderer accessed");
		List<DomContent> contents = Lists.newArrayList(content);
		contents.add(rawHtml(viewExceptionToString(ex)));
		return html(head(), body(contents.toArray(new DomContent[0])));
	}

	@Override
	public ContainerTag getContainer(ViewJ2HtmlException ex, String content) {
		log.debug("DefaultErrorRenderer accessed");
		return html(head(), body(rawHtml(content + viewExceptionToString(ex))));
	}

	private String viewExceptionToString(ViewJ2HtmlException ex) {
		StringBuilder sb = new StringBuilder();
		sb.append("<!-- Exception: \n");
		Exception exp = ex.getCause() != null ? (Exception) ex.getCause() : ex;
		for (StackTraceElement ste : exp.getStackTrace()) {
			sb.append(ste.toString() + "\n");
		}
		sb.append("\n-->");
		return sb.toString();
	}

}
