package br.com.caelum.vraptor.view.j2html.config;

import java.io.IOException;

import javax.inject.Inject;

import org.slf4j.Logger;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;

/**
 * [en_US] Class responsible for intercepting all method calls and checking
 * whether you have the @J2HtmlRenderer annotation or not. If the method has the
 * annotation, uses the J2HtmlView view to render.
 * 
 * [pt_BR] Classe responsável por interceptar todas as chamadas aos métodos e
 * verificar se possui a anotação @J2HtmlRenderer ou não. Caso o método possua a
 * anotação, usa a view J2HtmlView para renderização.
 * 
 * @author giovanny.brandalise
 *
 */

@Intercepts
public class J2HtmlInterceptor implements Interceptor {

	private Result result;
	private Logger log;

	/**
	 * @deprecated CDI eyes only
	 */
	@Deprecated
	protected J2HtmlInterceptor() {
		this(null, null);
	}

	@Inject
	public J2HtmlInterceptor(Result result, Logger log) {
		this.result = result;
		this.log = log;
	}

	/**
	 * [en_US] Method responsible for intercepting calls to controllers methods
	 * which have annotation @J2HtmlRenderer and use the J2HtmlView class view for
	 * html writing.
	 * 
	 * [pt_BR] Método responsável por interceptar as chamadas aos métodos dos
	 * controllers que possuem a anotação @J2HtmlRenderer e utilizar a classe view
	 * J2HtmlView para escrita do html.
	 */
	@Override
	public void intercept(InterceptorStack stack, ControllerMethod method, Object controllerInstance) {
		log.debug("intercept accessed");
		stack.next(method, controllerInstance);
		try {
			this.result.use(J2HtmlView.class).writeJ2Html(method);
		} catch (IOException e) {
			throw new InterceptionException(e);
		}
		this.result.nothing();
	}

	/**
	 * [en_US] Method responsible for validating if annotation @J2HtmlRenderer is
	 * present in method or in controller class.
	 * 
	 * [pt_BR] Método responsável por validar se a anotação @J2HtmlRenderer está
	 * presente no método ou na classe controller.
	 */
	@Override
	public boolean accepts(ControllerMethod method) {
		log.debug("accepts accessed");
		return method.getMethod().isAnnotationPresent(J2HtmlRenderer.class)
				|| method.getController().getType().getClass().isAnnotationPresent(J2HtmlRenderer.class);
	}

}
