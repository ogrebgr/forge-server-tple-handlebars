package com.bolyartech.forge.server.tple.handlebars;

import com.bolyartech.forge.server.misc.TemplateEngine;
import com.bolyartech.forge.server.misc.TemplateEngineFactory;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.cache.GuavaTemplateCache;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import com.github.jknack.handlebars.io.TemplateSource;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;


public class HandlebarsTemplateEngineFactory implements TemplateEngineFactory {
    private final Handlebars mHandlebars;


    public HandlebarsTemplateEngineFactory(Handlebars handlebars) {
        mHandlebars = handlebars;
    }


    public HandlebarsTemplateEngineFactory(String mTemplatePathPrefix) {
        TemplateLoader templateLoader = new ClassPathTemplateLoader();
        templateLoader.setPrefix(mTemplatePathPrefix);
        templateLoader.setSuffix(null);

        Handlebars handlebarsTmp = new Handlebars(templateLoader);
        Cache<TemplateSource, Template> cache = CacheBuilder.newBuilder().expireAfterWrite(10, TimeUnit.MINUTES)
                .maximumSize(1000).build();

        mHandlebars = handlebarsTmp.with(new GuavaTemplateCache(cache));
    }


    @Override
    public TemplateEngine createNew() {
        return new HandlebarsTemplateEngine(mHandlebars);
    }
}
