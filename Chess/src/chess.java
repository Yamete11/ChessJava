import java.util.Scanner;

public class chess {
    public static void main(String[] args) {


        String [][] chessBoard = new String[8][8];
        chessBoard[0][0] = "21";
        chessBoard[0][7] = "21";
        chessBoard[0][1] = "31";
        chessBoard[0][6] = "31";
        chessBoard[0][5] = "41";
        chessBoard[0][2] = "41";
        chessBoard[0][3] = "61";
        chessBoard[0][4] = "51";

        chessBoard[7][0] = "20";
        chessBoard[7][7] = "20";
        chessBoard[7][1] = "30";
        chessBoard[7][6] = "30";
        chessBoard[7][5] = "40";
        chessBoard[7][2] = "40";
        chessBoard[7][3] = "50";
        chessBoard[7][4] = "60";

        for(int i = 1; i < chessBoard.length - 1; i++){
            for(int j = 0; j < chessBoard[i].length; j++){
                if(i == 1){
                    chessBoard[i][j] = "11";
                } else if(i == 6){
                    chessBoard[i][j] = "10";
                } else {
                    chessBoard[i][j] = "  ";
                }
            }
        }

        System.out.println("First player controls figures with zero, second player goes with figures with one");
        
        Scanner scan = new Scanner(System.in);
        int actualPlayer = 0;


        boolean gameOver = false;
        while(!gameOver){


            for(int i = 0; i < chessBoard.length; i++){
                System.out.println("   +--+--+--+--+--+--+--+--+");
                System.out.print(chessBoard.length - i + "  ");
                for(int j = 0 ; j < chessBoard[i].length; j++){
                    System.out.print("|" + chessBoard[i][j]);
                }
                System.out.print("|\n");
            }
            System.out.println("   +--+--+--+--+--+--+--+--+\n");
            System.out.println("    A  B  C  D  E  F  G  H ");

            boolean allowanceToGo;
            int letterInitial, letterDestination, numberInitial, numberDestination;
            char [] players = {'0', '1'};

            actualPlayer = actualPlayer % 2;
            System.out.println("Player with " + actualPlayer + " can go");

            do{
                allowanceToGo = true;
                char error = 0;

                char[] arr = new char[4];
                for(int i = 0; i < 4; i++){
                    arr[i] = scan.next().charAt(0);
                }

                letterInitial = arr[0] - 65;
                numberInitial = chessBoard.length - (arr[1] - 48);
                letterDestination = arr[2] - 65;
                numberDestination = chessBoard.length - (arr[3] - 48);

                if(chessBoard[numberInitial][letterInitial].charAt(1) != players[actualPlayer]){
                    allowanceToGo = false;
                    error = '1';
                } else {
                    switch (chessBoard[numberInitial][letterInitial].charAt(0)){
                        case '1' -> {
                            allowanceToGo = false;
                            switch (chessBoard[numberInitial][letterInitial].charAt(1)){
                                case '0' -> {
                                    if((numberDestination - numberInitial) == -2 && numberInitial == 6 && letterDestination == letterInitial && chessBoard[numberDestination][letterDestination].equals("  ") && chessBoard[numberInitial - 1][letterInitial].equals("  ")){
                                        allowanceToGo = true;
                                    } else if(chessBoard[numberDestination][letterDestination].equals("  ") && letterInitial == letterDestination && (numberDestination - numberInitial) == -1){
                                        allowanceToGo = true;
                                    } else if(chessBoard[numberDestination][letterDestination].charAt(1) == '1' && (numberDestination - numberInitial) == -1 && Math.abs(letterDestination - letterInitial) == 1){
                                        allowanceToGo = true;
                                    }
                                }
                                case '1' -> {
                                    if((numberDestination - numberInitial) == 2 && numberInitial == 1 && letterDestination == letterInitial && chessBoard[numberDestination][letterDestination].equals("  ") && chessBoard[numberInitial + 1][letterInitial].equals("  ")){
                                        allowanceToGo = true;
                                    } else if(chessBoard[numberDestination][letterDestination].equals("  ") && letterInitial == letterDestination && (numberDestination - numberInitial) == 1){
                                        allowanceToGo = true;
                                    } else if(chessBoard[numberDestination][letterDestination].charAt(1) == '0' && (numberDestination - numberInitial) == 1 && Math.abs(letterDestination - letterInitial) == 1){
                                        allowanceToGo = true;
                                    }
                                }
                            }
                        }
                        case '2' -> {
                            if(letterInitial != letterDestination && numberInitial != numberDestination){
                                allowanceToGo = false;
                            } else {
                                if(letterInitial == letterDestination){
                                    if(numberInitial < numberDestination){
                                        for(int i = numberInitial + 1; i < numberDestination; i++){
                                            if (!chessBoard[i][letterInitial].equals("  ")) {
                                                allowanceToGo = false;
                                                error = '2';
                                                break;
                                            }
                                        }
                                    } else {
                                        for(int i = numberInitial - 1; i > numberDestination; i--){
                                            if (!chessBoard[i][letterInitial].equals("  ")) {
                                                allowanceToGo = false;
                                                error = '2';
                                                break;
                                            }
                                        }
                                    }
                                } else{
                                    if(letterInitial < letterDestination){
                                        for(int i = letterInitial + 1; i < letterDestination; i++){
                                            if (!chessBoard[numberInitial][i].equals("  ")) {
                                                allowanceToGo = false;
                                                error = '2';
                                                break;
                                            }
                                        }
                                    } else {
                                        for(int i = letterInitial - 1; i > letterDestination; i--){
                                            if (!chessBoard[numberInitial][i].equals("  ")) {
                                                allowanceToGo = false;
                                                error = '2';
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        case '3' -> {
                            allowanceToGo = false;
                            int [][] arrOfPossibleMoves = {{-1, -2}, {-2, -1}, {2, 1}, {1, 2}, {1, -2}, {2, -1}, {-2, 1}, {-1, 2}};
                            for (int[] arrOfPossibleMove : arrOfPossibleMoves) {
                                if (arrOfPossibleMove[0] == (letterDestination - letterInitial) && arrOfPossibleMove[1] == (numberDestination - numberInitial)) {
                                    allowanceToGo = true;
                                    break;
                                }
                            }

                        }
                        case '4' -> {
                            if(Math.abs(letterDestination - letterInitial) != Math.abs(numberDestination - numberInitial)){
                                allowanceToGo = false;
                            } else {
                                if(letterInitial > letterDestination && numberInitial > numberDestination){
                                    for(int i = numberInitial - 1, j = letterInitial - 1; i > numberDestination && j > letterDestination; i--, j--){
                                        if(!chessBoard[i][j].equals("  ")){
                                            allowanceToGo = false;
                                            error = '2';
                                            break;
                                        }
                                    }
                                } else if(letterInitial > letterDestination && numberInitial < numberDestination){
                                    for(int i = numberInitial + 1, j = letterInitial - 1; i < numberDestination && j > letterDestination; i++, j--){
                                        if(!chessBoard[i][j].equals("  ")){
                                            allowanceToGo = false;
                                            error = '2';
                                            break;
                                        }
                                    }
                                } else if(letterInitial < letterDestination && numberInitial < numberDestination) {
                                    for (int i = numberInitial + 1, j = letterInitial + 1; i < numberDestination && j < letterDestination; i++, j++) {
                                        if (!chessBoard[i][j].equals("  ")) {
                                            allowanceToGo = false;
                                            error = '2';
                                            break;
                                        }
                                    }
                                } else if(letterInitial < letterDestination && numberInitial > numberDestination) {
                                    for (int i = numberInitial - 1, j = letterInitial + 1; i > numberDestination && j < letterDestination; i--, j++) {
                                        if (!chessBoard[i][j].equals("  ")) {
                                            allowanceToGo = false;
                                            error = '2';
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        case '5' -> {
                            allowanceToGo = false;
                            if(Math.abs(letterDestination - letterInitial) <= 1 && Math.abs(numberDestination - numberInitial) <= 1){
                                allowanceToGo = true;
                            } else if(Math.abs(letterDestination - letterInitial) == Math.abs(numberDestination - numberInitial)){
                                if(letterInitial > letterDestination && numberInitial > numberDestination){
                                    for(int i = numberInitial - 1, j = letterInitial - 1; i > numberDestination && j > letterDestination; i--, j--){
                                        if(chessBoard[i][j].equals("  ")){
                                            allowanceToGo = true;
                                        } else {
                                            allowanceToGo = false;
                                            error = '2';
                                            break;
                                        }
                                    }
                                } else if(letterInitial > letterDestination && numberInitial < numberDestination){
                                    for(int i = numberInitial + 1, j = letterInitial - 1; i < numberDestination && j > letterDestination; i++, j--){
                                        if(chessBoard[i][j].equals("  ")){
                                            allowanceToGo = true;
                                        } else {
                                            allowanceToGo = false;
                                            error = '2';
                                            break;
                                        }
                                    }
                                } else if(letterInitial < letterDestination && numberInitial < numberDestination) {
                                    for (int i = numberInitial + 1, j = letterInitial + 1; i < numberDestination && j < letterDestination; i++, j++) {
                                        if(chessBoard[i][j].equals("  ")){
                                            allowanceToGo = true;
                                        } else {
                                            allowanceToGo = false;
                                            error = '2';
                                            break;
                                        }
                                    }
                                } else if(letterInitial < letterDestination && numberInitial > numberDestination) {
                                    for (int i = numberInitial - 1, j = letterInitial + 1; i > numberDestination && j < letterDestination; i--, j++) {
                                        if(chessBoard[i][j].equals("  ")){
                                            allowanceToGo = true;
                                        } else {
                                            allowanceToGo = false;
                                            error = '2';
                                            break;
                                        }
                                    }
                                }
                            } else if(letterInitial == letterDestination || numberInitial == numberDestination){
                                if(letterInitial == letterDestination){
                                    if(numberInitial < numberDestination){
                                        for(int i = numberInitial + 1; i < numberDestination; i++){
                                            if (chessBoard[i][letterInitial].equals("  ")) {
                                                allowanceToGo = true;
                                            } else {
                                                allowanceToGo = false;
                                                error = '2';
                                                break;
                                            }
                                        }
                                    } else {
                                        for(int i = numberInitial - 1; i > numberDestination; i--){
                                            if (chessBoard[i][letterInitial].equals("  ")) {
                                                allowanceToGo = true;
                                            } else {
                                                allowanceToGo = false;
                                                error = '2';
                                                break;
                                            }
                                        }
                                    }
                                } else{
                                    if(letterInitial < letterDestination){
                                        for(int i = letterInitial + 1; i < letterDestination; i++){
                                            if (chessBoard[numberInitial][i].equals("  ")) {
                                                allowanceToGo = true;
                                            } else {
                                                allowanceToGo = false;
                                                error = '2';
                                                break;
                                            }
                                        }
                                    } else {
                                        for(int i = letterInitial - 1; i > letterDestination; i--){
                                            if (chessBoard[numberInitial][i].equals("  ")) {
                                                allowanceToGo = true;
                                            } else {
                                                allowanceToGo = false;
                                                error = '2';
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        case '6' -> {
                            if(Math.abs(letterDestination - letterInitial) > 1 || Math.abs(numberDestination - numberInitial) > 1){
                                allowanceToGo = false;
                            }
                        }
                    }
                }

                if(chessBoard[numberInitial][letterInitial].charAt(1) == '0' && chessBoard[numberDestination][letterDestination].charAt(1) == '0'){
                    allowanceToGo = false;
                } else if(chessBoard[numberInitial][letterInitial].charAt(1) == '1' && chessBoard[numberDestination][letterDestination].charAt(1) == '1'){
                    allowanceToGo = false;
                }
                if(!allowanceToGo){
                    switch (error){
                        case '1' -> System.out.println("You can't use your opponent's figure. Try again!");
                        case '2' -> System.out.println("Something blocks you. Try again!");
                        default -> System.out.println("You can't go this way. Try again!");
                    }
                }
            } while (!allowanceToGo);
            if(chessBoard[numberDestination][letterDestination].charAt(0) == '6'){
                System.out.println("Player " + actualPlayer + " wins");
                gameOver = true;
            }
            actualPlayer++;
            chessBoard[numberDestination][letterDestination] =
            chessBoard[numberInitial][letterInitial];
            chessBoard[numberInitial][letterInitial] = "  ";

        }
    }
}