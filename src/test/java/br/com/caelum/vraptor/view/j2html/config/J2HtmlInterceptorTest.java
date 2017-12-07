package br.com.caelum.vraptor.view.j2html.config;

import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import br.com.caelum.vraptor.controller.BeanClass;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.controller.DefaultBeanClass;
import br.com.caelum.vraptor.controller.DefaultControllerMethod;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.view.j2html.beans.HomeController;

public class J2HtmlInterceptorTest {
	
	private J2HtmlInterceptor interceptor;
	private HomeController controller;
	private MockResult result;
	private ControllerMethod controllerMethod;
	@Mock
	private Logger log;
	@Mock
	private InterceptorStack stack;
	
	@Before
	public void setup() throws NoSuchMethodException, SecurityException {
		MockitoAnnotations.initMocks(this);
		result = new MockResult();
		interceptor = new J2HtmlInterceptor(result, log);
		controller = new HomeController();
		BeanClass clazz = new DefaultBeanClass(controller.getClass());
		Method method = controller.getClass().getMethod("home"); 
		controllerMethod = new DefaultControllerMethod(clazz, method);
	}
	
	@Test
	public void testInterceptorIsNotNull() {
		Assert.assertNotNull(interceptor);
	}
	
	@Test
	public void testAcceptsMethod() throws NoSuchMethodException, SecurityException {
		Assert.assertTrue(interceptor.accepts(controllerMethod));
	}
	
	@Test
	public void testInterceptMethod() throws NoSuchMethodException, SecurityException {
		interceptor.intercept(stack, controllerMethod, controller);
		Assert.assertTrue(result.used());
	}

}
