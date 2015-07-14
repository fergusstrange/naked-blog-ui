package com.nakedgardener.application;

import com.nakedgardener.application.navigation.NavigationItem;
import com.nakedgardener.application.navigation.NavigationItemFactory;
import org.junit.Test;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

public class NavigationItemFactoryTest {

    private Boolean blogOn = false;

    @Test
    public void shouldReturnFiveNavigationItems() throws Exception {
        List<NavigationItem> navigationItems = new NavigationItemFactory(blogOn).create("/nanana");

        assertThat(navigationItems).hasSize(3);
    }

    @Test
    public void shouldReturnActiveWhenCurrentPage() throws Exception {
        List<NavigationItem> navigationItems = new NavigationItemFactory(blogOn).create("/contact");

        assertThat(navigationItems.get(2).isActive()).isTrue();
    }

    @Test
    public void shouldReturnInactiveWhenNotCurrentPage() throws Exception {
        List<NavigationItem> navigationItems = new NavigationItemFactory(blogOn).create("/");

        assertThat(navigationItems.get(2).isActive()).isFalse();
    }

    @Test
    public void shouldReturnBlogWhenSwitchOn() throws Exception {
        List<NavigationItem> navigationItems = new NavigationItemFactory(true).create("/");

        assertThat(navigationItems.get(2).getName()).isEqualTo("Blog");
    }
}