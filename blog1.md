In this series of blog postings, I am discussing a standalone demo for
[Guice](http://code.google.com/p/google-guice/), Google's lightweight
dependency injection framework.
Source code for this demo is available on GitHub at
https://github.com/christian-schlichtherle/guice-demo .<!--more-->

## Motivation

For the purpose of this demonstration, let's forget about the package
`javax.print` and suppose you wanted to write a library which enables printing
of dynamically created content to arbitrary printers.

### Printers

To be useful at all, the library needs to provide some ready-made printers.
Here are some implementations I can imagine from the top of my head:

+ Print the content to standard output.
+ Append the content to a file.
+ Send an email with the content embedded or attached.
+ Print the content to a real printer.
+ Queue the content for later reuse.
+ Log some events for post-mortem analysis.
+ Do any combination of the above.

As you can see, the library sure needs to define some abstraction to support
all these scenarios, e.g. a Java interface.
This interface should be versatile, yet simple:

+ If the interface is versatile, then it may be happily reused by many client
  applications without anybody feeling the need to invent their own interface.
+ If the interface is simple, then it may easily implemented by many third
  parties for the benefit of all.

### Content

Obviously, the content will be provided by the applications.
It may be rendered dynamically, e.g. for a time-of-day or weather-forecast
service.

It may also be reused on demand, e.g. when reusing the time-of-day
Content should be reusable, too.

Content creation may be triggered by the printers on-demand.

This enables reuse of the content, e.g. when printing it to multiple output
channels.



## Demo Application

In our demo application, the content will be a time-of-the-day message,
decorated with a nice header and footer to set it apart from others.
The content is fully internationalized, with localizations for the UK, USA and
Germany.
Here's how the output from the printer looks like for the UK:

[text]
---------- BEGIN PRINT ----------
At the third stroke, it will be five fifty-one and fourty-eight seconds. BEEP, BEEP, BEEP.
----------  END PRINT  ----------
[/text]

You may notice that this message is similar to the messages of BT's popular
[Speaking Clock](http://en.wikipedia.org/wiki/Speaking_clock)

## The Semantic Model

The content may be large, say some megabytes, so precomputing the

[java]
[/java]

This is a variant of the loan pattern. The implementation of the method `Printer.print` is supposed to loan a `PrintStream` to the method `Job.printTo`.

## Standard Implementations

## Decorators
