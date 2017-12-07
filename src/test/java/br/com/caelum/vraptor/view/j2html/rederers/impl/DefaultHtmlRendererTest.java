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

public class DefaultHtmlRendererTest {

	private DefaultBodyRenderer bodyRenderer;
	private DefaultHeadRenderer headRenderer;
	private DefaultHtmlRenderer htmlRenderer;
	@Mock
	private Logger log;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		bodyRenderer = new DefaultBodyRenderer(log);
		headRenderer = new DefaultHeadRenderer(log);
		htmlRenderer = new DefaultHtmlRenderer(headRenderer, bodyRenderer, log);		
	}
	
	@Test
	public void testHtmlRendererIsNotNull() {
		Assert.assertNotNull(htmlRenderer);
	}
	
	@Test
	public void testGetContainerDomContent() throws ViewJ2HtmlException {
		ContainerTag container = htmlRenderer.getContainer(h1("Titulo"));
		Assert.assertEquals(html(headRenderer.getContainer(), bodyRenderer.getContainer(h1("Titulo"))), container);
	}
	
	@Test
	public void testGetContainerString() throws ViewJ2HtmlException {
		ContainerTag container = htmlRenderer.getContainer("Titulo");
		Assert.assertEquals(html(headRenderer.getContainer(), bodyRenderer.getContainer("Titulo")), container);
	}
	
	
}
