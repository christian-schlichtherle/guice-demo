/*
 * Copyright (C) 2013 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package de.schlichtherle.demo.guice.guicer;

import com.google.inject.Binder;
import com.google.inject.binder.ConstantBindingBuilder;

/**
 * A binding for a constant value.
 *
 * @param  <Type> the type of the constant to build.
 * @param  <Target> the type of the injection target which will be returned
 *         from {@link #inject()}.
 * @author Christian Schlichtherle
 */
@SuppressWarnings("PackageVisibleField")
public abstract class ConstantBinding<Type, Target>
extends Annotatable<ConstantBinding<Type, Target>>
implements Injection<Target> {

    Type instance;

    public final ConstantBinding<Type, Target> to(final Type instance) {
        this.instance = instance;
        return this;
    }

    @SuppressWarnings("unchecked")
    void installTo(final Binder binder) {
        final ConstantBindingBuilder
                builder = binder.bindConstant().annotatedWith(annotation);
        if (instance instanceof Boolean) builder.to((Boolean) instance);
        else if (instance instanceof Byte) builder.to((Byte) instance);
        else if (instance instanceof Character) builder.to((Character) instance);
        else if (instance instanceof Class<?>) builder.to((Class<?>) instance);
        else if (instance instanceof Double) builder.to((Double) instance);
        else if (instance instanceof Enum) builder.to((Enum) instance);
        else if (instance instanceof Float) builder.to((Float) instance);
        else if (instance instanceof Integer) builder.to((Integer) instance);
        else if (instance instanceof Long) builder.to((Long) instance);
        else if (instance instanceof Short) builder.to((Short) instance);
        else if (instance instanceof String) builder.to((String) instance);
        else throw new UnsupportedOperationException();
    }
}
