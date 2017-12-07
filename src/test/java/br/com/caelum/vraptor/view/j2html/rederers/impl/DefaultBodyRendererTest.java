package br.com.caelum.vraptor.view.j2html.rederers.impl;

import static j2html.TagCreator.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import br.com.caelum.vraptor.view.j2html.exception.ViewJ2HtmlException;
import j2html.tags.ContainerTag;

public class DefaultBodyRendererTest {

	private DefaultBodyRenderer bodyRenderer;
	@Mock
	private Logger log;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		bodyRenderer = new DefaultBodyRenderer(log);		
	}
	
	@Test
	public void testBodyRendererIsNotNull() {
		Assert.assertNotNull(bodyRenderer);
	}
	
	@Test
	public void testGetContainerDomContent() throws ViewJ2HtmlException {
		ContainerTag container = bodyRenderer.getContainer(h1("Titulo"));
		Assert.assertEquals(body(h1("Titulo")), container);
	}
	
	@Test
	public void testGetContainerString() throws ViewJ2HtmlException {
		ContainerTag container = bodyRenderer.getContainer("Titulo");
		Assert.assertEquals(body("Titulo"), container);
	}
	
	
}
