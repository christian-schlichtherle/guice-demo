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
 * Decorates print jobs with a printHeaderTo and a printFooterTo message.
 *
 * @author Christian Schlichtherle
 */
public class BanneredPrinter implements Printer {

    private final Printer printer;

    public @Inject BanneredPrinter(
            final @Context(BanneredPrinter.class) Printer printer) {
        this.printer = Objects.requireNonNull(printer);
    }

    /** Prints the header message to the given stream. */
    protected void printHeaderTo(PrintStream out) {
        out.println(headerMessage());
    }

    /** Returns the header message. */
    protected String headerMessage() {
        return "---------- BEGIN PRINT ----------";
    }

    /** Prints the footer message to the given stream. */
    protected void printFooterTo(PrintStream out) {
        out.println(footerMessage());
    }

    /** Returns the footer message. */
    protected String footerMessage() {
        return "----------  END PRINT  ----------";
    }

    @Override public final void print(final Job job) throws IOException {
        printer.print(new Job() {
            @Override public void printTo(final PrintStream out) {
                printHeaderTo(out);
                job.printTo(out);
                printFooterTo(out);
            }
        });
    }
}
