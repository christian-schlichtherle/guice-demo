/*
 * Copyright (C) 2013 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package de.schlichtherle.demo.guice.guicer;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 *
 * @author Christian Schlichtherle
 */
public class InjectorBuilder
extends ModuleContainer<InjectorBuilder>
implements Builder<Injector> {
    @Override public final Injector build() {
        return Guice.createInjector(swapModules());
    }
}
