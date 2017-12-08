package br.com.caelum.vraptor.view.j2html.util;

import javax.enterprise.inject.Vetoed;
import javax.servlet.http.HttpServletRequest;

/**
 * [en_US] Util class used to retrieve the attributes written in the request and
 * in the session in view render class.
 * 
 * [pt_BR] Classe útil usada para recuperar os atributos escritos no request e
 * na session na classe de renderização da view.
 * 
 * @author giovanny.brandalise
 *
 */
@Vetoed
public class ViewAttributesWrapper {

	private HttpServletRequest request;

	public ViewAttributesWrapper(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * [en_US] Method responsible for returning the attributes written in the
	 * request.
	 * 
	 * [pt_BR] Método responsável por retornar os atributos escritos no request.
	 * 
	 * @param attributeName
	 *            [en_US] attribute key written in the request. [pt_BR] chave do
	 *            atributo escrito na request.
	 * @param clazz
	 *            [en_US] class of return responsible for automatic casting. [pt_BR]
	 *            classe de retorno responsável por fazer o cast automático.
	 * @param <T>
	 *            [en_US] return class. [pt_BR] classe de retorno.
	 * @return [en_US] object of the type of the class passed as parameter. [pt_BR]
	 *         objeto do tipo da classe passada como parâmetro.
	 */
	public <T> T getRequestAttribute(String attributeName, Class<T> clazz) {
		return getAttribute(attributeName, clazz, false);
	}

	/**
	 * [en_US] Method responsible for returning the attributes written in the
	 * session.
	 * 
	 * [pt_BR] Método responsável por retornar os atributos escritos no session.
	 * 
	 * @param attributeName
	 *            [en_US] attribute key written in the session. [pt_BR] chave do
	 *            atributo escrito na session.
	 * @param clazz
	 *            [en_US] class of return responsible for automatic casting. [pt_BR]
	 *            classe de retorno responsável por fazer o cast automático.
	 * @param <T>
	 *            [en_US] return class. [pt_BR] classe de retorno.
	 * @return [en_US] object of the type of the class passed as parameter. [pt_BR]
	 *         objeto do tipo da classe passada como parâmetro.
	 */
	public <T> T getSessionAttribute(String attributeName, Class<T> clazz) {
		return getAttribute(attributeName, clazz, true);
	}

	@SuppressWarnings("unchecked")
	private <T> T getAttribute(String attributeName, Class<T> clazz, boolean session) {
		Object o = null;
		if (session) {
			o = request.getSession().getAttribute(attributeName);
		} else {
			o = request.getAttribute(attributeName);
		}
		if (o != null && clazz.isAssignableFrom(o.getClass())) {
			return (T) o;
		}
		return null;
	}

}
