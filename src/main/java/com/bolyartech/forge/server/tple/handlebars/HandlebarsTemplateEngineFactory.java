package com.bolyartech.forge.server.tple.handlebars;

import com.bolyartech.forge.server.misc.TemplateEngine;
import com.bolyartech.forge.server.misc.TemplateEngineFactory;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;


public class HandlebarsTemplateEngineFactory implements TemplateEngineFactory {
    private final Handlebars mHandlebars;


    public HandlebarsTemplateEngineFactory(Handlebars handlebars) {
        mHandlebars = handlebars;
    }


    public HandlebarsTemplateEngineFactory(String mTemplatePathPrefix) {
        TemplateLoader templateLoader = new ClassPathTemplateLoader();
        templateLoader.setPrefix(mTemplatePathPrefix);
        templateLoader.setSuffix(null);

        mHandlebars = new Handlebars(templateLoader);
    }


    @Override
    public TemplateEngine createNew() {
        return new HandlebarsTemplateEngine(mHandlebars);
    }
}
