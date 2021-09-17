/*
 * To change this license heaader, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pedro
 */

import java.util.Arrays;
public class Labirinto {

    private static final char TAMANHO = 10;
    private static char[][] tabuleiro;
    private static final double PROBABILIDADE = 0.7;
    private static int linhaInicio;
    private static int colunaInicio;
    
   public static int gerarNumero(int minimo, int maximo) {

    int valor = (int) Math.round(Math.random()  * (maximo - minimo));

    return minimo + valor;

  }

    
    public static char[][] CriarLabirinto(char[][] tabuleiro) {
        
        int i, j;
        for (i = 1; i < TAMANHO - 1; i++) {

            for (j = 1; j < TAMANHO - 1; j++) {

                if (Math.random() > PROBABILIDADE) {

                    tabuleiro[i][j] = '\u0001';

                }

            }

        }
        return tabuleiro;
        
    }
    
    public static void inicializarMatriz() {
    
        for(char[] arr1 : tabuleiro){ 
            Arrays.fill(arr1, ' ');
        }
        
    int i;
        for (i = 0; i < TAMANHO; i++) {
            tabuleiro[i][0] = '|';
            tabuleiro[i][TAMANHO - 1] = '|';
        
            tabuleiro[0][i] = '-';
            tabuleiro[TAMANHO - 1][i] = '-';
        
        }
        tabuleiro[TAMANHO - 1][0] = '-';
        
        tabuleiro=CriarLabirinto(tabuleiro);
        
        linhaInicio = gerarNumero(1, TAMANHO / 2 - 1);

        colunaInicio = gerarNumero(1, TAMANHO-2);

        tabuleiro[linhaInicio][colunaInicio] = '\u00A9';

        int linhaDestino = gerarNumero(TAMANHO / 2, TAMANHO - 2);

        int colunaDestino = gerarNumero(1, TAMANHO - 2);

        tabuleiro[linhaDestino][colunaDestino] = '\u00D7';

        
    }
   
    public static boolean posicaoVazia(int linha, int coluna) {
   boolean vazio = false;
   if (linha >= 0 && coluna >= 0 && linha < TAMANHO && coluna < TAMANHO) {
      vazio = (tabuleiro[linha][coluna] == ' ');
   }
   return vazio;
}

    private static boolean tentarCaminho(int proxLinha, int proxColuna) {
   boolean achou = false;
   if (tabuleiro[proxLinha][proxColuna] == '\u00D7') {
      achou = true;
   } else if (posicaoVazia(proxLinha, proxColuna)) {
      // marcar
      tabuleiro[proxLinha][proxColuna] = '.';
      imprimir();
      achou = procurarCaminho(proxLinha, proxColuna);
      if (!achou) {
         tabuleiro[proxLinha][proxColuna] = '_';
         imprimir();
      }
   }
   return achou;
}

    public static boolean procurarCaminho(int linhaAtual, int colunaAtual) {
   int proxLinha;
   int proxColuna;
   boolean achou = false;
   // tenta subir
   proxLinha = linhaAtual - 1;
   proxColuna = colunaAtual;
   achou = tentarCaminho(proxLinha, proxColuna);
   // tenta descer
   if (!achou) {
      proxLinha = linhaAtual + 1;
      proxColuna = colunaAtual;
      achou = tentarCaminho(proxLinha, proxColuna);
   }
   // tenta à esquerda
   if (!achou) {
      proxLinha = linhaAtual;
      proxColuna = colunaAtual - 1;
      achou = tentarCaminho(proxLinha, proxColuna);
   }
   // tenta à direita
   if (!achou) {
      proxLinha = linhaAtual;
      proxColuna = colunaAtual + 1;
      achou = tentarCaminho(proxLinha, proxColuna);
   }
   return achou;
}

    
    public static void imprimir() {
        for (int i = 0; i < TAMANHO; i++) {
           for (int j = 0; j < TAMANHO; j++) {
               System.out.print(tabuleiro[i][j]);
            }
        System.out.println();  

        }
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
     public static void main(String Arg[]) {
         tabuleiro = new char[TAMANHO][TAMANHO];
         inicializarMatriz();
         imprimir();
         System.out.println("\n- Procurando solução -\n");
   boolean achou = procurarCaminho(linhaInicio, colunaInicio);
   if (achou) {
      System.out.println("ACHOU O CAMINHO!");
   } else {
      System.out.println("Não tem caminho!");
}

         
     }
}