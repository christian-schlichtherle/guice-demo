/*
 * Copyright (C) 2013 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package de.schlichtherle.demo.guice.job;

import de.schlichtherle.demo.guice.inject.Context;
import de.schlichtherle.demo.guice.printer.Printer;
import static de.schlichtherle.demo.guice.util.Objects.requireNonNull;
import java.io.PrintStream;
import java.util.ResourceBundle;
import javax.annotation.concurrent.Immutable;
import javax.inject.Inject;

/**
 * Prints the identified localized content to a given stream.
 * The content gets lazily resolved by looking up a key in a resource bundle.
 *
 * @author Christian Schlichtherle
 */
@Immutable
public final class ResourceBundleJob implements Printer.Job {

    private final String key;
    private final ResourceBundle bundle;

    public @Inject ResourceBundleJob(
            final @Context(ResourceBundleJob.class) String key,
            final @Context(ResourceBundleJob.class) ResourceBundle bundle) {
        this.key = requireNonNull(key);
        this.bundle = requireNonNull(bundle);
    }

    @Override public void printTo(PrintStream out) { out.print(content()); }

    private Object content() { return bundle.getObject(key); }
}
