/*
 * Copyright (C) 2013 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package de.schlichtherle.demo.guice;

import de.schlichtherle.demo.guice.printer.Printer;
import de.schlichtherle.demo.guice.util.Objects;
import java.io.IOException;
import java.util.concurrent.Callable;
import javax.inject.*;

/**
 * When {@linkplain #call called}, this application prints a provided job to
 * a given printer.
 * 
 * @author Christian Schlichtherle
 */
public final class Application implements Callable<Void> {

    private final Printer printer;
    private final Provider<Printer.Job> jobProvider;

    public @Inject Application(
            final Printer printer,
            final Provider<Printer.Job> jobProvider) {
        this.printer = Objects.requireNonNull(printer);
        this.jobProvider = Objects.requireNonNull(jobProvider);
    }

    @Override public Void call() throws IOException {
        printer.print(jobProvider.get());
        return null;
    }
}
