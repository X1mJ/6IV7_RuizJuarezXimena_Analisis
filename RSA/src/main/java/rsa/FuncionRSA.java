package rsa;

import java.math.BigInteger;
import java.util.Random;

public class FuncionRSA {

    // Tamaño en bits para los números primos (pequeño para pruebas)
    int tamPrimo = 8;

    // Variables principales del algoritmo
    BigInteger n, p, q;       // n = p * q
    BigInteger fi;            // fi = (p-1) * (q-1)
    BigInteger e, d;          // Claves pública (e) y privada (d)

    // Genera dos números primos distintos
    public void generarPrimos() {
        p = new BigInteger(tamPrimo, 10, new Random());
        do {
            q = new BigInteger(tamPrimo, 10, new Random());
        } while (q.compareTo(p) == 0);
    }

    // Genera las claves pública y privada
    public void generarClaves() {
        n = p.multiply(q);
        fi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        // Buscar un valor de 'e' que sea coprimo con fi
        do {
            e = new BigInteger(2 * tamPrimo, new Random());
        } while (e.compareTo(fi) >= 0 || !e.gcd(fi).equals(BigInteger.ONE));

        // Calcular d como el inverso multiplicativo de e módulo fi
        d = e.modInverse(fi);
    }

    // Getters
    public BigInteger getP() { return p; }
    public BigInteger getQ() { return q; }
    public BigInteger getN() { return n; }
    public BigInteger getFi() { return fi; }
    public BigInteger getE() { return e; }
    public BigInteger getD() { return d; }

    // Cifra un mensaje (como string) en un arreglo de BigInteger
    public BigInteger[] cifrar(String mensaje) {
        byte[] bytes = mensaje.getBytes();
        BigInteger[] cifrado = new BigInteger[bytes.length];

        for (int i = 0; i < bytes.length; i++) {
            BigInteger m = BigInteger.valueOf(bytes[i]);
            cifrado[i] = m.modPow(e, n); // C = M^e mod n
        }
        return cifrado;
    }

    // Descifra un arreglo de BigInteger a texto plano
    public String descifrar(BigInteger[] cifrado, BigInteger d, BigInteger n) {
        char[] caracteres = new char[cifrado.length];

        for (int i = 0; i < cifrado.length; i++) {
            BigInteger m = cifrado[i].modPow(d, n); // M = C^d mod n
            caracteres[i] = (char) m.intValue();
        }

        return new String(caracteres);
    }
}
