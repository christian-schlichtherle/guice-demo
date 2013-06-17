/*
 * Copyright (C) 2013 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package de.schlichtherle.demo.guice;

import de.schlichtherle.demo.guice.printer.Printer;
import static de.schlichtherle.demo.guice.util.Objects.requireNonNull;
import java.io.IOException;
import java.util.concurrent.Callable;
import javax.annotation.concurrent.Immutable;
import javax.inject.*;

/**
 * When {@linkplain #call called}, this application prints a lazily resolved
 * job to a printer.
 *
 * @author Christian Schlichtherle
 */
@Immutable
public final class Application implements Callable<Void> {

    private final Printer printer;
    private final Provider<Printer.Job> jobProvider;

    public @Inject Application(
            final Printer printer,
            final Provider<Printer.Job> jobProvider) {
        this.printer = requireNonNull(printer);
        this.jobProvider = requireNonNull(jobProvider);
    }

    @Override public Void call() throws IOException {
        printer.print(jobProvider.get());
        return null;
    }
}
