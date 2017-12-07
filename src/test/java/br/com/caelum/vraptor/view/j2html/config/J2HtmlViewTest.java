package br.com.caelum.vraptor.view.j2html.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import br.com.caelum.vraptor.controller.BeanClass;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.controller.DefaultBeanClass;
import br.com.caelum.vraptor.controller.DefaultControllerMethod;
import br.com.caelum.vraptor.view.j2html.beans.Client;
import br.com.caelum.vraptor.view.j2html.beans.HomeController;
import br.com.caelum.vraptor.view.j2html.beans.HomeRenderer;
import br.com.caelum.vraptor.view.j2html.rederers.ErrorRenderer;
import br.com.caelum.vraptor.view.j2html.rederers.HtmlRenderer;
import br.com.caelum.vraptor.view.j2html.rederers.impl.DefaultBodyRenderer;
import br.com.caelum.vraptor.view.j2html.rederers.impl.DefaultErrorRenderer;
import br.com.caelum.vraptor.view.j2html.rederers.impl.DefaultHeadRenderer;
import br.com.caelum.vraptor.view.j2html.rederers.impl.DefaultHtmlRenderer;

@SuppressWarnings("deprecation")
public class J2HtmlViewTest extends J2HtmlView {
	
	private J2HtmlView view;
	private ControllerMethod controllerMethod;
	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	@Mock
	private Logger log;
	
	@Before
	public void setup() throws NoSuchMethodException, SecurityException, IOException {
		MockitoAnnotations.initMocks(this);
		Mockito.when(response.getWriter()).thenReturn(new PrintWriter(System.out));
		HtmlRenderer htmlRenderer = new DefaultHtmlRenderer(new DefaultHeadRenderer(log), new DefaultBodyRenderer(log), log);
		ErrorRenderer errorRenderer = new DefaultErrorRenderer(log);
		view = new J2HtmlView(htmlRenderer, errorRenderer, request, response, log);
		HomeController controller = new HomeController();
		BeanClass clazz = new DefaultBeanClass(controller.getClass());
		Method method = controller.getClass().getMethod("home"); 
		controllerMethod = new DefaultControllerMethod(clazz, method);
		Client client = controller.home();
		Mockito.when(request.getAttribute("client")).thenReturn(client);
	}
	
	@Test
	public void testViewIsNotNull() {
		Assert.assertNotNull(view);
	}
	
	@Test
	public void testrendererForMethod() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		Assert.assertEquals(HomeRenderer.class, view.rendererForMethod(controllerMethod).getClass());
	}
	
	@Test
	public void testWriteJ2Html() throws IOException {
		view.writeJ2Html(controllerMethod);
	}

}
