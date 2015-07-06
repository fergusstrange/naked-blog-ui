package com.nakedgardener.application;

import com.nakedgardener.application.navigation.NavigationItem;
import com.nakedgardener.application.navigation.NavigationItemFactory;
import org.junit.Test;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

public class NavigationItemFactoryTest {

    @Test
    public void shouldReturnFiveNavigationItems() throws Exception {
        List<NavigationItem> navigationItems = new NavigationItemFactory().create("/nanana");

        assertThat(navigationItems).hasSize(4);
    }

    @Test
    public void shouldReturnActiveWhenCurrentPage() throws Exception {
        List<NavigationItem> navigationItems = new NavigationItemFactory().create("/blog");

        assertThat(navigationItems.get(1).isActive()).isTrue();
    }

    @Test
    public void shouldReturnInactiveWhenNotCurrentPage() throws Exception {
        List<NavigationItem> navigationItems = new NavigationItemFactory().create("/");

        assertThat(navigationItems.get(3).isActive()).isFalse();
    }
}