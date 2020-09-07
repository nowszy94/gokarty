package com.szymon.demo.infra.e1gokart;

import com.szymon.demo.domain.Driver;
import com.szymon.demo.domain.QRaceReport;
import com.szymon.demo.domain.port.QRaceReportReader;
import org.apache.commons.lang3.StringUtils;
import org.apache.tika.Tika;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class E1GokartQRaceReportReader implements QRaceReportReader {
    @Override
    public QRaceReport readReport(File file) {
        String content = readContent(file);
        List<Driver> drivers = scrapDriversList(content);

        return null;
    }

    private String readContent(File file) {
        try {
            return new Tika().parseToString(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Driver> scrapDriversList(String content) {
        String drivers = StringUtils.substringBetween(content, "Kierowca", "Kart");
        List<Integer> kartNumbers = getKartNumbers(content);
        List<String> list = Stream.of(drivers.split("\n"))
                .filter(StringUtils::isNotEmpty)
                .collect(Collectors.toList());
        List<Driver> driversList = new ArrayList<>();
        for (int i = 0; i < list.size() / 2; i++) {
            String name = list.get(2 * i);
            String surname = list.get((2 * i) + 1);
            driversList.add(new Driver(name, surname, kartNumbers.get(i)));
        }
        fillDriversWithTheirLapTimes(content, driversList);
        return driversList;
    }

    private List<Integer> getKartNumbers(String content) {
        String kartNumbers = StringUtils.substringBetween(content, "Kart ", "Best time");
        return Arrays.stream(kartNumbers.split(" "))
                .filter(StringUtils::isNotEmpty)
                .filter(kartNo -> !"kart".equals(kartNo))
                .map(kartNo -> kartNo.replaceAll("\n", ""))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

    private void fillDriversWithTheirLapTimes(String content, List<Driver> drivers) {
        String times = StringUtils.substringBetween(content, "Okr.", "TOP DAY TOP WEEK");
        Stream.of(times.split("\n"))
                .filter(StringUtils::isNotEmpty)
                .filter(time -> !StringUtils.startsWith(time, "\\\\\\\\Śr."))
                .map(time -> StringUtils.substringAfter(time, " "))
                .map(time -> Arrays.asList(time.split(" ")))
                .forEach((lapTimes) -> {
                    for (int i = 0; i < lapTimes.size(); i++) {
                        String lapTime = lapTimes.get(i);
                        Driver driver = drivers.get(i);

                        driver.getTimes().add(lapTime);
                    }
                });

    }
}
