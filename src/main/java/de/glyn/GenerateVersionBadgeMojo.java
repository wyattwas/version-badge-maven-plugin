package de.glyn;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import javax.inject.Inject;

@Mojo(name = "version-badge", defaultPhase = LifecyclePhase.INITIALIZE)
public class GenerateVersionBadgeMojo extends AbstractMojo {

    @Parameter(property = "url", defaultValue = "https://img.shields.io/badge/%s-%s-%s.png")
    private String url;

    @Parameter(property = "output-path", defaultValue = "${project.build.directory}\\version-batch.png")
    private String outputPath;

    @Parameter(property = "color", defaultValue = "blue")
    private String color;

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
