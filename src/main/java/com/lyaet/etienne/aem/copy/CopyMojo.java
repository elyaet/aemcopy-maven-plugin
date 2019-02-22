package com.lyaet.etienne.aem.copy;


import org.apache.jackrabbit.vault.fs.api.PathFilterSet;
import org.apache.jackrabbit.vault.fs.api.RepositoryAddress;
import org.apache.jackrabbit.vault.fs.config.DefaultWorkspaceFilter;
import org.apache.jackrabbit.vault.fs.filter.DefaultPathFilter;
import org.apache.jackrabbit.vault.util.CredentialsProvider;
import org.apache.jackrabbit.vault.util.DefaultProgressListener;
import org.apache.jackrabbit.vault.vlt.ConfigCredentialsStore;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;
import java.util.List;

/**
 * CopyMojo node from repository to another one.
 */
@Mojo(name = "aem-copy", defaultPhase = LifecyclePhase.INSTALL)
public class CopyMojo extends AbstractMojo {

    protected static Logger log = LoggerFactory.getLogger(CopyMojo.class);

    private static final String CRX_JCR_ROOT = "/crx/-/jcr:root";

    @Parameter(required = true)
    private List<AemCopyJob> aemCopyJobs;

    public void execute() throws MojoExecutionException {

        CredentialsProvider confCredentialsProvider = new ConfigCredentialsStore();
        RepositoryAddress srcRA;
        RepositoryAddress dstRA;
        try {
            for (AemCopyJob aemCopyJob : aemCopyJobs) {
                checkParams(aemCopyJob);
                for (String path : aemCopyJob.getPaths()) {
                    srcRA = new RepositoryAddress(aemCopyJob.getSrc().toString() + CRX_JCR_ROOT + path);
                    dstRA = new RepositoryAddress(aemCopyJob.getDst().toString() + CRX_JCR_ROOT + path);
                    RepositoryCopier rcp = new RepositoryCopier();
                    if (!aemCopyJob.isQuiet()) {
                        rcp.setTracker(new DefaultProgressListener());
                    }
                    if (aemCopyJob.getBatchSize() != null && aemCopyJob.getBatchSize() > 0) {
                        rcp.setBatchSize(aemCopyJob.getBatchSize());
                    }
                    if (aemCopyJob.getThrottle() != null && aemCopyJob.getThrottle() > 0) {
                        rcp.setThrottle(aemCopyJob.getThrottle());
                    }
                    if (aemCopyJob.getResumeFrom() != null && !aemCopyJob.getResumeFrom().isEmpty()) {
                        rcp.setResumeFrom(aemCopyJob.getResumeFrom());
                    }
                    rcp.setUpdate(aemCopyJob.getUpdate());
                    rcp.setOnlyNewer(aemCopyJob.getOnlyNewer());
                    log.info("Newer: {}", aemCopyJob.getOnlyNewer());
                    rcp.setNoOrdering(aemCopyJob.getNoOrdering());
                    rcp.setCredentialsProvider(confCredentialsProvider);
                    DefaultWorkspaceFilter srcFilter = new DefaultWorkspaceFilter();
                    PathFilterSet excludes = new PathFilterSet("/");
                    if (aemCopyJob.getExcludes() != null && aemCopyJob.getExcludes().size() > 0) {
                        for (String exclude : aemCopyJob.getExcludes())
                            excludes.addExclude(new DefaultPathFilter(exclude));
                    }
                    srcFilter.add(excludes);
                    rcp.setSourceFilter(srcFilter);
                    rcp.copy(srcRA, dstRA, aemCopyJob.isRecursive());
                }
            }
        } catch (URISyntaxException e) {
            log.error(e.getMessage());
            throw new MojoExecutionException("Cannot execute AEM Copy.", e);
        }

    }

    private void checkParams(AemCopyJob aemCopyJob) throws MojoExecutionException {
        boolean error = false;
        if (aemCopyJob.getSrc() == null) {
            log.error("Src parameter cannot be null");
            error = true;
        }
        if (aemCopyJob.getDst() == null) {
            log.error("Dst parameter cannot be null");
            error = true;
        }

        if (aemCopyJob.getPaths() == null) {
            log.error("Path parameter cannot be null");
            error = true;
        }
        if (error) {
            throw new MojoExecutionException("All required parameters must be set.");
        }

    }
}
