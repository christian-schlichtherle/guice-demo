/*
 * Copyright (C) 2013 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package de.schlichtherle.demo.guice.printer;

import de.schlichtherle.demo.guice.inject.Context;
import de.schlichtherle.demo.guice.util.Objects;
import java.io.*;
import javax.inject.Inject;

/**
 * Prints jobs to a given file.
 *
 * @author Christian Schlichtherle
 */
public final class FilePrinter implements Printer {

    private final File file;

    public @Inject FilePrinter(final @Context(FilePrinter.class) File file) {
        this.file = Objects.requireNonNull(file);
    }

    @Override public void print(final Job job) throws IOException {
        final PrintStream out = new PrintStream(file);
        try { job.printTo(out); }
        finally { out.close(); }
    }
}
