/*
 * Copyright (C) 2013 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package de.schlichtherle.demo.guice.guicer;

/**
 *
 * @author Christian Schlichtherle
 */
@SuppressWarnings("PackageVisibleField")
public abstract class Bindable<This extends Bindable<This, Type>, Type>
extends Annotatable<This> {

    Class<Type> type;

    @SuppressWarnings("unchecked")
    final This bind(final Class<Type> type) {
        this.type = type;
        return (This) this;
    }
}
