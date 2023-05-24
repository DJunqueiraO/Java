package Experiment._070523;

import java.util.Scanner;

public class Exercício {
	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		
		System.out.print("Primeiro Salario: ");
		String valor1 = entrada.next().replace(',', '.');
		
		System.out.print("Segundo Salario: ");
		String valor2 = entrada.next().replace(',', '.');
		
		System.out.print("Terceiro Salario: ");
		String valor3 = entrada.next().replace(',', '.');
		
		double salario1 = Double.parseDouble(valor1);
		double salario2 = Double.parseDouble(valor2);
		double salario3 = Double.parseDouble(valor3);
		
		double media = (salario1 + salario2 + salario3)/3;
		
		System.out.print("A media dos salarios e: " + media);
		entrada.close();
	}
}
