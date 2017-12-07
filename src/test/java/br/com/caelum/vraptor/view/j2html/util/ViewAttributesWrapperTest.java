package br.com.caelum.vraptor.view.j2html.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.view.j2html.beans.Client;
import br.com.caelum.vraptor.view.j2html.beans.HomeController;

public class ViewAttributesWrapperTest {

	private ViewAttributesWrapper wrapper;
	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpSession session;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		wrapper = new ViewAttributesWrapper(request);
		HomeController controller = new HomeController();
		Client client = controller.home();
		Mockito.when(request.getAttribute("client")).thenReturn(client);
		Mockito.when(request.getSession()).thenReturn(session);
		Mockito.when(request.getSession().getAttribute("client")).thenReturn(client);
	}
	
	@Test
	public void testWrapperIsNotNull() {
		Assert.assertNotNull(wrapper);
	}
	
	@Test
	public void testGetRequestAttributeNotNull() {
		Assert.assertNotNull(wrapper.getRequestAttribute("client", Client.class));
	}
	
	@Test
	public void testGetRequestAttributeClass() {
		Assert.assertEquals(Client.class, wrapper.getRequestAttribute("client", Client.class).getClass());
	}
	
	@Test
	public void testGetRequestAttributeValue() {
		Assert.assertEquals("admin", wrapper.getRequestAttribute("client", Client.class).getLogin());
	}
	
	@Test
	public void testGetSessionAttributeNotNull() {
		Assert.assertNotNull(wrapper.getSessionAttribute("client", Client.class));
	}
	
	@Test
	public void testGetSessionAttributeClass() {
		Assert.assertEquals(Client.class, wrapper.getSessionAttribute("client", Client.class).getClass());
	}
	
	@Test
	public void testGetSessionAttributeValue() {
		Assert.assertEquals("admin", wrapper.getSessionAttribute("client", Client.class).getLogin());
	}
	
}
