/*
 * Copyright (C) 2013 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package de.schlichtherle.demo.guice.util;

import javax.annotation.concurrent.Immutable;

/**
 * A poor man's version of {@code java.util.Objects}, which appeared in
 * Java SE 7.
 *
 * @author Christian Schlichtherle
 */
@Immutable
public final class Objects {

    /**
     * Returns the given {@code object} if it's not {@code null};
     * otherwise throws a {@link NullPointerException}.
     *
     * @param <T> the type of the object.
     * @param object the object.
     * @return {@code object}
     * @throws NullPointerException if {@code object} is {@code null}.
     */
    public static <T> T requireNonNull(T object) {
        if (null == object) throw new NullPointerException();
        return object;
    }
}
