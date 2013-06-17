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

    /**
     * Returns the given {@code integer} if it's not negative;
     * otherwise throws an {@link IllegalArgumentException}.
     *
     * @param integer the integer.
     * @return {@code integer}
     * @throws IllegalArgumentException if {@code integer} is negative.
     */
    public static int requireNonNegative(int integer) {
        if (0 > integer) throw new IllegalArgumentException();
        return integer;
    }

    /**
     * Returns the given {@code integer} if it's positive;
     * otherwise throws an {@link IllegalArgumentException}.
     *
     * @param integer the integer.
     * @return {@code integer}
     * @throws IllegalArgumentException if {@code integer} is not positive.
     */
    public static int requirePositive(int integer) {
        if (0 >= integer) throw new IllegalArgumentException();
        return integer;
    }
}
