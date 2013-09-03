Available via [clojars](http://clojars.org/search?q=clj-aws-imds)    
Current stable version: [clj-aws-imds "1.0.1"]


# clj-aws-imds

A simple wrapper around calls to Amazon EC2's instance metadata service.
This allows an EC2 instance to determine properties such as it's public
and private IP/hostname, the instance's user data, it's instance-id and
ami-id, and it's security group and ami launch index.


## Usage
```clojure

(get-user-data)

(get-instance-id)

(get-all)
;; Which returns,
{:user-data (get-user-data)
 :instance-id (get-instance-id)
 :instance-type (get-instance-type)
 :ami-id (get-ami-id)
 :ami-launch-index (get-ami-launch-index)
 :public-ip (get-public-ip)
 :public-hostname (get-public-hostname)
 :local-ip (get-local-ip)
 :local-hostname (get-local-hostname)
 :mac (get-mac)
 :az (get-az)
 :security-groups (get-security-groups)
 }
```


## Artifacts

clj-aws-imds artifacts are [released to Clojars](https://clojars.org/clj-aws-imds).

If you are using Maven, add the following repository definition to your `pom.xml`:

``` xml
<repository>
  <id>clojars</id>
  <url>http://clojars.org/repo</url>
</repository>
```

### The Most Recent Release

With Leiningen:

    [clj-aws-imds "1.0.1"]


With Maven:

    <dependency>
      <groupId>bagotricks</groupId>
      <artifactId>clj-aws-imds</artifactId>
      <version>1.0.1</version>
    </dependency>


## License

MIT
http://opensource.org/licenses/MIT

Copyright (C) 2013 Alan Busby
