# Introduction
This grep application is a Java program to validate regex patterns. The application searches for a text pattern recursively in a given directory and outputs matched lines to a file. This project uses the Maven Standard Directory Layout following the industry standard. Managed with IntelliJ IDE and utilizes Java Regex API, Lambda and Stream API's. The grep app is then Dockerized so it's easier for the user to consume.

# Quick Start
How to use your apps? 
This project is an implementation of the Linux grep command using grep application in Java. There are two main implementations in this application as well as Maven package management and Dockerize. 

Maven
mvn clean package

java -cp target/*.jar <class_file> <regex/pattern> <directory> <out_file>

Docker

#Implemenation

JavaGrepImp.java

        List<String> matchedLines = new ArrayList<>();
        List<File> listedFiles = listFiles(getRootPath());

        for(File f : listedFiles) {
            List<String> linesRead = readLines(f);
            for(String s : linesRead) {
                if(containsPattern(s)) {
                    matchedLines.add(s);
                }
            }
        }
        writeToFile(matchedLines);
  
JavaGrepLambdaImp

        if (args.length != 3) {
            throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
        }

        JavaGrepLambdaImp jglImp = new JavaGrepLambdaImp();
        jglImp.setRegex(args[0]);
        jglImp.setRootPath(args[1]);
        jglImp.setOutFile(args[2]);

        try {
            jglImp.process();
        } catch (Exception e) {
            jglImp.logger.error(e.getMessage());
        }

## Pseudocode
write `process` method pseudocode.
The app takes three arguments: regex, rootPath and outFile.
- regex: a special text string for describing a search pattern
- rootPath: root directory path
- outFile: output file name


## Performance Issue
(30-60 words)
Discuss the memory issue and how would you fix it

# Test
How did you test your application manually? (e.g. prepare sample data, run some test cases manually, compare result)

# Deployment
Created a dockerfile and built a new docker image locally. Then verified the image and ran the docker container. Finally, pushing the image to Docker Hub.

# Improvement
List three things you can improve in this project.
