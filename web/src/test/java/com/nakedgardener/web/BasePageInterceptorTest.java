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
}