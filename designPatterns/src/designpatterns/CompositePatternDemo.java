/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package designpatterns;

import java.util.ArrayList;
import java.util.List;

// Component
interface FileComponent {
    void showDetails();
}

// Leaf
class File implements FileComponent {
    private String name;

    public File(String name) {
        this.name = name;
    }

    @Override
    public void showDetails() {
        System.out.println("File: " + name);
    }
}

// Composite
class Directory implements FileComponent {
    private String name;
    private List<FileComponent> components = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    public void addComponent(FileComponent component) {
        components.add(component);
    }

    public void removeComponent(FileComponent component) {
        components.remove(component);
    }

    @Override
    public void showDetails() {
        System.out.println("Directory: " + name);
        for (FileComponent component : components) {
            component.showDetails();
        }
    }
}

// Client
public class CompositePatternDemo {
    public static void main(String[] args) {
        FileComponent file1 = new File("file1.txt");
        FileComponent file2 = new File("file2.txt");
        FileComponent file3 = new File("file3.txt");

        Directory directory1 = new Directory("dir1");
        Directory directory2 = new Directory("dir2");

        directory1.addComponent(file1);
        directory1.addComponent(file2);

        directory2.addComponent(file3);
        directory2.addComponent(directory1);

        directory2.showDetails();
    }
}

