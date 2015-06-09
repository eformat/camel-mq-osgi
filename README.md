MQ Example
==============

This example shows how Camel can communicate with Websphere MQ, using the standard
Camel JMS component in a route.

The example listens for HTTP requests using the Camel Jetty componenent.
When a request arrives over HTTP, it triggers the route. The next component in 
the route (JMS producer) puts the message onto an MQ Queue called outgoingPayments.
Under the covers, the route implicitely waits for a response on a designated response
queue called incomingPayments. The replyTo queue is specified as an option to the 
JMS Producer endpoint URL. Once the response is received, the route will return the 
response back to the external client.

Key Points
------------

* camel route for request/reply using JMS (WebSphere MQ)
* need for IBM runtime libraries 
* jms config based on spring (see camel-context.xml)


System requirements
-------------------
Before building and running this quick start you need:

* Maven 3.0.4 or higher
* JDK 1.6 or 1.7
* JBoss Fuse 6.1
* IBM MQ 7.0.X queue manager, OSGi client libraries (7.1.0.6 minimum)


IBM libraries (OSGI run-time) dependencies
----------------------------------------------------------------------------
There are references to IBM classes inside the Spring configuration (see camel-context.xml)
Because these references are in Spring configuration only, and not in Java code, then
it is possible to build the project without introducing any build-time dependencies on
IBM MQ libraries.

For runtime, a set of MQ libraries needs to be installed into FUSE ESB making them available 
at run time. This set of dependent runtime JARs is provided as part of a MQ installation. I used
Websphere MQ version 7. These JARS are packaged as OSGI bundles by IBM, and suitable for installing into an OSGI
container such as Karaf.

Note: if you get errors deploying the OSGi bundles - that for some versions of the OSGi client libraries
you need to manually add this property to the MANIFEST.MF to be able to deploy them e.g.

modify META-INF/MANIFEST
add: Bundle-ManifestVersion: 2


* See pox.xml for &lt;fabric8.bundels&gt; file:// dependencies - these could be put into a nexus/maven repo for instance mvn://

The connection to IBM MQ is configured in camel-context.xml

These need to point to a suitably configured IBM MQ Queue Manager e.g. set the MCAUSER for now:

ALTER CHANNEL('SYSTEM.DEF.SVRCONN') CHLTYPE('SVRCONN') MCAUSER('mqm')


Build and Deploy
----------------

To deploy the profile into a running FUSE ESB (on localhost) run:

mvn clean fabric8:deploy

* See &lt;jolokiaUrl&gt;http://localhost:8181/jolokia&lt;/jolokiaUrl&gt; - you can modify this to be any FUSE instace


