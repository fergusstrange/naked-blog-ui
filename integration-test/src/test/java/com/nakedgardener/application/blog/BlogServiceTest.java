package com.nakedgardener.application.blog;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.google.common.io.Resources;
import com.nakedgardener.Application;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.google.common.io.Resources.getResource;
import static java.nio.charset.Charset.defaultCharset;
import static org.fest.assertions.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class BlogServiceTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(3456);

    @Autowired
    private BlogService blogService;

    @Test
    public void shouldReturnBogPosts() throws Exception {
        stubFor(get(
                urlEqualTo("/blog-posts?indexFrom=0&indexTo=4"))
                .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody(restResponse())
                ));

        WireMock.listAllStubMappings().getMappings().stream().forEach(stubMapping -> {
            System.out.println(stubMapping.getRequest().getUrl());
        });

        BlogPosts blogPosts = blogService.blogPostsByIndex(0);

        assertThat(blogPosts).isNotNull();
        assertThat(blogPosts.getBlogPosts()).hasSize(5);
    }

    private String restResponse() throws IOException {
        return Resources.toString(getResource("com/nakedgardener/application/blog/blog-posts_rest_response.json"), defaultCharset());
    }
}