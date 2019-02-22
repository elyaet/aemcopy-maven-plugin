package com.lyaet.etienne.aem.copy;

import java.net.URL;
import java.util.List;

public class AemCopyJob {

    private static final boolean QUIET_DEFAULT = false;
    private Boolean quiet;

    private static final Integer BATCHSIZE_DEFAULT = 1024;
    private Integer batchSize;

    private static final Long OPTTHROTTLE_DEFAULT = 0L;
    private Long throttle;

    private static final String OPTRESUMEFROM_DEFAULT = "";
    private String resumeFrom;

    private static final boolean OPTUPDATE_DEFAULT = false;
    private Boolean update;

    private static final boolean OPTNEWER_DEFAULT = false;
    private Boolean onlyNewer;

    private static final boolean OPTNOORDERING_DEFAULT = false;
    private Boolean noOrdering;

    private static final boolean OPTRECURSIVE_DEFAULT = false;
    private Boolean recursive;

    private List<String> excludes;

    private URL src;

    private URL dst;

    private List<String> paths;


    public boolean isQuiet() {
        if (quiet == null) quiet = QUIET_DEFAULT;
        return quiet;
    }


    public Integer getBatchSize() {
        if (batchSize == null) batchSize = BATCHSIZE_DEFAULT;
        return batchSize;
    }

    public Long getThrottle() {
        if (throttle == null) throttle = OPTTHROTTLE_DEFAULT;
        return throttle;
    }


    public String getResumeFrom() {
        if (resumeFrom == null) resumeFrom = OPTRESUMEFROM_DEFAULT;
        return resumeFrom;
    }


    public boolean getUpdate() {
        if (update == null) update = OPTUPDATE_DEFAULT;
        return update;
    }


    public boolean getOnlyNewer() {
        if (onlyNewer == null) onlyNewer = OPTNEWER_DEFAULT;
        return onlyNewer;
    }


    public boolean getNoOrdering() {
        if (noOrdering == null) noOrdering = OPTNOORDERING_DEFAULT;
        return noOrdering;
    }


    public boolean isRecursive() {
        if (recursive == null) recursive = OPTRECURSIVE_DEFAULT;
        return recursive;
    }

    public List<String> getExcludes() {
        return excludes;
    }

    public URL getSrc() {
        return src;
    }


    public URL getDst() {
        return dst;
    }


    public List<String> getPaths() {
        return paths;
    }


}
