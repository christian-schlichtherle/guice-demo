/*
 * Copyright (C) 2013 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package de.schlichtherle.demo.guice.guicer;

import java.lang.annotation.Annotation;

/**
 *
 * @author Christian Schlichtherle
 */
@SuppressWarnings("PackageVisibleField")
public abstract class Annotatable<This extends Annotatable<This>> {

    Annotation annotation;

    @SuppressWarnings("unchecked")
    public final This annotatedWith(final Annotation annotation) {
        this.annotation = annotation;
        return (This) this;
    }
}
