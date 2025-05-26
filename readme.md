# N1netails

<div align="center">
  <img src="https://raw.githubusercontent.com/n1netails/n1netails/refs/heads/main/n1netails_icon_transparent.png" alt="N1ne Tails" width="500" style="display: block; margin: auto;"/>
</div>

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

# Kuda
Integration library that acts as a smart link between your application and the n1netails platform quiet, powerful, and built to vanish into your stack like a spirit fox.

## How to set up kuda
Kuda can act as a simple logger but its main purpose is to send tail data over to `n1netails-api`. More details on setting 
up and using Kuda will be reveled later.

## Install
Install the Kuda by adding the following dependency:
```xml
<dependency>
    <groupId>com.n1netails</groupId>
    <artifactId>n1netails-kuda</artifactId>
    <version>0.0.6</version>
</dependency>
```

## Configure
### Requirements
Set up and have the `n1netails-api` running on your server.
- N1netails-Api [README](https://github.com/n1netails/n1netails/blob/main/n1netails-api/readme.md)


In order to get started with Kuda all you need to do is add the following three lines of code.

```groovy
// your n1netails-api location
TailConfig.setApiUrl("http://your-n1netails-api-location.com");
// your n1ne token (created by the n1netails user interface or api)
TailConfig.setN1neToken("n1ne-token-uuid");
// send your first tail
Tail.error("Testing kuda error").withTag("env", "prod").send();
```

⚠️ Note: If you do not configure the `TailConfig` then Kuda will log a single warning about missing configurations and default to simple logging.

## Use
### Java

```java
import com.n1netails.n1netails.kuda.api.Tail;
import com.n1netails.n1netails.kuda.internal.TailConfig;

public class ExampleService {
    
    public ExampleService() {
        // You can configure this anywhere must be set once.
        TailConfig.setApiUrl("http://localhost:9901");
        TailConfig.setN1neToken("79dd8985-2d85-4a22-bfa0-0d70e963d713");
    }
    
    public void myRandomMethod() {
        try {
            this.doSomeLogic();
            Tail.success("Testing kuda success").send();
        } catch (Exception ex) {
            Tail.error("Testing kuda error").withTag("env", "prod").send();
        }
    }
    
    public void otherN1neTailNotes() {
        // Here are the other methods that can be used to provide more kuda details.

        /////////////////
        // INFO
        ////////////////
        Tail.info("Testing kuda info").send();
        Tail.info("Testing kuda info1").withTag("env", "prod").send();
        Tail.info("Testing kuda info2", "extra log stack details").withTag("env", "prod").send();
        Tail.info("Testing kuda info3", "extra log stack details", "SYSTEM_ALERT").withTag("env", "prod").send();

        Map<String, String> tags = new HashMap<>();
        tags.put("env", "prod");
        tags.put("test", "info");
        tags.put("n1ne", "tails-info");
        Tail.info("Testing kuda info4", "extra log stack details", "SYSTEM_ALERT").withTags(tags).send();

        /////////////////
        // SUCCESS
        /////////////////
        Tail.success("Testing kuda success").send();
        Tail.success("Testing kuda success1").withTag("env", "prod").send();
        Tail.success("Testing kuda success2", "extra log stack details").withTag("env", "prod").send();
        Tail.success("Testing kuda success3", "extra log stack details", "SYSTEM_ALERT").withTag("env", "prod").send();

        Map<String, String> tags = new HashMap<>();
        tags.put("env", "prod");
        tags.put("test", "success");
        tags.put("n1ne", "tails-success");
        Tail.success("Testing kuda success4", "extra log stack details", "SYSTEM_ALERT").withTags(tags).send();

        /////////////////
        // WARN
        /////////////////
        Tail.warn("Testing kuda warn").send();
        Tail.warn("Testing kuda warn1").withTag("env", "prod").send();
        Tail.warn("Testing kuda warn2", "extra log stack details").withTag("env", "prod").send();
        Tail.warn("Testing kuda warn3", "extra log stack details", "SYSTEM_ALERT").withTag("env", "prod").send();

        Map<String, String> tags = new HashMap<>();
        tags.put("env", "prod");
        tags.put("test", "warn");
        tags.put("n1ne", "tails-warn");
        Tail.warn("Testing kuda warn4", "extra log stack details", "SYSTEM_ALERT").withTags(tags).send();

        /////////////////
        // ERROR
        /////////////////
        Tail.error("Testing kuda error").send();
        Tail.error("Testing kuda error1").withTag("env", "prod").send();
        Tail.error("Testing kuda error2", "extra log stack details").withTag("env", "prod").send();
        Tail.error("Testing kuda error3", "extra log stack details", "SYSTEM_ALERT").withTag("env", "prod").send();

        Map<String, String> tags = new HashMap<>();
        tags.put("env", "prod");
        tags.put("test", "error");
        tags.put("n1ne", "tails-error");
        Tail.error("Testing kuda error4", "extra log stack details", "SYSTEM_ALERT").withTags(tags).send();

        /////////////////
        // CRITICAL
        /////////////////
        Tail.critical("Testing kuda critical").send();
        Tail.critical("Testing kuda critical1").withTag("env", "prod").send();
        Tail.critical("Testing kuda critical2", "extra log stack details").withTag("env", "prod").send();
        Tail.critical("Testing kuda critical3", "extra log stack details", "SYSTEM_ALERT").withTag("env", "prod").send();

        Map<String, String> tags = new HashMap<>();
        tags.put("env", "prod");
        tags.put("test", "critical");
        tags.put("n1ne", "tails-critical");
        Tail.critical("Testing kuda critical4", "extra log stack details", "SYSTEM_ALERT").withTags(tags).send();

        /////////////////
        // KUDA
        // Send message with your own custom level (replace "MY_KUDA_LEVEL" with the level you want to create)
        /////////////////
        Tail.kuda("MY_KUDA_LEVEL", "Testing kuda").send();
        Tail.kuda("MY_KUDA_LEVEL", "Testing kuda1").withTag("env", "prod").send();
        Tail.kuda("MY_KUDA_LEVEL", "Testing kuda2", "extra log stack details").withTag("env", "prod").send();
        Tail.kuda("MY_KUDA_LEVEL", "Testing kuda3", "extra log stack details", "SYSTEM_ALERT").withTag("env", "prod").send();

        Map<String, String> tags = new HashMap<>();
        tags.put("env", "prod");
        tags.put("test", "kuda");
        tags.put("n1ne", "tails-kuda");
        Tail.kuda("MY_KUDA_LEVEL", "Testing kuda4", "extra log stack details", "SYSTEM_ALERT").withTags(tags).send();
    }
}

```

# Develop
## Build
Build the project using the following command
```bash
mvn clean install
```

## Maven Central Repository
Use the following doc to get setup with publishing to the maven central repository
https://central.sonatype.org/register/central-portal/#publishing

Maven install using release profile.
```bash
mvn clean install -P release
```

Maven deploy to the maven central repository
```bash
mvn deploy -P release
```

## Support

For community users, open an issue on GitHub or Join our Discord

[![Join our Discord](https://img.shields.io/badge/Join_Discord-7289DA?style=for-the-badge&logo=discord&logoColor=white)](https://discord.gg/ma9CCw7F2x)

## Contributing

Please use the following guidelines for contributions [CONTRIBUTING](./contributing.md)