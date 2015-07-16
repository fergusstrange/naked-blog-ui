package com.nakedgardener.web.blog;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BlogPageIndexFactoryTest {

    private BlogPageIndexFactory blogPageIndexFactory = new BlogPageIndexFactory(5);

    @Test
    public void shouldReturnZero() throws Exception {
        int indexFromPage = blogPageIndexFactory.indexFromPage(1);

        assertEquals(0, indexFromPage);
    }

    @Test
    public void shouldReturnFive() throws Exception {
        int indexFromPage = blogPageIndexFactory.indexFromPage(2);

        assertEquals(5, indexFromPage);
    }

    @Test
    public void shouldReturnThirtyFive() throws Exception {
        int indexFromPage = blogPageIndexFactory.indexFromPage(8);

        assertEquals(35, indexFromPage);
    }
}