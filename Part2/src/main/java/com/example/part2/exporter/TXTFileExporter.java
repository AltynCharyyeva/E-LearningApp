package com.example.part2.exporter;

public class TXTFileExporter implements FileExporter {

    @Override
    public String exportData(Object object) {
        return object.toString();
    }
}
