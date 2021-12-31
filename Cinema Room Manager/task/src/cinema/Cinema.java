package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        final int EXIT = 0;

        int boughtTickets = 0;
        int currentIncome = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int cinemaRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int cinemaSeatsInEachRow = scanner.nextInt();
        int totalSeats = cinemaSeatsInEachRow * cinemaRows;

        char[][] cinema = createCinema(cinemaRows, cinemaSeatsInEachRow);
        int choose;
        do {
            printMenu();
            choose = scanner.nextInt();
            switch (choose) {
                case 1:
                    printCinema(cinema);
                    break;
                case 2:
                    boolean bought = false;
                    do {
                        System.out.println("Enter a row number:");
                        int row = scanner.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        int seatNumber = scanner.nextInt();
                        if (row >= cinema.length || row < 1 || seatNumber >= cinema[0].length || seatNumber < 1) {
                            System.out.println("Wrong input");
                            continue;
                        }
                        int ticketPrice = calculateTicketPrice(cinemaRows, cinemaSeatsInEachRow, row);

                        if (cinema[row][seatNumber] != 'B') {
                            System.out.println("Ticket price: $" + ticketPrice);
                            cinema[row][seatNumber] = 'B';
                            boughtTickets++;
                            bought = true;
                            currentIncome += ticketPrice;
                        } else {
                            System.out.println("That ticket has already been purchased!");
                            System.out.println();
                        }
                    } while (!bought);
                    break;
                case 3:
                    System.out.println("Number of purchased tickets: " + boughtTickets);
                    double ticketsInPercent = (boughtTickets * 100) / (double) totalSeats;
                    System.out.printf("Percentage : %.2f%%%n", ticketsInPercent);
                    System.out.println("Current income: $" + currentIncome);
                    System.out.println("Total income: $" + calculateTotalIncome(cinemaRows, cinemaSeatsInEachRow));
                    break;
            }
        } while (choose != EXIT);

    }

    private static void printMenu() {
        System.out.println();
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }

    private static char[][] createCinema(int rows, int seats) {
        char[][] cinema = new char[rows + 1][seats + 1];

        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[i].length; j++) {
                if (i == 0) {
                    if (j == 0) {
                        cinema[i][j] = ' ';
                    } else {
                        cinema[i][j] = (char) (j + '0');
                    }
                } else {
                    if (j == 0) {
                        cinema[i][j] = (char) (i + '0');
                    } else {
                        cinema[i][j] = 'S';
                    }
                }
            }
        }
        return cinema;
    }

    private static void printCinema(char[][] cinema) {
        System.out.println("Cinema:");
        for (char[] chars : cinema) {
            for (char aChar : chars) {
                System.out.print(aChar + " ");
            }
            System.out.println();
        }
    }

    public static int calculateTicketPrice(int rows, int seats, int searchingRow) {
        int totalSeats = rows * seats;
        int ticketPrice;

        if (totalSeats <= 60) {
            ticketPrice = 10;
        } else {
            if (searchingRow <= rows / 2) {
                ticketPrice = 10;
            } else {
                ticketPrice = 8;
            }
        }
        return ticketPrice;
    }

    public static int calculateTotalIncome(int rows, int seats) {
        int totalSeats = rows * seats;
        int ticketPrice;
        int totalIncome;

        if (totalSeats <= 60) {
            ticketPrice = 10;
            totalIncome = totalSeats * ticketPrice;
        } else {
            int frontHalfIncome = rows / 2 * seats * 10;
            int backHalfIncome = (rows / 2 + 1) * seats * 8;
            totalIncome = frontHalfIncome + backHalfIncome;
        }
        return totalIncome;
    }
}
