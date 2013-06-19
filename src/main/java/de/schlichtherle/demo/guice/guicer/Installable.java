/*
 * Copyright (C) 2013 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package de.schlichtherle.demo.guice.guicer;

/**
 *
 * @author Christian Schlichtherle
 */
interface Installable<Target> {
    void installTo(Target binder);
}
