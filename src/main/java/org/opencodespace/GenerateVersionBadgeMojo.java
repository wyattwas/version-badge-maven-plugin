package org.opencodespace;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import javax.inject.Inject;

/**
 * This Mojo will generate a version badge for the project to use in documentation
 *
 * @author wyatt
 */
@Mojo(name = "version-badge", defaultPhase = LifecyclePhase.INITIALIZE)
public class GenerateVersionBadgeMojo extends AbstractMojo {

    /**
     * The url for the badge endpoint
     */
    @Parameter(property = "url", defaultValue = "https://img.shields.io/badge/%s-%s-%s.png")
    private String url;

    /**
     * The save location for the badge, this also includes the file name
     */
    @Parameter(property = "output-path", defaultValue = "${project.build.directory}\\version-batch.png")
    private String outputPath;

    /**
     * The color of the badge
     */
    @Parameter(property = "color", defaultValue = "blue")
    private String color;

    /**
     * The label of the badge
     */
    @Parameter(property = "label", defaultValue = "version")
    private String label;

    @Parameter(property = "project", readonly = true)
    private MavenProject project;

    @Inject
    ImageProvider imageProvider;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        String version = project.getVersion();

        url = String.format(url, label, version, color);

        imageProvider.getImage(url, outputPath);

        getLog().info("Version badge created: " + outputPath);
    }
}
