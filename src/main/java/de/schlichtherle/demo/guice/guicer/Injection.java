/*
 * Copyright (C) 2013 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package de.schlichtherle.demo.guice.guicer;

/**
 * Injects a dependency into some target.
 *
 * @param  <Target> the type of the injection target which will be returned
 *         from {@link #inject()}.
 * @author Christian Schlichtherle
 */
public interface Injection<Target> {
    /** Injects the dependency into the target and returns the target. */
    Target inject();
}
