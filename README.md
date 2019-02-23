# Maven AEM Copy Plugin

## Description

_aemcopy-maven-plugin_ is a Maven wrapper of Jackrabbit FileVault RCP.<br />
It Copy node trees from one remote repository to another.<br />
Note that &lt;src&gt; points at the source node, and &lt;dst&gt; points at the destination path, which parent node must exist.                               

## Features

### _aem-copy_ Goal

<table>
    <tr><th>Options</th><th>Mandatory</th><th>Type</th><th>Description</th></tr>
    <tr><td>src</td><td>yes</td><td>URI</td><td>the repository address of the source tree</td></tr>
    <tr><td>dst</td><td>yes</td><td>URI</td><td>the repository address of the destination node</td></tr>
    <tr><td>paths</td><td>yes</td><td>list of path</td><td>the paths list to copy</td></tr>
    <tr><td>quiet</td><td>no</td><td>boolean</td><td>print as little as possible</td></tr>
    <tr><td>recursive</td><td>no</td><td>boolean</td><td>descend recursively</td></tr>
    <tr><td>batchSize</td><td>no</td><td>number</td><td>number of nodes until intermediate save</td></tr>
    <tr><td>throttle</td><td>no</td><td>number</td><td>number of seconds to wait after an intermediate save</td></tr>
    <tr><td>resume</td><td>no</td><td>path</td><td>source path to resume operation after a restart</td></tr>
    <tr><td>update</td><td>no</td><td>boolean</td><td>overwrite/delete existing nodes.</td></tr>
    <tr><td>onlyNewer</td><td>no</td><td>boolean</td><td>respect lastModified properties for update.</td></tr>
    <tr><td>excludes</td><td>no</td><td>list of regexp</td><td>regexps of excluded source paths.</td></tr>
    <tr><td>noOrdering</td><td>no</td><td>boolean</td><td>disable node ordering for updated content</td></tr>
</table>

## Examples

### _aem-copy_ Goal

<pre>
&lt;project&gt;
...
        &lt;plugins&gt;
            ...
            &lt;plugin&gt;
              &lt;groupId&gt;com.valtech.aem.copy&lt;/groupId&gt;
              &lt;artifactId&gt;aemcopy-maven-plugin&lt;/artifactId&gt;              &lt;configuration&gt;
                &lt;aemCopyJobs&gt;
                    &lt;aemCopyJob&gt;
                        &lt;src&gt;http://admin:admin@localhost:4501&lt;/src&gt;
                        &lt;dst&gt;http://admin:admin@localhost:4502&lt;/dst&gt;
                        &lt;paths&gt;
                          &lt;path&gt;/content/dam/aemcopy-sample/folder01&lt;/path&gt;
                          &lt;path&gt;/content/dam/aemcopy-sample/folder02&lt;/path&gt;
                          &lt;path&gt;/content/dam/aemcopy-sample/folder03&lt;/path&gt;
                        &lt;/paths&gt;
                        &lt;excludes&gt;
                          &lt;exclude&gt;/content/dam/aemcopy-sample/folder03/original\.png&lt;/exclude&gt;
                          &lt;exclude&gt;.*_2\.jpg&lt;/exclude&gt;
                        &lt;/excludes&gt;
                        &lt;quiet&gt;false&lt;/quiet&gt;
                        &lt;update&gt;false&lt;/update&gt;
                        &lt;onlyNewer&gt;true&lt;/onlyNewer&gt;
                        &lt;recursive&gt;true&lt;/recursive&gt;
                        &lt;batchSize&gt;256&lt;/batchSize&gt;
                  &lt;/aemCopyJob&gt;
                &lt;/aemCopyJobs&gt;
              &lt;/configuration&gt;
            &lt;/plugin&gt;
        &lt;/plugins&gt;
...
&lt;/project&gt;
</pre>