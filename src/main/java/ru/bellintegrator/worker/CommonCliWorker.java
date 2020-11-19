package ru.bellintegrator.worker;

import org.apache.commons.cli.*;
import ru.bellintegrator.controller.ConfigController;

import java.io.OutputStream;
import java.io.PrintWriter;

public class CommonCliWorker {

    private static boolean isHelp = false;
    private static Options posixOptions = new Options();
    public static void start(String[] str) {
        initPosixOptions(posixOptions);

        CommandLineParser cmdLinePosixParser = new DefaultParser();
        CommandLine commandLine;

        try {
            commandLine = cmdLinePosixParser.parse(posixOptions, str);
            selectCommand(commandLine);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void selectCommand(CommandLine commandLine) {
        if (commandLine.hasOption("timeInfluxDb")) {
            String[] arguments = commandLine.getOptionValues("timeInfluxDb");
            if (arguments.length > 0) {
                ConfigController.setTimeInfluxDB(arguments[0]);
            } else {
                System.err.println("Аргумент не указан или указан не верно");
            }
        }
        if (commandLine.hasOption("сonfigSql")) {
            String[] arguments = commandLine.getOptionValues("сonfigSql");
            if (arguments.length > 0) {
                ConfigController.setConfigSQL(arguments[0]);
            } else {
                System.err.println("Аргумент не указан или указан не верно");
            }
        }
        if (commandLine.hasOption("сonfig")) {
            String[] arguments = commandLine.getOptionValues("сonfig");
            if (arguments.length > 0) {
                ConfigController.setConfig(arguments[0]);
            } else {
                System.err.println("Аргумент не указан или указан не верно");
            }
        }
        if (commandLine.hasOption("resultXlsx")) {
            String[] arguments = commandLine.getOptionValues("resultXlsx");
            if (arguments.length > 0) {
                ConfigController.setResultXlsx(arguments[0]);
            } else {
                System.err.println("Аргумент не указан или указан не верно");
            }
        }
        if (commandLine.hasOption("resultCsv")) {
            String[] arguments = commandLine.getOptionValues("resultCsv");
            if (arguments.length > 0) {
                ConfigController.setResultCsv(arguments[0]);
            } else {
                System.err.println("Аргумент не указан или указан не верно");
            }
        }
        if (commandLine.hasOption("template")) {
            String[] arguments = commandLine.getOptionValues("template");
            if (arguments.length > 0) {
                ConfigController.setTemplate(arguments[0]);
            } else {
                System.err.println("Аргумент не указан или указан не верно");
            }
        }
        if (commandLine.hasOption("sql")) {
            String[] arguments = commandLine.getOptionValues("sql");
            if (arguments.length > 0) {
                ConfigController.setConfig(arguments[0]);
            } else {
                System.err.println("Аргумент не указан или указан не верно");
            }
        }
        if (commandLine.hasOption("generateTemplate")) {
            String[] arguments = commandLine.getOptionValues("generateTemplate");
            System.out.println("Option generateTemplate : " + arguments[0]);
        }
        if (commandLine.hasOption("generateSqlConfig")) {
            String[] arguments = commandLine.getOptionValues("generateSqlConfig");
            System.out.println("Option generateSqlConfig : " + arguments[0]);
        }
        if (commandLine.hasOption("generateConfig")) {
            String[] arguments = commandLine.getOptionValues("generateConfig");
            System.out.println("Option generateConfig : " + arguments[0]);
        }
        if(commandLine.hasOption("h")){
            isHelp = true;
            printHelp(
                    posixOptions, // опции по которым составляем help
                    100, // ширина строки вывода
                    "Options:", // строка предшествующая выводу
                    "- - HELP - -", // строка следующая за выводом
                    3, // число пробелов перед выводом опции
                    3, // число пробелов перед выводом опцисания опции
                    true, // выводить ли в строке usage список команд
                    System.out // куда производить вывод
            );
        }
    }

    private static void initPosixOptions(Options posixOptions) {
        Option timeInfluxDb = new Option( "timeInfluxDb", true, "Set time boundaries. Use quotes to split FROM and TO date.");
        Option сonfigSql = new Option( "сonfigSql", true, "Set path to ConfigSQL file");
        Option сonfig = new Option( "сonfig", true, "Set path to Config file");
        Option resultXlsx = new Option( "resultXlsx", true, "Set XLSX-file output path");
        Option resultCsv = new Option( "resultCsv", true, "Set CSV-file output path");
        Option template = new Option( "template", true, "Set path to template XLSX");
        Option sql = new Option("sql", true, "Request SELECT query");
        Option generateTemplate = new Option( "generateTemplate", true, "Login");
        Option generateSqlConfig = new Option( "generateSqlConfig", true, "Login");
        Option generateConfig = new Option( "generateConfig", true, "Login");
        Option help = new Option("h","help", false, "Help");

        timeInfluxDb.setArgs(1);
        сonfigSql.setArgs(1);
        сonfig.setArgs(1);
        resultXlsx.setArgs(1);
        resultCsv.setArgs(1);
        template.setArgs(1);
        sql.setArgs(1);
        generateTemplate.setArgs(1);
        generateSqlConfig.setArgs(1);
        generateConfig.setArgs(1);

        posixOptions.addOption(timeInfluxDb);
        posixOptions.addOption(сonfigSql);
        posixOptions.addOption(сonfig);
        posixOptions.addOption(resultXlsx);
        posixOptions.addOption(resultCsv);
        posixOptions.addOption(template);
        posixOptions.addOption(sql);
        posixOptions.addOption(generateTemplate);
        posixOptions.addOption(generateSqlConfig);
        posixOptions.addOption(generateConfig);
        posixOptions.addOption(help);
    }

    public static void printHelp(
            final Options options,
            final int printedRowWidth,
            final String header,
            final String footer,
            final int spacesBeforeOption,
            final int spacesBeforeOptionDescription,
            final boolean displayUsage,
            final OutputStream out)
    {
        final String commandLineSyntax = "java test.jar";//подсказка по запуску самой программы
        final PrintWriter writer = new PrintWriter(out);// куда печатаем help
        final HelpFormatter helpFormatter = new HelpFormatter();// создаем объект для вывода help`а
        helpFormatter.printHelp(
                writer,
                printedRowWidth,
                commandLineSyntax,
                header,
                options,
                spacesBeforeOption,
                spacesBeforeOptionDescription,
                footer,
                displayUsage);//формирование справки
        writer.flush(); // вывод
    }

    public static boolean isHelp() {
        return isHelp;
    }
}
