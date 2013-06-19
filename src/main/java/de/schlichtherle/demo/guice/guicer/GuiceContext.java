/*
 * Copyright (C) 2013 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package de.schlichtherle.demo.guice.guicer;

import com.google.inject.Injector;

/**
 * Provides an alternative, simple Domain Specific Language (DSL) for
 * configuring a Guice {@link Injector}.
 *
 * @author Christian Schlichtherle
 */
public final class GuiceContext {
    public InjectorBuilder injector() { return new InjectorBuilder(); }
}
