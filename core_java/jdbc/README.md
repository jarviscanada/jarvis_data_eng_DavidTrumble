# Introduction
This grep application is a Java program to validate regex patterns. The application searches for a text pattern recursively in a given directory and outputs matched lines to a file. This project uses the Maven Standard Directory Layout following the industry standard. Managed with IntelliJ IDE and utilizes Java Regex API, Lambda and Stream API's. Also, SL4J as a logging framework for debug and error information. The grep app is then Dockerized so it's easier for the user to consume.

# Quick Start
Maven

```
mvn clean package

java -cp target/*.jar <class_file> <regex/pattern> <directory> <out_file>
```

Docker

```
docker pull dttrumble/grep

docker run --rm -v <directory> -v <out_file> norivinay/grep <regex/pattern> <directory> <out_file>
```

# Implemenation
This project is an implementation of the Linux grep command using grep application in Java.


- JavaGrepImp

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
        
- JavaGrepLambdaImp

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
The process of the grep application is as follows:
- Traverse a given directory and return all files.
- Read a file and return all the lines.
- Check if a line contains the regex pattern.
- Write lines to a file.

## Performance Issue
The memory issue with this application was that each List item is added to heap memory, causing OutOfMemoryError Exception. This was resolved by returning Java Stream API rather than List Objects. 

# Test
The application takes three arguments: regex, rootPath and outFile.
- regex: a special text string for describing a search pattern.
- rootPath: root directory path.
- outFile: output file name.

The 3 arguments are manually tested against: nested directories, input text files and application output files. 

# Deployment
- Manage Maven Project and pom.xml dependencies.
- Push Docker image to Docker hub.
- Deploy project through Github for source code control.

# Improvement
1. The directory search can be modified and expanded. 
2. Create choke points and manage irrelevant file extensions.
3. Better handle failed search results and log descriptions.
