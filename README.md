# version-badge-maven-plugin
![Version](version-batch.png) [![MIT license](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/license/MIT)

This is a very simple Maven plugin that can be used to automate the generation of version badges locally.
This is especially usefully for cases where you use a private repository for example or you cant use much outside services.

Keep in mind that this application is going to make a request to [shields.io](https://shields.io) by default in order to get the image of for the badge.
The plugin uses a [static badge](https://shields.io/badges/static-badge) and passes the project version to the website.

### Important
You can not have anything in your version by a dash (-) divided. Something like "1.1-SNAPSHOT" is not going to work. You can have something like "1.1_SNAPSHOT" though.
It's even going to separate everything that's divided by an underscore (_) ![Version by underscore divided](version-batch-SNAPSHOT.png)

## Usage
1. Add the plugin to your maven project
```xml
<plugins>
    <plugin>
        <groupId>org.opencodespace</groupId>
        <artifactId>version-badge-maven-plugin</artifactId>
        <version>1.1</version>
        <executions>
            <execution>
                <goals>
                    <goal>version-badge</goal>
                </goals>
            </execution>
        </executions>
        <configuration>
            <color>blue</color>
            <label>version</label>
            <outputPath>${project.basedir}</outputPath>
        </configuration>
    </plugin>
</plugins>
```

2. `mvn install`<br>
3. `mvn org.opencodespace:version-badge-maven-plugin:version-badge`<br>
   `mvn badge:1.1:version-badge` should also work

## Configuration
You don't need to configure the plugin necessarily.
The default values for the parameters are:
- color: blue
- label: version
- outputPath: `${project.build.directory}\version-batch.png`<br>
  You can also change the file name by including it into the path
- url: https://img.shields.io/badge/{label}-{version}-{color}.png<br>
  If you want to use another API you could do that. At the time there isn't support for any other schematic though. It needs to be label, version, color, in that order.

## Contributing
If you need anything or have any ideas, feel free to contribute to the project.

## License
This project is licensed under the [MIT License](https://opensource.org/license/MIT) - see the [LICENSE](LICENSE) file for details