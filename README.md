### General info
MaleOrang provides access to [MailChimp API v3.0](http://developer.mailchimp.com/documentation/mailchimp/reference/overview/) methods from Java code. It is a replacement and upgrade for [ecwid-mailchimp](https://github.com/Ecwid/maleorang/) which has now been inactive.

MaleOrang is written in [Kotlin language](https://kotlinlang.org/) and can be used with any language which runs on JVM. Refer to the [javadoc pages](http://www.javadoc.io/doc/com.ecwid/maleorang/) for details.

this version of MaleOrang has many upgrades :
* compiled using JDK version 11 fom JDK 1.8 
* gradle version 7.5
* guava version 31.1-jre
* gson version 2.9.1
* httpclient version 4.5.13
* joda-time version 2.11.1
* testng version 7.6.1
* kotlin version 1.7.10
* dokka version 1.7.10


### Supported MailChimp API methods

Currently MaleOrang has wrappers for a limited number of MailChimp API methods, namely:
* [Members methods](http://developer.mailchimp.com/documentation/mailchimp/reference/lists/members/)
* [Batch Operations methods](http://developer.mailchimp.com/documentation/mailchimp/reference/batches/)
* Some of [Lists methods](http://developer.mailchimp.com/documentation/mailchimp/reference/lists/)

However, it is easy to extend the API and add support for any method you need (see the samples below).

### Code samples

* [Using an existing method implementation (Java)](src/test/java/com/ecwid/maleorang/examples/ExistingMethodExample.java)
* [Using an existing method implementation (Kotlin)](src/test/java/com/ecwid/maleorang/method/v3_0/lists/members/MembersTest.kt)
* [Using a custom method implementation (Java)](src/test/java/com/ecwid/maleorang/examples/CustomMethodExample.java)

### Build tools integration

MaleOrang is accessible from [Maven Central](https://search.maven.org/search?q=maleorang) so it can be easily integrated with your favorite build tools like Gradle or Maven.

