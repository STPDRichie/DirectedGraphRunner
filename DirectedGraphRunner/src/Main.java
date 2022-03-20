import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            File input = new File("input.txt");
            Scanner scanner = new Scanner(input);

            var nodeCount = Integer.parseInt(scanner.nextLine());

            var precedings = new String[nodeCount];
            for (var i = 0; i < nodeCount; i++)
                precedings[i] = scanner.nextLine();

            var graph = new DirectedGraph(nodeCount, precedings);

            var start = Integer.parseInt(scanner.nextLine());
            var end = Integer.parseInt(scanner.nextLine());
            var result = graph.run(start, end);

            FileWriter writer = new FileWriter("output.txt");
            writer.write(String.join("\n", result));
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found...");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Writing failed...");
            e.printStackTrace();
        }
    }
}
