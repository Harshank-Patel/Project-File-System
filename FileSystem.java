import java.util.*;

public class FileSystem {
    private static class Directory {
        String name;
        Map<String, Directory> subDirectories;

        Directory(String name) {
            this.name = name;
            this.subDirectories = new TreeMap<>();
        }
    }

    private Directory root;

    public FileSystem() {
        root = new Directory(""); // Root directory
    }

    public void create(String path) {
        String[] parts = path.split("/");
        Directory current = root;
        for (String part : parts) {
            current.subDirectories.putIfAbsent(part, new Directory(part));
            current = current.subDirectories.get(part);
        }
    }

    public void list() {
        System.out.println();
        listHelper(root, 0);
    }

    private void listHelper(Directory dir, int level) {
        if (!dir.name.isEmpty()) {
            System.out.println("  ".repeat(level) + dir.name);
        }
        for (Directory subDir : dir.subDirectories.values()) {
            listHelper(subDir, level + 1);
        }
    }

    public void move(String sourcePath, String destinationPath) {
        Directory parent = findParent(sourcePath);
        if (parent == null || !parent.subDirectories.containsKey(getLastPart(sourcePath))) {
            System.out.println("\n\n **** Cannot move " + sourcePath + " - " + sourcePath + " does not exist");
            return;
        }

        Directory destination = findDirectory(destinationPath);
        if (destination == null) {
            System.out.println("\n\n **** Cannot move " + sourcePath + " - destination " + destinationPath + " does not exist");
            return;
        }

        Directory toMove = parent.subDirectories.remove(getLastPart(sourcePath));
        destination.subDirectories.put(toMove.name, toMove);
    }

    public void delete(String path) {
        Directory parent = findParent(path);
        if (parent == null || !parent.subDirectories.containsKey(getLastPart(path))) {
            System.out.println("\n\n **** Cannot delete " + path + " - " + getRootPart(path) + " does not exist");
            return;
        }
        parent.subDirectories.remove(getLastPart(path));
    }

    private Directory findParent(String path) {
        String[] parts = path.split("/");
        Directory current = root;
        for (int i = 0; i < parts.length - 1; i++) {
            current = current.subDirectories.get(parts[i]);
            if (current == null) return null;
        }
        return current;
    }

    private Directory findDirectory(String path) {
        String[] parts = path.split("/");
        Directory current = root;
        for (String part : parts) {
            current = current.subDirectories.get(part);
            if (current == null) return null;
        }
        return current;
    }

    private String getLastPart(String path) {
        return path.substring(path.lastIndexOf('/') + 1);
    }

    private String getRootPart(String path) {
        return path.contains("/") ? path.substring(0, path.indexOf('/')) : path;
    }

    private static void showCommands() {
        
        System.out.println("\n\n\n ** Available Commands: **");
        System.out.println(" * CREATE <directory_path>  -> Create a new directory");
        System.out.println(" * MOVE <source> <destination>  -> Move a directory");
        System.out.println(" * DELETE <directory_path>  -> Delete a directory");
        System.out.println(" * LIST  -> Show the directory structure");
        System.out.println(" * EXIT or QUIT  -> Quit the program");
        System.out.println("\n\n ** WARN: Directoy names are ALWAYS case sensetive **");
        System.out.print("\nEnter a command: ");
    }

    public static void main(String[] args) {
        FileSystem fs = new FileSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            showCommands(); // Show available commands

            String command = scanner.nextLine().trim();
            if (command.isEmpty()) continue;

            String[] parts = command.split(" ", 2);
            String operation = parts[0];
            operation = operation.toLowerCase().trim();

            switch (operation) {
                case "create":
                    fs.create(parts[1]);
                    fs.list(); 
                    break;
                case "move":
                    String[] moveArgs = parts[1].split(" ");
                    fs.move(moveArgs[0], moveArgs[1]);
                    fs.list(); 
                    break;
                case "delete":
                    fs.delete(parts[1]);
                    fs.list(); 
                    break;
                case "list":
                    fs.list();
                    break;
                case "exit":
                case "quit":
                case "q":
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid command: " + command);
            }
        }
    }
}