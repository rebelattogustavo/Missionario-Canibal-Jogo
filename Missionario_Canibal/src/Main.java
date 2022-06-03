import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner tec = new Scanner(System.in);
    static ArrayList<Pessoa> listaMisLadoDireito = new ArrayList<>();
    static ArrayList<Pessoa> listaMisLadoEsquerdo = new ArrayList<>();
    static ArrayList<Pessoa> listaCanLadoDireito = new ArrayList<>();
    static ArrayList<Pessoa> listaCanLadoEsquerdo = new ArrayList<>();
    static boolean lado = false;
    static int[][] barco = new int[1][2];
    public static void main(String[] args) throws Exception{
        setaPersonagens();
        menu();
    }

    public static void menu() throws Exception{
        if(listaCanLadoDireito.size() > listaMisLadoDireito.size() && listaMisLadoDireito.size()!= 0){
            throw new PerdeuException();
        }if(listaCanLadoEsquerdo.size() > listaMisLadoEsquerdo.size() && listaMisLadoEsquerdo.size()!=0){
            throw new PerdeuException();
        }if((listaMisLadoEsquerdo.size() + listaCanLadoEsquerdo.size()) == 6){
            throw new GanhouException();
        }
        System.out.println("Informe o que você deseja fazer" +
                "\n1- Colocar uma pessoa no barco" +
                "\n2- Retirar uma pessoa do barco" +
                "\n3- Levar o barco ao outro lado");
        int opcao = tec.nextInt();
        try{
            switchzada(opcao);
        }catch(Exception erro){
            System.out.println("Deu ruim\n"
                    + erro.getClass().getSimpleName() + ": "
                    + erro.getMessage());
        }
    }

    public static void switchzada(int opcao) throws Exception {
        switch (opcao){
            case 1:
                coloca();
                menu();
                break;
            case 2:
                retira();
                menu();
                break;
            case 3:
                leva();
                menu();
        }
    }

    private static void coloca(){
        if((barco[0][0] + barco[0][1]) < 2){
            int opcaoPerso = select("colocar no barco");
            switch(opcaoPerso){
                case 1:
                    System.out.println("Informe quantos missionário você deseja: ");
                    int opcao = tec.nextInt();
                    barco[0][1] = opcao;
                    break;
                case 2:
                    System.out.println("Informe quantos canibais você deseja: ");
                    opcao = tec.nextInt();
                    barco[0][0] = opcao;
                    break;
            }
        }else{
            System.out.println("Barco lotado! Retire alguém");
        }
    }

    private static void retira(){
        if((barco[0][0] + barco[0][1]) > 0){
            int opcaoPerso = select("retirar do barco");
            switch (opcaoPerso){
                case 1:
                    System.out.println("Informe quantos missionário você deseja: ");
                    int opcao = tec.nextInt();
                    if (opcao > barco[0][1]){
                        System.out.println("Quantidade maior do que há no barco!");
                    }else {
                        barco[0][1] = barco[0][1] - opcao;
                    }
                    break;
                case 2:
                    System.out.println("Informe quantos canibais você deseja: ");
                    opcao = tec.nextInt();
                    if (opcao > barco[0][0]){
                        System.out.println("Quantidade maior do que há no barco!");
                    }else {
                        barco[0][0] = barco[0][0] - opcao;
                    }
                    break;
            }
        }else {
            System.out.println("Não há ninguém no barco :(");
        }
    }

    private static void leva(){
        System.out.println(lado);
        if(lado == false){
            if(barco[0][1]>0){
                for(int i =0; i<barco[0][1]; i++){
                    if(listaMisLadoDireito.size() == 1){
                        listaMisLadoEsquerdo.add(listaMisLadoDireito.get(0));
                        listaMisLadoDireito.remove(0);
                    }else{
                        listaMisLadoEsquerdo.add(listaMisLadoDireito.get(i));
                        listaMisLadoDireito.remove(i);
                    }
                }
            }
            if(barco[0][0] >0){
                for(int i =0;i<barco[0][0]; i++){
                    if(listaCanLadoDireito.size() == 1){
                        listaCanLadoEsquerdo.add(listaCanLadoDireito.get(0));
                        listaCanLadoDireito.remove(0);
                    }else{
                        listaCanLadoEsquerdo.add(listaCanLadoDireito.get(i));
                        listaCanLadoDireito.remove(i);
                    }
                }
            }
            lado = true;
        }else {
            if(barco[0][1] > 0){
                for(int i =0; i<barco[0][1]; i++){
                    listaMisLadoDireito.add(listaMisLadoEsquerdo.get(i));
                    listaMisLadoEsquerdo.remove(i);
                }
            }
            if(barco[0][0] > 0){
                for(int i =0;i<barco[0][0]; i++){
                    listaCanLadoDireito.add(listaCanLadoEsquerdo.get(i));
                    listaCanLadoEsquerdo.remove(i);
                }
            }
            lado = false;
        }
        barco[0][0] = 0;
        barco[0][1] = 0;
        System.out.println("Missionários no lado direito: " + listaMisLadoDireito.size());
        System.out.println("Missionários no lado esquerdo: " + listaMisLadoEsquerdo.size());
        System.out.println("Canibais no lado direito: " + listaCanLadoDireito.size());
        System.out.println("Canibais no lado esquerdo: " + listaCanLadoEsquerdo.size());

    }


    private static int select(String opcao){
        System.out.println("Informe quem você deseja " +opcao +
                "\n1- Missionário" +
                "\n2- Canibal");
        return tec.nextInt();
    }


    public static void setaPersonagens(){
        listaMisLadoDireito.add(new Missionario(1, "M1"));
        listaMisLadoDireito.add(new Missionario(2, "M2"));
        listaMisLadoDireito.add(new Missionario(3, "M3"));
        listaCanLadoDireito.add(new Canibal(1, "C1"));
        listaCanLadoDireito.add(new Canibal(2, "C2"));
        listaCanLadoDireito.add(new Canibal(3, "C3"));
    }




}