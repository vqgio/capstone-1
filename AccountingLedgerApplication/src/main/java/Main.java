import ui.ConsoleUI;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static model.Transaction.*;
import static service.ReportService.*;

public class Main {
    public static void main(String[] args) {
        ConsoleUI ui = new ConsoleUI();
        ui.display();
    }
}


