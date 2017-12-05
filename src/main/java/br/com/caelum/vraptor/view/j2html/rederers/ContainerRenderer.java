package br.com.caelum.vraptor.view.j2html.rederers;

import br.com.caelum.vraptor.view.j2html.exception.ViewJ2HtmlException;
import j2html.tags.ContainerTag;
import j2html.tags.DomContent;

public interface ContainerRenderer {

	ContainerTag getContainer(DomContent... content) throws ViewJ2HtmlException;

	ContainerTag getContainer(String content) throws ViewJ2HtmlException;

}
