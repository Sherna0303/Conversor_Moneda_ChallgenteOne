import java.io.InputStream;
import java.net.URL;
import java.text.NumberFormat;
import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;

public class Conversor {
	// Variables
	private double moneda_1;
	
	public double getMoneda_1() {
		return moneda_1;
	}
	public void setMoneda_1(double moneda_1) {
		this.moneda_1 = moneda_1;
	}
	
	// Convertir el valor ingresado
	public String tipo_Moneda(int tipo_moneda, int tipo_Conversion) {
		String valorAc = valorActual(codigoMoneda(tipo_moneda));
		double valor = Double.parseDouble(valorAc);

		switch (tipo_Conversion) {
		case 0: {
			// Dolare a pesos clombianos
			valor = cop_moneda(valor);
			break;
		}
		case 1: {
			// Pesos Colombianos a dolares
			valor = moneda_cop(valor);
			break;
		}
		default:
			System.out.println("ERROR");
		}
		
		NumberFormat fNumberFormat = NumberFormat.getCurrencyInstance();
		String resultado = fNumberFormat.format(valor);
		
		return resultado;
	}
	
	private double cop_moneda(double valor) {
		return this.moneda_1 / valor;
	}
	
	private double moneda_cop(double valor) {
		return this.moneda_1 * valor;
	}
	
	// Recibir por medio de una API el valor actual de la moneda solicitada
	private String valorActual(String tipo_Moneda) {
		String valor = "";
		String urlstr = "https://v6.exchangerate-api.com/v6/2cf4a42cd4d31bb8aafe962e/latest/COP";
		try {
			// Abrir conexcion con la API
			URL url = new URL(urlstr);
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			
			// Almacenando los datos
			InputStream stream = connection.getInputStream();
			byte[] arrStream = stream.readAllBytes();
			String jsonString = "";
			
			// Capturando los datos
			for (byte tmp: arrStream)
				jsonString+=(char)tmp;
			
			// Creando el objeto JSON
			JSONObject jsonObject = new JSONObject(jsonString);
			
			// Ingresando a un SubObjeto del Objeto MAIN y luego Ingresando un Elemento del subObjeto
			Object jsonObject2 = jsonObject.getJSONObject("conversion_rates").get(tipo_Moneda);
			valor = jsonObject2.toString();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return valor;
	}
	
	// Obtener el codigo de la moneda seleccionada
	private String codigoMoneda(int codigo) {
		//Selector de moneda
		String moneda;
			switch (codigo)
			{
				case 0: {
					//Dolares
					moneda = "USD";
					break;
				} 
				case 1: {		
					//Euros
					moneda = "EUR";
					break;
				}
				case 2: {
					//Libras Esterlinas
					moneda = "GBP";
					break;
				}
				case 3: {
					//Yen Japones
					moneda = "JPY";
					break;
				}
				case 4: {
					//Won sul-Coreano
					moneda = "KRW";
					break;
				}	
					default:
						throw new IllegalArgumentException("Unexpected value: " + codigo);
			}
		return moneda;	
	}
}
