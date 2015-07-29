package com.nakedgardener.web;

import com.nakedgardener.application.navigation.NavigationItemFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class BasePageInterceptorTest {

    @Mock
    private NavigationItemFactory navigationItemFactory;

    @InjectMocks
    private BasePageInterceptor basePageInterceptor;

    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpServletRequest request;

    @Test
    public void shouldAddAllBaseObjectsToModel() throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        basePageInterceptor.postHandle(request, response, new Object(), modelAndView);

        assertThat(modelAndView.getModel().keySet()).containsOnly("pageTitle", "pageYear",
                "pageDescription", "pageTags", "navigationItems");
    }

    @Test
    public void shouldNotOverrideValueOfSEOContent() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageTitle", "Rubbish page!");
        modelAndView.addObject("pageDescription", "Lalala");
        modelAndView.addObject("pageTags", "Some tags");

        basePageInterceptor.postHandle(request, response, new Object(), modelAndView);

        assertThat(modelAndView.getModel().size()).isEqualTo(5);

        assertThat(modelAndView.getModel().get("pageTitle")).isEqualTo("Rubbish page!");
        assertThat(modelAndView.getModel().get("pageDescription")).isEqualTo("Lalala");
        assertThat(modelAndView.getModel().get("pageTags")).isEqualTo("Some tags");
    }
}