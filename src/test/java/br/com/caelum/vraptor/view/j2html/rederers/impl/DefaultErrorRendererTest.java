package br.com.caelum.vraptor.view.j2html.rederers.impl;

import static j2html.TagCreator.body;
import static j2html.TagCreator.h1;
import static j2html.TagCreator.head;
import static j2html.TagCreator.html;
import static j2html.TagCreator.rawHtml;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import br.com.caelum.vraptor.view.j2html.exception.ViewJ2HtmlException;
import j2html.tags.ContainerTag;

public class DefaultErrorRendererTest {

	private DefaultErrorRenderer errorRenderer;
	private ViewJ2HtmlException ex;
	private StringBuilder sb;
	@Mock
	private Logger log;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		ex = new ViewJ2HtmlException("Error");
		sb = new StringBuilder();
		sb.append("<!-- Exception: \n");
		Exception exp = ex.getCause() != null ? (Exception) ex.getCause() : ex;
		for (StackTraceElement ste : exp.getStackTrace()) {
			sb.append(ste.toString() + "\n");
		}
		sb.append("\n-->");
		errorRenderer = new DefaultErrorRenderer(log);
	}
	
	@Test
	public void testErrorRendererIsNotNull() {
		Assert.assertNotNull(errorRenderer);
	}
	
	@Test
	public void testGetContainerDomContent() throws ViewJ2HtmlException {		
		ContainerTag container = errorRenderer.getContainer(ex, h1("Error"));
		Assert.assertEquals(html(head(), body(h1("Error"), rawHtml(sb.toString()))), container);
	}
	
	@Test
	public void testGetContainerString() throws ViewJ2HtmlException {
		ContainerTag container = errorRenderer.getContainer(ex, "Error");
		Assert.assertEquals(html(head(), body(rawHtml("Error"), rawHtml(sb.toString()))), container);
	}
	
	
}
