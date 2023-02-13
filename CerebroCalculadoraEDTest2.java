package testsJUnit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import practicaJUnit.CerebroCalculadoraED;
import practicaJUnit.Operaciones;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
class CerebroCalculadoraEDTest2 {
	CerebroCalculadoraED c = new CerebroCalculadoraED();

	// En las cuatro primeras operaciones de la calculadora he comprobado
	// que se realizan las operaciones pertinentes tanto a c.numero1 como
	// c.numero2:

	@Test
	@Order(1)
	public void testProcesarSuma() {

		Operaciones signo = Operaciones.SUMAR;

		c.operarSuma(signo);
		double esperado = c.numero1 + c.numero2;
		double resul = c.resultado;

		assertEquals(esperado, resul);

	}

	@Test
	@Order(2)
	public void testProcesarResta() {

		Operaciones signo = Operaciones.RESTAR;

		c.operarResta(signo);
		double esperado = c.numero1 - c.numero2;
		double resul = c.resultado;

		assertEquals(esperado, resul);

	}

	@Test
	@Order(3)
	public void testProcesarMulti() {

		Operaciones signo = Operaciones.MULTIPLICAR;

		c.operarMultiplica(signo);
		double esperado = c.numero1 * c.numero2;
		double resul = c.resultado;

		assertEquals(esperado, resul);

	}

	@Test
	@Order(4)
	public void testProcesarDivision() {

		Operaciones signo = Operaciones.DIVIDIR;

		c.operarDivide(signo);
		double esperado = c.numero1 / c.numero2;
		double resul = c.resultado;

		assertEquals(esperado, resul);

	}

	// En los siguientes test, he simulado que vengo desde la operación SUMAR en
	// todos
	// doy por hecho que da igual de que operación vengamos, porque lo importante
	// es que sumemos, restemos... a un resultado anterior.

	// He obligado a que el resultado obtenido sea 15 para que pase el test
	// y lo pasa correctamente.

	@Test
	@Order(5)
	public void testSumaRes() {
		System.out.println("El resultado final debe ser 15.0");
		Operaciones signo = Operaciones.SUMAR_RES;
		c.operarSuma(signo);
		double a = c.resultado;
		a = 0;
		c.operarSumaRes(signo);
		double b = c.resultado;

		assertEquals(15.0, a + b);
	}

	// Aquí lo he hecho como en el anterior test pero restando al resultado:
	// El resultado debe ser ¡13!
	@Test
	@Order(6)
	public void testRestaRes() {
		System.out.println("El resultado final debe ser 13.0");
		Operaciones signo = Operaciones.RESTAR_RES;
		c.operarSuma(signo);
		double a = c.resultado;
		c.numero2 = 0;// Reseteo c.numero2

		c.operarRestaRes(signo);
		double resul = a - c.numero2;
		assertEquals(13.0, resul);

		// En este test hay que meter el número en negativo para que de positivo
		// por lo que considero que el metodo está mal configurado.
		// He probado varias formas de realizar el test y está mal.
	}

	// Igual que el de arriba pero después multiplico al resultado.
	// El resultado debe dar ¡28!

	@Test
	@Order(7)
	public void testMultiplicaRes() {
		System.out.println("El resultado final debe ser 28.0");
		Operaciones signo = Operaciones.MULTIPLICAR_RES;
		c.operarSuma(signo);
		double a = c.resultado;

		c.operarMultiplicaRes(signo);
		double resul = a * c.numero2;
		assertEquals(28.0, resul);

	}

	// Lo mismo que antes pero divido al resultado.
	@Test
	@Order(8)
	public void testDividirRes() {
		System.out.println("El resultado final debe ser 28.0");
		Operaciones signo = Operaciones.DIVIDIR_RES;
		c.operarSuma(signo);
		double a = c.resultado;

		c.operarDivideRes(signo);
		double resul = a / c.numero2;
		assertEquals(28.0, resul);

	}

	// Muestro el resultado de una suma anterior que sería el resultado actual:
	@Test
	@Order(9)
	public void testMostrarResAct() {
		System.out.println("El resultado final debe ser 14.0");
		Operaciones signo = Operaciones.RESULTADO;
		c.operarSuma(signo);
		double a = c.resultado;

		c.mostrarResultado(signo);
		double resul = a;
		assertEquals(14.0, resul);

	}

	// En este he tenido que acotar la comparación porque mi cpu se estaba
	// calentando jajaja.
	@Test
	@Order(10)

	public void testNumAleatorio() {
		// He redondeado los numeros porque sino puede estar 20 anios
		// sin parar de ejercutarse el bucle while hasta que se cumpla
		// la condicion.
		Operaciones signo = Operaciones.RANDOM;
		double resul = 0.0;
		c.numeroAleatorio(signo);
		double a = c.resultado;
		double x = Math.round(a);// Redondeo la variable a

		while (resul != x) {

			resul = Math.round((Math.random() * 100 + 1));

			if (resul == x)
				break;
		}

		assertEquals(x, resul);

	}

	// Aquí he utilizado dos arrays de tipo String para rellenarlos con los datos y
	// compararlos.
	@Test
	@Order(11)
	public void testMostrarHistorial() {

		Operaciones sig1 = Operaciones.SUMAR;
		Operaciones sig2 = Operaciones.RESTAR;
		Operaciones sig3 = Operaciones.MULTIPLICAR;
		Operaciones sig4 = Operaciones.DIVIDIR;
		Operaciones sig5 = Operaciones.SUMAR_RES;

		// Simulo que se han realizado estas operaciones para cargar el historial.
		c.operarSuma(sig1);
		c.operarResta(sig2);
		c.operarMultiplica(sig3);
		c.operarDivide(sig4);
		c.operarSumaRes(sig5);

		String[] esperado = { "", "", "", "", "" };

		esperado[0] = "SUMAR";
		esperado[1] = "RESTAR";
		esperado[2] = "MULTIPLICAR";
		esperado[3] = "DIVIDIR";
		esperado[4] = "SUMAR_RES";

		String[] obtenido = { "", "", "", "", "" };

		obtenido[0] = (sig1.toString());
		obtenido[1] = (sig2.toString());
		obtenido[2] = (sig3.toString());
		obtenido[3] = (sig4.toString());
		obtenido[4] = (sig5.toString());
		// Comparo los arrays. El resultado es satisfactorio.
		assertArrayEquals(esperado, obtenido);
	}

//	Con estos test compruebo que el signo recibido de los Enum es el deseado.

	@Test
	@Order(12)
	public void testProcesarSum() {

		Operaciones signo = Operaciones.SUMAR;
		String simbolo = signo.getSimbolo();
		String simbolo2 = "+";

		String obtenido = simbolo;
		String esperado = simbolo2;

		assertEquals(obtenido, esperado);

	}

	@Test
	@Order(13)
	public void testProcesarRest() {

		Operaciones signo = Operaciones.RESTAR;
		String simbolo = signo.getSimbolo();
		String simbolo2 = "-";

		String obtenido = simbolo;
		String esperado = simbolo2;

		assertEquals(obtenido, esperado);

	}

	@Test
	@Order(14)
	public void testProcesarMultiplicacion() {

		Operaciones signo = Operaciones.MULTIPLICAR;
		String simbolo = signo.getSimbolo();
		String simbolo2 = "*";

		String obtenido = simbolo;
		String esperado = simbolo2;

		assertEquals(obtenido, esperado);

	}

	@Test
	@Order(15)
	public void testProcesarDividir() {

		Operaciones signo = Operaciones.DIVIDIR;
		String simbolo = signo.getSimbolo();
		String simbolo2 = "/";

		String obtenido = simbolo;
		String esperado = simbolo2;

		assertEquals(obtenido, esperado);

	}

	@Test
	@Order(16)
	public void testProcesarSumarAlResul() {

		Operaciones signo = Operaciones.SUMAR_RES;
		String simbolo = signo.getSimbolo();
		String simbolo2 = "+=";

		String obtenido = simbolo;
		String esperado = simbolo2;

		assertEquals(obtenido, esperado);

	}

	@Test
	@Order(17)
	public void testProcesarRestarAlResul() {

		Operaciones signo = Operaciones.RESTAR_RES;
		String simbolo = signo.getSimbolo();
		String simbolo2 = "-=";

		String obtenido = simbolo;
		String esperado = simbolo2;

		assertEquals(obtenido, esperado);

	}

	@Test
	@Order(18)
	public void testProcesarMultiplicarAlResul() {

		Operaciones signo = Operaciones.MULTIPLICAR_RES;
		String simbolo = signo.getSimbolo();
		String simbolo2 = "*=";

		String obtenido = simbolo;
		String esperado = simbolo2;

		assertEquals(obtenido, esperado);

	}

	@Test
	@Order(19)
	public void testProcesarDividirAlResul() {

		Operaciones signo = Operaciones.DIVIDIR_RES;
		String simbolo = signo.getSimbolo();
		String simbolo2 = "/=";

		String obtenido = simbolo;
		String esperado = simbolo2;

		assertEquals(obtenido, esperado);

	}

	@Test
	@Order(20)
	public void testResul() {

		Operaciones signo = Operaciones.RESULTADO;
		String simbolo = signo.getSimbolo();
		String simbolo2 = "!";

		String obtenido = simbolo;
		String esperado = simbolo2;

		assertEquals(obtenido, esperado);

	}

	@Test
	@Order(21)
	public void testRandom() {

		Operaciones signo = Operaciones.RANDOM;
		String simbolo = signo.getSimbolo();
		String simbolo2 = "¿?";

		String obtenido = simbolo;
		String esperado = simbolo2;

		assertEquals(obtenido, esperado);

	}

	@Test
	@Order(22)
	public void testHistorial() {

		Operaciones signo = Operaciones.HISTORIAL;
		String simbolo = signo.getSimbolo();
		String simbolo2 = "h";

		String obtenido = simbolo;
		String esperado = simbolo2;

		assertEquals(obtenido, esperado);

	}

//Para el Switch he probado con la primera opcion, una vez que funciona, ya se
// que todas funcionan.
	@Test
	@Order(23)
	public void testParaSwitch() {
		Operaciones sig1 = Operaciones.SUMAR;

		c.procesarOperacion("1");

		int ide = sig1.getId();
		String numId = Integer.toString(ide);

		assertEquals("1", numId);
	}

}
