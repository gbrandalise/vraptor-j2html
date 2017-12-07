package br.com.caelum.vraptor.view.j2html.rederers;

import br.com.caelum.vraptor.view.j2html.exception.ViewJ2HtmlException;
import j2html.tags.ContainerTag;
import j2html.tags.DomContent;

/**
 * [en_US] Interface for rendering html container elements (html, head, body,
 * etc).
 * 
 * [pt_BR] Interface de renderização de elementos containers do html (html,
 * head, body, etc).
 * 
 * @author giovanny.brandalise
 *
 */
public interface ContainerRenderer {

	/**
	 * [en_US] Method that receives a list of child elements from DomContent to be
	 * rendered inside the container.
	 * 
	 * [pt_BR] Método que recebe uma lista de elementos filhos de DomContent para
	 * serem renderizados dentro do container.
	 * 
	 * @param content
	 * @return [en_US] child element of ContainerTag. [pt_BR] elemento filho de
	 *         ContainerTag.
	 * @throws ViewJ2HtmlException
	 */
	ContainerTag getContainer(DomContent... content) throws ViewJ2HtmlException;

	/**
	 * [en_US] Method that receives a String with the html to be rendered inside the
	 * container.
	 * 
	 * [pt_BR] Método que recebe uma String com o html a ser renderizado dentro do
	 * container.
	 * 
	 * @param content
	 * @return [en_US] child element of ContainerTag. [pt_BR] elemento filho de
	 *         ContainerTag.
	 * @throws ViewJ2HtmlException
	 */
	ContainerTag getContainer(String content) throws ViewJ2HtmlException;

}
