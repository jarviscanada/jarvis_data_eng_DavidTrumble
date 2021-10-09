package ca.jrvs.apps.grep;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.nio.file.Path;
import java.util.regex.Pattern;

public class JavaGrepImp implements JavaGrep {

    final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

    private String m_regex;
    private String m_rootPath;
    private String m_outFile;

    @Override
    public void process() {

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
    }

    @Override
    public List<File> listFiles(String rootDir) {

        List<Path> filePathList = new ArrayList<>();
        List<File> fileList = new ArrayList<>();
        Path path = Paths.get(rootDir);

        try {

            filePathList = Files.walk(path)
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());


            for(Path f : filePathList) {

                fileList.add(f.toFile());
            }
        } catch(IOException e) {
            logger.error(e.getMessage());
        }
        return fileList;
    }

    @Override
    public List<String> readLines(File inputFile) {

        List<String> linesRead = new ArrayList<>();
        BufferedReader bufferReader = null;
        String currentLine;
        String ioFile = inputFile.getPath();

        try {
            bufferReader = new BufferedReader(new FileReader(ioFile));

            while((currentLine = bufferReader.readLine()) != null) {
                linesRead.add(currentLine);
            }

        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        return linesRead;
    }

    @Override
    public boolean containsPattern(String line) { return Pattern.matches(getRegex(), line); }

    @Override
    public void writeToFile(List<String> lines) {

        try {

            BufferedWriter bufferWrite = null;
            bufferWrite = new BufferedWriter(new FileWriter(getOutFile()));

            for(String s : lines) {
                bufferWrite.write(s);
                bufferWrite.newLine();
            }
            bufferWrite.close();

        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }

    @Override
    public String getRootPath() { return m_rootPath; }

    @Override
    public void setRootPath(String rootPath) { m_rootPath = rootPath; }

    @Override
    public String getRegex() { return m_regex; }

    @Override
    public void setRegex(String regex) { m_regex = regex; }

    @Override
    public String getOutFile() { return m_outFile; }

    @Override
    public void setOutFile(String outFile) { m_outFile = outFile; }

    public static void main(String[] args) {

        if (args.length != 3) {
            throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
        }
            JavaGrepImp jgImp = new JavaGrepImp();
            jgImp.setRegex(args[0]);
            jgImp.setRootPath(args[1]);
            jgImp.setOutFile(args[2]);

            try {
                jgImp.process();
            } catch(Exception e) {
                jgImp.logger.error(e.getMessage());
            }
    }
}
