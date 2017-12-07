package br.com.caelum.vraptor.view.j2html.rederers.impl;

import static j2html.TagCreator.rawHtml;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.view.j2html.exception.ViewJ2HtmlException;

public class DefaultViewRendererTest {
	
	private DefaultViewRenderer view;
	
	@Before
	public void setUp() {
		view = new DefaultViewRenderer();
	}
	
	@Test
	public void testViewIsNotNull() {
		Assert.assertNotNull(view);
	}
	
	@Test
	public void testGetElements() throws ViewJ2HtmlException {
		Assert.assertEquals(rawHtml("").render(), view.getElements()[0].render());
	}

}
