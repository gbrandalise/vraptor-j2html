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

public class DefaultHeadRendererTest {

	private DefaultHeadRenderer headRenderer;
	@Mock
	private Logger log;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		headRenderer = new DefaultHeadRenderer(log);		
	}
	
	@Test
	public void testHeadRendererIsNotNull() {
		Assert.assertNotNull(headRenderer);
	}
	
	@Test
	public void testGetContainerDomContent() throws ViewJ2HtmlException {
		ContainerTag container = headRenderer.getContainer(title("Titulo"));
		Assert.assertEquals(head(title("Titulo")), container);
	}
	
	@Test
	public void testGetContainerString() throws ViewJ2HtmlException {
		ContainerTag container = headRenderer.getContainer("Titulo");
		Assert.assertEquals(head("Titulo"), container);
	}
	
	
}
