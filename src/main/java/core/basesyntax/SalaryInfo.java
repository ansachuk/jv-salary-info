package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        StringBuilder sb = new StringBuilder("Report for period ").append(
                dateFrom).append(" - ").append(dateTo);

        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);

        for (String name : names) {
            int totalSalary = 0;

            for (String element : data) {
                String[] fields = element.split(" ");

                LocalDate current = LocalDate.parse(fields[0], formatter);
                String currentName = fields[1];

                if (!name.equals(currentName)) {
                    continue;
                }

                if ((current.isAfter(from) || current.isEqual(from))
                        && (current.isBefore(to) || current.isEqual(to))) {
                    totalSalary += Integer.parseInt(fields[2]) * Integer.parseInt(fields[3]);
                }
            }

            sb.append(System.lineSeparator()).append(name).append(" - ").append(totalSalary);
        }
        return sb.toString();
    }
}
