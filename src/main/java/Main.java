package ElectoralSystem;

class Main {
  public static void main(String[] args) {
    if (args.length < 1) {
      System.err.println("Program usage: <program> <election-type>");
      System.exit(1);
    }
    var electionType = args[0];
    ElectoralSystem s = new ElectoralSystem(electionType);
    s.startMenu();
  }
}
