package ca.jrvs.apps.grep;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JavaGrepLambdaImp extends JavaGrepImp {

    @Override
    public List<String> readLines(File inputFile) throws IllegalArgumentException {

        Path path = inputFile.toPath();
        List<String> linesRead = new ArrayList<>();

        try {
            Files.lines(path).forEach(line -> linesRead.add(line));

        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new IllegalArgumentException();
        }
        return linesRead;
    }

    @Override
    public List<File> listFiles(String rootDir) throws RuntimeException {

        Path path = Paths.get(rootDir);
        List<File> listedFiles = new ArrayList<>();

        try {
            Files.walk(path)
                    .filter(Files::isRegularFile)
                    .forEach(file -> listedFiles.add(file.toFile()));
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException();
        }
        return listedFiles;
    }

    public static void main(String[] args) {

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

    }
}
