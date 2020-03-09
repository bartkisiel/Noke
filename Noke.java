/* Autor: Bartłomiej Kisiel
9.03.2020
Prosty program Noke to nic innego jak próba odtworzenia systemu popularnej gry losowej Keno.
Rozgrywka polega na obstawieniu 1-10 liczb, gdzie jeden zakład kosztuje 2 złote i 
porównaniu ich z wygrywającymi, losowo wygenerowanymi przez komputer 20 liczbami. 
W obecnej wersji mojego programu nie obowiązują wygrane pośrednie - albo trafimy wszystko, albo nic. 
Są jednak plany dodania systemu "nagród pocieszenia". 
Gracz może wpłacać dowolną ilość środków na swoje konto, na które również trafiają wszystkie wygrane.
Liczby losowane przez komputer nie powtarzają się w obrębie jednego losowania. 
Użytkownik nie może podać kilku tych samych liczb.
Dla wygody sprawdzania numerów zdecydowałem się dodać funkcje sortujące obie tablice. 

*/
package noke;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Noke {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int playerMoney = 0;
        int howManyNum;
        int [] winningNums = new int [20];
        boolean game = false;
        int depositMenu = 0;
        int earnings = 0;
       
        
        
        do{        
        System.out.println("*==================================*");
        System.out.println("    Witaj w Noke!");
        System.out.println("");
        System.out.println("        Twoje środki to: " + playerMoney + " zł.");
        System.out.println("*==================================*");
        System.out.println("Czy chcesz wpłacić pieniądze? 1 - Tak, 2 - Nie.");
        depositMenu = in.nextInt();
            if(depositMenu == 1){
                playerMoney = playerMoney + deposit();
                System.out.println("Twoje środki to: " + playerMoney + " zł.");
            }
            
            
            else if(depositMenu == 2 && playerMoney < 2){
                System.out.println("Dziękujemy za grę.");
                game = false;
                break;
            }
            
            if(playerMoney < 2){
                System.out.println("Nie stać Cię na zakład.");
                game = false;
                continue;
            }
            
           
        
        System.out.println("Podaj ile liczb chcesz obstawić!");
        howManyNum = in.nextInt();
        if(howManyNum < 0 || howManyNum > 10){
            System.out.println("Przeczytaj ponownie zasady gry.");
            break;
        }
        int [] playerNum = new int [howManyNum];
        System.out.println("Obstawiasz " + howManyNum + " liczb.");
        playerMoney = playerMoney - 2;
        System.out.println("Doskonale! Teraz je podaj z zakresu 1-70.");
        for(int i = 0; i < howManyNum; i++){
            int userInput = in.nextInt();
            if(userInput > 70 || userInput < 1){
                System.out.println("Przeczytaj ponownie zasady gry.");
                System.out.println("W tym losowaniu straciłeś swoją szansę!");
                break;
            }
            
            if(duplicatesDetector(userInput, playerNum)){
                playerNum[i] = userInput;
            }
            
            
        }
        showPlayerNums(playerNum, howManyNum);
       
            generateWinningNums(winningNums);
        
        
            System.out.println("Wygrywające liczby to:");
            System.out.print(Arrays.toString(winningNums));
            System.out.print(" ");
            System.out.println();
            System.out.println();
            Arrays.sort(winningNums);
            Arrays.sort(playerNum);
            System.out.println("Znajdź swoje liczby: " + Arrays.toString(winningNums));
            System.out.println();
            System.out.println(Arrays.toString(playerNum));
            System.out.println();
            
            
            Integer [] winningInt = new Integer[winningNums.length];
            int i = 0;
                    for(int value : winningNums){
                        winningInt[i++] = value;
                    }
            Integer [] playerInt = new Integer[playerNum.length];
            int j = 0;
                    for(int value : playerNum){
                        playerInt[j++] = value;
                    }
               
            
            
            boolean checkwin = checkwin(winningInt, playerInt);
           if(checkwin == true){ 
       
                System.out.println("Gratulację!!!");
            switch(howManyNum){
                case 1:
                    playerMoney = playerMoney + 3;
                            continue;
                case 2: 
                    playerMoney = playerMoney + 14;
                    continue;
                case 3:
                    playerMoney = playerMoney + 18;
                    continue;
                case 4:
                    playerMoney = playerMoney + 60;
                    continue;
                case 5: 
                    playerMoney = playerMoney + 250;
                    continue;
                case 6: 
                    playerMoney = playerMoney + 500;
                    continue;
                case 7: 
                    playerMoney = playerMoney + 1500;
                    continue;
                case 8:
                    playerMoney = playerMoney + 10000;
                    continue;
                case 9: 
                    playerMoney = playerMoney + 50000;
                    continue;
                case 10:
                    playerMoney = playerMoney + 200000;
                    continue;
                
                  
            }
            
        }
        
       
       if(playerMoney < 2){
           game = false;
       } 
            
        
    }while(game = true);
        
    }

    private static void showPlayerNums(int[] playerNum,int howManyNum) {
        System.out.print("Twoje liczby to: ");

        System.out.println(Arrays.toString(playerNum));
        
        System.out.println();
    }

    private static int deposit() {
        Scanner in = new Scanner(System.in);
        System.out.println("Ile chcesz wpłacić?");
        int deposit = in.nextInt();
        return deposit;
        
        
    }

    private static int[] generateWinningNums(int [] winningNums) {
        Random rand = new Random();
        for(int i = 0; i < winningNums.length; i++){
            int randomNumber = rand.nextInt((70) + 1);
            if(duplicatesDetector(randomNumber, winningNums) && randomNumber != 0){
                winningNums[i] = randomNumber;
            } else {
                i--;
            }
            
           
        }
      return winningNums;
    }
        
    private static boolean checkwin(Integer [] winningInt, Integer [] playerInt) {
 
       List winningList = Arrays.asList(winningInt);
       List playerList = Arrays.asList(playerInt);
        return winningList.containsAll(playerList);
       
    }

    private static boolean duplicatesDetector(int randomNumber, int[] winningNums) {
        for(int i = 0; i < winningNums.length; i++){
            if(randomNumber == winningNums[i]){
                return false;
            }
        }
        return true;
        
    }
}
  