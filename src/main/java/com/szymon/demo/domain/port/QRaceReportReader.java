package com.szymon.demo.domain.port;

import com.szymon.demo.domain.QRaceReport;

import java.io.File;

public interface QRaceReportReader {
    QRaceReport readReport(File file);
}
