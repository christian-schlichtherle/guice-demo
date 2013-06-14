/*
 * Copyright (C) 2013 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package de.schlichtherle.demo.guice;

import de.schlichtherle.demo.guice.printer.Printer;
import java.io.PrintStream;

/**
 * Prints <code>Hello world&#33;</code> to a given stream.
 *
 * @author Christian Schlichtherle
 */
public final class HelloWorldJob implements Printer.Job {
    @Override public void printTo(PrintStream out) {
        out.println("Hello world!");
    }
}
