/*
 * Copyright (C) 2013 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package de.schlichtherle.demo.guice.guicer;

import com.google.inject.Module;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Christian Schlichtherle
 */
public class ModuleContainer<This extends ModuleContainer<This>> {

    private List<Module> modules = emptyList();

    static <T> List<T> emptyList() { return new LinkedList<T>(); }

    @SuppressWarnings("ReturnOfCollectionOrArrayField")
    final List<Module> swapModules() {
        try { return this.modules; }
        finally { this.modules = emptyList(); }
    }

    public final ModuleBuilder<This> module() {
        return new ModuleBuilder<This>() {
            @Override public This inject() {
                return ModuleContainer.this.module(build());
            }
        };
    }

    @SuppressWarnings("unchecked")
    public final This module(final Module module) {
        modules.add(module);
        return (This) this;
    }
}
