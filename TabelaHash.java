
package tabelahash;
import java.util.Random;
public class TabelaHash {
    static int colisao=0;
    static int comparacao = 0;
    public static class Tabela{
    int tamanho;
    Registro[] vetor;
     
        public void hashing(int codigo){
           Registro a = new Registro(codigo);
           int indice = codigo % tamanho;
           if (vetor[indice] != null){
               rehashing(a, vetor[indice]);
               colisao++;
           }
           else{
               vetor[indice] = a;
        }
        }
        public void rehashing(Registro a, Registro b){
            if(b.getProximo()!= null){
                rehashing(a,b.getProximo());
            }
            else{
                b.setProximo(a);
                
            }
        }
        public void busca_hashing(int codigo){
            int indice = codigo % tamanho;
            Registro atual = vetor[indice];
            while (atual.getCodigo_registro() != codigo && atual.getProximo() != null){
                atual = atual.getProximo();
                comparacao++;
            }
            if (atual.getCodigo_registro() == codigo){System.out.println("Valor Encontrado");}
            else {System.out.println("Valor Não Encontrado");}
        }
        public void print(){
            for(int i=0;i<tamanho;i++){
                Registro atual = vetor[i];
                System.out.print("Vetor["+i+"]:");
                while(atual != null){
                    System.out.print(atual.getCodigo_registro()+",");
                    atual = atual.getProximo();
                }
                System.out.println("\n");
            }
        }

    public Tabela(int tamanho){
        this.tamanho = tamanho;
        vetor = new Registro[tamanho];
    }
    } 
    
    public static class Registro{
        int codigo_registro;
        Registro proximo;

        public int getCodigo_registro() {
            return codigo_registro;
        }

        public void setCodigo_registro(int codigo_registro) {
            this.codigo_registro = codigo_registro;
        }

        public Registro getProximo() {
            return proximo;
        }

        public void setProximo(Registro proximo) {
            this.proximo = proximo;
        }
       
       
       public Registro(int codigo){
       codigo_registro = codigo;
       proximo = null;
       }
       
    }
    
    
    
    
    public static void main(String[] args) {
        Random random = new Random(123456789);
        Tabela tabela = new Tabela(100);
        double startTime = System.nanoTime();
        for(int i= 0; i<5000000; i++){
            int randomInt = 100000000 + random.nextInt(900000000);
            tabela.hashing(randomInt);
        }
        double endTime = System.nanoTime();
        double duracaoInsert = (endTime - startTime)/ 1000000;
        tabela.print();
        startTime = System.nanoTime();
        for (int i = 0; i<5;i++){
            int randomInt = 100000000 + random.nextInt(900000000);
            tabela.busca_hashing(randomInt);
        }
        endTime = System.nanoTime();
        double duracaoBusca = (endTime - startTime)/ 1000000;
        System.out.println("\n\nTempo de execucao de 5 buscas: " + duracaoBusca + " milissegundos");
        System.out.println("\n\nTempo de execucao de insercao: " + duracaoInsert + " milissegundos");
        System.out.print(colisao);
        System.out.print(comparacao);
    }
    
}
