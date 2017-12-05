package br.com.caelum.vraptor.view.j2html.rederers;

import br.com.caelum.vraptor.view.j2html.exception.ViewJ2HtmlException;
import j2html.tags.ContainerTag;
import j2html.tags.DomContent;

public interface ErrorRenderer {

	ContainerTag getContainer(ViewJ2HtmlException ex, DomContent... content);

	ContainerTag getContainer(ViewJ2HtmlException ex, String content);

}
