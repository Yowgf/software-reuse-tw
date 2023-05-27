package ElectoralSystem;

class Main {
  public static void main(String[] args) {
      if (args.length < 2) {
          System.err.println("Program usage: <program> <election-type>");
          System.exit(1);
      }
      var electionType = args[1];
    ElectoralSystem s = new ElectoralSystem(electionType);
    s.startMenu();
  }
}
