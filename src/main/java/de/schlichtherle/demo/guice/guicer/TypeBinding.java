/*
 * Copyright (C) 2013 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package de.schlichtherle.demo.guice.guicer;

import com.google.inject.Binder;
import com.google.inject.Module;

/**
 * A declaration of a binding for a type for use in any {@link Module}.
 *
 * @param  <Type> The type of the bindable type.
 * @param  <Target> the type of the injection target which will be returned
 *         from {@link #inject()}.
 * @author Christian Schlichtherle
 */
@SuppressWarnings("PackageVisibleField")
public abstract class TypeBinding<Type, Target>
extends Bindable<TypeBinding<Type, Target>, Type>
implements Injection<Target> {

    Class<? extends Type> implementation;
    Type instance;

    public final TypeBinding<Type, Target> to(final Class<? extends Type> implementation) {
        this.implementation = implementation;
        return this;
    }

    public final TypeBinding<Type, Target> toInstance(final Type instance) {
        this.instance = instance;
        return this;
    }

    void installTo(final Binder binder) {
        if (null == instance) {
            if (null == annotation)
                binder.bind(type).to(implementation);
            else
                binder.bind(type).annotatedWith(annotation).to(implementation);
        } else {
            if (null != implementation) throw new IllegalStateException();
            if (null == annotation)
                binder.bind(type).toInstance(instance);
            else
                binder.bind(type).annotatedWith(annotation).toInstance(instance);
        }
    }
}
