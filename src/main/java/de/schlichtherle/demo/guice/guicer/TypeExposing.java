/*
 * Copyright (C) 2013 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package de.schlichtherle.demo.guice.guicer;

import com.google.inject.PrivateBinder;

/**
 *
 * @author Christian Schlichtherle
 */
public abstract class TypeExposing<Type, Target>
extends Bindable<TypeExposing<Type, Target>, Type>
implements Injection<Target> {

    void installTo(final PrivateBinder binder) {
        if (null == annotation) binder.expose(type);
        else binder.expose(type).annotatedWith(annotation);
    }
}
