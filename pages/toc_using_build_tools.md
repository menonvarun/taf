---
layout: default
title: Using Build Tools
permalink: /using_build_tools.html
heading: Using Build Tools
toc: true
---

#Using Build Tools

TAF can be used any of the build tools. You simple have to add the TAF jar as a dependency in your build tool. TAF is deployed on maven repository so it can be downloaded automatically by ivy, maven or gradle build tools. Following are the configurations for few tools:

##Maven

Add TAF repository to your pom.xml file or setting.xml as shown below:

<pre class="brush: xml;">
    <repositories>
        <repository>
            <id>taf-repo</id>
            <name>TAF repo</name>
            <url>https://github.com/menonvarun/mvn-repo/raw/master/releases</url>
        </repository>
    </repositories>
	
</pre>

Add TAF as a dependency in your pom.xml.

<pre class="brush: xml;">
 	<dependency>
		&lt;groupId&gt;org.imaginea&lt;/groupId&gt;
		&lt;artifactId&gt;testautomationframework&lt;/artifactId&gt;
		<version>(taf-version)</version>
	</dependency>
 	
</pre>

##Gradle

Add TAF repository to your builds.gradle file as shown below:

<pre class="brush: java;">
    reposittories {
		mavenRepo urls: "https://github.com/menonvarun/mvn-repo/raw/master/releases"
	}
	
</pre>

Add TAF as a dependency in your build.gradle.

<pre class="brush: java;">
 	dependencies {
		compile "org.imaginea:testautomationframework:0.0.4"
	}
 	
</pre>

