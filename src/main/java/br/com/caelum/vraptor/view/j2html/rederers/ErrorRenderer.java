package br.com.caelum.vraptor.view.j2html.rederers;

import br.com.caelum.vraptor.view.j2html.exception.ViewJ2HtmlException;
import j2html.tags.ContainerTag;
import j2html.tags.DomContent;

/**
 * [en_US] Error page rendering interface.
 * 
 * [pt_BR] Interface de renderização da página de erro.
 * 
 * @author giovanny.brandalise
 *
 */
public interface ErrorRenderer {

	/**
	 * [en_US] Method that receives the thrown exception and a list of child
	 * elements from DomContent to be rendered inside the container.
	 * 
	 * [pt_BR] Método que recebe a exceção lançada e uma lista de elementos filhos
	 * de DomContent para serem renderizados dentro do container.
	 * 
	 * @param ex
	 *            [en_US] exception thrown. [pt_BR] exceção lançada.
	 * @param content
	 *            [en_US] list of elements. [pt_BR] lista de elementos.
	 * @return [en_US] child element of ContainerTag. [pt_BR] elemento filho de
	 *         ContainerTag.
	 */
	ContainerTag getContainer(ViewJ2HtmlException ex, DomContent... content);

	/**
	 * Method that receives the thrown exception and a String with the html to be
	 * rendered inside the container.
	 * 
	 * @param ex
	 *            [en_US] exception thrown. [pt_BR] exceção lançada.
	 * @param content
	 *            [en_US] list of elements. [pt_BR] lista de elementos.
	 * @return [en_US] child element of ContainerTag. [pt_BR] elemento filho de
	 *         ContainerTag.
	 */
	ContainerTag getContainer(ViewJ2HtmlException ex, String content);

}
