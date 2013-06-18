/*
 * Copyright (C) 2013 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package de.schlichtherle.demo.guice;

import java.util.ResourceBundle;

/**
 * Hosts a resource bundle with localized messages.
 *
 * @author Christian Schlichtherle
 */
enum Messages {
    beginPrint, helloWorld, endPrint;

    static final ResourceBundle
            bundle = ResourceBundle.getBundle(Messages.class.getName());
}
