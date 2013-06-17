/*
 * Copyright (C) 2013 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package de.schlichtherle.demo.guice.inject;

import de.schlichtherle.demo.guice.util.Objects;
import java.lang.annotation.Annotation;
import javax.annotation.concurrent.Immutable;

/**
 * Provides utility functions for the {@link Context} annotation.
 *
 * @author Christian Schlichtherle
 */
@Immutable
public final class Contexts {

    /**
     * Returns a new {@code Context} annotation with the given {@code clazz}
     * as it's {@link Context#value}.
     *
     * @param clazz the clazz to use as the value of the annotation.
     */
    public static Context context(Class<?> clazz) {
        return contextChecked(Objects.requireNonNull(clazz));
    }

    private static Context contextChecked(final Class<?> clazz) {
        return new Context() {
            @Override public Class<?> value() { return clazz; }

            @Override public Class<? extends Annotation> annotationType() {
                return Context.class;
            }

            @Override public boolean equals(Object obj) {
                // Mind contract of java.lang.Annotation!
                if (!(obj instanceof Context)) return false;
                final Context that = (Context) obj;
                return this.value().equals(that.value());
            }

            @Override public int hashCode() {
                // Mind contract of java.lang.Annotation!
                return (127 * "value".hashCode()) ^ value().hashCode();
            }

            @Override public String toString() {
                return "@" + Context.class.getName() + "(value=" + value() + ")";
            }
        };
    }
}
