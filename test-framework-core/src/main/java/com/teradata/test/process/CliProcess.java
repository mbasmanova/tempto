/*
 * Copyright 2015, Teradata, Inc. All rights reserved.
 */
package com.teradata.test.process;

import java.io.Closeable;
import java.io.PrintStream;
import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toList;

/**
 * Interface for interacting with CLI processes.
 */
public interface CliProcess extends Closeable
{
    public static List<String> trimLines(List<String> lines)
    {
        return lines.stream().map(String::trim).collect(toList());
    }

    List<String> readRemainingOutputLines();

    String nextOutputLine();

    String nextOutputToken();

    boolean hasNextOutputLine();

    boolean hasNextOutput(Pattern pattern);

    boolean hasNextOutputToken();

    List<String> readRemainingErrorLines();

    String nextErrorLine();

    String nextErrorToken();

    boolean hasNextErrorLine();

    boolean hasNextError(Pattern pattern);

    boolean hasNextErrorToken();

    PrintStream getProcessInput();

    /**
     * Waits for a process to finish and ensures it returns with 0 status. If the process
     * fails to finish within given timeout it is killed and {@link RuntimeException} is thrown.
     */
    void waitForWithTimeoutAndKill()
            throws InterruptedException;

    void waitForWithTimeoutAndKill(Duration timeout)
            throws InterruptedException;
}