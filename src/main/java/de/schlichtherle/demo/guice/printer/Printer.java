/*
 * Copyright (C) 2013 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package de.schlichtherle.demo.guice.printer;

import java.io.*;

/**
 * A printer prints a given job.
 *
 * @author Christian Schlichtherle
 */
public interface Printer {

    /** Prints the given job. */
    void print(Job job) throws IOException;

    /** A job renders some content to a given print stream. */
    interface Job {
        /** Renders some content to the given print stream. */
        void renderTo(PrintStream out);
    }
}
