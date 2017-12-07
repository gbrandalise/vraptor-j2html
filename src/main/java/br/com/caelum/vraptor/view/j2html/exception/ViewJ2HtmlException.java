package br.com.caelum.vraptor.view.j2html.exception;

/**
 * [en_US] Class responsible for exceptions in views that use the J2Html engine.
 * 
 * [pt_BR] Classe responsável pelas exceções nas views que usam a engine J2Html.
 * 
 * @author giovanny.brandalise
 *
 */
public class ViewJ2HtmlException extends Exception {

	private static final long serialVersionUID = 1L;

	public ViewJ2HtmlException(Throwable cause) {
		super(cause);
	}

	public ViewJ2HtmlException(String message) {
		super(message);
	}

}
