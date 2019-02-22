package com.lyaet.etienne.aem.copy.exception;

import org.apache.maven.plugin.MojoExecutionException;

public class AEMCopyException extends MojoExecutionException {

    public AEMCopyException(String message, Exception cause) {
        super(message, cause);
    }

    public AEMCopyException(String message, Throwable cause) {
        super(message, cause);
    }

    public AEMCopyException(String message) {
        super(message);
    }
}
