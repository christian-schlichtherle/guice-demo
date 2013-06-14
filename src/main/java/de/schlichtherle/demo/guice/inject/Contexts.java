/*
 * Copyright (C) 2013 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package de.schlichtherle.demo.guice.inject;

import de.schlichtherle.demo.guice.util.Objects;
import java.lang.annotation.Annotation;

/**
 * Provides utility functions for the {@link Context} annotation.
 *
 * @author Christian Schlichtherle
 */
public final class Contexts {

    /**
     * Returns a new {@code Context} annotation with the given {@code clazz}
     * as it's {@link Context#value}.
     *
     * @param clazz the clazz to use as the value of the annotation.
     */
    public static Context inClass(Class<?> clazz) {
        return context(Objects.requireNonNull(clazz));
    }

    private static Context context(final Class<?> clazz) {
        return new Context() {
            @Override public Class<?> value() { return clazz; }

            @Override public Class<? extends Annotation> annotationType() {
                return Context.class;
            }

            @Override public boolean equals(Object obj) {
                // Mind contract!
                if (!(obj instanceof Context)) return false;
                final Context that = (Context) obj;
                return this.value().equals(that.value());
            }

            @Override public int hashCode() {
                // Mind contract!
                return (127 * "value".hashCode()) ^ value().hashCode();
            }

            @Override public String toString() {
                return "@" + Context.class.getName() + "(value=" + value() + ")";
            }
        };
    }
}
