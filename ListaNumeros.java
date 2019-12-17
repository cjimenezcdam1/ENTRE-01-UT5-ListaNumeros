import java.util.Arrays;
/**
 * La clase encapsula en un array
 * una lista de numeros
 * 
 * @author Christian Jiménez Cuesta 
 * 
 */


public class ListaNumeros 
{
    public static final int TAM_LISTA = 16;
    private int[] numeros;  
    private int pos;  

    /**
     * Constructor de la clase ListaNumeros 
     * Crea e inicializa adecuadamente los
     * atributos
     * 
     * @param n el tamaño máximo del array
     */
    public ListaNumeros(int n) 
    {
        if (n > TAM_LISTA) {
            throw new IllegalArgumentException("Valor no permitido para tamaño lista");
        }
        numeros = new int[n];
        pos = 0;
    }

    /**
     * @param numero el valor que se añade al final de numeros. No se hace nada si
     *               el array está completo o ya está el elemento
     * @return true si se ha podido añadir, false en otro caso
     * 
     * asumimos que numero es >= 0 (no hay que comprobarlo)
     */
    public boolean addElemento(int numero)
    {
        boolean resultado = !estaCompleta() && !estaElemento(numero);
        if(resultado){
            numeros[pos] = numero;
            pos++;
        }
        return resultado;
    }

    /**
     * devuelve true si numeros está completo, false en otro caso Hazlo sin if
     */
    public boolean estaCompleta()
    {
        return pos == numeros.length;
    }

    /**
     * devuelve true si la lista está vacía, false en otro caso. Hazlo sin if
     */
    public boolean estaVacia() 
    {
        return pos == 0;
    }

    /**
     * devuelve el nº de elementos realmente guardados en la lista
     */
    public int getTotalNumeros()
    {
        return pos;
    }

    /**
     * Vacía la lista
     */
    public void vaciarLista() 
    {
        for(int i = 0; i < pos; i++){
            numeros[i] = 0;
        }
        pos = 0; 
    }

    /**
     * @param numero el valor a buscar
     * @return true si se encuentra, false en otro caso
     */
    public boolean estaElemento(int numero) 
    {
        boolean resultado = false;
        int i = 0;
        while(i < pos && !resultado){
            resultado = numeros[i] == numero;
            i++;
        }
        return resultado;
    }

    /**
     * Representación textual de la lista de la forma indicada  (ver enunciado)
     * Si numeros = {14, 8, 13, 9, 11, 5, 3, 10, 7, 1}
     *  devuelve | 14 | 8 | 13 | 9 | 11 | 5 | 3 | 10 | 7 | 1 |
     * 
     * Si la lista está vacía devuelve | |
     */
    public String toString() 
    {
        String representacion = "";
        if(pos == 0){
            representacion += "| |";
        }else{
            for(int i = 0; i < pos; i++){
                representacion += " | " + numeros[i];
            }
            representacion += " |";
        }
        return representacion;
    }

    /**
     * Mostrar en pantalla la lista
     */
    public void escribirLista() 
    {
        System.out.println(this.toString());
    }

    /**
     * a partir de un array de pares contador/valor crea y devuelve
     * un nuevo array resultado de expandir estos pares contador/valor
     *  
     *   Si numeros =  {3, 8, 4, 2, 0, 42, 5, 1}
     *                  |  |  |  |  |   |  |  | 
     *                  +--+  +--+  +---+  +--+ 
     *                  par    par    par   par 
     * 
     *  se devuelve: {8, 8, 8, 2, 2, 2, 2, 1, 1, 1, 1, 1}
     * (ver detalles en el enunciado)
     */
    public int[] expandir() {
        //Comprobamos si es impar el nº de numeros o no
        if(esImpar(pos)){
            throw new RuntimeException("Nº impar de elementos en el array, añada uno más");
        }
        //Contamos la cantidad de elementos del array expandido
        int totalElementos = 0;
        for(int i = 0; i < pos; i += 2){
            totalElementos += numeros[i];
        }
        //Formación del array expandido
        int[] arrayExpandido = new int[totalElementos];
        int posArrayExp = 0;
        for(int i = 0; i < pos; i += 2){
            int repeticiones = numeros[i];
            for(int j = 1; j <= repeticiones; j++){
                arrayExpandido[posArrayExp] = numeros[i + 1];
                posArrayExp++;
            }
        }
        return arrayExpandido;
    }

    /**
     * @param valor el nº a analizar
     * @return true si valor es impar, false en otro caso
     */
    private static boolean esImpar(int valor) {
        return valor % 2 != 0;
    }

    /**
     *  Modifica la lista reorganizando los valores pares e impares, los pares se
     *  colocan al principio y los impares al final. Se mantiene el orden de ambos
     *  
     *  Se hará recorriendo una sola vez la lista y sin  usar ningún otro array auxiliar
     * 
     *  Si numeros = {3, 7, 4, 9, 2, 5, 8, 11, 13} 
     *  después de reorganizarParesImpares() quedaría {4, 2, 8, 3, 7, 9, 5, 11, 13}
     */
    public void reorganizarParesImpares() {
        int valorAuxiliar = 0;
        int posAuxiliar = 0;
        int posNuevo = 0;
        for(int i = 0; i < pos; i++){
             if(numeros[i] % 2 == 0){
                 valorAuxiliar = numeros[i];
                 for(int j = i; j > posNuevo; j--){
                     numeros[j] = numeros[j - 1];
                 }
                 numeros[posNuevo] = valorAuxiliar;
                 posNuevo++;
             }
        }
    }

     
    /**
     *  Usando métodos de la clase Arrays haz una copia 
     *  de numeros al tamaño indicado por su longitud lógica
     *  Ordena esta copia
     *  Crea y devuelve un nuevo objeto ListaNumeros 
     *  que incluya los elementos del array ordenado
     */
    public ListaNumeros nuevaLista() {
        int[] nuevoNumeros = Arrays.copyOf(numeros, pos);
        Arrays.sort(nuevoNumeros);
        ListaNumeros listaOrdenada = new ListaNumeros(pos);
        for(int i = 0; i < pos; i++){
            listaOrdenada.addElemento(nuevoNumeros[i]);
        }
        return listaOrdenada;
    }

    /**
     * devuelve un array de 2 dimensiones de 4 filas y 4 columnas  
     * y guarda en este array los elementos de numeros tal como indica el enunciado
     * 
     *  Si numeros = {3, 7, 4, 9, 2, 5, 8, 11, 13}
     *  el nuevo array tendrá { {3, 7, 4, 9},
     *                          {2, 5, 8, 11} ,
     *                          {13, 0, 0, 0} ,
     *                          {0, 0, 0, 0} }
     * 
     */
    public int[][] toArray2D() 
    {
        int[][] array2D = new int[4][4];
        int posCopia = 0;
        for(int i = 0; i < array2D.length && posCopia < pos; i++){
            for(int j = 0; j < array2D[i].length && posCopia < pos; j++){
                array2D[i][j] = numeros[posCopia];
                posCopia++;
            }
        }
        return array2D;
    }

    /**
     * Punto de entrada a la aplicación
     * Contiene código para probar los métodos de ListaNumeros
     */
    public static void main(String[] args) 
    {
        ListaNumeros numeros = new ListaNumeros(10);
        numeros.addElemento(3);
        numeros.addElemento(7);
        numeros.addElemento(4);
        numeros.addElemento(9);
        numeros.addElemento(2);
        numeros.addElemento(5);
        numeros.addElemento(8);
        numeros.addElemento(11);

        System.out.println("Original: " + numeros.toString());
        int[] expandido = numeros.expandir();
        System.out.println("Expandido: " + Arrays.toString(expandido));
        System.out.println("El array está compuesto por " + numeros.getTotalNumeros() + " numeros");
        numeros.reorganizarParesImpares();
        System.out.println("Reorganizado: " + numeros.toString());
        int[][] arrayEn2D = numeros.toArray2D();
        System.out.println("VISTA DEL ARRAY EN 2D");
        for(int i = 0; i < arrayEn2D.length; i++){
            System.out.println(Arrays.toString(arrayEn2D[i]));
        }
    }
}
