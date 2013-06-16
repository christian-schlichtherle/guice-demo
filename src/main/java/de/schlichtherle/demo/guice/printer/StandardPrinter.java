/*
 * Copyright (C) 2013 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package de.schlichtherle.demo.guice.printer;

import de.schlichtherle.demo.guice.inject.Context;
import de.schlichtherle.demo.guice.util.Objects;
import java.io.*;
import javax.annotation.concurrent.Immutable;
import javax.inject.Inject;

/**
 * Prints jobs to a given print stream.
 *
 * @author Christian Schlichtherle
 */
@Immutable
public final class StandardPrinter implements Printer {

    private PrintStream out;

    public @Inject StandardPrinter(
            final @Context(StandardPrinter.class) PrintStream out) {
        this.out = Objects.requireNonNull(out);
    }

    @Override public void print(Job job) throws IOException {
        job.printTo(out);
    }
}
