package com.bolyartech.forge.server.tple.handlebars;

import com.bolyartech.forge.server.misc.TemplateEngine;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class HandlebarsTemplateEngine implements TemplateEngine {
    private final Handlebars mHandlebars;
    private final Map<String, Object> mAttributes = new HashMap<>();


    public HandlebarsTemplateEngine(Handlebars handlebars) {
        mHandlebars = handlebars;
    }


    @Override
    public void assign(String varName, Object object) {
        mAttributes.put(varName, object);
    }


    @Override
    public String render(String templateName) {
        try {
            Template template = mHandlebars.compile(templateName);
            return template.apply(mAttributes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
