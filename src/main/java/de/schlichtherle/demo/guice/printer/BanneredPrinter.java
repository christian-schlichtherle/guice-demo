/*
 * Copyright (C) 2013 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package de.schlichtherle.demo.guice.printer;

import de.schlichtherle.demo.guice.inject.Context;
import static de.schlichtherle.demo.guice.util.Objects.requireNonNull;
import java.io.*;
import javax.annotation.concurrent.Immutable;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Decorates print jobs with a header and a footer.
 *
 * @author Christian Schlichtherle
 */
@Immutable
public class BanneredPrinter implements Printer {

    private final Printer printer;
    private final Job header, footer;

    public @Inject BanneredPrinter(
            final @Context(BanneredPrinter.class) Printer printer,
            final @Named("header") Job header,
            final @Named("footer") Job footer) {
        this.printer = requireNonNull(printer);
        this.header = requireNonNull(header);
        this.footer = requireNonNull(footer);
    }

    @Override public final void print(final Job job) throws IOException {
        printer.print(new Job() {
            @Override public void printTo(final PrintStream out) {
                header.printTo(out);
                job.printTo(out);
                footer.printTo(out);
            }
        });
    }
}
