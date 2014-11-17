package com.seied.datamining;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.converters.FileConverter;

import java.io.File;

/**
 * Created by Aleksander on 2014-11-17.
 */
public class DataMiningArguments {
    @Parameter(names = {"-file", "-f"}, converter = FileConverter.class, description = "Path to file with data", required = true)
    private File file;

    @Parameter(names = {"-threshold", "-t"}, description = "Value of support threshold", required = true)
    private int supportThreshold;

    @Parameter(names = {"-products", "-p"}, description = "Number of products", required = true)
    private int productNr;

    @Parameter(names = {"-help"}, help = true)
    private boolean help;

    public File getFile() {
        return file;
    }

    public int getSupportThreshold() {
        return supportThreshold;
    }

    public int getProductNr() {
        return productNr;
    }

    public boolean isHelp() {
        return help;
    }

    public static DataMiningArguments parse(String[] args) {
        DataMiningArguments commandLineArguments = new DataMiningArguments();
        JCommander jCommander = new JCommander(commandLineArguments);

        try {
            jCommander.parse(args);
        } catch (ParameterException e) {
            e.getMessage();
            jCommander.usage();
            System.exit(1);
        }

        if (commandLineArguments.isHelp()) {
            jCommander.usage();
            System.exit(0);
        }

        return commandLineArguments;
    }
}
