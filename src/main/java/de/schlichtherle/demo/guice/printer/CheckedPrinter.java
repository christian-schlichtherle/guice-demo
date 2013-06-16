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
 * Flushes the print stream and checks its status after
 * {@linkplain #print printing} each job.
 * If an error occured while printing, an {@link IOException} gets thrown.
 *
 * @author Christian Schlichtherle
 */
@Immutable
public final class CheckedPrinter implements Printer {

    private final Printer printer;

    public @Inject CheckedPrinter(
            final @Context(CheckedPrinter.class) Printer printer) {
        this.printer = Objects.requireNonNull(printer);
    }

    @Override public void print(final Job job) throws IOException {
        class CheckedJob implements Job {

            private boolean error;

            @Override public void renderTo(final PrintStream out) {
                job.renderTo(out);
                error = out.checkError();
            }

            void checkError() throws IOException {
                if (error) throw new IOException();
            }
        }
        final CheckedJob checkedJob = new CheckedJob();
        printer.print(checkedJob);
        checkedJob.checkError();
    }
}
