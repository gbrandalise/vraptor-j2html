package br.com.caelum.vraptor.view.j2html.rederers;

import br.com.caelum.vraptor.view.j2html.exception.ViewJ2HtmlException;
import j2html.tags.DomContent;

/**
 * [en_US] Render interface for the internal views of each page.
 * 
 * [pt_BR] Interface de renderização das views internas de cada página.
 * 
 * @author giovanny.brandalise
 *
 */
public interface ViewRenderer {

	/**
	 * [en_US] Method that return a array of html elements of DomContent children to
	 * be rendered inside containers.
	 * 
	 * [pt_BR] Método que retorna um array de elementos html filhos de DomContent a
	 * serem renderizados dentro dos containers.
	 * 
	 * @return [en_US] array of DomContent child elements. [pt_BR] array de
	 *         elementos filhos de DomContent.
	 * @throws ViewJ2HtmlException
	 *             [en_US] custom exception for errors using the engine. [pt_BR]
	 *             exceção customizada para erros usando a engine.
	 */
	DomContent[] getElements() throws ViewJ2HtmlException;

}
