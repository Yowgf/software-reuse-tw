package ElectoralSystem;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public abstract class Urna {
  protected final BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
  protected Election currentElection;
  protected String electionPassword = "password";

  protected void print(String output) {
    System.out.println(output);
  }

  protected String readString() {
    try {
      return scanner.readLine();
    } catch (Exception e) {
      print("\nErro na leitura de entrada, digite novamente");
      return readString();
    }
  }

  protected int readInt() {
    try {
      return Integer.parseInt(readString());
    } catch (Exception e) {
      print("\nErro na leitura de entrada, digite novamente");
      return readInt();
    }
  }
}
