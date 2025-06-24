package rsa;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RSAAlgoritmo {

    private int tamPrimo; // Tamaño en bits para los primos
    private BigInteger n, p, q; // n = p * q
    private BigInteger fi;      // fi = (p-1)(q-1)
    private BigInteger e, d;    // Claves pública y privada

    // Constructor con tamaño de primo
    public RSAAlgoritmo(int tamPrimo) {
        this.tamPrimo = tamPrimo;
    }

    // Genera dos números primos aleatorios distintos
    public void generarPrimos() {
        SecureRandom rnd = new SecureRandom();
        p = new BigInteger(tamPrimo, 10, rnd);
        do {
            q = new BigInteger(tamPrimo, 10, rnd);
        } while (q.equals(p));
    }

    // Genera claves pública (e) y privada (d)
    public void generarClaves() {
        n = p.multiply(q);
        fi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        do {
            e = new BigInteger(2 * tamPrimo, new SecureRandom());
        } while (e.compareTo(fi) >= 0 || !e.gcd(fi).equals(BigInteger.ONE));

        d = e.modInverse(fi);
    }

    // Cifra el mensaje como arreglo de BigInteger
    public BigInteger[] cifrar(String mensaje) {
        byte[] bytes = mensaje.getBytes();
        BigInteger[] cifrado = new BigInteger[bytes.length];

        for (int i = 0; i < bytes.length; i++) {
            BigInteger m = BigInteger.valueOf(bytes[i]);
            cifrado[i] = m.modPow(e, n);
        }

        return cifrado;
    }

    // Descifra un arreglo de BigInteger a texto
    public String descifrar(BigInteger[] cifrado) {
        char[] resultado = new char[cifrado.length];

        for (int i = 0; i < cifrado.length; i++) {
            BigInteger m = cifrado[i].modPow(d, n);
            resultado[i] = (char) m.intValue();
        }

        return new String(resultado);
    }

    // Getters para acceder a los parámetros
    public BigInteger getP() { return p; }
    public BigInteger getQ() { return q; }
    public BigInteger getN() { return n; }
    public BigInteger getFi() { return fi; }
    public BigInteger getE() { return e; }
    public BigInteger getD() { return d; }
}
