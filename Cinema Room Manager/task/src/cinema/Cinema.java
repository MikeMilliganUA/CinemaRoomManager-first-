package cinema;

import java.util.Scanner;

public class Cinema {
    private static Scanner scanner = new Scanner(System.in);
    private static String[][] room;
    private static int ticketsSold = 0;

    public static void main(String[] args) {
        menu();
    }

    private static void menu() {
        System.out.println("Enter the number of rows: ");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row: ");
        int seatsInRow = scanner.nextInt();
        buildRoom(rows, seatsInRow);
        while (true) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int action = scanner.nextInt();
            if (action == 0) {
                break;
            } else if (action == 1) {
                printRoom(room);
            } else if (action == 2) {
                buyTicket();
            } else if (action == 3) {
                printStatistics();
            }
        }
    }

    private static void printRoom(String[][] room) {
        System.out.println("Cinema:");
        for (String[] arr : room) {
            for (String s : arr) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void buyTicket() {
        while (true) {
            int price;
            int totalAmount = (room.length - 1) * (room[1].length - 1);
            System.out.println("Enter a row number: ");
            int rowNumber = scanner.nextInt();
            System.out.println("Enter a seat number in that row: ");
            int seatNumberInThatRow = scanner.nextInt();
            if (rowNumber > room.length - 1 || seatNumberInThatRow > room[1].length - 1) {
                System.out.println("Wrong input!");
                return;
            } else {
                double half = (double) (room.length - 1) / 2.0;
                if (room[rowNumber][seatNumberInThatRow].equals("S")) {
                    if (totalAmount < 60) {
                        price = 10;
                    } else if ((double) rowNumber < half) {
                        price = 10;
                    } else {
                        price = 8;
                    }
                    room[rowNumber][seatNumberInThatRow] = "B";
                    ticketsSold++;
                    printRoom(room);
                    System.out.printf("Ticket price: $%d \n", price);
                    break;
                } else {
                    System.out.println("That ticket has already been purchased!");
                }
            }
        }
    }

    private static void printStatistics() {
        System.out.printf("Number of purchased tickets: %d \n", ticketsSold);
        double total = (room.length - 1) * (room[1].length - 1);
        double sold = ticketsSold;
        double percent = (sold / total) * 100;
        if (ticketsSold == 0) {
            System.out.println("Percentage: 0.00%");
        } else {
            System.out.printf("Percentage: %.2f%% \n", percent);
        }
        int income = 0;
        for (int i = 0; i < room.length; i++) {
            for (int j = 0; j < room[1].length; j++) {
                if (room[i][j].equals("B")) {
                    income += i >= room.length / 2 ? 8 : 10;
                }
            }
        }
        System.out.printf("Current income: $%d \n", income);
        calculateProfit();
    }

    private static String[][] buildRoom(int rows, int seatsInRow) {
        room = new String[rows + 1][seatsInRow + 1];
        for (int i = 0; i < seatsInRow + 1; i++) {
            room[0][i] = String.valueOf(i);
        }
        room[0][0] = " ";
        for (int i = 1; i < rows + 1; i++) {
            for (int j = 1; j < room[i].length; j++) {
                room[i][j] = "S";
            }
            room[i][0] = String.valueOf(i);
        }
        return room;
    }

    private static void calculateProfit() {
        int rows = room.length - 1;
        int seatsInRow = room[1].length - 1;
        int totalNumberOfSeats = rows * seatsInRow;
        int totalIncome;
        if (totalNumberOfSeats <= 60) {
            totalIncome = totalNumberOfSeats * 10;
            System.out.printf("Total income: $%d \n", totalIncome);
        } else {
            if (rows % 2 == 0) {
                int half = rows / 2;
                totalIncome = (half * seatsInRow * 10) + (half * seatsInRow * 8);
                System.out.printf("Total income: $%d \n", totalIncome);
            } else {
                int half = rows / 2;
                totalIncome = (half * seatsInRow * 10) + ((half + 1) * seatsInRow * 8);
                System.out.printf("Total income: $%d \n", totalIncome);
            }
        }
    }

}
