# tncomputer
Computation with Extendable Query Language


## Goal
1. Creating an extensible mechanism for making calculations with structured data.
2. Creating a mechanism for searching values in structured data with filtering and computations.
3. Creating a fully customizable mechanism for calculating and converting values of different types.

This is like xpath for working with xml or like sql for working with databases. \
The module allows you to manipulate any data from any sources - json, file system, or a java object, and of course from XML and databases. 
As a matter of fact, the data can be in absolutely any format.
Data may not exist at all - you yourself can specify a method for extracting data from a source or a method for generating it.


Look at [wiki](https://github.com/anymaker/tncomputer/wiki)
or [Demo.java](https://github.com/anymaker/tncomputer/blob/master/test/a2u/tn/utils/computer/Demo.java)
for see posibilities.


## Get jar
You can use maven dependensy
```xml
    <dependency>
      <groupId>com.github.anymaker</groupId>
      <artifactId>tncomputer</artifactId>
      <version>0.0.9</version>
    </dependency>
```
Or download jar from https://mvnrepository.com/artifact/com.github.anymaker/tncomputer


## Note
Currently this is a experimental project and it has no fixed API.
