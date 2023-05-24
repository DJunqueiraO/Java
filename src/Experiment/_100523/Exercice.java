package Experiment._100523;

public class Exercice {
	public static void main(String[] args) {
		for (int counter = 1; counter <= 50; counter++) {
			System.out.print(termOfSequence(counter) + " ");
			if(counter % 10 == 0) System.out.print("\n");
		}
	}
//		A sequence X é formada da seguinte maneira. Cada termo da sequence é a soma do penultimate e antipenultimate number. 
//		so em uma sequence begining por 5 6 8, os 10 primeiros termos is 5 6 8 11 14 19 25 33 44 58
//		Considerando essa sequence begining por 5 6 e 8, imprima o valor do fiftieth termo.
//		OBS: No vale arrays
	static int termOfSequence(int position) {
		int firstNumber = 5;
		int secondNumber = 6;
		int thirdNumber = 8;
        if(position == 1) return firstNumber;
        if(position == 2) return secondNumber;
        if(position == 3) return thirdNumber;
        if(position < 1) return 0;
        for (int i = 4; i <= position; i++) {
        	int sum = firstNumber + secondNumber;
        	firstNumber = secondNumber;
        	secondNumber = thirdNumber;
        	thirdNumber = sum;
        }
        return thirdNumber;
   }
}
