public class Calculadora {

public double resulatodAtual;
public int resultadoAtual;

public Calculadora(){
    this.resulatodAtual = 0.0;
}

public void somar(int valor){
    this.resultadoAtual += valor;
}
public void diminuir(int valor){
    this.resultadoAtual -= valor;
}
public String parOuImpar() {
    int parteInteira = (int) resultadoAtual; 
    if (parteInteira % 2 == 0) {
        return "par";
    } else {
        return "ímpar";
    }
}
public double getResultadoAtual() {
    return getResultadoAtual();
}
}









