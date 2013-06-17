/*
 * Copyright (C) 2013 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package de.schlichtherle.demo.guice.printer;

import de.schlichtherle.demo.guice.inject.Context;
import de.schlichtherle.demo.guice.util.Objects;
import java.io.*;
import javax.annotation.concurrent.Immutable;
import javax.inject.*;

/**
 * Prints jobs to a file.
 *
 * @author Christian Schlichtherle
 */
@Immutable
public final class FilePrinter implements Printer {

    private final File file;
    private final boolean append;

    public @Inject FilePrinter(
            final @Context(FilePrinter.class) File file,
            final @Named("append") boolean append) {
        this.file = Objects.requireNonNull(file);
        this.append = append;
    }

    @Override public void print(final Job job) throws IOException {
        final PrintStream out = new PrintStream(new FileOutputStream(file, append));
        try { job.renderTo(out); }
        finally { out.close(); }
    }
}
