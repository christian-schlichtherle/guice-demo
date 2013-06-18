/*
 * Copyright (C) 2013 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package de.schlichtherle.demo.guice;

import de.schlichtherle.demo.guice.job.ResourceBundleJob;
import de.schlichtherle.demo.guice.printer.Printer;
import java.util.ResourceBundle;

/**
 * Creates jobs for printing an object from a resource bundle which is keyed by
 * its name.
 *
 * @author Christian Schlichtherle
 */
enum Messages {
    beginPrint, helloWorld, endPrint;

    private static final ResourceBundle
            bundle = ResourceBundle.getBundle(Messages.class.getName());

    Printer.Job printerJob() { return new ResourceBundleJob(name(), bundle); }
}
