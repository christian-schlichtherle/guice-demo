/*
 * Copyright (C) 2013 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package de.schlichtherle.demo.guice.job;

import de.schlichtherle.demo.guice.inject.Context;
import de.schlichtherle.demo.guice.printer.Printer;
import de.schlichtherle.demo.guice.util.Objects;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Buffers the rendered content for subsequent re-use so that the delegate job
 * is only told once to render its content.
 * <p>
 * Note that this class is not thread-safe!
 *
 * @author Christian Schlichtherle
 */
public final class BufferedJob implements Printer.Job {

    private Printer.Job job;
    private final int initialCapacity;
    private byte[] content;

    public @Inject BufferedJob(
            final @Context(BufferedJob.class) Printer.Job job,
            final @Named("initialCapacity") int initialCapacity) {
        this.job = Objects.requireNonNull(job);
        if (0 > (this.initialCapacity = initialCapacity))
            throw new IllegalArgumentException();
    }

    @Override public void renderTo(final PrintStream out) {
        if (null == content) {
            Objects.requireNonNull(out); // fail early!
            final ByteArrayOutputStream
                    buffer = new ByteArrayOutputStream(initialCapacity);
            final PrintStream subst = new PrintStream(buffer);
            job.renderTo(subst);
            subst.close();
            content = buffer.toByteArray();
            job = null; // enable garbage collection
        }
        out.write(content, 0, content.length);
    }
}
