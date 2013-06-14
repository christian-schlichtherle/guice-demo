/*
 * Copyright (C) 2013 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package de.schlichtherle.demo.guice.printer;

import java.io.*;

/**
 * A printer prints jobs.
 *
 * @author Christian Schlichtherle
 */
public interface Printer {

    /** Prints the given job. */
    void print(Job job) throws IOException;

    /** A job feeds some content to print streams. */
    interface Job {
        /** Prints some content to the given stream. */
        void printTo(PrintStream out);
    }
}
