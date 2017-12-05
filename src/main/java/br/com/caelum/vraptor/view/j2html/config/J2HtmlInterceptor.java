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

	@Override
	public void intercept(InterceptorStack stack, ControllerMethod method, Object controllerInstance)
			throws InterceptionException {
		log.debug("intercept accessed");
		stack.next(method, controllerInstance);
		try {
			this.result.use(J2HtmlView.class).writeJ2Html(method);
		} catch (IOException e) {
			throw new InterceptionException(e);
		}
		this.result.nothing();
	}

	@Override
	public boolean accepts(ControllerMethod method) {
		log.debug("accepts accessed");
		return method.getMethod().isAnnotationPresent(J2HtmlRenderer.class)
				|| method.getController().getType().getClass().isAnnotationPresent(J2HtmlRenderer.class);
	}

}
