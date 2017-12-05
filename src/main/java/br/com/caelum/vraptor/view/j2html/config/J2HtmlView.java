package br.com.caelum.vraptor.view.j2html.config;

import static j2html.TagCreator.document;
import static j2html.TagCreator.text;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;

import br.com.caelum.vraptor.View;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.view.j2html.exception.ViewJ2HtmlException;
import br.com.caelum.vraptor.view.j2html.rederers.ErrorRenderer;
import br.com.caelum.vraptor.view.j2html.rederers.HtmlRenderer;
import br.com.caelum.vraptor.view.j2html.rederers.ViewRenderer;
import br.com.caelum.vraptor.view.j2html.util.ViewAttributesWrapper;

@RequestScoped
public class J2HtmlView implements View {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HtmlRenderer htmlRenderer;
	private ErrorRenderer errorRenderer;
	private Logger log;

	/**
	 * @deprecated CDI eyes only
	 */
	@Deprecated
	protected J2HtmlView() {
		this(null, null, null, null, null);
	}

	@Inject
	public J2HtmlView(HtmlRenderer htmlRenderer, ErrorRenderer errorRenderer, HttpServletRequest request, 
			HttpServletResponse response, Logger log) {
		this.htmlRenderer = htmlRenderer;
		this.errorRenderer = errorRenderer;
		this.response = response;
		this.log = log;
		this.request = request;
	}

	public void writeJ2Html(ControllerMethod method) throws IOException {
		log.debug("writeJ2Html accessed");
		String html = "";
		try {
			ViewRenderer rendererMethod = rendererForMethod(method);
			html = document().render() + "\n" + htmlRenderer.getContainer(rendererMethod.getElements()).renderFormatted();
		} catch (Exception e) {
			ViewJ2HtmlException j2e = new ViewJ2HtmlException(e);
			log.error(j2e.getClass().getName(), j2e);
			html = document().render() + "\n" + errorRenderer.getContainer(j2e, text("Error")).renderFormatted();
		} finally {
			response.setContentType(MediaType.TEXT_HTML);
			response.setCharacterEncoding(StandardCharsets.UTF_8.name());
			response.getWriter().write(html);
			response.getWriter().flush();
			response.getWriter().close();
		}
	}

	protected ViewRenderer rendererForMethod(ControllerMethod method) throws NoSuchMethodException, 
		InstantiationException, IllegalAccessException, InvocationTargetException {
		Class<? extends ViewRenderer> classRenderer = null;
		if (method.getMethod().isAnnotationPresent(J2HtmlRenderer.class)) {
			classRenderer = method.getMethod().getAnnotation(J2HtmlRenderer.class).value();
		} else if (method.getController().getType().getClass().isAnnotationPresent(J2HtmlRenderer.class)) {
			classRenderer = method.getController().getType().getClass().getAnnotation(J2HtmlRenderer.class).value();
		}
		log.debug("rendererForMethod accessed");
		if (classRenderer != null &&
			classRenderer.getConstructor(ViewAttributesWrapper.class) != null) {
			ViewAttributesWrapper attributes = new ViewAttributesWrapper(request);
			return classRenderer.getConstructor(ViewAttributesWrapper.class).newInstance(attributes);
		}
		return classRenderer != null ? classRenderer.getConstructor().newInstance() : null;
	}

}
